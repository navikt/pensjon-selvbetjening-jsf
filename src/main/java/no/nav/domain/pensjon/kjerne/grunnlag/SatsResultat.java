package no.nav.domain.pensjon.kjerne.grunnlag;

import java.io.Serializable;
import java.util.Date;

import no.stelvio.common.util.DateUtil;

public class SatsResultat implements Serializable {

    private static final long serialVersionUID = 4795355031074889659L;
    private Date fom;
    private Date tom;
    private double verdi;

    public Date getFom() {
        return fom;
    }

    public Date getTom() {
        return tom;
    }

    public double getVerdi() {
        return verdi;
    }

    public void setFom(Date fom) {
        this.fom = fom;
    }

    public void setTom(Date tom) {
        this.tom = tom;
    }

    public void setVerdi(double verdi) {
        this.verdi = verdi;
    }

    @Override
    public String toString() {
        return "SatsResultat{" +
                "fom=" + DateUtil.format(fom) +
                ", tom=" + (tom != null ? DateUtil.format(tom) : "") +
                ", verdi=" + verdi +
                '}';
    }
}
