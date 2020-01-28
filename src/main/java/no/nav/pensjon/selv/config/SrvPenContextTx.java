package no.nav.pensjon.selv.config;

import no.nav.pensjon.selv.service.fake.FakeAvvikService;
import no.nav.pensjon.selv.service.fake.FakePlatformTransactionManager;
import no.nav.pensjon.selv.service.fake.FakeVedlikeholdService;
import no.nav.service.pensjon.avvik.AvvikServiceBi;
import no.nav.service.pensjon.vedlikehold.VedlikeholdServiceBi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.Properties;

@Configuration
@Import(SrvPenPselvConfig.class)
public class SrvPenContextTx {

    @Autowired
    SrvPenPselvConfig srvPenPselvConfig;

    @Bean(name = "pen.avvikService")
    public AvvikServiceBi penAvvikService() {
        FakeAvvikService service = new FakeAvvikService();
        service.setTarget(srvPenPselvConfig.simpleAvvikService()); //TODO "pen.pojo.avvikService"
        service.setTransactionManager(new FakePlatformTransactionManager()); //TODO "transactionManager"
        service.setTransactionAttributes(newTransactionAttributes());
        service.setPreInterceptors(new Object[]{}); //TODO "srv.pen.preServiceInterceptors"
        service.setPostInterceptors(new Object[]{}); //TODO "srv.pen.postServiceInterceptors"
        return service;
    }

    @Bean(name = "pen.vedlikeholdService")
    public VedlikeholdServiceBi vedlikeholdService() {
        FakeVedlikeholdService service = new FakeVedlikeholdService();
        return service;
    }

    private static Properties newTransactionAttributes() {
        Properties properties = new Properties();
        properties.put("opprettKrav", "PROPAGATION_REQUIRED, -java.lang.Exception, -no.nav.domain.pensjon.kjerne.exception.PEN142KravIkkeTillattOpprettException");
        properties.put("sendSkjema", "PROPAGATION_REQUIRED, -java.lang.Exception, -no.nav.domain.pensjon.kjerne.exception.PEN150BrukerHarAktivAPSakException");
        properties.put("*", "PROPAGATION_REQUIRED, -java.lang.Exception");
        return properties;
    }
}
