package no.nav.consumer.pensjon.pselv.samhandler;

import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerRequest;
import no.nav.consumer.pensjon.pselv.samhandler.to.FinnSamhandlerResponse;

public interface SamhandlerServiceBi {

    FinnSamhandlerResponse finnSamhandler(FinnSamhandlerRequest request);
}
