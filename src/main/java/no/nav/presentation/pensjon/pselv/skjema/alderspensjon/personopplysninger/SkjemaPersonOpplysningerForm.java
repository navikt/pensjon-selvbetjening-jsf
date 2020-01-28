package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger;

import no.nav.domain.pensjon.common.person.AnnenAdresse;
import no.nav.domain.pensjon.common.person.BostedsAdresse;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCode;
import no.nav.presentation.pensjon.pselv.common.utils.AccountNumberUtil;
import no.nav.presentation.pensjon.pselv.common.utils.FnrUtil;
import no.nav.presentation.pensjon.pselv.common.utils.NameUtil;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaAlderspensjonCommonForm;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * Form used in SKS002 "SkjemaPersonOpplysninger".
 */
public class SkjemaPersonOpplysningerForm extends SkjemaAlderspensjonCommonForm {

    private static final long serialVersionUID = -2243141602530624656L;
    private static final String PAGE_TITLE = "pselv.sks002.skjermbildetittel.tittel";
    private static final String HEADER = "pselv.sks002.statisk_tekst.overskriftopplysninger";
    private String valgtSprakMalform;
    private String fnr;
    private String fornavn;
    private String mellomnavn;
    private String etternavn;
    private String telefonNummerHjem;
    private String telefonNummerArbeid;
    private boolean diskresjon;
    private String telefonNummerMob;
    private List<SelectItem> sprakMalformListe;
    private String kontonummerNorge;
    private String oppholdsLand;
    private boolean diskresjonkode7;
    private BostedsAdresse bostedAdresse;
    private String boadresse1;
    private AnnenAdresse utvandretAdresse;
    private List<SelectItem> landListe;
    private String statsborgerskap;
    private boolean norskStatsborger;
    private boolean highSecurityLogon;
    private Boolean flyktning = false;
    private boolean personUtVandret;
    private boolean kontonummerPrefilled;
    private Boolean harBostedAdresse;
    private Boolean harTilleggsAdresse;
    private AnnenAdresse tilleggsAdresse;
    private String feilmelding;
    private String tilleggsadresse1;

    public Boolean getHarBostedAdresse() {
        return harBostedAdresse;
    }

    public void setHarBostedAdresse(Boolean harBostedAdresse) {
        this.harBostedAdresse = harBostedAdresse;
    }

    public Boolean getHarTilleggsAdresse() {
        return harTilleggsAdresse;
    }

    public void setHarTilleggsAdresse(Boolean harTilleggsAdresse) {
        this.harTilleggsAdresse = harTilleggsAdresse;
    }

    public AnnenAdresse getTilleggsAdresse() {
        return tilleggsAdresse;
    }

    public void setTilleggsAdresse(AnnenAdresse tilleggsAdresse) {
        this.tilleggsAdresse = tilleggsAdresse;
    }

    @Override
    public boolean isShowDialogboks() {
        return flyktning;
    }

    public boolean isKontonummerPrefilled() {
        return kontonummerPrefilled;
    }

    public void setKontonummerPrefilled(boolean kontonummerPrefilled) {
        this.kontonummerPrefilled = kontonummerPrefilled;
    }

    public boolean isPersonUtVandret() {
        return personUtVandret;
    }

    public void setPersonUtVandret(boolean personUtVandret) {
        this.personUtVandret = personUtVandret;
    }

    public String getEtternavn() {
        return etternavn;
    }

    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    public Boolean getFlyktning() {
        return flyktning;
    }

    public void setFlyktning(Boolean flykning) {
        flyktning = flykning;
    }

    public String getFnr() {
        return new FnrUtil().getFormattedFnrNullSafe(fnr);
    }

    public void setFnr(String fnr) {
        this.fnr = fnr.trim();
    }

    public String getFornavn() {
        return fornavn;
    }

    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    public String getKontonummerNorge() {
        return new AccountNumberUtil().getFormattedAccountNumberNullSafe(kontonummerNorge);
    }

    public void setKontonummerNorge(String kontonummerNorge) {
        this.kontonummerNorge = kontonummerNorge.trim();
    }

    public String getMellomnavn() {
        return mellomnavn;
    }

    public void setMellomnavn(String mellomnavn) {
        this.mellomnavn = mellomnavn;
    }

    public String getOppholdsLand() {
        return oppholdsLand;
    }

    public void setOppholdsLand(String oppholdsLand) {
        this.oppholdsLand = oppholdsLand;
    }

    public BostedsAdresse getBostedAdresse() {
        return bostedAdresse;
    }

    /**
     * Returns the address, formatted. First letters are capital, the rest is lower case. I.e. Kjell Stubbs Vei 12
     */
    public String getBoadresse1() {
        if (bostedAdresse == null) {
            return boadresse1;
        }

        NameUtil nameUtil = new NameUtil();
        boadresse1 = nameUtil.formatName(bostedAdresse.getBoadresse1());
        return boadresse1;
    }

