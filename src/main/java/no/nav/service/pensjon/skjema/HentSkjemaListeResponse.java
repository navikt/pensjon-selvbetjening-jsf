package no.nav.service.pensjon.skjema;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;

public class HentSkjemaListeResponse extends ServiceResponse {

    private static final long serialVersionUID = 3526123971937117271L;
    private List<Skjema> skjemaListe;

    public HentSkjemaListeResponse(List<Skjema> skjemaListe) {
        this.skjemaListe = skjemaListe;
    }

    public List<Skjema> getSkjemaListe() {
        return skjemaListe;
    }
}
