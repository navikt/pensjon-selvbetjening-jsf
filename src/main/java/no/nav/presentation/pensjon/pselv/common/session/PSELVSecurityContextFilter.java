package no.nav.presentation.pensjon.pselv.common.session;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import no.stelvio.common.context.RequestContext;
import no.stelvio.common.context.RequestContextHolder;
import no.stelvio.common.context.support.RequestContextSetter;
import no.stelvio.common.context.support.SimpleRequestContext;
import no.stelvio.presentation.security.context.WSCustomSecurityContextFilter;

import static no.stelvio.common.context.RequestContextHolder.currentRequestContext;
import static no.stelvio.common.context.RequestContextHolder.isRequestContextSet;
import static no.stelvio.common.context.support.RequestContextSetter.setRequestContext;

/**
 * Sets the user to "IKKE_INNLOGGET" if null.
 */
public class PSELVSecurityContextFilter extends WSCustomSecurityContextFilter {

    private static final long serialVersionUID = 1L;
    private static final String NOT_LOGGED_IN_USER_ID = "IKKE_INNLOGGET";

    /**
     * Set user_id on requestContext if user_id is NULL. {@inheritDoc}
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (isRequestContextSet()) {
            setNotLoggedInIfMissingUserId();
        }

        chain.doFilter(request, response);
    }

    private static void setNotLoggedInIfMissingUserId() {
        RequestContext context = currentRequestContext();

        if (context.getUserId() == null) {
            setRequestContext(newNotLoggedInContext(context));
        }
    }

    private static RequestContext newNotLoggedInContext(RequestContext context) {
        return new SimpleRequestContext
                .Builder(context)
                .userId(NOT_LOGGED_IN_USER_ID)
                .build();
    }
}
