package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;

public class PEN031SakManglerEierenhetException extends FunctionalRecoverableException {

    private static final long serialVersionUID = -774098126356784850L;
    private Long id;

    public PEN031SakManglerEierenhetException(Long id) {
        super("Saken " + id + " har ingen tilknyttet eierenhet.");
        this.id = id;
    }

    public PEN031SakManglerEierenhetException(Throwable cause, Long id) {
        super("Saken " + id + " har ingen tilknyttet eierenhet.", cause);
        this.id = id;
    }

    public PEN031SakManglerEierenhetException(String msg, Long id) {
        super(msg);
        this.id = id;
    }

    public PEN031SakManglerEierenhetException(String msg, Throwable cause, Long id) {
        super(msg, cause);
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
