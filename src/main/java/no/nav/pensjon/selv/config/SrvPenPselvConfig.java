package no.nav.pensjon.selv.config;

import no.nav.pensjon.selv.service.fake.FakeAvvikRepository;
import no.nav.pensjon.selv.service.fake.FakeCodesTableRepository;
import no.nav.pensjon.selv.service.fake.FakeVedlikeholdService;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.delegate.SkjemaCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger.SkjemaPersonOpplysningerAction;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger.SkjemaPersonOpplysningerFormPopulator;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger.SkjemaPersonOpplysningerValidator;
import no.nav.service.pensjon.avvik.support.SimpleAvvikService;
import no.nav.service.pensjon.vedlikehold.VedlikeholdServiceBi;
import no.stelvio.common.codestable.support.DefaultCodesTableManager;
import no.stelvio.repository.codestable.CodesTableRepository;
import no.stelvio.service.codestable.RepositoryCodesTableItemsFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.webflow.execution.ScopeType;

@Configuration
public class SrvPenPselvConfig {

    @Autowired
    MessageSource messageSource;

    @Bean(name = "alderPensjonAction")
    public SkjemaPersonOpplysningerAction skjemaPersonOpplysningerAction() {
        SkjemaPersonOpplysningerAction action = new SkjemaPersonOpplysningerAction();
        action.setFormObjectName("form");
        action.setFormObjectClass(no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger.SkjemaPersonOpplysningerForm.class);
        action.setFormObjectScope(ScopeType.FLOW);
        action.setSkjemaPersonOpplysningerFormPopulator(skjemaPersonOpplysningerFormPopulator());
        SkjemaCommonActionDelegate d = new SkjemaCommonActionDelegate();
        action.setSkjemaCommonActionDelegate(d);
        action.setCodesTableManager(defaultCodesTableManager());
        action.setValidator(new SkjemaPersonOpplysningerValidator());
        action.setPselvTransferObject(new PselvTransferObject());
        action.setMessageSource(messageSource);
        action.setCommonActionDelegate(new PrsCommonContext().commonActionDelegate()); //TODO autowire
        action.setPersonCommonActionDelegate(new PersonCommonActionDelegate());
        return action;
    }

    @Bean(name = "pen.codesTableFactory")
    public RepositoryCodesTableItemsFactory repositoryCodesTableItemsFactory() {
        RepositoryCodesTableItemsFactory factory = new RepositoryCodesTableItemsFactory();
        CodesTableRepository repository = new FakeCodesTableRepository();
        factory.setCodesTableRepository(repository);
        return factory;
    }

    @Bean(name = "pen.codesTableManager")
    public DefaultCodesTableManager defaultCodesTableManager() {
        DefaultCodesTableManager manager = new DefaultCodesTableManager();
        manager.setCodesTableItemsFactory(repositoryCodesTableItemsFactory());
        return manager;
    }

    @Bean(name = "pen.pojo.avvikService")
    public SimpleAvvikService simpleAvvikService() {
        SimpleAvvikService service = new SimpleAvvikService();
        service.setAvvikRepository(new FakeAvvikRepository());
        return service;
    }

    @Bean(name = "pen.pojo.vedlikeholdService")
    public VedlikeholdServiceBi vedlikeholdService() {
        FakeVedlikeholdService service = new FakeVedlikeholdService();
        //TODO set properties
        return service;
    }

    @Bean(name = "prs.pselv.personopplysninger.skjemaPersonOpplysningerFormPopulator")
    public SkjemaPersonOpplysningerFormPopulator skjemaPersonOpplysningerFormPopulator() {
        SkjemaPersonOpplysningerFormPopulator populator = new SkjemaPersonOpplysningerFormPopulator();
        populator.setCodesTableManager(defaultCodesTableManager());
        populator.setMessageSource(messageSource);
        return populator;
    }
}
