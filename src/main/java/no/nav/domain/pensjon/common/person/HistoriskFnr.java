package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class HistoriskFnr implements Serializable {

    private static final long serialVersionUID = 2784794352224897429L;
    private String fodselsnummer;
    private Calendar fom;

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }
}
