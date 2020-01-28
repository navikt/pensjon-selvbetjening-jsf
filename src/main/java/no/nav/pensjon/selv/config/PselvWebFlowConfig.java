package no.nav.pensjon.selv.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

/**
 * Moved from package no.nav.presentation.pensjon.pselv.config.
 */
@Configuration
public class PselvWebFlowConfig {

    @Bean(name = "breadCrumbSource")
    public ResourceBundleMessageSource breadCrumbSource() {
        ResourceBundleMessageSource source = new ResourceBundleMessageSource();
        source.setBasename("no-nav-pensjon-pselv-breadcrumb");
        return source;
    }
}
