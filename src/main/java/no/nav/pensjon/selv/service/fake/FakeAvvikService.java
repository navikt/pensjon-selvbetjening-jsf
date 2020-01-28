package no.nav.pensjon.selv.service.fake;

import no.nav.service.pensjon.avvik.AvvikServiceBi;
import no.nav.service.pensjon.avvik.KategoriserAvvikRequest;
import no.nav.service.pensjon.avvik.KategoriserAvvikResponse;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

public class FakeAvvikService extends TransactionProxyFactoryBean implements AvvikServiceBi {

    @Override
    public KategoriserAvvikResponse kategoriserAvvik(KategoriserAvvikRequest kategoriserAvvikRequest) {
        return new KategoriserAvvikResponse(1L, "melding", "status");
    }
}
