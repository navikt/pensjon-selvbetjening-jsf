package no.nav.domain.pensjon.common.exception;

import no.stelvio.common.error.FunctionalUnrecoverableException;

/**
 * Wraps checked exceptions from services that will not be explicitly handled.
 */
public class ImplementationUnrecoverableException extends FunctionalUnrecoverableException {

    public static final String UNHANDLED_ERROR_MSG_PREFIX = "Uhåndtert feilsituasjon: ";
    private static final long serialVersionUID = -5274520168465743385L;

    public ImplementationUnrecoverableException(String errorDescription) {
        super(UNHANDLED_ERROR_MSG_PREFIX + errorDescription);
    }

    public ImplementationUnrecoverableException(Throwable cause) {
        super(cause == null ? "" : cause.getMessage() + " class " + cause.getClass().toString(), cause);
    }

    public ImplementationUnrecoverableException(String errorDescription, Throwable cause) {
        super(UNHANDLED_ERROR_MSG_PREFIX + errorDescription, cause);
    }
}
