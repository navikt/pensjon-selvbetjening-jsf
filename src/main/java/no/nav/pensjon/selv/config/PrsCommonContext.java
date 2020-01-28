package no.nav.pensjon.selv.config;

import net.sf.ehcache.CacheManager;
import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.pensjon.selv.service.fake.*;
import no.nav.presentation.pensjon.pselv.common.CommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.delegate.SamhandlerCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.delegate.SkjemaCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.publisering.dininnboks.DinInnboksHelper;
import no.nav.presentation.pensjon.pselv.publisering.korrespondanse.DinKorrespondanse;
import no.nav.presentation.pensjon.pselv.publisering.korrespondanse.DinKorrespondanseCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.publisering.loginserviceredirect.LoginserviceRedirectAction;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingFilter;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingFilterFactory;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.InnloggingPSAKFilter;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.decorator.DecoratorFilterFactory;
import no.nav.presentation.pensjon.pselv.tilleggsfunksjonalitet.decorator.PENonicContentRetriever;
import no.stelvio.common.codestable.support.DefaultCodesTableManager;
import no.stelvio.presentation.context.RequestContextFilter;
import no.stelvio.presentation.security.logout.LogoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
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

//    @Autowired
//    EhCacheManagerFactoryBean ehCacheManagerFactoryBean;

    @Bean
    public DecoratorFilterFactory decoratorFilter() {
        DecoratorFilterFactory factory = new DecoratorFilterFactory();
        factory.setApplicationName("PSELV"); // TODO ${appres.cms.uniqueAppName}
        factory.setFragmentsUrl("common-html/v4/navno"); // ${appres.cms.fragmentsUrl}
        factory.setContentRetriever(newContentRetriever());
        return factory;
    }

    @Bean(name = "loginserviceRedirectAction")
    public LoginserviceRedirectAction loginserviceRedirectAction() {
        LoginserviceRedirectAction action = new LoginserviceRedirectAction();
        action.setMessageSource(messageSource);
        action.setSelvbetjeningssone(selvbetjeningssone());
        action.setAllowedSkew(10);
        return action;
    }

    @Bean(name = "prs.pselv.commonActionDelegate")
    public CommonActionDelegate commonActionDelegate() {
        CommonActionDelegate delegate = new CommonActionDelegate();
        delegate.setPersonService(personService);
        delegate.setCodesTableManager(new SrvPenPselvConfig().defaultCodesTableManager());
        delegate.setGrunnlagService(new FakeGrunnlagService());
        delegate.setBrukerprofilService(new FakeBrukerprofilService());
        delegate.setOrganisasjonEnhetService(new FakeOrganisasjonEnhetService());
        delegate.setPersonService3(new FakePersonService3());
        return delegate;
    }

    @Bean(name = "prs.pselv.dinInnboksHelper")
    public DinInnboksHelper dinInnboksHelper() {
        DinInnboksHelper helper = new DinInnboksHelper();
        helper.setKorrespondanseDelegate(new DinKorrespondanseCommonActionDelegate());
        helper.setDinKorrespondanse(new DinKorrespondanse());
        helper.setCodesTableManager(new DefaultCodesTableManager());
        return helper;
    }

    @Bean(name = "prs.pselv.innloggingFilterFactory")
    public InnloggingFilterFactory innloggingFilterFactory() {
        InnloggingFilterFactory factory = new InnloggingFilterFactory();
        factory.setSelvbetjeningsSone(selvbetjeningssone());
        factory.setInnloggingMinSideFilter(innloggingFilter());
        factory.setInnloggingPsakFilter(innloggingPsakFilter());
        return factory;
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

    @Bean(name = "prs.pselv.personCommonActionDelegate")
    public PersonCommonActionDelegate personCommonActionDelegate() {
        PersonCommonActionDelegate delegate = new PersonCommonActionDelegate();
        delegate.setPersonService(personService);
        return delegate;
    }

    @Bean(name = "prs.pselv.requestContextFilter")
    public RequestContextFilter requestContextFilter() {
        return new RequestContextFilter();
    }

    @Bean(name = "prs.pselv.skjemaCommonActionDelegate")
    public SkjemaCommonActionDelegate skjemaCommonActionDelegate() {
        SkjemaCommonActionDelegate delegate = new SkjemaCommonActionDelegate();
        delegate.setSkjemaService(new FakeSkjemaService());
//        delegate.setSkjemaService(skjemaService); //TODO autowire
        return delegate;
    }

    @Bean(name = "prs.pselv.transferObject")
    public PselvTransferObject pselvTransferObject() {
        return new PselvTransferObject();
    }

    @Bean(name = "selvbetjeningsSone")
    public Boolean selvbetjeningssone() {
        return Boolean.FALSE;
    }

    private PENonicContentRetriever newContentRetriever() {
        PENonicContentRetriever retriever = new PENonicContentRetriever(10000); //TODO ${appres.cms.httpTimeoutMillis}
        retriever.setBaseUrl("https://appres-t4.nav.no/"); // ${appres.cms.url}
//        retriever.setCacheManager(ehCacheManagerFactoryBean);
        retriever.setCacheManager(CacheManager.create()); //TODO cns.pselv.ehcacheManager (ehCacheManagerFactoryBean)
//        retriever.setRefreshIntervalSeconds(60000); // ${appres.cms.refreshIntervalSeconds}
        return retriever;
    }
}
