package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;

public class SendSkjemaRequest extends ServiceRequest {

    private static final long serialVersionUID = -4770324404610782175L;
    private Skjema skjema;
    private Sak sak;
    private Boolean behandleAutomatisk;
    private String kommentar;

    public Sak getSak() {
        return sak;
    }

    public void setSak(Sak sak) {
        this.sak = sak;
    }

    public Skjema getSkjema() {
        return skjema;
    }

    public void setSkjema(Skjema skjema) {
        this.skjema = skjema;
    }

    /**
     * @param kommentar contains iformation on why the application should be processed
     *                  manually when <code>behandleAutomatisk</code> is <code>false</code>
     */
    public void setKommentar(String kommentar) {
        this.kommentar = kommentar;
    }

    public String getKommentar() {
        return kommentar;
    }

    public void setBehandleAutomatisk(Boolean behandleAutomatisk) {
        this.behandleAutomatisk = behandleAutomatisk;
    }

    public Boolean getBehandleAutomatisk() {
        return behandleAutomatisk;
    }
}
