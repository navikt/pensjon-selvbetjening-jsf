package no.nav.pensjon.selv.config;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CfgPselvContext {

    @Bean(name = "cns.pselv.ehcacheManager")
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean() {
        EhCacheManagerFactoryBean factory = new EhCacheManagerFactoryBean();
        factory.setConfigLocation(new ClassPathResource("cfg-pen-ehcache.xml"));
        factory.setShared(true);
        return factory;
    }

    //TODO Is this bean needed?
    @Bean(name = "cfg.propertyPlaceholderConfigurer")
    public PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
        PropertyPlaceholderConfigurer configurer = new PropertyPlaceholderConfigurer();
        configurer.setSystemPropertiesModeName("SYSTEM_PROPERTIES_MODE_OVERRIDE");
        configurer.setSearchSystemEnvironment(true);
        configurer.setTrimValues(true);
        configurer.setLocations(
//                new ClassPathResource("cfg-pselv-environment.properties"),
//                new ClassPathResource("cfg-pen-provider-environment.properties"),
//                new ClassPathResource("cfg-pen-consumer-endpoints.properties"),
                new ClassPathResource("nav-security-pensjon-common-java.properties"));
        configurer.setIgnoreUnresolvablePlaceholders(false);
        return configurer;
    }
}
