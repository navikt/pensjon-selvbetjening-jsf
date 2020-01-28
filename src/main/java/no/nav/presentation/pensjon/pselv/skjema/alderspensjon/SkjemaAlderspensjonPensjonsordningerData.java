package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.io.Serializable;
import java.util.Date;

public class SkjemaAlderspensjonPensjonsordningerData implements Serializable {

    private static final long serialVersionUID = -2586722705266336190L;
    private String navn;
    private Date fomDate;
    private Integer belop;

    public Integer getBelop() {
        return belop;
    }

    public void setBelop(Integer belop) {
        this.belop = belop;
    }

    public Date getFomDate() {
        return fomDate;
    }

    public void setFomDate(Date fomDate) {
        this.fomDate = fomDate;
    }

    public String getNavn() {
        return navn;
    }

    public void setNavn(String navn) {
        this.navn = navn;
    }
}
