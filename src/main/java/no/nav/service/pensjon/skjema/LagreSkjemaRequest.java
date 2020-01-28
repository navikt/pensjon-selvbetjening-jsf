package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;

public class LagreSkjemaRequest extends ServiceRequest {

    private static final long serialVersionUID = 8967076424504522251L;
    private Skjema skjema;

    public LagreSkjemaRequest(Skjema skjema) {
        this.skjema = skjema;
    }

    public Skjema getSkjema() {
        return skjema;
    }
}
