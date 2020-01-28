package no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.to;

import java.util.Collections;
import java.util.List;

import no.stelvio.common.transferobject.ServiceRequest;

/**
 * Request object for hentForventetInntekt
 *
 */
public class HentForventetInntektRequest extends ServiceRequest {

    private static final long serialVersionUID = -4986949726882980146L;

    private final String ident;
    private final List<Integer> inntektAarListe;

    public HentForventetInntektRequest(String ident, List<Integer> inntektAarListe) {
        this.ident = ident;
        this.inntektAarListe = inntektAarListe;
    }

    public String getIdent() {
        return ident;
    }

    public List<Integer> getInntektAarListe() {
        return inntektAarListe != null ? Collections.unmodifiableList(inntektAarListe) : Collections.<Integer>emptyList();
    }
}
