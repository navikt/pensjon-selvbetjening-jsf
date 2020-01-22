package no.nav.pensjon.selv.config;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingFilter;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingFilterFactory;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingPSAKFilter;
import no.stelvio.presentation.context.RequestContextFilter;
import no.stelvio.presentation.security.logout.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Replaces prs-common-context.xml.
 */
@Configuration
@Import({CfgPselvSecurityContext.class, ConsPselvContext.class})
public class PrsCommonContext {

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersonServiceBi personService;

    @Autowired
    LogoutService logoutService;

    @Bean(name = "selvbetjeningsSone")
    public Boolean selvbetjeningssone() {
        return Boolean.FALSE;
    }

    @Bean(name = "prs.pselv.requestContextFilter")
    public RequestContextFilter requestContextFilter() {
        return new RequestContextFilter();
    }

    @Bean(name = "prs.pselv.innloggingMinSideFilter")
    public InnloggingFilter innloggingFilter() {
        InnloggingFilter filter = new InnloggingFilter();
        filter.setPersonService(personService);
        filter.setLogoutService(logoutService);
        filter.setMessageSource(messageSource);
        return filter;
    }

    @Bean(name = "prs.pselv.innloggingPSAKFilter")
    public InnloggingPSAKFilter innloggingPsakFilter() {
        InnloggingPSAKFilter filter = new InnloggingPSAKFilter();
        filter.setPersonService(personService);
        return filter;
    }

    @Bean(name = "prs.pselv.innloggingFilterFactory")
    public InnloggingFilterFactory innloggingFilterFactory() {
        InnloggingFilterFactory factory = new InnloggingFilterFactory();
        factory.setSelvbetjeningsSone(selvbetjeningssone());
        factory.setInnloggingMinSideFilter(innloggingFilter());
        factory.setInnloggingPsakFilter(innloggingPsakFilter());
        return factory;
    }
}
