package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

public class PEN150BrukerHarAktivAPSakException extends FunctionalRecoverableException {

    private static final long serialVersionUID = 6078793327527615122L;

    public PEN150BrukerHarAktivAPSakException() {
        super("Det finnes en aktiv alderspensjonsak for brukeren");
    }

    public PEN150BrukerHarAktivAPSakException(Throwable cause) {
        super("Det finnes en aktiv alderspensjonsak for brukeren", cause);
    }

    public PEN150BrukerHarAktivAPSakException(String msg) {
        super(msg);
    }

    public PEN150BrukerHarAktivAPSakException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
