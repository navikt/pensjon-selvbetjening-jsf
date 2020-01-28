package no.nav.pensjon.selv.service.fake;

import no.nav.service.pensjon.krav.KravServiceBi;
import no.nav.service.pensjon.krav.HentKravListeRequest;
import no.nav.service.pensjon.krav.HentKravListeResponse;

public class FakeKravService implements KravServiceBi {

    @Override
    public HentKravListeResponse hentKravListe(HentKravListeRequest hentKravListeRequest) {
        HentKravListeResponse response = new HentKravListeResponse();
        response.setKravListe(FakeSakService.newSak().getKravHodeListe());
        return response;
    }
}
