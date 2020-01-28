package no.nav.domain.pensjon.common.exception.tjenestepensjon;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class TomDatoForanFomDatoException extends ConsumerRecoverableException {

    private static final long serialVersionUID = -8809516208579270030L;
    private String origin;
    private String tom;
    private String fom;

    public TomDatoForanFomDatoException(String origin, String tom, String fom) {
        super("Tom " + tom + " is before Fom " + fom + ", source: " + origin);
        this.fom = fom;
        this.tom = tom;
        this.origin = origin;
    }

    public TomDatoForanFomDatoException(Throwable cause, String origin, String tom, String fom) {
        super("Tom " + tom + " is before Fom " + fom + ", source: " + origin, cause);
        this.fom = fom;
        this.tom = tom;
        this.origin = origin;
    }

    public TomDatoForanFomDatoException(String message, String origin, String tom, String fom) {
        super(message);
        this.fom = fom;
        this.tom = tom;
        this.origin = origin;
    }

    public TomDatoForanFomDatoException(String message, Throwable cause, String origin, String tom, String fom) {
        super(message, cause);
        this.fom = fom;
        this.tom = tom;
        this.origin = origin;
    }

    public String getFom() {
        return fom;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTom() {
        return tom;
    }
}
