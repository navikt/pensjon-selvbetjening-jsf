package no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten;

import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektRequest;
import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to.HentForventetInntektResponse;

public interface InntektServiceBi {
    HentForventetInntektResponse hentForventetInntekt(HentForventetInntektRequest request);
}
