package no.nav.presentation.pensjon.pselv.common;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.presentation.pensjon.pselv.common.enums.UserGroup;
import no.nav.presentation.pensjon.pselv.common.utils.NameUtil;
import no.nav.presentation.pensjon.pselv.common.utils.NumberFormatUtil;
import no.stelvio.domain.person.Pid;

import java.io.Serializable;
import java.util.Date;

import javax.faces.event.AjaxBehaviorEvent;

public abstract class CommonForm implements Serializable {

    private static final long serialVersionUID = -9154149292018682434L;
    private Person bruker;
    private UserGroup userGroup;
    private Double grunnbelop;
    private boolean internBruker;

    /**
     * If true, the user has access to view fnr & name for a relation (EPS / Barn)
     */
    private boolean visSensitivInformasjonForRelasjon;

    /**
     * If true, error message will be shown at the top of the page. If false, the error message will not be shown. Set to true
     * by default in CommonAction.
     */
    private boolean showGlobalMessages;

    private boolean showHelpModal;

    /**
     * Return Grunnbelop as a String, formatted correctly 123 456.
     */
    public String getG1() {
        return NumberFormatUtil.formatAsIntegerAmount(grunnbelop);
    }

    /**
     * Return 2xGrunnbelop as a String, formatted correctly 123 456.
     */
    public String getG2() {
        return NumberFormatUtil.formatAsIntegerAmount(grunnbelop * 2);
    }

    /**
     * Return 6xGrunnbelop as a String, formatted correctly 123 456.
     */
    public String getG6() {
        return NumberFormatUtil.formatAsIntegerAmount(grunnbelop * 6);
    }

    /**
     * Return 12xGrunnbelop as a String, formatted correctly 123 456.
     */
    public String getG12() {
        return NumberFormatUtil.formatAsIntegerAmount(grunnbelop * 12);
    }

    /**
     * If true and you are using ramme-skjermbildemal the menu will NOT be shown. Must be overridden to show the menu. Return
     * FALSE will SHOW the menu.
     */
    public boolean isHideLeftMenu() {
        return false;
    }

    /**
     * True if Brukerhjelpa is logged on and is working on a user.
     */
    public boolean isErBrukerhjelpa() {
        return false;
    }

    /**
     * True if it is the last page before SIS005
     */
    public boolean isLastPage() {
        return false;
    }

    /* Abstract methods, this methods must be set if the 'MAL' is being used. */

    /**
     * If true there will be a right column, i.e. the pagecontent will only be in the middle of the page.
     */
    public abstract boolean isEnableRightColumn();

    /**
     * If a helpKey is set, there will be a help icon i the upper right corner of the page content.
     */
    public abstract String getHelpKey();

    /**
     * The page title. Used in psak.xhtml to show the page title, and the heading for the back menu.
     */
    public abstract String getPageTitle();

    /**
     * If a page icon is set, there will be an icon next to the page's title
     */
    public abstract String getPageIcon();

    /* AUTO-GENERATED */

    public Double getGrunnbelop() {
        return grunnbelop;
    }

    public Person getBruker() {
        return bruker;
    }

    public int getBrukerDagensAlder() {
        return Pid.calculateAge(bruker.getPid(), new Date());
    }

    /**
     * Returns the full name of the user in the format Firstname Middlename Surname.
     */
    public String getFormatedUserName() {
        String name = "";

        if (bruker != null) {
            NameUtil nameUtil = new NameUtil();
            name = nameUtil.formatName(bruker.getFornavn(), bruker.getMellomnavn(), bruker.getEtternavn());
        }

        return name;
    }

    public void setBruker(Person bruker) {
        this.bruker = bruker;
    }

    public void setGrunnbelop(Double grunnbelop) {
        if (grunnbelop != null) {
            this.grunnbelop = grunnbelop;
        }
    }

    public UserGroup getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(UserGroup userGroup) {
        this.userGroup = userGroup;
    }

    public boolean isUserGroup1() {
        return UserGroup.USER_GROUP_1.equals(getUserGroup());
    }

    public boolean isUserGroup2() {
        return UserGroup.USER_GROUP_2.equals(getUserGroup());
    }

    public boolean isUserGroup3() {
        return UserGroup.USER_GROUP_3.equals(getUserGroup());
    }

    public boolean isUserGroup4() {
        return UserGroup.USER_GROUP_4.equals(getUserGroup());
    }

    public boolean isUserGroup5() {
        return UserGroup.USER_GROUP_5.equals(getUserGroup());
    }

    public boolean isShowGlobalMessages() {
        return showGlobalMessages;
    }

    public void setShowGlobalMessages(boolean showGlobalMessages) {
        this.showGlobalMessages = showGlobalMessages;
    }

    public boolean isEksternBruker() {
        return !internBruker;
    }

    public boolean isInternBruker() {
        return internBruker;
    }

    public void setInternBruker(boolean internBruker) {
        this.internBruker = internBruker;
    }

    public boolean isVisSensitivInformasjonForRelasjon() {
        return visSensitivInformasjonForRelasjon;
    }

    public void setVisSensitivInformasjonForRelasjon(boolean visSensitivInformasjonForRelasjon) {
        this.visSensitivInformasjonForRelasjon = visSensitivInformasjonForRelasjon;
    }

    public void openHelpModal(AjaxBehaviorEvent event) {
        showHelpModal = true;
    }

    public void closeHelpModal() {
        showHelpModal = false;
    }

    public boolean isShowHelpModal() {
        return showHelpModal;
    }
}
