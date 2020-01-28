package no.nav.consumer.pensjon.pen.aktoerregister;

import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdRequest;
import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdResponse;

public interface AktoerRegisterServiceBi {
    HentAktoerIdResponse hentAktoerId(HentAktoerIdRequest request);
}
