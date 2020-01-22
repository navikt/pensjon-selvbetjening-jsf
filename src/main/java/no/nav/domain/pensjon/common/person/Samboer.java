package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;

public class Samboer implements Serializable {

    private static final long serialVersionUID = 8904286122169034390L;
    private String fodselsnummer;
    private String kortnavn;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String diskresjonskode;
    private Calendar fomDato;
    private Calendar tomDato;
    private Calendar registrertFomDato;
    private Calendar registrertTomDato;
    private Calendar endretTidspunkt;
    private String endretAvSystem;
    private String endretAvSaksbehandler;

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
    }

    public String getKortnavn() {
        return kortnavn;
    }

    public void setKortnavn(String kortnavn) {
        this.kortnavn = kortnavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getMellomnavn() {
        return mellomnavn;
    }

    public void setMellomnavn(String mellomnavn) {
        this.mellomnavn = mellomnavn;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public String getDiskresjonskode() {
        return diskresjonskode;
    }

    public void setDiskresjonskode(String diskresjonskode) {
        this.diskresjonskode = diskresjonskode;
    }

    public Calendar getFomDato() {
        return fomDato;
    }

    public void setFomDato(Calendar fomDato) {
        this.fomDato = fomDato;
    }

    public Calendar getTomDato() {
        return tomDato;
    }

    public void setTomDato(Calendar tomDato) {
        this.tomDato = tomDato;
    }

    public Calendar getRegistrertFomDato() {
        return registrertFomDato;
    }

    public void setRegistrertFomDato(Calendar registrertFomDato) {
        this.registrertFomDato = registrertFomDato;
    }

    public Calendar getRegistrertTomDato() {
        return registrertTomDato;
    }

    public void setRegistrertTomDato(Calendar registrertTomDato) {
        this.registrertTomDato = registrertTomDato;
    }

    public Calendar getEndretTidspunkt() {
        return endretTidspunkt;
    }

    public void setEndretTidspunkt(Calendar endretTidspunkt) {
        this.endretTidspunkt = endretTidspunkt;
    }

    public String getEndretAvSystem() {
        return endretAvSystem;
    }

    public void setEndretAvSystem(String endretAvSystem) {
        this.endretAvSystem = endretAvSystem;
    }

    public String getEndretAvSaksbehandler() {
        return endretAvSaksbehandler;
    }

    public void setEndretAvSaksbehandler(String endretAvSaksbehandler) {
        this.endretAvSaksbehandler = endretAvSaksbehandler;
    }
}
