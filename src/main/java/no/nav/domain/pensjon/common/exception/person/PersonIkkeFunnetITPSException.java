package no.nav.domain.pensjon.common.exception.person;

import no.stelvio.common.error.UnrecoverableException;

public class PersonIkkeFunnetITPSException extends UnrecoverableException {

    private static final long serialVersionUID = -174669065350226994L;

    public PersonIkkeFunnetITPSException(String message, Throwable cause) {
        super(message, cause);
    }

    public PersonIkkeFunnetITPSException(String message) {
        super(message);
    }
}
