package no.nav.domain.pensjon.common;

import java.io.Serializable;

public class Fagomrade implements Serializable {

    private static final long serialVersionUID = 4041594328916099241L;
    private String trekkgruppeKode;
    private String fagomradeKode;
    private String fagomradeBeskrivelse;
    private Boolean gyldig;
    private Endringsinfo endringsInfo;

    public Endringsinfo getEndringsInfo() {
        return endringsInfo;
    }

    public void setEndringsInfo(Endringsinfo endringsInfo) {
        this.endringsInfo = endringsInfo;
    }

    public String getFagomradeBeskrivelse() {
        return fagomradeBeskrivelse;
    }

    public void setFagomradeBeskrivelse(String fagomradeBeskrivelse) {
        this.fagomradeBeskrivelse = fagomradeBeskrivelse;
    }

    public String getFagomradeKode() {
        return fagomradeKode;
    }

    public void setFagomradeKode(String fagomradeKode) {
        this.fagomradeKode = fagomradeKode;
    }

    public Boolean getGyldig() {
        return gyldig;
    }

    public void setGyldig(Boolean gyldig) {
        this.gyldig = gyldig;
    }

    public String getTrekkgruppeKode() {
        return trekkgruppeKode;
    }

    public void setTrekkgruppeKode(String trekkgruppeKode) {
        this.trekkgruppeKode = trekkgruppeKode;
    }
}
