package no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag;

//import org.codehaus.jackson.annotate.JsonIgnoreProperties;
//import org.codehaus.jackson.annotate.JsonProperty;

import no.nav.domain.pensjon.kjerne.opptjening.OpptjeningsGrunnlag;

//@JsonIgnoreProperties(ignoreUnknown = true)
public class HentOpptjeningsGrunnlagResponse {

    @SuppressWarnings("unused")
//    @JsonProperty("opptjeningsGrunnlag")
    private OpptjeningsGrunnlag opptjeningsGrunnlag;

    public OpptjeningsGrunnlag getOpptjeningsGrunnlag() {
        return opptjeningsGrunnlag;
    }

    public void setOpptjeningsGrunnlag(OpptjeningsGrunnlag opptjeningsGrunnlag) {
        this.opptjeningsGrunnlag = opptjeningsGrunnlag;
    }
}
