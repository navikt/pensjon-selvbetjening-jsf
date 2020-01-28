package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

/**
 * Thrown if simulering of alderspensjon is not granted for user that is 67 år.
 *
 */
public class PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering extends FunctionalRecoverableException {
    private static final long serialVersionUID = -653911373270753595L;
    private static final String ERROR_MESSAGE = "I forbindelse med simulering er det ikke gitt innvilgelse av alderspensjon ved fylte 67 år.";

    /**
     * Default constructor, will use the default errormessage of the class.
     */
    public PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering() {
        super(ERROR_MESSAGE);
    }

}
