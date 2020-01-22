package no.nav.presentation.pensjon.pselv.fullmakter;

import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import no.nav.presentation.pensjon.common.security.validation.enums.Roles;

import static java.util.Arrays.stream;

/**
 * Service for updating roles related to the fullmakt and bytt-bruker functionality in PSELV.
 * The role information is stored in session attributes that are retrieved by WSPselvCustomSecurityContextFilter,
 * which populates SecurityContext.
 * The role information is maintained in two lists on the session:
 * 1. Assigned fullmakter - represents whether the user has been assigned any fullmakter.
 * 2. Working on behalf of others - represents whether the user is working on behalf of anyone else.
 * The service also maintains the authorizedAs attribute which represents who a user is working on behalf of.
 */
public class FullmakterSessionMaintenanceService {

    static final String ASSIGNED_FULLMAKTER_ATTR = "no.nav.presentation.pensjon.pselv.fullmakter.attribute.assignedFullmakter";
    static final String BEHALF_OF_OTHER_ATTR = "no.nav.presentation.pensjon.pselv.fullmakter.attribute.behalfOfOther";
    static final String AUTHORIZED_AS_ATTR = "no.nav.presentation.pensjon.pselv.fullmakter.attribute.authorizedAs";

    public void setAssignedFullmaktRoles(HttpSession session, Roles... roles) {
        session.setAttribute(ASSIGNED_FULLMAKTER_ATTR, extractRoleNames(roles));
    }

    public void setUserWorkingOnBehalfOfOthers(HttpSession session, String behalfOfPid, Roles... roles) {
        session.setAttribute(BEHALF_OF_OTHER_ATTR, extractRoleNames(roles));
        session.setAttribute(AUTHORIZED_AS_ATTR, behalfOfPid);
    }

    public void setUserWorkingOnBehalfOfSelf(HttpSession session, String userPid) {
        session.setAttribute(BEHALF_OF_OTHER_ATTR, extractRoleNames(Roles.PERSONLIG));
        session.setAttribute(AUTHORIZED_AS_ATTR, userPid);
    }

    private List<String> extractRoleNames(Roles... roles) {
        return stream(roles)
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}
