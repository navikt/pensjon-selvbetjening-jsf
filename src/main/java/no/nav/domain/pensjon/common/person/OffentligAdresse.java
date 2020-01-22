package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class OffentligAdresse implements Serializable {

    private static final long serialVersionUID = 6698103491476556033L;
    private String gatenavn;
    private String husnr;
    private String bokstav;
    private String bolignr;

    public String getBokstav() {
        return bokstav;
    }

    public void setBokstav(String bokstav) {
        this.bokstav = bokstav;
    }

    public String getBolignr() {
        return bolignr;
    }

    public void setBolignr(String bolignr) {
        this.bolignr = bolignr;
    }

    public String getGatenavn() {
        return gatenavn;
    }

    public void setGatenavn(String gatenavn) {
        this.gatenavn = gatenavn;
    }

    public String getHusnr() {
        return husnr;
    }

    public void setHusnr(String husnr) {
        this.husnr = husnr;
    }
}
