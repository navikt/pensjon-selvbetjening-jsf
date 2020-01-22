package no.nav.domain.pensjon.common.exception.person;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class BrukerprofilIkkeFunnetException extends ConsumerRecoverableException {

    private static final long serialVersionUID = -213073700515725992L;
    private String fnr;
    private String origin;

    public BrukerprofilIkkeFunnetException(String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin);
        this.fnr = fnr;
        this.origin = origin;
    }

    public BrukerprofilIkkeFunnetException(Throwable cause, String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin, cause);
        this.fnr = fnr;
        this.origin = origin;
    }

    public BrukerprofilIkkeFunnetException(String message, String origin, String fnr) {
        super(message);
        this.fnr = fnr;
        this.origin = origin;
    }

    public BrukerprofilIkkeFunnetException(String message, Throwable cause, String origin, String fnr) {
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
