package no.nav.domain.pensjon.common;

import java.io.Serializable;
import java.util.Calendar;

public class Endringsinfo implements Serializable {

    private static final long serialVersionUID = 173891182630411983L;
    private String endretAvId;
    private String endretAvNavn;
    private String endretAvEnhetId;
    private String endretAvEnhetNavn;
    private String opprettetAvId;
    private String opprettetAvEnhetId;
    private String opprettetAvEnhetNavn;
    private String opprettetAvNavn;
    private Calendar endretDato;
    private Calendar opprettetDato;
    private String kildeId;
    private String kildeNavn;

    public String getEndretAvEnhetId() {
        return endretAvEnhetId;
    }

    public void setEndretAvEnhetId(String endretAvEnhetId) {
        this.endretAvEnhetId = endretAvEnhetId;
    }

    public String getEndretAvEnhetNavn() {
        return endretAvEnhetNavn;
    }

    public void setEndretAvEnhetNavn(String endretAvEnhetNavn) {
        this.endretAvEnhetNavn = endretAvEnhetNavn;
    }

    public String getEndretAvId() {
        return endretAvId;
    }

    public void setEndretAvId(String endretAvId) {
        this.endretAvId = endretAvId;
    }

    public String getEndretAvNavn() {
        return endretAvNavn;
    }

    public void setEndretAvNavn(String endretAvNavn) {
        this.endretAvNavn = endretAvNavn;
    }

    public Calendar getEndretDato() {
        return endretDato;
    }

    public void setEndretDato(Calendar endretDato) {
        this.endretDato = endretDato;
    }

    public String getKildeId() {
        return kildeId;
    }

    public void setKildeId(String kildeId) {
        this.kildeId = kildeId;
    }

    public String getKildeNavn() {
        return kildeNavn;
    }

    public void setKildeNavn(String kildeNavn) {
        this.kildeNavn = kildeNavn;
    }

    public String getOpprettetAvEnhetId() {
        return opprettetAvEnhetId;
    }

    public void setOpprettetAvEnhetId(String opprettetAvEnhetId) {
        this.opprettetAvEnhetId = opprettetAvEnhetId;
    }

    public String getOpprettetAvEnhetNavn() {
        return opprettetAvEnhetNavn;
    }

    public void setOpprettetAvEnhetNavn(String opprettetAvEnhetNavn) {
        this.opprettetAvEnhetNavn = opprettetAvEnhetNavn;
    }

    public String getOpprettetAvId() {
        return opprettetAvId;
    }

    public void setOpprettetAvId(String opprettetAvId) {
        this.opprettetAvId = opprettetAvId;
    }

    public String getOpprettetAvNavn() {
        return opprettetAvNavn;
    }

    public void setOpprettetAvNavn(String opprettetAvNavn) {
        this.opprettetAvNavn = opprettetAvNavn;
    }

    public Calendar getOpprettetDato() {
        return opprettetDato;
    }

    public void setOpprettetDato(Calendar opprettetDato) {
        this.opprettetDato = opprettetDato;
    }
}
