package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class AnnenAdresse implements Serializable {

    private static final long serialVersionUID = 8635623741647829464L;
    private String adresselinje1;
    private String adresselinje2;
    private String adresselinje3;
    private String postnr;
    private String poststed;
    private String landkode;
    private String land;
    private Calendar fom;
    private Calendar tom;
    private String adresseType;
    private String adresseTypeBeskrivelse;

    public String getAdresselinje1() {
        return adresselinje1;
    }

    public void setAdresselinje1(String adresselinje1) {
        this.adresselinje1 = adresselinje1;
    }

    public String getAdresselinje2() {
        return adresselinje2;
    }

    public void setAdresselinje2(String adresselinje2) {
        this.adresselinje2 = adresselinje2;
    }

    public String getAdresselinje3() {
        return adresselinje3;
    }

    public void setAdresselinje3(String adresselinje3) {
        this.adresselinje3 = adresselinje3;
    }

    public String getAdresseType() {
        return adresseType;
    }

    public void setAdresseType(String adresseType) {
        this.adresseType = adresseType;
    }

    public String getAdresseTypeBeskrivelse() {
        return adresseTypeBeskrivelse;
    }

    public void setAdresseTypeBeskrivelse(String adresseTypeBeskrivelse) {
        this.adresseTypeBeskrivelse = adresseTypeBeskrivelse;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }

    public String getLand() {
        return land;
    }

    public void setLand(String land) {
        this.land = land;
    }

    public String getLandkode() {
        return landkode;
    }

    public void setLandkode(String landkode) {
        this.landkode = landkode;
    }

    public String getPostnr() {
        return postnr;
    }

    public void setPostnr(String postnr) {
        this.postnr = postnr;
    }

    public String getPoststed() {
        return poststed;
    }

    public void setPoststed(String poststed) {
        this.poststed = poststed;
    }

    public Calendar getTom() {
        return tom;
    }

    public void setTom(Calendar tom) {
        this.tom = tom;
    }
}
