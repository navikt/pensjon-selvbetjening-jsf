package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;

public class LagreSkjemaResponse extends ServiceResponse {

    private static final long serialVersionUID = 5267586172084330092L;
    private Skjema skjema;

    public LagreSkjemaResponse(Skjema skjema) {
        this.skjema = skjema;
    }

    public Skjema getSkjema() {
        return skjema;
    }
}
