package no.nav.presentation.pensjon.pselv.common.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import no.stelvio.common.security.SecurityContext;
import no.stelvio.common.security.SecurityContextHolder;
import no.stelvio.domain.person.Pid;

import no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;

/**
 * Represents an item in the leftmenu of PSELV. The menu will be implemented as a linked list of elements.
 */
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 6820615423895668964L;

    /**
     * The resource key for the label of this menu item. Key is located in breadcrumb.properties.
     */
    private String label;

    /**
     * A list of the roles a logged in user must have to use this menu item
     */
    private List<String> roles = new ArrayList<>();

    private MenuItem parent = null; // will be null at root
    private List<MenuItem> children;
    private String action; // flow ID

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<MenuItem> getChildren() {
        return children;
    }

    public void setChildren(List<MenuItem> children) {
        this.children = children;
    }

    public MenuItem getParent() {
        return parent;
    }

    public void setParent(MenuItem parent) {
        this.parent = parent;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    /**
     * Checks if the menu item should be rendered.
     * That is that the user has one of the roles listed for this element in the menu.xml file.
     */
    public boolean getRenderForUser() {
        SecurityContext context = SecurityContextHolder.currentSecurityContext();

        if (context != null) return true;//TODO remove

        for (String role : roles) {
            if (context.isUserInAllRoles(role.split(","))) {
                if (!label.equals("skjema/endringalderspensjon")) {
                    return true;
                }

                // If menu item is SIS021 Din tjenestepensjon, check user's birth year.
                // The option should only be available if the user is born in 1943 or later
                int fodselsar = Pid.get4DigitYearOfBirth(PselvSessionMapWrapper.getBrukerPid().getPid());
                return fodselsar >= PensjonsKalkulatorConstants.YEAR_1943;
            }
        }

        return false;
    }

    /**
     * Finds an element with the specified action. Searches this element and its children.
     */
    public MenuItem findItem(String label) {
        if (this.label.equalsIgnoreCase(label)) {
            return this;
        }

        if (children == null) {
            return null;
        }

        for (MenuItem current : children) {
            MenuItem found = current.findItem(label);

            if (found != null) {
                return found;
            }
        }

        return null;
    }

    /**
     * Checks to see if the current action is equal to the active menuItem.
     */
    public boolean isActive() {
        MenuItem activeItem = (MenuItem) PselvSessionMapWrapper.get(PselvSessionMapWrapper.ACTIVE);
        return activeItem != null && label.equals(activeItem.getLabel());
    }

    /**
     * Checks if the menu item or any of its children, is selected. If yes, the submenu (children) should be visible
     */
    public boolean isOpen() {
        if (isActive()) {
            return true;
        }

        if (children == null) {
            return false;
        }

        for (MenuItem child : children) {
            if (child.isActive()) {
                return true;
            }
        }

        return false;
    }
}
