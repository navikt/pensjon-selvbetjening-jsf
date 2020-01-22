package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Brukerprofil implements Serializable {

    private static final long serialVersionUID = -588994669500097522L;
    private String brukerprioritertAdresse;
    private String informasjonskanal;
    private String varsling;
    private String varslingskanal;
    private Calendar datoFom;
    private Calendar datoTom;
    private List<Enkeltbehov> behov;

    public String getBrukerprioritertAdresse() {
        return brukerprioritertAdresse;
    }

    public void setBrukerprioritertAdresse(String brukerprioritertAdresse) {
        this.brukerprioritertAdresse = brukerprioritertAdresse;
    }

    public String getInformasjonskanal() {
        return informasjonskanal;
    }

    public void setInformasjonskanal(String informasjonskanal) {
        this.informasjonskanal = informasjonskanal;
    }

    public String getVarsling() {
        return varsling;
    }

    public void setVarsling(String varsling) {
        this.varsling = varsling;
    }

    public String getVarslingskanal() {
        return varslingskanal;
    }

    public void setVarslingskanal(String varslingskanal) {
        this.varslingskanal = varslingskanal;
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

    public List<Enkeltbehov> getBehov() {
        return behov;
    }

    public void setBehov(List<Enkeltbehov> behov) {
        this.behov = behov;
    }
}
