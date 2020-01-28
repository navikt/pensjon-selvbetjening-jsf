package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.samhandler.SamhandlerServiceBi;
import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerRequest;
import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerResponse;

public class FakeSamhandlerService implements SamhandlerServiceBi {

    @Override
    public FinnSamhandlerResponse finnSamhandler(FinnSamhandlerRequest request) {
        return new FinnSamhandlerResponse();
    }
}
