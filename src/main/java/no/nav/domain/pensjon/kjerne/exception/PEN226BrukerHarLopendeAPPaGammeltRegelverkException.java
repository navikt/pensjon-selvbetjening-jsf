package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation when user has a running AP on the old rules set.
 *
 */
public class PEN226BrukerHarLopendeAPPaGammeltRegelverkException extends FunctionalRecoverableException {
    private static final long serialVersionUID = -8063095425560956274L;

    private static final String MESSAGE = "Bruker har løpende alderspensjon på gammelt regelverk.";

    /**
     * Default constructor.
     */
    public PEN226BrukerHarLopendeAPPaGammeltRegelverkException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN226BrukerHarLopendeAPPaGammeltRegelverkException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN226BrukerHarLopendeAPPaGammeltRegelverkException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN226BrukerHarLopendeAPPaGammeltRegelverkException(String message, Throwable cause) {
        super(message, cause);
    }
}
