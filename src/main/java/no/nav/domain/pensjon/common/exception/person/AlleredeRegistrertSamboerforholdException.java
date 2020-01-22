package no.nav.domain.pensjon.common.exception.person;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class AlleredeRegistrertSamboerforholdException extends ConsumerRecoverableException {

    private static final long serialVersionUID = 4675146572222381563L;
    private String fnr;
    private String origin;

    public AlleredeRegistrertSamboerforholdException(String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertSamboerforholdException(Throwable cause, String origin, String fnr) {
        super("Fnr " + fnr + " could not be found, source: " + origin, cause);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertSamboerforholdException(String message, String origin, String fnr) {
        super(message);
        this.fnr = fnr;
        this.origin = origin;
    }

    public AlleredeRegistrertSamboerforholdException(String message, Throwable cause, String origin, String fnr) {
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
