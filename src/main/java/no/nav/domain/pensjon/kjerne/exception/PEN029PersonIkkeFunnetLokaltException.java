package no.nav.domain.pensjon.kjerne.exception;

import no.stelvio.common.error.FunctionalRecoverableException;
import no.stelvio.domain.person.Pid;

public class PEN029PersonIkkeFunnetLokaltException extends FunctionalRecoverableException {

    private static final long serialVersionUID = -6170065906050853382L;
    private String fnr;

    public PEN029PersonIkkeFunnetLokaltException(Pid pid) {
        super("Personen med fødselsnummer " + pid + " finnes ikke i den lokale oversikten over personer.");

        if (pid != null) {
            fnr = pid.getPid();
        }
    }

    public PEN029PersonIkkeFunnetLokaltException(Throwable cause, Pid pid) {
        super("Personen med fødselsnummer " + pid + " finnes ikke i den lokale oversikten over personer.", cause);

        if (pid != null) {
            fnr = pid.getPid();
        }
    }

    public PEN029PersonIkkeFunnetLokaltException(String fnr) {
        super("Personen med fødselsnummer " + fnr + " finnes ikke i den lokale oversikten over personer.");
        this.fnr = fnr;
    }

    public PEN029PersonIkkeFunnetLokaltException(Throwable cause, String fnr) {
        super("Personen med fødselsnummer " + fnr + " finnes ikke i den lokale oversikten over personer.", cause);
        this.fnr = fnr;
    }

    public PEN029PersonIkkeFunnetLokaltException(String msg, String fnr) {
        super(msg);
        this.fnr = fnr;
    }

    public PEN029PersonIkkeFunnetLokaltException(String msg, Throwable cause, String fnr) {
        super(msg, cause);
        this.fnr = fnr;
    }

    public String getFnr() {
        return fnr;
    }
}
