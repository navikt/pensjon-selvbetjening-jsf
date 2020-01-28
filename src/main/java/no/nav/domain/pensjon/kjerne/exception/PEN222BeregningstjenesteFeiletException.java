package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation in case of general PREG failure.
 *
 */
public class PEN222BeregningstjenesteFeiletException extends FunctionalRecoverableException {
    private static final long serialVersionUID = 2282769836816239008L;

    private static final String MESSAGE = "Beregningstjeneste feiler.";

    /**
     * Default constructor.
     */
    public PEN222BeregningstjenesteFeiletException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN222BeregningstjenesteFeiletException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN222BeregningstjenesteFeiletException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN222BeregningstjenesteFeiletException(String message, Throwable cause) {
        super(message, cause);
    }
}
