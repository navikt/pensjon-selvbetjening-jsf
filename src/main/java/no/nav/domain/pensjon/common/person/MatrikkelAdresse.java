package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class MatrikkelAdresse implements Serializable {

    private static final long serialVersionUID = 7172047221987923744L;
    private String mellomAdresse;
    private String gardsnr;
    private String bruksnr;
    private String festenr;
    private String undernr;

    public String getUndernr() {
        return undernr;
    }

    public void setUndernr(String undernr) {
        this.undernr = undernr;
    }

    public String getBruksnr() {
        return bruksnr;
    }

    public void setBruksnr(String bruksnr) {
        this.bruksnr = bruksnr;
    }

    public String getFestenr() {
        return festenr;
    }

    public void setFestenr(String festenr) {
        this.festenr = festenr;
    }

    public String getGardsnr() {
        return gardsnr;
    }

    public void setGardsnr(String gardsnr) {
        this.gardsnr = gardsnr;
    }

    public String getMellomAdresse() {
        return mellomAdresse;
    }

    public void setMellomAdresse(String mellomAdresse) {
        this.mellomAdresse = mellomAdresse;
    }
}
