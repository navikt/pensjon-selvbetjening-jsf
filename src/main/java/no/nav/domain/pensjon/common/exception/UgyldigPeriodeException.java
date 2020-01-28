package no.nav.domain.pensjon.common.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

public class UgyldigPeriodeException extends FunctionalRecoverableException {

    private static final long serialVersionUID = 1909993464219864548L;

    public UgyldigPeriodeException(String message) {
        super(message);
    }
}
