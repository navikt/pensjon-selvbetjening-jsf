package no.nav.domain.pensjon.common.person;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import no.stelvio.domain.person.Pid;

public class Person implements Serializable {

    public static final String DISKRESJON_SPES_REG_KODE_6 = "SPSF"; // Sperret adresse, strengt fortrolig
    public static final String DISKRESJON_SPES_REG_KODE_7 = "SPFO"; // Sperret adresse, fortrolig
    private static final long serialVersionUID = 5161161234286123247L;
    private Pid pid;
    private String kortnavn;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String status;
    private String statusKode;
    private String diskresjonskode;
    private Calendar dodsdato;
    private Calendar umyndiggjortDato;
    private String sivilstand;
    private Calendar sivilstandDato;
    private String tlfPrivat;
    private String tlfJobb;
    private String tlfMobil;
    private String epost;
    private String navEnhet;
    private String sprakKode;
    private String sprakBeskrivelse;
    private Calendar sprakDatoFom;
    private Boolean erEgenansatt;
    private BostedsAdresse bostedsAdresse;
    private AnnenAdresse postAdresse;
    private AnnenAdresse tilleggsAdresse;
    private AnnenAdresse utenlandsAdresse;
    private Utbetalingsinformasjon utbetalingsinformasjon;
    private PersonUtland personUtland;
    private List<Relasjon> relasjoner;
    private Historikk historikk;
    private Brukerprofil brukerprofil;
    private Samboer samboer;

    public BostedsAdresse getBostedsAdresse() {
        return bostedsAdresse;
    }

    public boolean hasDiskresjon() {
        return diskresjonskode != null
                && (diskresjonskode.equals(DISKRESJON_SPES_REG_KODE_6) || diskresjonskode.equals(DISKRESJON_SPES_REG_KODE_7));
    }

    public String getDiskresjonskode() {
        return diskresjonskode;
    }

    public Calendar getDodsdato() {
        return dodsdato;
    }

    public String getEpost() {
        return epost;
    }

    public String getSprakKode() {
        return sprakKode;
    }

    public void setSprakKode(String sprakKode) {
        this.sprakKode = sprakKode;
    }

    public String getSprakBeskrivelse() {
        return sprakBeskrivelse;
    }

    public void setSprakBeskrivelse(String sprakBeskrivelse) {
        this.sprakBeskrivelse = sprakBeskrivelse;
    }

    public Calendar getSprakDatoFom() {
        return sprakDatoFom;
    }

    public void setSprakDatoFom(Calendar sprakDatoFom) {
        this.sprakDatoFom = sprakDatoFom;
    }

    public Boolean getErDod() {
        return dodsdato == null ? Boolean.FALSE : Boolean.TRUE;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public String getFornavn() {
        return fornavn;
    }

    public String getKortnavn() {
        return kortnavn;
    }

    public String getMellomnavn() {
        return mellomnavn;
    }

    public PersonUtland getPersonUtland() {
        return personUtland;
    }

    public AnnenAdresse getPostAdresse() {
        return postAdresse;
    }

    public List<Relasjon> getRelasjoner() {
        return relasjoner;
    }

    public Relasjon getRelasjoner(int i) {
        return relasjoner.get(i);
    }

    public String getSivilstand() {
        return sivilstand;
    }

    public Calendar getSivilstandDato() {
        return sivilstandDato;
    }

    public AnnenAdresse getTilleggsAdresse() {
        return tilleggsAdresse;
    }

    public String getTlfJobb() {
        return tlfJobb;
    }

    public String getTlfMobil() {
        return tlfMobil;
    }

    public String getTlfPrivat() {
        return tlfPrivat;
    }

    public Boolean getUmyndiggjort() {
        return umyndiggjortDato == null ? Boolean.FALSE : Boolean.TRUE;
    }

    public Calendar getUmyndiggjortDato() {
        return umyndiggjortDato;
    }

    public Utbetalingsinformasjon getUtbetalingsinformasjon() {
        return utbetalingsinformasjon;
    }

    public AnnenAdresse getUtenlandsAdresse() {
        return utenlandsAdresse;
    }

    public void setBostedsAdresse(BostedsAdresse bostedsAdresse) {
        this.bostedsAdresse = bostedsAdresse;
    }

    public void setDiskresjonskode(String diskresjonskode) {
        this.diskresjonskode = diskresjonskode;
    }

    public void setDodsdato(Calendar dodsdato) {
        this.dodsdato = dodsdato;
    }

    public void setEpost(String epost) {
        this.epost = epost;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public void setKortnavn(String kortnavn) {
        this.kortnavn = kortnavn;
    }

    public void setMellomnavn(String mellomnavn) {
        this.mellomnavn = mellomnavn;
    }

    public void setPersonUtland(PersonUtland personUtland) {
        this.personUtland = personUtland;
    }

    public void setPostAdresse(AnnenAdresse postAdresse) {
        this.postAdresse = postAdresse;
    }

    public void setRelasjoner(List<Relasjon> relasjoner) {
        this.relasjoner = relasjoner;
    }

    public void setSivilstand(String sivilstand) {
        this.sivilstand = sivilstand;
    }

    public void setSivilstandDato(Calendar sivilstandDato) {
        this.sivilstandDato = sivilstandDato;
    }

    public void setTilleggsAdresse(AnnenAdresse tilleggsAdresse) {
        this.tilleggsAdresse = tilleggsAdresse;
    }

    public void setTlfJobb(String tlfJobb) {
        this.tlfJobb = tlfJobb;
    }

    public void setTlfMobil(String tlfMobil) {
        this.tlfMobil = tlfMobil;
    }

    public void setTlfPrivat(String tlfPrivat) {
        this.tlfPrivat = tlfPrivat;
    }

    public void setUmyndiggjortDato(Calendar umyndiggjortDato) {
        this.umyndiggjortDato = umyndiggjortDato;
    }

    public void setUtbetalingsinformasjon(Utbetalingsinformasjon utbetaling) {
        utbetalingsinformasjon = utbetaling;
    }

    public void setUtenlandsAdresse(AnnenAdresse utenlandsAdresse) {
        this.utenlandsAdresse = utenlandsAdresse;
    }

    public Brukerprofil getBrukerprofil() {
        return brukerprofil;
    }

    public void setBrukerprofil(Brukerprofil brukerprofil) {
        this.brukerprofil = brukerprofil;
    }

    public Samboer getSamboer() {
        return samboer;
    }

    public void setSamboer(Samboer samboer) {
        this.samboer = samboer;
    }

    public Boolean getErEgenansatt() {
        return erEgenansatt;
    }

    public void setErEgenansatt(Boolean erEgenansatt) {
        this.erEgenansatt = erEgenansatt;
    }

    public String getFodselsnummer() {
        return pid == null ? null : pid.getPid();
    }

    public void setFodselsnummer(String fodselsnummer) {
        if (Pid.isValidPid(fodselsnummer, true)) {
            pid = new Pid(fodselsnummer, true);
        }
    }

    public Historikk getHistorikk() {
        return historikk;
    }

    public void setHistorikk(Historikk historikk) {
        this.historikk = historikk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusKode() {
        return statusKode;
    }

    public void setStatusKode(String statusKode) {
        this.statusKode = statusKode;
    }

    public Pid getPid() {
        return pid;
    }

    public void setPid(Pid pid) {
        this.pid = pid;
    }

    public String getNavEnhet() {
        return navEnhet;
    }

    public void setNavEnhet(String navEnhet) {
        this.navEnhet = navEnhet;
    }
}
