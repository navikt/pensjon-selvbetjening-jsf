package no.nav.service.pensjon.vedtak;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.Validate;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;

/**
 * Response object for TPEN410 hentVedtakListe
 */
public class HentVedtakListeResponse extends ServiceResponse {

    private static final long serialVersionUID = 943637068860918929L;
    private List<Vedtak> vedtakListe = new ArrayList<>();

    public HentVedtakListeResponse() {
    }

    public HentVedtakListeResponse(List<Vedtak> vedtakListe) {
        this.vedtakListe = vedtakListe;
    }

    public List<Vedtak> getVedtakListe() {
        return vedtakListe;
    }

    public void setVedtakListe(List<Vedtak> vedtakListe) {
        Validate.notNull(vedtakListe);
        this.vedtakListe = vedtakListe;
    }
}
