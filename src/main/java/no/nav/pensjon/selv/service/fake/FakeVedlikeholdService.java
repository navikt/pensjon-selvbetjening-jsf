package no.nav.pensjon.selv.service.fake;

import no.nav.service.pensjon.vedlikehold.HentPenPersonRequest;
import no.nav.service.pensjon.vedlikehold.HentPenPersonResponse;
import no.nav.service.pensjon.vedlikehold.VedlikeholdServiceBi;

public class FakeVedlikeholdService implements VedlikeholdServiceBi {

    @Override
    public HentPenPersonResponse hentPenPerson(HentPenPersonRequest hentPenPersonRequest) {
        return new HentPenPersonResponse();
    }
}
