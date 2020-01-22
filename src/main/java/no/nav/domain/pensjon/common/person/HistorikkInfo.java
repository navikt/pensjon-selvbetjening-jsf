package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

import no.nav.domain.pensjon.common.Endringsinfo;

public class HistorikkInfo implements Serializable {

    private static final long serialVersionUID = 4792176210616373334L;
    private String kode;
    private String beskrivelse;
    private Calendar fom;
    private Calendar tom;
    private Endringsinfo endringsInfo;

    public String getBeskrivelse() {
        return beskrivelse;
    }

    public void setBeskrivelse(String beskrivelse) {
        this.beskrivelse = beskrivelse;
    }

    public Endringsinfo getEndringsInfo() {
        return endringsInfo;
    }

    public void setEndringsInfo(Endringsinfo endringsInfo) {
        this.endringsInfo = endringsInfo;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    public Calendar getTom() {
        return tom;
    }

    public void setTom(Calendar tom) {
        this.tom = tom;
    }
}
