package no.nav.presentation.pensjon.pselv.publisering.loginserviceredirect;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

//import org.jose4j.jwt.NumericDate;
//import org.jose4j.jwt.consumer.InvalidJwtException;
//import org.jose4j.jwt.consumer.JwtConsumer;
//import org.jose4j.jwt.consumer.JwtConsumerBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.util.WebUtils;
import org.springframework.webflow.action.MultiAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

public class LoginserviceRedirectAction extends MultiAction {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginserviceRedirectAction.class);
    private static final int DEFAULT_ALLOWED_SKEW = 20;

    private static final String LOGINSERVICE_URL = "loginservice.endpoint.url";
    private static final String PSELV_ROOT_URL = "cfg.pselv.security.pointofcontacthost.url";
    private static final String FLOWSCOPE_LOGINSERVICE_URL_KEY = "loginserviceURL";

    private static final String ID_TOKEN_COOKIE_NAME = "selvbetjening-idtoken";

    private MessageSource messageSource;
    private boolean selvbetjeningssone;
    private Integer allowedSkew;

    public Event shouldRedirectToLoginservice(RequestContext ctx) {
        boolean xxx = !isOIDCTokenInCookieAndNotExpired(ctx) && selvbetjeningssone && isUserLoggedInWithDIFI();
        Event event = xxx ? yes() : no();
        return event;
//        return (!isOIDCTokenInCookieAndNotExpired(ctx) && selvbetjeningssone && isUserLoggedInWithDIFI()) ? yes() : no();
    }

    public Event putLoginserviceUrlOnFlowScope(RequestContext ctx) {
        ctx.getFlowScope().put(FLOWSCOPE_LOGINSERVICE_URL_KEY, getLoginserviceUrl(ctx));
        return success();
    }

    private boolean isOIDCTokenInCookieAndNotExpired(RequestContext ctx) {
        Cookie idTokenCookie = WebUtils.getCookie((HttpServletRequest) ctx.getExternalContext().getNativeRequest(), ID_TOKEN_COOKIE_NAME);
        return idTokenCookie != null && isValidDateOnOIDCToken(idTokenCookie.getValue());
    }

    private boolean isValidDateOnOIDCToken(String token) {
//        JwtConsumer jwtConsumer = new JwtConsumerBuilder()
//                .setSkipSignatureVerification()
//                .setSkipDefaultAudienceValidation()
//                .setRequireExpirationTime()
//                .setEvaluationTime(NumericDate.now())
//                .setAllowedClockSkewInSeconds(allowedSkew == null ? DEFAULT_ALLOWED_SKEW : allowedSkew)
//                .build();
//        try {
//            jwtConsumer.process(token);
//        } catch (InvalidJwtException e) {
//            LOGGER.error("LoginserviceRedirectAction: Token is expired or not valid json, redirecting to loginservice. ErrorDetails: {}", e.getErrorDetails());
//            return false;
//        }
        return true;
    }

    private boolean isUserLoggedInWithDIFI() {
        return !PSELVSecurityFunctions.isInternBruker() && (PSELVSecurityFunctions.isUserInHoy() || PSELVSecurityFunctions.isUserInMedium());
    }

    private String getLoginserviceUrl(RequestContext ctx) {
        return getMessage(LOGINSERVICE_URL) + "&redirect=" + getCurrentUrl(ctx);
    }

    private String getCurrentUrl(RequestContext ctx) {
        return getMessage(PSELV_ROOT_URL) + ((HttpServletRequest) ctx.getExternalContext().getNativeRequest()).getRequestURI();
    }

    private String getMessage(String key) {
        return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
    }

    @Required
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Required
    public void setSelvbetjeningssone(boolean selvbetjeningssone) {
        this.selvbetjeningssone = selvbetjeningssone;
    }

    public void setAllowedSkew(Integer allowedSkew) {
        this.allowedSkew = allowedSkew;
    }
}
