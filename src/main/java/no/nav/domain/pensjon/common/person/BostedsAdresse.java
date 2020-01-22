package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class BostedsAdresse implements Serializable {

    private static final long serialVersionUID = 6718062934384186070L;
    private String boadresse1;
    private String boadresse2;
    private String bolignr;
    private String postnummer;
    private String poststed;
    private String kommunenr;
    private String navEnhet;
    private String adresseType;
    private String adresseTypeBeskrivelse;
    private Calendar fom;
    private Calendar tom;
    private OffentligAdresse offentligAdresse;
    private MatrikkelAdresse matrikkelAdresse;

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

    public String getBoadresse1() {
        return boadresse1;
    }

    public void setBoadresse1(String boadresse1) {
        this.boadresse1 = boadresse1;
    }

    public String getBoadresse2() {
        return boadresse2;
    }

    public void setBoadresse2(String boadresse2) {
        this.boadresse2 = boadresse2;
    }

    public String getBolignr() {
        return bolignr;
    }

    public void setBolignr(String bolignr) {
        this.bolignr = bolignr;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }

    public String getKommunenr() {
        return kommunenr;
    }

    public void setKommunenr(String kommunenr) {
        this.kommunenr = kommunenr;
    }

    public MatrikkelAdresse getMatrikkelAdresse() {
        return matrikkelAdresse;
    }

    public void setMatrikkelAdresse(MatrikkelAdresse matrikkelAdresse) {
        this.matrikkelAdresse = matrikkelAdresse;
    }

    public String getNavEnhet() {
        return navEnhet;
    }

    public void setNavEnhet(String navEnhet) {
        this.navEnhet = navEnhet;
    }

    public OffentligAdresse getOffentligAdresse() {
        return offentligAdresse;
    }

    public void setOffentligAdresse(OffentligAdresse offentligAdresse) {
        this.offentligAdresse = offentligAdresse;
    }

    public String getPostnummer() {
        return postnummer;
    }

    public void setPostnummer(String postnummer) {
        this.postnummer = postnummer;
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
