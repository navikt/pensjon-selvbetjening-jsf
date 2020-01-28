package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

public class PEN211KravetErIkkeAFPPrivatException extends FunctionalRecoverableException {

    private static final long serialVersionUID = -1883191852763079663L;
    private static final String ERROR_MESSAGE = "Kravet er ikke et krav av type AFP-Privat, og det er derfor ikke mulig å lagre noe resultat fra Fellesordningen på det.";

    public PEN211KravetErIkkeAFPPrivatException() {
        super(ERROR_MESSAGE);
    }

    public PEN211KravetErIkkeAFPPrivatException(Throwable cause) {
        super(ERROR_MESSAGE, cause);
    }

    public PEN211KravetErIkkeAFPPrivatException(String message) {
        super(message);
    }

    public PEN211KravetErIkkeAFPPrivatException(String message, Throwable cause) {
        super(message, cause);
    }
}
