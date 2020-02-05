package no.nav.presentation.pensjon.pselv.common.tekster;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.MessageSource;

/**
 * Spring factory bean used to instantiate a {@link TeksterMessageSource}.
 */
public class TeksterMessageSourceFactoryBean implements FactoryBean<MessageSource> {

    private String[] basenames;
    private boolean useCodeAsDefaultMessage;
    private int cacheRefresh;
    private String helpPageBaseUrl;

    @Override
    public MessageSource getObject() {
        final TeksterMessageSource source = new TeksterMessageSource();
        source.setBasenames(basenames);
        source.setUseCodeAsDefaultMessage(useCodeAsDefaultMessage);
        source.setCacheSeconds(cacheRefresh);
        source.setHelpurlPath(helpPageBaseUrl);
        return source;
    }

    @Override
    public Class<?> getObjectType() {
        return TeksterMessageSource.class;
    }

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
