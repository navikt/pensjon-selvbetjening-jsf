package no.nav.pensjon.selv.service.fake;

import no.nav.service.pensjon.simulering.SimuleringServiceBi;
import no.nav.service.pensjon.simulering.SimuleringEtter2011Request;
import no.nav.service.pensjon.simulering.SimuleringEtter2011Response;

public class FakeSimuleringService implements SimuleringServiceBi {

    @Override
    public SimuleringEtter2011Response finnForsteMuligeUttaksalder(SimuleringEtter2011Request request) {
        return new SimuleringEtter2011Response();
    }

    @Override
    public SimuleringEtter2011Response simulerFleksibelAP(SimuleringEtter2011Request request) {
        return new SimuleringEtter2011Response();
    }
}
