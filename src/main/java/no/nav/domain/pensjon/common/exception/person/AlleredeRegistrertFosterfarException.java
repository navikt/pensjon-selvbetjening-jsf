package no.nav.domain.pensjon.common.exception.person;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class AlleredeRegistrertFosterfarException extends ConsumerRecoverableException {

    private static final long serialVersionUID = 364146572222381551L;
    private String fnr;
    private String origin;

    public AlleredeRegistrertFosterfarException(String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertFosterfarException(Throwable cause, String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin, cause);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertFosterfarException(String message, String origin, String fnr) {
        super(message);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertFosterfarException(String message, Throwable cause, String origin, String fnr) {
        super(message, cause);
        this.fnr = fnr;
        this.origin = origin;
    }

    public String getFnr() {
        return fnr;
    }

    public String getOrigin() {
        return origin;
    }
}
