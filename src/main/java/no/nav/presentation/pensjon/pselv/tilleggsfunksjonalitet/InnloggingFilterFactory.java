package no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Factory for delegating which innloggingsfilter to be used, if application is deployed to fagsystemsone or selvbetjeningssone.
 */
public class InnloggingFilterFactory implements FactoryBean<OncePerRequestFilter> {

    private static final Log LOG = LogFactory.getLog(InnloggingFilterFactory.class);
    private boolean selvbetjeningssone;
    private InnloggingFilter innloggingMinSideFilter;
    private InnloggingPSAKFilter innloggingPsakFilter;

    @Override
    public OncePerRequestFilter getObject() {
        OncePerRequestFilter filter = selvbetjeningssone ? innloggingMinSideFilter : innloggingPsakFilter;

        if (LOG.isDebugEnabled()) {
            LOG.debug("Returning a instance of: " + filter.getClass().getSimpleName());
        }

        return filter;
    }

    @Override
    public Class<?> getObjectType() {
        return OncePerRequestFilter.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setSelvbetjeningsSone(boolean isSelvbetjeningsSone) {
        selvbetjeningssone = isSelvbetjeningsSone;
    }

    public void setInnloggingMinSideFilter(InnloggingFilter filter) {
        innloggingMinSideFilter = filter;
    }

    public void setInnloggingPsakFilter(InnloggingPSAKFilter filter) {
        innloggingPsakFilter = filter;
    }
}
