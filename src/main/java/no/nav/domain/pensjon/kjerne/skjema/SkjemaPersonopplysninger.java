package no.nav.domain.pensjon.kjerne.skjema;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

//import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.kodetabeller.KanalBprofCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.KommunikasjonsformCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SprakCti;

import java.io.Serializable;

//@Entity
//@Table(name = "T_SKJEMA_PERS_OPPL")
//@NamedQueries({@NamedQuery(name = "SkjemaPersonopplysninger.findSkjemaPersonopplysningerByPenPerson", query = "select s from SkjemaPersonopplysninger s"
//        + " where s.penPerson = :penPerson")})
//public class SkjemaPersonopplysninger extends AbstractVersionedPersistentDomainObject {
public class SkjemaPersonopplysninger  implements Serializable {
    private static final long serialVersionUID = 5993997913751167715L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "skjema_pers_oppl", nullable = false)
    private Long skjemaPersonopplysningerId;

    //@Column(name = "fornavn", nullable = true)
    private String fornavn;

    //@Column(name = "mellomnavn", nullable = true)
    private String mellomnavn;

    //@Column(name = "etternavn", nullable = true)
    private String etternavn;

    //@Column(name = "bosted_adresse1", nullable = true)
    private String bostedsadresse1;

    //@Column(name = "bosted_adresse2", nullable = true)
    private String bostedsadresse2;

    //@Column(name = "bosted_adresse3", nullable = true)
    private String bostedsadresse3;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "bostedsland", nullable = true)
    private Land3TegnCti land;

    //@Column(name = "utvandret", nullable = true)
    private Boolean utvandret;

    //@Column(name = "adresse_epost", nullable = true)
    private String epost;

    //@Column(name = "tlf_nr_mob", nullable = true)
    private String telefonnummerMobil;

    //@Column(name = "tlf_nr_arb", nullable = true)
    private String telefonnummerArbeid;

    //@Column(name = "tlf_nr_hjemme", nullable = true)
    private String telefonnummerHjemme;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "K_KOMMSJN_FORM", nullable = true)
    private KommunikasjonsformCti kanalPreferanse;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_kanal_bprof_t", nullable = true)
    private KanalBprofCti varslingskanalForetrukket;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "valgt_malform", nullable = true)
    private SprakCti valgtMalform;

    //@Column(name = "kontonummer_norge", nullable = true)
    private String kontonummerNorge;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "statsborger_i_land", nullable = false)
    private Land3TegnCti statsborgerskap;

    //@Column(name = "flyktning", nullable = false)
    private Boolean flyktning;

    //SIR 34210: Changed Fetchtype to eager to avoid lazyInitializationException.
    //@ManyToOne(fetch = javax.persistence.FetchType.EAGER, cascade = CascadeType.REFRESH)
    //@JoinColumn(name = "person_id", nullable = false)
    private PenPerson penPerson;

    public boolean livesInNorway() {
        return !utvandret;
    }

    public boolean livesAbroad() {
        return !livesInNorway();
    }

    /**
     * @return the bostedsadresse1
     */
    public String getBostedsadresse1() {
        return bostedsadresse1;
    }

    /**
     * @param bostedsadresse1 the bostedsadresse1 to set
     */
    public void setBostedsadresse1(String bostedsadresse1) {
        this.bostedsadresse1 = bostedsadresse1;
    }

    /**
     * @return the bostedsadresse2
     */
    public String getBostedsadresse2() {
        return bostedsadresse2;
    }

    /**
     * @param bostedsadresse2 the bostedsadresse2 to set
     */
    public void setBostedsadresse2(String bostedsadresse2) {
        this.bostedsadresse2 = bostedsadresse2;
    }

    /**
     * @return the bostedsadresse3
     */
    public String getBostedsadresse3() {
        return bostedsadresse3;
    }

    /**
     * @param bostedsadresse3 the bostedsadresse3 to set
     */
    public void setBostedsadresse3(String bostedsadresse3) {
        this.bostedsadresse3 = bostedsadresse3;
    }

    /**
     * @return the epost
     */
    public String getEpost() {
        return epost;
    }

    /**
     * @param epost the epost to set
     */
    public void setEpost(String epost) {
        this.epost = epost;
    }

    /**
     * @return the etternavn
     */
    public String getEtternavn() {
        return etternavn;
    }

    /**
     * @param etternavn the etternavn to set
     */
    public void setEtternavn(String etternavn) {
        this.etternavn = etternavn;
    }

    /**
     * @return the flyktning
     */
    public Boolean getFlyktning() {
        return flyktning;
    }

    /**
     * @param flyktning the flyktning to set
     */
    public void setFlyktning(Boolean flyktning) {
        this.flyktning = flyktning;
    }

    /**
     * @return the fornavn
     */
    public String getFornavn() {
        return fornavn;
    }

    /**
     * @param fornavn the fornavn to set
     */
    public void setFornavn(String fornavn) {
        this.fornavn = fornavn;
    }

    /**
     * @return the kontonummerNorge
     */
    public String getKontonummerNorge() {
        return kontonummerNorge;
    }

    /**
     * @param kontonummerNorge the kontonummerNorge to set
     */
    public void setKontonummerNorge(String kontonummerNorge) {
        this.kontonummerNorge = kontonummerNorge;
    }

    /**
     * @return the mellomnavn
     */
    public String getMellomnavn() {
        return mellomnavn;
    }

    /**
     * @param mellomnavn the mellomnavn to set
     */
    public void setMellomnavn(String mellomnavn) {
        this.mellomnavn = mellomnavn;
    }

    /**
     * @return the skjemaPersonopplysningerId
     */
    public Long getSkjemaPersonopplysningerId() {
        return skjemaPersonopplysningerId;
    }

    /**
     * @param skjemaPersonopplysningerId the skjemaPersonopplysningerId to set
     */
    public void setSkjemaPersonopplysningerId(Long skjemaPersonopplysningerId) {
        this.skjemaPersonopplysningerId = skjemaPersonopplysningerId;
    }

    /**
     * @return the statsborgerskap
     */
    public Land3TegnCti getStatsborgerskap() {
        return statsborgerskap;
    }

    /**
     * @param statsborgerskap the statsborgerskap to set
     */
    public void setStatsborgerskap(Land3TegnCti statsborgerskap) {
        this.statsborgerskap = statsborgerskap;
    }

    /**
     * @return the valgtMalform
     */
    public SprakCti getValgtMalform() {
        return valgtMalform;
    }

    /**
     * @param valgtMalform the valgtMalform to set
     */
    public void setValgtMalform(SprakCti valgtMalform) {
        this.valgtMalform = valgtMalform;
    }

    public PenPerson getPenPerson() {
        return penPerson;
    }

    public void setPenPerson(PenPerson penPerson) {
        this.penPerson = penPerson;
    }

    /**
     * @return the land
     */
    public Land3TegnCti getLand() {
        return land;
    }

    /**
     * @param land the land to set
     */
    public void setLand(Land3TegnCti land) {
        this.land = land;
    }

    /**
     * @return the utvandret
     */
    public Boolean getUtvandret() {
        return utvandret;
    }

    /**
     * @param utvandret the utvandret to set
     */
    public void setUtvandret(Boolean utvandret) {
        this.utvandret = utvandret;
    }

    /**
     * @return the kanalPreferanse
     */
    public KommunikasjonsformCti getKanalPreferanse() {
        return kanalPreferanse;
    }

    /**
     * @param kanalPreferanse the kanalPreferanse to set
     */
    public void setKanalPreferanse(KommunikasjonsformCti kanalPreferanse) {
        this.kanalPreferanse = kanalPreferanse;
    }

    /**
     * @return the arbeidtelefonnummer
     */
    public String getTelefonnummerArbeid() {
        return telefonnummerArbeid;
    }

    /**
     * @param telefonnummerArbeid the arbeidtelefonnummer to set
     */
    public void setTelefonnummerArbeid(String telefonnummerArbeid) {
        this.telefonnummerArbeid = telefonnummerArbeid;
    }

    /**
     * @return the hjemmetelefonnummer
     */
    public String getTelefonnummerHjemme() {
        return telefonnummerHjemme;
    }

    /**
     * @param telefonnummerHjemme the hjemmetelefonnummer to set
     */
    public void setTelefonnummerHjemme(String telefonnummerHjemme) {
        this.telefonnummerHjemme = telefonnummerHjemme;
    }

    /**
     * @return the mobiltelefonnummer
     */
    public String getTelefonnummerMobil() {
        return telefonnummerMobil;
    }

    /**
     * @param telefonnummerMobil the mobiltelefonnummer to set
     */
    public void setTelefonnummerMobil(String telefonnummerMobil) {
        this.telefonnummerMobil = telefonnummerMobil;
    }

    /**
     * @return the foretrukket varslingskanal
     */
    public KanalBprofCti getVarslingskanalForetrukket() {
        return varslingskanalForetrukket;
    }

    /**
     * @param varslingskanalForetrukket the foretrukket varslingskanal to set
     */
    public void setVarslingskanalForetrukket(KanalBprofCti varslingskanalForetrukket) {
        this.varslingskanalForetrukket = varslingskanalForetrukket;
    }
}

