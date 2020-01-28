package no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to;

import java.util.ArrayList;
import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.grunnlag.Inntektsgrunnlag;

/**
 * Response object for hentForventetInntekt
 *
 */
public class HentForventetInntektResponse extends ServiceResponse {

    private static final long serialVersionUID = -1126580256694383449L;
    private String ident;
    private List<Inntektsgrunnlag> inntektsgrunnlagListe;
    private List<Inntektsgrunnlag> inntektsgrunnlagListeEps;

    /**
     * Default constructor
     */
    public HentForventetInntektResponse() {
        super();
    }

    /**
     * Max constructor
     *
     * @param ident
     * @param inntektsgrunnlagListe
     */
    public HentForventetInntektResponse(String ident, List<Inntektsgrunnlag> inntektsgrunnlagListe, List<Inntektsgrunnlag> inntektsgrunnlagListeEps) {
        super();
        this.ident = ident;
        this.inntektsgrunnlagListe = inntektsgrunnlagListe;
        this.inntektsgrunnlagListeEps = inntektsgrunnlagListeEps;
    }

    /**
     * @return the ident
     */
    public String getIdent() {
        return ident;
    }

    /**
     * @param ident the ident to set
     */
    public void setIdent(String ident) {
        this.ident = ident;
    }

    /**
     * @return the inntektsgrunnlagListe
     */
    public List<Inntektsgrunnlag> getInntektsgrunnlagListe() {
        if (inntektsgrunnlagListe == null) {
            inntektsgrunnlagListe = new ArrayList<Inntektsgrunnlag>();
        }
        return inntektsgrunnlagListe;
    }

    /**
     * @return the inntektsgrunnlagListeEps
     */
    public List<Inntektsgrunnlag> getInntektsgrunnlagListeEps() {
        if (inntektsgrunnlagListeEps == null) {
            inntektsgrunnlagListeEps = new ArrayList<Inntektsgrunnlag>();
        }
        return inntektsgrunnlagListeEps;
    }
}
