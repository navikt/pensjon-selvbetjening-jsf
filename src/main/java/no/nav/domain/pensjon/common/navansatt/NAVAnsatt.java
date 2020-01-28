package no.nav.domain.pensjon.common.navansatt;

import java.io.Serializable;
import java.util.List;

import no.nav.domain.pensjon.common.Fagomrade;
import no.nav.domain.pensjon.common.navorgenhet.NAVEnhet;

public class NAVAnsatt implements Serializable {

    private static final long serialVersionUID = -3335543866295723482L;
    private String ansattId;
    private String ansattNavn;
    private String fornavn;
    private String etternavn;
    private List<NAVEnhet> enheter;
    private List<Fagomrade> fagomrader;

    public String getAnsattId() {
        return ansattId;
    }

    public void setAnsattId(String ansattId) {
        this.ansattId = ansattId;
    }

    public String getAnsattNavn() {
        return ansattNavn;
    }

    public void setAnsattNavn(String ansattNavn) {
        this.ansattNavn = ansattNavn;
    }

    public List<NAVEnhet> getEnheter() {
        return enheter;
    }

    public void setEnheter(List<NAVEnhet> enheter) {
        this.enheter = enheter;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(java.lang.String etternavn) {
        this.etternavn = etternavn;
    }

    public List<Fagomrade> getFagomrader() {
        return fagomrader;
    }

    public void setFagomrader(List<Fagomrade> fagomrader) {
        this.fagomrader = fagomrader;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }
}
