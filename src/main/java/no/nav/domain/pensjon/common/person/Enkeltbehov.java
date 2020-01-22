package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class Enkeltbehov implements Serializable {

    private static final long serialVersionUID = 7616027903430731189L;
    private String behov;
    private String behovsBeskrivelse;
    private Calendar datoFom;
    private Calendar datoTom;

    public String getBehov() {
        return behov;
    }

    public void setBehov(String behov) {
        this.behov = behov;
    }

    public String getBehovsBeskrivelse() {
        return behovsBeskrivelse;
    }

    public void setBehovsBeskrivelse(String behovsBeskrivelse) {
        this.behovsBeskrivelse = behovsBeskrivelse;
    }

    public Calendar getDatoFom() {
        return datoFom;
    }

    public void setDatoFom(Calendar datoFom) {
        this.datoFom = datoFom;
    }

    public Calendar getDatoTom() {
        return datoTom;
    }

    public void setDatoTom(Calendar datoTom) {
        this.datoTom = datoTom;
    }
}
