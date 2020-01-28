package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * PEN exception thrown from FPEN028-based simulation in case of insufficient trygdetid.
 *
 */
public class PEN224AvslagVilkarsprovingForKortTrygdetidException extends FunctionalRecoverableException {
    private static final long serialVersionUID = -2593662570442296853L;

    private static final String MESSAGE = "Avslag på vilkårsprøving grunnet for kort trygdetid.";

    /**
     * Default constructor.
     */
    public PEN224AvslagVilkarsprovingForKortTrygdetidException() {
        super(MESSAGE);
    }

    /**
     * Constructor with supplied message.
     *
     * @param message the message to use
     */
    public PEN224AvslagVilkarsprovingForKortTrygdetidException(String message) {
        super(message);
    }

    /**
     * Constructor with supplied root cause.
     *
     * @param cause the root cause
     */
    public PEN224AvslagVilkarsprovingForKortTrygdetidException(Throwable cause) {
        super(MESSAGE, cause);
    }

    /**
     * Constructor with supplied message and root cause
     *
     * @param message the message to use
     * @param cause the root cause
     */
    public PEN224AvslagVilkarsprovingForKortTrygdetidException(String message, Throwable cause) {
        super(message, cause);
    }
}
