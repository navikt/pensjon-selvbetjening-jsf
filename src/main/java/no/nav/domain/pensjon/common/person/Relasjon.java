package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

import no.stelvio.common.util.DateUtil;

public class Relasjon implements Serializable {

    private static final long serialVersionUID = -4328058113126101719L;
    private String relasjonsType;
    private String adresseStatus;
    private Calendar fom;
    private Calendar tom;
    private Person person;

    public String getRelasjonsType() {
        return relasjonsType;
    }

    public void setRelasjonsType(String relasjonsType) {
        this.relasjonsType = relasjonsType;
    }

    public String getAdresseStatus() {
        return adresseStatus;
    }

    public void setAdresseStatus(String adresseStatus) {
        this.adresseStatus = adresseStatus;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }

    public Calendar getTom() {
        return tom;
    }

    public void setTom(Calendar tom) {
        this.tom = tom;
    }

    @Override
    public String toString() {
        return "Relasjon{" + relasjonsType +
                ", person=" + (person != null ? person.getFodselsnummer() : "") +
                ", fom=" + (fom != null ? DateUtil.format(fom.getTime()) : "") +
                ", tom=" + (tom != null ? DateUtil.format(tom.getTime()) : "") +
                '}';
    }
}