    public void setBostedAdresse(BostedsAdresse bostedAdresse) {
        this.bostedAdresse = bostedAdresse;
    }

    public boolean isNorskStatsborger() {
        return norskStatsborger;
    }

    public void setNorskStatsborger(boolean norskStatsborger) {
        this.norskStatsborger = norskStatsborger;
    }

    public String getStatsborgerskap() {
        return statsborgerskap;
    }

    /**
     * This method exists due to design requirements saying that the drop down menu in the SKS002 SkjemaPersonOpplysninger is to
     * have the default value of "velg land" if norway is pre-selected for citicenship. To make this posible the statsborgerskap
     * is checked for "NOR" and returns null if norway is pre selected. This way the default value is chosen in the screenshot.
     */
    public String getStatsborgerskap2() {
        return Land3TegnCode.NOR.name().equals(statsborgerskap) ? null : statsborgerskap;
    }

    public void setStatsborgerskap(String statsborgerskap) {
        this.statsborgerskap = statsborgerskap;
    }

    public void setStatsborgerskap2(String statsborgerskap) {
        this.statsborgerskap = statsborgerskap;
    }

    public String getFormatedPostAdress() {
        return bostedAdresse.getPostnummer() + " " + new NameUtil().formatName(bostedAdresse.getPoststed());
    }

    public String getTilleggAdrPostAdrFormated() {
        return tilleggsAdresse.getPostnr() + " " + new NameUtil().formatName(tilleggsAdresse.getPoststed());
    }

    public List<SelectItem> getSprakMalformListe() {
        return sprakMalformListe;
    }

    public void setSprakMalformListe(List<SelectItem> sprakMalformListe) {
        this.sprakMalformListe = sprakMalformListe;
    }

    public String getValgtSprakMalform() {
        return valgtSprakMalform;
    }

    public void setValgtSprakMalform(String valgtSprakMalform) {
        this.valgtSprakMalform = valgtSprakMalform;
    }

    public boolean isHighSecurityLogon() {
        return highSecurityLogon;
    }

    public void setHighSecurityLogon(boolean highSecurityLogon) {
        this.highSecurityLogon = highSecurityLogon;
    }

    public boolean isDiskresjon() {
        return getBruker().hasDiskresjon();
    }

    /**
     * This method "translates" the decression codes into numbers indication the discression level. Note that i only check for
     * SPFO. If discression is SPFO i return 7. all other values return 6. There should be only two levels, so this is ok, but
     * this code must be changed if a more fine graned discression levels is implemented.
     */
    public int getDiskresjonsKode() {
        return getBruker().getDiskresjonskode().equalsIgnoreCase("SPFO") ? 7 : 6;
    }

    public AnnenAdresse getUtvandretAdresse() {
        return utvandretAdresse;
    }

    public void setUtvandretAdresse(AnnenAdresse utvandretAdresse) {
        this.utvandretAdresse = utvandretAdresse;
    }

    public boolean isDiskresjonkode7() {
        return diskresjonkode7;
    }

    public void setDiskresjonkode7(boolean diskresjonkode7) {
        this.diskresjonkode7 = diskresjonkode7;
    }

    public void setLandListe(List<SelectItem> landListe) {
        this.landListe = landListe;
    }

    public List<SelectItem> getLandListe() {
        return landListe;
    }

    @Override
    public String getPageTitle() {
        return PAGE_TITLE;
    }

    @Override
    public String getSkjemaStepHeader() {
        return HEADER;
    }

    public String getTelefonNummerArbeid() {
        return telefonNummerArbeid;
    }

    public void setTelefonNummerArbeid(String telefonNummerArbeid) {
        this.telefonNummerArbeid = telefonNummerArbeid;
    }

    public String getTelefonNummerHjem() {
        return telefonNummerHjem;
    }

    public void setTelefonNummerHjem(String telefonNummerHjem) {
        this.telefonNummerHjem = telefonNummerHjem;
    }

    public String getTelefonNummerMob() {
        return telefonNummerMob;
    }

    public void setTelefonNummerMob(String telefonNummerMob) {
        this.telefonNummerMob = telefonNummerMob;
    }

    public void setDiskresjon(boolean diskresjon) {
        this.diskresjon = diskresjon;
    }

    public void setFeilmelding(String feilmelding) {
        this.feilmelding = feilmelding;
    }

    public String getFeilmelding() {
        return feilmelding;
    }

    public String getTilleggsadresse1() {
        if (tilleggsAdresse == null) {
            return tilleggsadresse1;
        }

        NameUtil nameUtil = new NameUtil();
        tilleggsadresse1 = nameUtil.formatName(tilleggsAdresse.getAdresselinje1());
        return tilleggsadresse1;
    }
}
