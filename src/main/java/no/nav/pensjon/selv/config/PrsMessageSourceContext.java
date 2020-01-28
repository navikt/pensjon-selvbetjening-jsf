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
        bean.setBasenames(new String[]{"no-nav-pensjon-pen-resources", "cfg-pen-provider-environment", "file:/Users/egjostol/IdeaProjects/pselv2/src/main/resources/tekster-properties"});
        bean.setUseCodeAsDefaultMessage(true);
        bean.setCacheRefresh(60);
        bean.setHelpPageBaseUrl("http://help");
        return bean;
    }
}
