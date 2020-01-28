package no.nav.presentation.pensjon.pselv.common.tekster;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.MessageSource;

/**
 * Spring factory bean used to instantiate a {@link TeksterMessageSource}.
 *
 * @see TeksterMessageSource
 */
public class TeksterMessageSourceFactoryBean implements FactoryBean<MessageSource> {

    private String[] basenames;
    private boolean useCodeAsDefaultMessage;
    private int cacheRefresh;
    private String helpPageBaseUrl;

    /**
     * {@inheritDoc}
     */
    @Override
    public MessageSource getObject() throws Exception {
        final TeksterMessageSource messageSource = new TeksterMessageSource();

        messageSource.setBasenames(this.basenames);
        messageSource.setUseCodeAsDefaultMessage(this.useCodeAsDefaultMessage);
        messageSource.setCacheSeconds(this.cacheRefresh);
        messageSource.setHelpurlPath(this.helpPageBaseUrl);

        return messageSource;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType() {
        return TeksterMessageSource.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setBasenames(String[] basenames) {
        this.basenames = basenames;
    }

    public void setUseCodeAsDefaultMessage(boolean useCodeAsDefaultMessage) {
        this.useCodeAsDefaultMessage = useCodeAsDefaultMessage;
    }

    public void setCacheRefresh(int cacheRefresh) {
        this.cacheRefresh = cacheRefresh;
    }

    public void setHelpPageBaseUrl(String helpPageBaseUrl) {
        this.helpPageBaseUrl = helpPageBaseUrl;
    }

}
