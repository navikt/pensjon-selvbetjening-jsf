package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation when no løpende alderspensjon is found.
 *
 */
public class PEN223BrukerHarIkkeLopendeAlderspensjonException extends FunctionalRecoverableException {
    private static final long serialVersionUID = -855179548941394361L;

    private static final String MESSAGE = "Bruker har ikke løpende alderspensjon";

    /**
     * Default constructor.
     */
    public PEN223BrukerHarIkkeLopendeAlderspensjonException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN223BrukerHarIkkeLopendeAlderspensjonException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN223BrukerHarIkkeLopendeAlderspensjonException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN223BrukerHarIkkeLopendeAlderspensjonException(String message, Throwable cause) {
        super(message, cause);
    }
}
