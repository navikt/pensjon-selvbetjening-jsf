package no.nav.service.pensjon.sak;

import java.util.ArrayList;
import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.sak.Sak;

/**
 * Response object for <b>TPEN204 - hentSakListe</b>.
 * <p>
 * <i>HentSakListeResponse</i> consists of the following properties:
 * <ul>
 * <li>saker (<code>List<Sak></code>) A List of the requested Sak objects. Contains only Sak objects. Empty if the search criteria matches no objects.
 * </ul>
 * </p>
 */
public class HentSakListeResponse extends ServiceResponse {

    private static final long serialVersionUID = -4161036119209061086L;
    private List<Sak> saker = new ArrayList<>();

    public HentSakListeResponse() {
    }

    public HentSakListeResponse(List<Sak> saker) {
        this.saker = saker;
    }

    public List<Sak> getSaker() {
        return saker;
    }

    public void setSaker(List<Sak> saker) {
        this.saker = saker;
    }
}
