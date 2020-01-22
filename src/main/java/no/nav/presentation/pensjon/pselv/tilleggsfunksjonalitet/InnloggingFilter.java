package no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import no.stelvio.common.security.SecurityContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import no.stelvio.domain.person.Pid;
import no.stelvio.domain.person.PidValidationException;
import no.stelvio.presentation.security.logout.LogoutService;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonResponse;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.SpesialRegisteringCode;
import no.nav.presentation.pensjon.pselv.common.PselvConstants;

import static no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper.COMMON_BRUKER_PID;
import static no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper.COMMON_LOGGED_ON_NAME;
import static no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper.*;
import static no.nav.presentation.pensjon.pselv.common.utils.UserGroupUtil.findUserGroup;
import static no.stelvio.common.security.SecurityContextHolder.currentSecurityContext;
import static org.apache.commons.lang.WordUtils.capitalizeFully;

/**
 * Filter used with login with MinID.
 * The filter checks if a user is allowed access to the application, and sets necessary session attributes.
 */
public class InnloggingFilter extends OncePerRequestFilter {

    private static final Log LOG = LogFactory.getLog(InnloggingFilter.class);
    private static final String DEG_SELV = "pselv.standardtekst.statisk_tekst.degselv";
    private PersonServiceBi personService;
    private LogoutService logoutService;
    private MessageSource messageSource;

