package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation in case of for lavt tidlig uttak.
 *
 */
public class PEN225AvslagVilkarsprovingForLavtTidligUttakException extends FunctionalRecoverableException {
    private static final long serialVersionUID = -6143203088968059740L;

    private static final String MESSAGE = "Avslag på vilkårsprøving grunnet for lavt tidlig uttak.";

    /**
     * Default constructor.
     */
    public PEN225AvslagVilkarsprovingForLavtTidligUttakException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN225AvslagVilkarsprovingForLavtTidligUttakException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN225AvslagVilkarsprovingForLavtTidligUttakException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN225AvslagVilkarsprovingForLavtTidligUttakException(String message, Throwable cause) {
        super(message, cause);
    }
}
