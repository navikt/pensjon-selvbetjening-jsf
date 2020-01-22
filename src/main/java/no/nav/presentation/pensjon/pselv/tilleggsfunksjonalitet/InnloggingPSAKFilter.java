package no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.person.PidValidationException;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonResponse;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.presentation.pensjon.common.security.validation.enums.Roles;
import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.utils.UserGroupUtil;

import static no.nav.presentation.pensjon.pselv.common.utils.UserGroupUtil.findUserGroup;
import static no.stelvio.common.security.SecurityContextHolder.currentSecurityContext;

/**
 * Filter used for setting state when going from PSAK to PSELV.
 */
public class InnloggingPSAKFilter extends OncePerRequestFilter {

    private static final String BRUKER_ID = "_brukerId";
    private static final String LOGGED_ON_NAME = "_loggedOnName";
    private static final Log LOG = LogFactory.getLog(InnloggingPSAKFilter.class);
    private PersonServiceBi personService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String brukerIdEncrypted = request.getParameter(BRUKER_ID);
        String loggedOnName = request.getParameter(LOGGED_ON_NAME);

        if (brukerIdEncrypted != null && loggedOnName != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("doFilterInternal()");
                LOG.debug(getUserInfo(brukerIdEncrypted, loggedOnName));
                LOG.debug(getRoleInfo(currentSecurityContext().getRoles()));
            }

            String brukerId = brukerIdEncrypted; // not encrypted now
            Pid pid = null;
            Person onBehalfOfPerson = null;
            Map<String, Object> holder = new HashMap<>();

            if (Pid.isValidPid(brukerId)) {
                pid = new Pid(brukerId);

                try {
                    // Sjekk lagt inn som del av SIR 35296 - bma2812
                    if (currentSecurityContext() != null && currentSecurityContext().getUserId() != null) {
                        onBehalfOfPerson = hentPerson(pid);
                    }

                    if (LOG.isDebugEnabled()) {
                        LOG.debug("valid pid");
                    }
                } catch (PidValidationException | PersonIkkeFunnetException e) {
                    throw new ServletException(e);
                }
            }

            if (isAllowToAccess(onBehalfOfPerson)) {
                holder.put(PselvSessionMapWrapper.ON_BEHALF_OF_NAME, onBehalfOfPerson.getFornavn() + " " + onBehalfOfPerson.getEtternavn());
                holder.put(AbstractSessionMapWrapper.COMMON_BRUKER_PID, pid);
                holder.put(AbstractSessionMapWrapper.COMMON_LOGGED_ON_NAME, loggedOnName);
                holder.put(PselvSessionMapWrapper.FROM_LOGING_PAGE, "false");
                // Changed logic for finding user group. SIR 128662
                holder.put(PselvSessionMapWrapper.USER_GROUP, findUserGroup(pid));

                request.getSession(true).setAttribute(AbstractSessionMapWrapper.PENSJON_COMMON_SESSIONMAP, holder);

                if (LOG.isDebugEnabled()) {
                    LOG.debug("Stored login details from PSAK on session.");
                    LOG.debug(getLoginDetails(holder));
                }
            } else {
                if (LOG.isDebugEnabled()) {
                    LOG.debug("Access refused to PSELV");
                }

                response.sendRedirect("tilleggsfunksjonalitet/tilgangnektet.jsf");
                return;
            }
        }

        chain.doFilter(request, response);
    }

    private Person hentPerson(Pid pid) throws PersonIkkeFunnetException {
        Person person = new Person();
        person.setPid(pid);
        HentPersonRequest request = new HentPersonRequest(person, true);
        HentPersonResponse response = personService.hentPerson(request);
        return response.getPerson();
    }

    public void setPersonService(PersonServiceBi service) {
        personService = service;
    }

    private boolean isDiskresjon(Person person) {
        return !isEmpty(person.getDiskresjonskode());
    }

    private boolean isEmpty(String value) {
        return value == null || value.equalsIgnoreCase("");
    }

    private boolean isAllowToAccess(Person person) {
        if (person == null) {
            return false;
        }

        if (isDiskresjon(person)) {
            String diskresjonskode = person.getDiskresjonskode();
            //TODO use consts
            if (diskresjonskode.equals("SPSF") && !isUserInRoles(Roles.KODE_6)) {
                return false;
            }

            if (diskresjonskode.equals("SPFO") && !isUserInRoles(Roles.KODE_7)) {
                return false;
            }
        }

        return !BooleanUtils.isTrue(person.getErEgenansatt()) || isUserInRoles(Roles.UTVIDET);
    }

    private static boolean isUserInRoles(Roles roles) {
        return currentSecurityContext().isUserInRoles(roles.toString());
    }

    private static String getLoginDetails(Map<String, Object> holder) {
        StringBuilder builder = new StringBuilder();
        builder.append(PselvSessionMapWrapper.ON_BEHALF_OF_NAME);
        builder.append(": ");
        builder.append(holder.get(PselvSessionMapWrapper.ON_BEHALF_OF_NAME));
        builder.append(" ");
        builder.append(AbstractSessionMapWrapper.COMMON_BRUKER_PID);
        builder.append(": ");
        builder.append(holder.get(AbstractSessionMapWrapper.COMMON_BRUKER_PID));
        builder.append(" ");
        builder.append(AbstractSessionMapWrapper.COMMON_LOGGED_ON_NAME);
        builder.append(": ");
        builder.append(holder.get(AbstractSessionMapWrapper.COMMON_LOGGED_ON_NAME));
        return builder.toString();
    }

    private static String getRoleInfo(List<String> roles) {
        StringBuilder builder = new StringBuilder();
        builder.append("Roles: ");

        for (String role : roles) {
            builder.append(role);
            builder.append("; ");
        }

        return builder.toString();
    }

    private static String getUserInfo(String brukerIdEncrypted, String loggedOnName) {
        StringBuilder builder = new StringBuilder();
        builder.append("User Pid: ");
        builder.append(brukerIdEncrypted);
        builder.append(" Internal user: ");
        builder.append(loggedOnName);
        builder.append(" UserID:");
        builder.append(currentSecurityContext().getUserId());
        return builder.toString();
    }
}
