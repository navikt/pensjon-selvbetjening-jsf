package no.nav.domain.pensjon.common.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

public abstract class ConsumerRecoverableException extends FunctionalRecoverableException {

    private static final long serialVersionUID = 1L;

    protected ConsumerRecoverableException(String message) {
        super(message);
    }

    protected ConsumerRecoverableException(String message, Throwable cause) {
        super(message, cause);
    }
}
