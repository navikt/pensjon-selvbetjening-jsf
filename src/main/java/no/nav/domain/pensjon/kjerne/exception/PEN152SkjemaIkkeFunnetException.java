package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalUnrecoverableException;

public class PEN152SkjemaIkkeFunnetException extends FunctionalUnrecoverableException {

    private static final long serialVersionUID = -3336865507260591062L;
    private Long skjemaId;

    public PEN152SkjemaIkkeFunnetException(Long skjemaId) {
        super("Det eksisterer ikke noe skjema med id " + skjemaId);
        this.skjemaId = skjemaId;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }
}
