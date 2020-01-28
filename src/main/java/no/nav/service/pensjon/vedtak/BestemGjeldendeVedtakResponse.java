package no.nav.service.pensjon.vedtak;

import java.util.ArrayList;
import java.util.List;

import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;

/**
 * Response class for TPEN486 BestemGjeldendeVedtak.
 */
public class BestemGjeldendeVedtakResponse {

    private List<Vedtak> vedtakListe = new ArrayList<>();

    public BestemGjeldendeVedtakResponse() {
    }

    public BestemGjeldendeVedtakResponse(List<Vedtak> vedtakList) {
        this.vedtakListe = vedtakList;
    }

    public List<Vedtak> getVedtakListe() {
        return vedtakListe;
    }

    public void setVedtakListe(List<Vedtak> vedtakListe) {
        this.vedtakListe = vedtakListe;
    }
}
