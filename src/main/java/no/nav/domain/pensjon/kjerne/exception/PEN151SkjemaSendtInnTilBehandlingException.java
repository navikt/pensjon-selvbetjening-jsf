package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalUnrecoverableException;

public class PEN151SkjemaSendtInnTilBehandlingException extends FunctionalUnrecoverableException {

    private static final long serialVersionUID = 5294417237456717866L;
    private Long skjemaId;

    public PEN151SkjemaSendtInnTilBehandlingException(Long skjemaId) {
        super("Skjemaet " + skjemaId + " kan ikke slettes da det er sendt inn til behandling");
        this.skjemaId = skjemaId;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }
}
