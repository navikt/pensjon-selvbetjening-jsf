package no.nav.domain.pensjon.common.exception.person;

import no.nav.domain.pensjon.common.exception.ConsumerRecoverableException;

public class DatoerStemmerIkkeMedRegistrertSamboerforholdException extends ConsumerRecoverableException {

    private static final long serialVersionUID = -1153263386049109185L;
    private String origin;
    private String tom;
    private String fom;

    public DatoerStemmerIkkeMedRegistrertSamboerforholdException(String origin, String tom, String fom) {
        super("Tom " + tom + " is before Fom " + fom + ", source: " + origin);
        this.origin = origin;
        this.fom = fom;
        this.tom = tom;
    }

    public DatoerStemmerIkkeMedRegistrertSamboerforholdException(Throwable cause, String origin, String tom, String fom) {
        super("Tom " + tom + " is before Fom " + fom + ", source: " + origin, cause);
        this.origin = origin;
        this.fom = fom;
        this.tom = tom;
    }

    public DatoerStemmerIkkeMedRegistrertSamboerforholdException(String message, String origin, String tom, String fom) {
        super(message);
        this.origin = origin;
        this.fom = fom;
        this.tom = tom;
    }

    public DatoerStemmerIkkeMedRegistrertSamboerforholdException(String message, Throwable cause, String origin, String tom, String fom) {
        super(message, cause);
        this.origin = origin;
        this.fom = fom;
        this.tom = tom;
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
