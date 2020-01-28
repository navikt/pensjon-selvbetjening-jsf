package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.HentOpptjeningsGrunnlagRequest;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.HentOpptjeningsGrunnlagResponse;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.OpptjeningsGrunnlagServiceBi;
import no.nav.domain.pensjon.kjerne.opptjening.Inntekt;
import no.nav.domain.pensjon.kjerne.opptjening.OpptjeningsGrunnlag;

import java.util.Collections;

public class FakeOpptjeningsGrunnlagService implements OpptjeningsGrunnlagServiceBi {

    @Override
    public HentOpptjeningsGrunnlagResponse hentOpptjeningsGrunnlag(HentOpptjeningsGrunnlagRequest request) {
        HentOpptjeningsGrunnlagResponse response = new HentOpptjeningsGrunnlagResponse();
        response.setOpptjeningsGrunnlag(newOpptjeningsGrunnlag());
        return response;
    }

    private static OpptjeningsGrunnlag newOpptjeningsGrunnlag() {
        OpptjeningsGrunnlag grunnlag = new OpptjeningsGrunnlag();
        grunnlag.setInntektListe(Collections.singletonList(newInntekt()));
        return grunnlag;
    }

    private static Inntekt newInntekt() {
        Inntekt inntekt = new Inntekt();
        inntekt.setInntektType("SUM_PI");
        inntekt.setBelop(187756L);
        inntekt.setInntektAr(2017);
        return inntekt;
    }
}
