package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pen.person3.to.*;

public class FakePersonService3 implements no.nav.consumer.pensjon.pen.person3.PersonServiceBi {

    @Override
    public HentGeografiskTilknytningResponse hentGeografiskTilknytning(HentGeografiskTilknytningRequest hentGeografiskTilknytningRequest) {
        return new HentGeografiskTilknytningResponse();
    }

    @Override
    public HentEkteskapshistorikkResponse hentEkteskapshistorikk(HentEkteskapshistorikkRequest hentEkteskapshistorikkRequest) {
        return new HentEkteskapshistorikkResponse();
    }

    @Override
    public HentPersonerMedSammeAdresseResponse hentPersonerMedSammeAdresse(HentPersonerMedSammeAdresseRequest hentPersonerMedSammeAdresseRequest) {
        return new HentPersonerMedSammeAdresseResponse();
    }
}
