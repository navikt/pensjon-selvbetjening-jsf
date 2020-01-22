package no.nav.presentation.pensjon.pselv.common;

import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_REQ_PARAM;
import static no.nav.presentation.pensjon.pselv.common.PselvConstants.CONTEXT_SESSION_ATTR;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * If request contains context parameter, save it to session.
 * This filter helps support navigation by browser back/forward keys by ensuring that context parameter is not lost during navigation.
 */
public class PselvContextFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        // do nothing
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (request.getParameterMap().containsKey(CONTEXT_REQ_PARAM)) {
            getSession(request).setAttribute(CONTEXT_SESSION_ATTR, request.getParameter(CONTEXT_REQ_PARAM));
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // do nothing
    }

    private static HttpSession getSession(ServletRequest request) {
        return ((HttpServletRequest) request).getSession();
    }
}
