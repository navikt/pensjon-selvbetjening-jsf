package no.nav.consumer.pensjon.pselv.tjenestepensjon;

import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdRequest;
import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdResponse;

public interface TjenestepensjonServiceBi {

    FinnTjenestepensjonForholdResponse finnTjenestepensjonForhold(FinnTjenestepensjonForholdRequest request);
}
