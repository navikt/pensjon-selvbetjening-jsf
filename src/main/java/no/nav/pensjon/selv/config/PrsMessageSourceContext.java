package no.nav.pensjon.selv.config;

import no.nav.presentation.pensjon.pselv.common.tekster.TeksterMessageSourceFactoryBean;
import no.nav.repository.pensjon.util.EnvironmentFirstResourceBundleMessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PrsMessageSourceContext {

    @Bean(name = "messageSource")
    public TeksterMessageSourceFactoryBean messageSource() {
        TeksterMessageSourceFactoryBean bean = new TeksterMessageSourceFactoryBean();
        bean.setBasenames(new String[]{"no-nav-pensjon-pen-resources", "cfg-pen-provider-environment", "file:/PATH/resources/tekster-properties"}); // TODO replace PATH
        bean.setUseCodeAsDefaultMessage(true);
        bean.setCacheRefresh(60000);
        bean.setHelpPageBaseUrl("http://help");
        return bean;
    }
}
