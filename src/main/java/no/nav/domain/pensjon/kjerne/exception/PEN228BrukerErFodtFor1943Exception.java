package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation in case of user is born before 1943.
 *
 */
public class PEN228BrukerErFodtFor1943Exception extends FunctionalRecoverableException {
    private static final long serialVersionUID = -5509866122271556574L;

    private static final String DEFAULT_MESSAGE = "Bruker er født før 1943.";

    /**
     * Default Constructor
     */
    public PEN228BrukerErFodtFor1943Exception() {
        super(DEFAULT_MESSAGE);
    }

    /**
     * Constructor with supplied message
     *
     * @param message the message to show
     */
    public PEN228BrukerErFodtFor1943Exception(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause
     *
     * @param cause The cause
     */
    public PEN228BrukerErFodtFor1943Exception(Throwable cause) {
        super(DEFAULT_MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message The message to add
     * @param cause The cause of the exception
     */
    public PEN228BrukerErFodtFor1943Exception(String message, Throwable cause) {
        super(message, cause);
    }
}
