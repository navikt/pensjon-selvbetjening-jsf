package no.nav.domain.pensjon.common.person;

import java.io.Serializable;

public class AdresseInfo implements Serializable {

    private static final long serialVersionUID = -6665982129250037674L;
    private String forespurtFnr;
    private String adresseKode;
    private TPSSvarStatus svarStatus;
    private Person person;

    public String getAdresseKode() {
        return adresseKode;
    }

    public void setAdresseKode(String adresseKode) {
        this.adresseKode = adresseKode;
    }

    public String getForespurtFnr() {
        return forespurtFnr;
    }

    public void setForespurtFnr(String forespurtFnr) {
        this.forespurtFnr = forespurtFnr;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public TPSSvarStatus getSvarStatus() {
        return svarStatus;
    }

    public void setSvarStatus(TPSSvarStatus svarStatus) {
        this.svarStatus = svarStatus;
    }
}
