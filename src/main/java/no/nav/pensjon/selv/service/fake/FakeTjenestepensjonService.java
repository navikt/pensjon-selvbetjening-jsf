package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.tjenestepensjon.TjenestepensjonServiceBi;
import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdRequest;
import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdResponse;

public class FakeTjenestepensjonService implements TjenestepensjonServiceBi {

    @Override
    public FinnTjenestepensjonForholdResponse finnTjenestepensjonForhold(FinnTjenestepensjonForholdRequest request) {
        return new FinnTjenestepensjonForholdResponse();
    }
}
