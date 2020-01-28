package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation in case of mindre enn 20 års botid i Norge.
 *
 */
public class PEN227AvslagVilkarsprovingForKortBotidException extends FunctionalRecoverableException {
    private static final long serialVersionUID = -2513910748419665020L;

    private static final String MESSAGE = "Avslag på vilkårsprøving grunnet at bruker ikke er medlem i folketrygden og har mindre enn 20 års botid i Norge";

    /**
     * Default constructor.
     */
    public PEN227AvslagVilkarsprovingForKortBotidException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN227AvslagVilkarsprovingForKortBotidException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN227AvslagVilkarsprovingForKortBotidException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN227AvslagVilkarsprovingForKortBotidException(String message, Throwable cause) {
        super(message, cause);
    }
}
