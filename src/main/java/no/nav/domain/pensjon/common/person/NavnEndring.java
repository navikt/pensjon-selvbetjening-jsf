package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class NavnEndring implements Serializable {

    private static final long serialVersionUID = 6792867649060477291L;
    private Calendar datoRegistrert;
    private String kortnavn;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String tidligereNavn;

    public Calendar getDatoRegistrert() {
        return datoRegistrert;
    }

    public void setDatoRegistrert(Calendar datoRegistrert) {
        this.datoRegistrert = datoRegistrert;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getKortnavn() {
        return kortnavn;
    }

    public void setKortnavn(String kortnavn) {
        this.kortnavn = kortnavn;
    }

    public String getMellomnavn() {
        return mellomnavn;
    }

    public void setMellomnavn(String mellomnavn) {
        this.mellomnavn = mellomnavn;
    }

    public String getTidligereNavn() {
        return tidligereNavn;
    }

    public void setTidligereNavn(String tidligereNavn) {
        this.tidligereNavn = tidligereNavn;
    }
}
