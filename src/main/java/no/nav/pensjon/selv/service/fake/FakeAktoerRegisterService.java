package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pen.aktoerregister.AktoerRegisterServiceBi;
import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdRequest;
import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdResponse;

public class FakeAktoerRegisterService implements AktoerRegisterServiceBi {

    @Override
    public HentAktoerIdResponse hentAktoerId(HentAktoerIdRequest request) {
        return new HentAktoerIdResponse();
    }
}
