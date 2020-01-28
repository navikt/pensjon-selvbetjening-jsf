package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.opptjening.beholdning.BeholdningServiceBi;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.HentBeholdningListeRequest;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.HentBeholdningListeResponse;

public class FakeBeholdningService implements BeholdningServiceBi {

    @Override
    public HentBeholdningListeResponse hentBeholdningListe(HentBeholdningListeRequest request) {
        return new HentBeholdningListeResponse();
    }
}
