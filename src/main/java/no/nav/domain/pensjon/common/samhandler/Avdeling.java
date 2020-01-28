package no.nav.domain.pensjon.common.samhandler;

import java.io.Serializable;
import java.util.List;

import no.nav.domain.pensjon.common.navorgenhet.Adresse;

public class Avdeling implements Serializable {

    private static final long serialVersionUID = -119853864031056677L;
    private String idTSSEkstern;
    private String avdelingNavn;
    private String avdelingType;
    private String avdelingsnr;
    private String kontaktperson;
    private String ePost;
    private String telefon;
    private String mobil;
    private List<Konto> kontoer;
    private Adresse aAdresse;
    private Adresse pAdresse;
    private Adresse tAdresse;
    private Adresse uAdresse;

    public Adresse getAAdresse() {
        return aAdresse;
    }

    public void setAAdresse(Adresse adresse) {
        aAdresse = adresse;
    }

    public String getAvdelingNavn() {
        return avdelingNavn;
    }

    public void setAvdelingNavn(String avdelingNavn) {
        this.avdelingNavn = avdelingNavn;
    }

    public String getAvdelingsnr() {
        return avdelingsnr;
    }

    public void setAvdelingsnr(String avdelingsnr) {
        this.avdelingsnr = avdelingsnr;
    }

    public String getAvdelingType() {
        return avdelingType;
    }

    public void setAvdelingType(String avdelingType) {
        this.avdelingType = avdelingType;
    }

    public String getEPost() {
        return ePost;
    }

    public void setEPost(String post) {
        ePost = post;
    }

    public String getIdTSSEkstern() {
        return idTSSEkstern;
    }

    public void setIdTSSEkstern(String idTSSEkstern) {
        this.idTSSEkstern = idTSSEkstern;
    }

    public String getKontaktperson() {
        return kontaktperson;
    }

    public void setKontaktperson(String kontaktperson) {
        this.kontaktperson = kontaktperson;
    }

    public List<Konto> getKontoer() {
        return kontoer;
    }

    public void setKontoer(List<Konto> kontoer) {
        this.kontoer = kontoer;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public Adresse getPAdresse() {
        return pAdresse;
    }

    public void setPAdresse(Adresse adresse) {
        pAdresse = adresse;
    }

    public Adresse getTAdresse() {
        return tAdresse;
    }

    public void setTAdresse(Adresse adresse) {
        tAdresse = adresse;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Adresse getUAdresse() {
        return uAdresse;
    }

    public void setUAdresse(Adresse adresse) {
        uAdresse = adresse;
    }
}
