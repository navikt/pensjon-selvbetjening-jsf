package no.nav.domain.pensjon.common.brukerprofil;

import java.io.Serializable;
import java.util.List;

public class Epostmelding implements Serializable {

    private static final long serialVersionUID = 1776861536842941197L;
    private String fra;
    private List<Epostadresse> tilListe;
    private List<Epostadresse> kopiListe;
    private List<Epostadresse> blindkopiListe;
    private String emne;
    private String melding;
    private Boolean erHtml;

    public List<Epostadresse> getBlindkopiListe() {
        return blindkopiListe;
    }

    public void setBlindkopiListe(List<Epostadresse> blindkopiListe) {
        this.blindkopiListe = blindkopiListe;
    }

    public String getEmne() {
        return emne;
    }

    public void setEmne(String emne) {
        this.emne = emne;
    }

    public Boolean getErHtml() {
        return erHtml;
    }

    public void setErHtml(Boolean erHtml) {
        this.erHtml = erHtml;
    }

    public java.lang.String getFra() {
        return fra;
    }

    public void setFra(java.lang.String fra) {
        this.fra = fra;
    }

    public List<Epostadresse> getKopiListe() {
        return kopiListe;
    }

    public void setKopiListe(List<Epostadresse> kopiListe) {
        this.kopiListe = kopiListe;
    }

    public String getMelding() {
        return melding;
    }

    public void setMelding(String melding) {
        this.melding = melding;
    }

    public List<Epostadresse> getTilListe() {
        return tilListe;
    }

    public void setTilListe(List<Epostadresse> tilListe) {
        this.tilListe = tilListe;
    }

    public Epostmelding() {
    }
}
