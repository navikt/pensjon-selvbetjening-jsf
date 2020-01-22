package no.nav.presentation.pensjon.pselv.fullmakter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import no.stelvio.common.security.SecurityContext;
import no.stelvio.common.security.support.SimpleSecurityContext;
import no.stelvio.presentation.security.context.WSCustomSecurityContextFilter;
import no.nav.presentation.pensjon.common.security.validation.enums.Roles;
import no.nav.presentation.pensjon.pselv.uforevedtak.UforeVedtakSessionMaintenanceService;

/**
 * A servlet filter which inherits the WSCustomSecurityContextFilter in Stelvio Presentation
 * and overrides the populateSecurityContext() method.
 * The purpose of the PSELV specific filter is to add the different fullmakter roles and the
 * authorizedAs attribute to the SecurityContext. This was before PKPUMA-131 maintained by
 * TAI/WEBSEAL and EAI, but after the transition to OpenAM, the information is contained in
 * HttpSession attributes. The filter therefore has to read the data from the attributes and
 * populate it to the SecurityContext.
 * The information in the SecurityContext is logged by the SecurityContextDebugFilter, which
 * runs after this filter.
 */
public class WSPselvCustomSecurityContextFilter extends WSCustomSecurityContextFilter {

    @Override
    public SecurityContext populateSecurityContext(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String user = getUserId();
        String authorizedAs = getAuthorizedAs(session);
        List<String> roles = getRoles(request, session);
        SecurityContext securityContext = newSecurityContext(user, authorizedAs, roles);

        if (isLoggedIn(user)) {
            setStartPageAfterLogin(session);
        }

        return securityContext;
    }

    /**
     * Returns all the user roles in the JAAS Subject. The PERSONLIG role is set by WebSeal when the user is
     * not working on behalf of anyone else. OpenAM will not set the PERSONLIG role on the JAAS Subject, so we
     * therefore have to maintain the role ourselves in a session attribute.
     * The implementation of PKPUMA-131 should support both WebSeal and OpenAM. We therefore remove the
     * PERSONLIG role from the role list if it exists, and maintains this role in a session attribute.
     */
    @Override
    protected List<String> getUserRoles(HttpServletRequest request) {
        List<String> roles = super.getUserRoles(request);
        removePersonligRoleAddedByWebSeal(roles);
        return roles;
    }

    private List<String> getRoles(HttpServletRequest request, HttpSession session) {
        List<String> roles = getUserRoles(request);
        roles.addAll(getFullmakterRoles(session));
        roles.addAll(getUforeVedtakRoles(session));
        return roles;
    }

    private static boolean isLoggedIn(String user) {
        return user != null;
    }

    // If a role enumeration has been specified in the init params, a role-validator
    // will be created and added to the security context.
    private SecurityContext newSecurityContext(String user, String authorizedAs, List<String> roles) {
        return validator == null
                ? new SimpleSecurityContext(user, authorizedAs, roles)
                : new SimpleSecurityContext(user, authorizedAs, roles, validator);
    }

    private String getAuthorizedAs(HttpSession session) {
        Object authorizedAs = session.getAttribute(FullmakterSessionMaintenanceService.AUTHORIZED_AS_ATTR);
        return authorizedAs == null ? getUserId() : (String) authorizedAs;
    }

    private static List<String> getFullmakterRoles(HttpSession session) {
        List<String> roles = new ArrayList<>();
        addAssignedFullmakterRoles(session, roles);
        addWorkingOnBehalfOfOthersRoles(session, roles);
        return roles;
    }

    private static List<String> getUforeVedtakRoles(HttpSession session) {
        List<String> roles = new ArrayList<>();
        addAssignedUforeVedtakRoles(session, roles);
        return roles;
    }

    private static void addAssignedUforeVedtakRoles(HttpSession session, List<String> roles) {
        addRoles(UforeVedtakSessionMaintenanceService.HAS_UFORE_VEDTAK, session, roles);
    }

    private static void addAssignedFullmakterRoles(HttpSession session, List<String> roles) {
        addRoles(FullmakterSessionMaintenanceService.ASSIGNED_FULLMAKTER_ATTR, session, roles);
    }

    private static void addWorkingOnBehalfOfOthersRoles(HttpSession session, List<String> roles) {
        addRoles(FullmakterSessionMaintenanceService.BEHALF_OF_OTHER_ATTR, session, roles);
    }

    private static void removePersonligRoleAddedByWebSeal(List<String> roles) {
        roles.remove(Roles.PERSONLIG.name());
    }

    @SuppressWarnings("unchecked")
    private static void addRoles(String attributeName, HttpSession session, List<String> roles) {
        Object attributeValue = session.getAttribute(attributeName);

        if (attributeValue == null) {
            return;
        }

        roles.addAll((List<String>) attributeValue);
    }

    //TODO: How to get user ID
    protected String getUserId() {
//        return no.stelvio.common.security.ws.WSCustomSubject.getUserId();
        return "WSCustomSubject_UserId";
    }
}
