package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class NorskKonto implements Serializable {

    private static final long serialVersionUID = -7089883731362784256L;
    private String kontonummer;
    private String endretAv;
    private String endretAvSystem;
    private Calendar endretTidspunkt;

    public String getEndretAv() {
        return endretAv;
    }

    public void setEndretAv(String endretAv) {
        this.endretAv = endretAv;
    }

    public String getEndretAvSystem() {
        return endretAvSystem;
    }

    public void setEndretAvSystem(String endretAvSystem) {
        this.endretAvSystem = endretAvSystem;
    }

    public Calendar getEndretTidspunkt() {
        return endretTidspunkt;
    }

    public void setEndretTidspunkt(Calendar endretTidspunkt) {
        this.endretTidspunkt = endretTidspunkt;
    }

    public String getKontonummer() {
        return kontonummer;
    }

    public void setKontonummer(String kontonummer) {
        this.kontonummer = kontonummer;
    }
}
