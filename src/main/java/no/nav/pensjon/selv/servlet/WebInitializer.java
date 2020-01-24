package no.nav.pensjon.selv.servlet;


import no.nav.pensjon.selv.config.*;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.ServletRegistration.Dynamic;

/**
 * For bootstrapping the application.
 */
public class WebInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public WebInitializer() {
        super();
    }

    protected Class<?>[] getRootConfigClasses() {
        return new Class<?>[]{
                WebMvcConfig.class,
                WebFlowConfig.class,
                CfgPselvContext.class,
                CfgPselvSecurityContext.class,
                ConsPselvContext.class,
                PrsCommonContext.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected void customizeRegistration(final Dynamic registration) {
        super.customizeRegistration(registration);
    }
}

