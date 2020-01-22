package no.nav.domain.pensjon.common.exception.person;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class SamboerIFamilieException extends ConsumerRecoverableException {

    private static final long serialVersionUID = 8853263386049109185L;
    private String fnr;
    private String origin;

    public SamboerIFamilieException(String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin);
        this.fnr = fnr;
        this.origin = origin;
    }

    public SamboerIFamilieException(Throwable cause, String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin, cause);
        this.fnr = fnr;
        this.origin = origin;
    }

    public SamboerIFamilieException(String message, String origin, String fnr) {
        super(message);
        this.fnr = fnr;
        this.origin = origin;
    }

    public SamboerIFamilieException(String message, Throwable cause, String origin, String fnr) {
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
