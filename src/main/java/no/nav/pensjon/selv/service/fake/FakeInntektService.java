package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.InntektServiceBi;
import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektRequest;
import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektResponse;

public class FakeInntektService implements InntektServiceBi {

    @Override
    public HentForventetInntektResponse hentForventetInntekt(HentForventetInntektRequest request) {
        return new HentForventetInntektResponse();
    }
}