    /**
     * Sets required session attributes when the user logs in through MinID.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        boolean loggedOut = currentSecurityContext().getUserId() != null && isSessionMapEmptyOrInvalid(session)
                ? checkAccessAndPopulateSession(request, response)
                : populateSessionAndLogOut(request, response);

        if (loggedOut) {
            return;
        }

        chain.doFilter(request, response);
    }

    private boolean checkAccessAndPopulateSession(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SecurityContext securityContext = currentSecurityContext();

        if (LOG.isDebugEnabled()) {
            LOG.debug("Current user: " + securityContext.getUserId());
        }

        String userId = securityContext.getUserId();
        Person innloggetBruker = getInnloggetBruker(userId);

        if (innloggetBruker == null) {
            return false;
        }

        populateHttpSession(request, innloggetBruker);

        if (accessToApplicationAllowed(innloggetBruker)) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Access is allowed. Populating the HttpSession.");
            }

            return false;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Access is not allowed. Logging the user out and exiting the filter chain.");
        }

        logoutService.logout(request, response);
        return true;
    }

    private boolean populateSessionAndLogOut(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        if (request.isRequestedSessionIdValid()) {
            return false;
        }

        String userId = currentSecurityContext().getUserId();
        Person innloggetBruker = getInnloggetBrukerIgnoringInvalidPid(userId);

        if (innloggetBruker == null) {
            return false;
        }

        populateHttpSession(request, innloggetBruker);
        logOutIgnoringExceptions(request, response);
        return true;
    }

    private Person getInnloggetBruker(String pid) throws ServletException {
        try {
            return hentPerson(new Pid(pid));
        } catch (PidValidationException | PersonIkkeFunnetException e) {
            throw new ServletException(e);
        }
    }

    private Person getInnloggetBrukerIgnoringInvalidPid(String pid) {
        try {
            return hentPerson(new Pid(pid));
        } catch (PidValidationException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(getMessageForUgyldigPid(pid, "is not valid"));
            }
        } catch (PersonIkkeFunnetException e) {
            if (LOG.isWarnEnabled()) {
                LOG.warn(getMessageForUgyldigPid(pid, "was not found in DB"));
            }
        }

        return null;
    }

    private void populateHttpSession(HttpServletRequest request, Person innloggetBruker) throws ServletException {
        String authorizedAs = currentSecurityContext().getAutorizedAs();

        if (LOG.isDebugEnabled()) {
            LOG.debug(new StringBuilder("Current user's name: ").append(getConcatenatedName(innloggetBruker)));
        }

        Pid authorizedAsPid = new Pid(authorizedAs);
        HttpSession session = request.getSession(true);
        Map<String, Object> sessionMap = getSessionMap(session);
        setOnBehalfOfInSession(innloggetBruker, authorizedAsPid, sessionMap);
        sessionMap.put(COMMON_BRUKER_PID, authorizedAsPid);
        sessionMap.put(COMMON_LOGGED_ON_NAME, getConcatenatedName(innloggetBruker));
        sessionMap.put(USER_GROUP, findUserGroup(authorizedAsPid));
        session.setAttribute(PENSJON_COMMON_SESSIONMAP, sessionMap);
    }

    private void setOnBehalfOfInSession(Person bruker, Pid authorizedAs, Map<String, Object> sessionMap) throws ServletException {
        if (bruker.getPid().getPid().equalsIgnoreCase(authorizedAs.getPid())) {
            sessionMap.put(ON_BEHALF_OF_NAME, messageSource.getMessage(DEG_SELV, null, LocaleContextHolder.getLocale()));
            sessionMap.put(ON_BEHALF_OF_FIRSTNAME, null);
            return;
        }

        try {
            Person authorizedPerson = hentPerson(authorizedAs);
            sessionMap.put(ON_BEHALF_OF_NAME, getConcatenatedName(authorizedPerson));
            sessionMap.put(ON_BEHALF_OF_FIRSTNAME, capitalizeFully(authorizedPerson.getFornavn()));
        } catch (PidValidationException | PersonIkkeFunnetException e) {
            throw new ServletException(e);
        }
    }

    private void logOutIgnoringExceptions(HttpServletRequest request, HttpServletResponse response) {
        try {
            logoutService.logoutToUrl("/", request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Person hentPerson(Pid pid) throws PersonIkkeFunnetException {
        HentPersonRequest request = new HentPersonRequest(pid);
        HentPersonResponse response = personService.hentPerson(request);
        return response.getPerson();
    }

    public void setPersonService(PersonServiceBi service) {
        personService = service;
    }

    public void setLogoutService(LogoutService service) {
        logoutService = service;
    }

    public void setMessageSource(MessageSource source) {
        messageSource = source;
    }

    private String getMessageForUgyldigPid(String pid, String reason) {
        return String.format("PID '%s' %s. This is gracefully ignored in %s", pid, reason, getClass().getName());
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> getSessionMap(HttpSession session) {
        Map<String, Object> map = (Map<String, Object>) session.getAttribute(PENSJON_COMMON_SESSIONMAP);

        if (map != null) {
            return map;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("session map was null.");
        }

        return new HashMap<>();
    }

    @SuppressWarnings("unchecked")
    private static boolean isSessionMapEmptyOrInvalid(HttpSession session) {
        Map<String, Object> map = (Map<String, Object>) session.getAttribute(PENSJON_COMMON_SESSIONMAP);

        if (map == null || !map.containsKey(COMMON_BRUKER_PID)) {
            return true;
        }

        String authorizedAs = currentSecurityContext().getAutorizedAs();
        Pid brukerPid = (Pid) map.get(COMMON_BRUKER_PID);
        return !brukerPid.getPid().equalsIgnoreCase(authorizedAs);
    }

    private static String getConcatenatedName(Person person) {
        return person.getFornavn() + " " + person.getEtternavn();
    }

    private static boolean accessToApplicationAllowed(Person person) {
        boolean diskresjon = diskresjonPakrevd(person);
        boolean umyndiggjort = person.getUmyndiggjort();
        int currentAge = Pid.calculateAge(person.getPid(), new Date());
        boolean umyndig = currentAge < PselvConstants.MINIMUM_ALLOWED_AGE;

        if (LOG.isDebugEnabled()) {
            LOG.debug("Checking if the user should be allowed to access the application.");
            LOG.debug("The criteria checked are: [diskresjon, umyndiggjort, umyndig]");
            LOG.debug("The results are: [" + diskresjon + ", " + umyndiggjort + ", " + umyndig + "]");
        }

        return !diskresjon && !umyndiggjort && !umyndig;
    }

    private static boolean diskresjonPakrevd(Person person) {
        String diskresjonskode = person.getDiskresjonskode();

        return diskresjonskode != null
                && (diskresjonskode.equalsIgnoreCase(SpesialRegisteringCode.SPFO.name())
                || diskresjonskode.equalsIgnoreCase(SpesialRegisteringCode.SPSF.name()));
    }
}
