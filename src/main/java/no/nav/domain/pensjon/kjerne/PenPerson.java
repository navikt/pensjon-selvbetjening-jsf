//package no.nav.domain.pensjon.kjerne;
//
//import no.stelvio.domain.person.Pid;
//
//public class PenPerson {
//
//    public Pid getFnr() {
//        return new Pid("09038042332");
//    }
//
//    public void setFnr(Pid pid) {
//
//    }
//}
package no.nav.domain.pensjon.kjerne;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import static no.nav.domain.pensjon.common.util.DateCopyUtil.copyDate;

import java.io.Serializable;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

//import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.common.util.DomainUtils;
import no.nav.domain.pensjon.kjerne.grunnlag.InstOpphReduksjonsperiode;
import no.nav.domain.pensjon.kjerne.kodetabeller.KjonnCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.UforeTypeCti;
import no.nav.domain.pensjon.kjerne.leveattest.LeveattestHistorikk;
import no.nav.domain.pensjon.kjerne.vedtak.Afphistorikk;
import no.nav.domain.pensjon.kjerne.vedtak.GenerellHistorikk;
import no.nav.domain.pensjon.kjerne.vedtak.Uforehistorikk;
import no.nav.domain.pensjon.kjerne.vedtak.Uforeperiode;

//@Entity
//@Table(name = "T_PERSON")
//@NamedQueries({@NamedQuery(name = "PenPerson.findPersonByFnr", query = "select u from PenPerson u where u.fnr.pid = :fnr"),
//        @NamedQuery(name = "PenPerson.findPersonByPid", query = "select u from PenPerson u where u.fnr = :pid"),
//        @NamedQuery(name = "PenPerson.findPersonByAge", query = "select p from PenPerson p where substr(p.fnr.pid,3,4) = :mmyy67 or substr(p.fnr.pid,3,4) = :mmyy70")})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_WRITE, region = "semistaticdomaincache")
//public class PenPerson extends AbstractVersionedPersistentDomainObject {
public class PenPerson implements Serializable {
    private static final long serialVersionUID = -4577545331846329747L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "person_id")
    private Long penPersonId;

    private Pid fnr;

    // Actually OneToOne, but changed to OneToMany for performance reasons (CR49696). The exposed interface is still OneToOne.
    @OneToMany(mappedBy = "penPerson", fetch = javax.persistence.FetchType.LAZY, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Uforehistorikk> uforehistorikk = new ArrayList<Uforehistorikk>();

    // Actually OneToOne, but changed to OneToMany for performance reasons (CR49696). The exposed interface is still OneToOne.
    @OneToMany(mappedBy = "penPerson", fetch = javax.persistence.FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Afphistorikk> afphistorikk = new ArrayList<Afphistorikk>();

    // Actually OneToOne, but changed to OneToMany for performance reasons (CR49696). The exposed interface is still OneToOne.
    @OneToMany(mappedBy = "penPerson", fetch = javax.persistence.FetchType.LAZY, cascade = CascadeType.ALL)
    private List<GenerellHistorikk> generellHistorikk = new ArrayList<GenerellHistorikk>();

    /*
     * En liste med vurderte sivilstender gjort av saksbehandler
     */
    @OneToMany(mappedBy = "penPerson", fetch = javax.persistence.FetchType.LAZY, cascade = CascadeType.ALL)
    private List<VurdertSivilstand> vurdertSivilstandListe = new ArrayList<VurdertSivilstand>();

    @OneToMany(mappedBy = "penPerson", fetch = javax.persistence.FetchType.LAZY, cascade = CascadeType.ALL)
    private List<InstOpphReduksjonsperiode> instOpphReduksjonsperiodePenPersonListe = new ArrayList<InstOpphReduksjonsperiode>();

    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    @Column(name = "DATO_FODSEL", nullable = true)
    private Date fodselsDato;

    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    @Column(name = "DATO_DOD")
    private Date dodsDato;

    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    @Column(name = "DATO_UTVANDRET")
    private Date utvandretDato;

    @OneToMany(cascade = {CascadeType.ALL}, mappedBy = "penPerson", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<LeveattestHistorikk> leveattestHistorikkListe = new ArrayList<LeveattestHistorikk>();

    @ManyToOne
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "BOSTEDSLAND", nullable = true)
    private Land3TegnCti bostedsland;

    /**
     * Method that calculates the age of the person and checks if the user is born AFTER 31.12.1953 and BEFORE 01.01.1963
     *
     * @return boolean
     */
    public boolean erPersonRelevantForRegelverk2016() {
        Date today = Calendar.getInstance().getTime();
        Date fisrtDayOf1954 = DateUtil.createDate(1954, Calendar.JANUARY, 1);
        Date lastDayOf1962 = DateUtil.createDate(1962, Calendar.DECEMBER, 31);
        int age = Pid.calculateAge(fnr, today);
        return age < 67 && DateUtil.isDateInPeriod(fnr.getFodselsdato(), fisrtDayOf1954, lastDayOf1962);
    }

    /**
     * Method that calculates the age of the person and checks if the user is born AFTER 31.12.1962
     *
     * @return boolean
     */
    public boolean erPersonRelevantForRegelverk2025() {
        Date today = Calendar.getInstance().getTime();
        Date lastDayOf1962 = DateUtil.createDate(1962, Calendar.DECEMBER, 31);
        int age = Pid.calculateAge(fnr, today);
        return age < 67 && DateUtil.isAfterByDay(fnr.getFodselsdato(), lastDayOf1962, false);
    }

    /**
     * Method that calculates the age of the person and checks if the user is born BEFORE 01.01.1954
     *
     * @return boolean
     */
    public boolean erPersonRelevantForRegelverk2011() {
        Date today = Calendar.getInstance().getTime();
        Date fisrtDayOf1954 = DateUtil.createDate(1954, Calendar.JANUARY, 1);
        int age = Pid.calculateAge(fnr, today);

        return age < 67 && DateUtil.isBeforeByDay(fnr.getFodselsdato(), fisrtDayOf1954, false);
    }

    /**
     * EPEN071 hentRegelverkType Entitetstjeneste som returnerer regelverktype basert på brukers fødselsdato.
     *
     * @return regelverktype basert på brukers fødselsdato.
     */
    public RegelverkTypeCode hentRegelverkType() {
        int yearOfBirth = Pid.get4DigitYearOfBirth(fnr.getPid());

        if (yearOfBirth <= 1942) {
            return RegelverkTypeCode.G_REG;
        } else if (yearOfBirth >= 1943 && yearOfBirth <= 1953) {
            return RegelverkTypeCode.N_REG_G_OPPTJ;
        } else if (yearOfBirth >= 1954 && yearOfBirth <= 1962) {
            return RegelverkTypeCode.N_REG_G_N_OPPTJ;
        } else {
            return RegelverkTypeCode.N_REG_N_OPPTJ;
        }
    }

    /**
     * Default constructor
     */
    public PenPerson() {
    }

    /**
     * Initializing constructor
     *
     * @param penPersonId definerer om personen er persistert fra for
     */
    public PenPerson(Long penPersonId) {
        this.penPersonId = penPersonId;
    }

    public boolean erSammeSom(PenPerson person) {
        return penPersonId != null && penPersonId.equals(person.getPenPersonId());
    }

    /**
     * Returns all afphistorik where bruk = true;
     *
     * @return the afphistorikk
     */
    public List<Afphistorikk> getAllAfphistorikkIBruk() {
        List<Afphistorikk> historikk = new ArrayList<Afphistorikk>();

        for (Afphistorikk h : afphistorikk) {
            if (h.getBruk()) {
                historikk.add(h);
            }
        }

        return historikk;
    }

    /**
     * Get the newest AfpHistorikk based on lastChangedDate
     *
     * @return the afphistorikk
     */
    public Afphistorikk getAfphistorikk() {
        if (afphistorikk == null || afphistorikk.isEmpty()) {
            return null;
        }

        Afphistorikk afphistIBruk = null;

        for (Afphistorikk afp : afphistorikk) {
            if (Boolean.TRUE.equals(afp.getBruk())) {
                afphistIBruk = afp;
                break;
            }
        }

        return afphistIBruk;
        /*
         * Afphistorikk nyesteHistorikk = afphistorikk.get(0);
         * for (Afphistorikk afp : afphistorikk) {
         * if (afp.getChangeStamp() == null || afp.getChangeStamp().getUpdatedDate() == null) {
         * nyesteHistorikk = afp;
         * break;
         * } else {
         * if (nyesteHistorikk.getChangeStamp() != null
         * && nyesteHistorikk.getChangeStamp().getUpdatedDate() != null
         * && afp.getChangeStamp().getUpdatedDate().getTime() > nyesteHistorikk.getChangeStamp().getUpdatedDate()
         * .getTime()) {
         * nyesteHistorikk = afp;
         * }
         * }
         * }
         * return nyesteHistorikk;
         */
    }

    public Date getDodsDato() {
        return copyDate(dodsDato);
    }

    /**
     * Getter that returns pid of type Pid
     *
     * @return pid Pid
     */
    public Pid getFnr() {
        return fnr;
    }

    /**
     * Getter that returns fodselsdato of type Date
     *
     * @return fodseldato Date
     */
    public Date getFodselsDatoFromPenPersonOrPid() {
        if (fodselsDato != null) {
            return fodselsDato;
        } else {
            return fnr.getFodselsdato();
        }
    }

    /**
     * @return the penPersonId
     */
    public Long getPenPersonId() {
        return penPersonId;
    }

    /**
     * @return the uforehistorikk
     */
    public Uforehistorikk getUforehistorikk() {
        if (uforehistorikk == null || uforehistorikk.isEmpty()) {
            return null;
        }

        return uforehistorikk.get(0);
    }

    public Date getUtvandretDato() {
        return utvandretDato;
    }

    /**
     * Legg til afp historikk i afphistorikklisten. Get afpHistorikk vil alltid returnere det nyeste afphistorikkobjektet basert
     * på sist endret dato
     *
     * @param afphistorikk the afphistorikk to set
     */
    public void setAfphistorikk(Afphistorikk afphistorikk) {
        if (afphistorikk == null) {
            return;
        }

        // Logisk sletting av tidligere afphistorikk objekt
        for (Afphistorikk afph : this.afphistorikk) {
            afph.setBruk(false);
        }

        this.afphistorikk.add(afphistorikk);
        afphistorikk.setPenPerson(this);
        afphistorikk.setBruk(true);
    }

    /**
     * This method removes the Afphistorikk from the PenPerson object. NB: Kun logisk sletting: all historikk vil få bruk=false.
     */
    public void removeAfphistorikk() {
        if (afphistorikk != null && !afphistorikk.isEmpty()) {
            for (Afphistorikk afp : afphistorikk) {
                afp.setBruk(false);
            }
        }
    }

    public void setDodsDato(Date dodsDato) {
        this.dodsDato = copyDate(dodsDato);
    }

    /**
     * Set the fnr.
     *
     * @param pid The fnr to set.
     */
    public void setFnr(Pid pid) {
        fnr = pid;
    }

    /**
     * @param penPersonId the penPersonId to set
     */
    public void setPenPersonId(Long penPersonId) {
        this.penPersonId = penPersonId;
    }

    /**
     * @param uforehistorikk the uforehistorikk to set
     */
    public void setUforehistorikk(Uforehistorikk uforehistorikk) {
        DomainUtils.setOneToOneFieldOverwritePrevious(this.uforehistorikk, uforehistorikk);

        if (uforehistorikk != null) {
            uforehistorikk.setPenPerson(this);
        }
    }

    public void setUtvandretDato(Date utvandretDato) {
        this.utvandretDato = utvandretDato;
    }

    /**
     * Override default toString method
     *
     * @return The string.
     */
    @Override
    public String toString() {
        return "personId: " + penPersonId + " fnr: " + (fnr == null ? "null" : fnr.getPid());
    }

    /**
     * Bruk {@link #getFodselsDatoFromPenPersonOrPid()} for aa beregne fodselsdato fra pid (fnr)
     *
     * @return fodselsdato
     */
    public Date getFodselsDato() {
        return fodselsDato;
    }

    /**
     * @param fodselsDato the fodselsDato to set
     */
    public void setFodselsDato(Date fodselsDato) {
        this.fodselsDato = fodselsDato;
    }

    /**
     * @return the bostedsland
     */
    public Land3TegnCti getBostedsland() {
        return bostedsland;
    }

    /**
     * @param bostedsland the bostedsland to set
     */
    public void setBostedsland(Land3TegnCti bostedsland) {
        this.bostedsland = bostedsland;
    }

    /**
     * @return the generellHistorikk
     */
    public GenerellHistorikk getGenerellHistorikk() {
        if (generellHistorikk == null || generellHistorikk.isEmpty()) {
            return null;
        }

        return generellHistorikk.get(0);
    }

    /**
     * This method will throw a RuntimeException if you run it multiple times with different objects, i.e., you are only allowed
     * to alter existing objects, not to overwrite with a new object.
     *
     * @param generellHistorikk the generellHistorikk to set
     */
    public void setGenerellHistorikk(GenerellHistorikk generellHistorikk) {
        // If we already have a persistent object, we are only allowed to set the same object
        GenerellHistorikk oldGenerellHistorikk = getGenerellHistorikk();

        if (generellHistorikk != null) {
            if (oldGenerellHistorikk != null && oldGenerellHistorikk.getGenerellHistorikkId() != null
                    && !oldGenerellHistorikk.getGenerellHistorikkId().equals(generellHistorikk.getGenerellHistorikkId())) {
                throw new RuntimeException("Det finnes allerede en generellHistorikk registrert på person " + getPenPersonId()
                        + ". Det er kun lov å endre eksisterende generellHistorikk, det er ikke lov å overskrive med en ny.");
            }

            this.generellHistorikk.clear();
            this.generellHistorikk.add(generellHistorikk);
            generellHistorikk.setPenPerson(this);
        } else if (oldGenerellHistorikk != null && oldGenerellHistorikk.getGenerellHistorikkId() != null) {
            throw new RuntimeException("Det finnes allerede en generellHistorikk registrert på person " + getPenPersonId()
                    + ". Det er kun lov å endre eksisterende generellHistorikk, det er ikke lov å nullstille en eksisterende.");
        }
    }

    public void clearUforeHistorikk() {
        if (uforehistorikk != null) {
            uforehistorikk.clear();
        }
    }

    public void clearAFPHistorikk() {
        if (afphistorikk != null) {
            afphistorikk.clear();
        }
    }

    public void clearGenerellHistorikk() {
        if (generellHistorikk != null) {
            generellHistorikk.clear();
        }
    }

    /**
     * Checks if the fnr for this person equals the input.
     *
     * @param fnr fodselsnummer
     * @return <code>true</code> hvis samme fodselsnummer
     */
    public Boolean isPidEqualTo(Pid fnr) {
        Boolean result = Boolean.FALSE;

        if (this.fnr != null) {
            if (this.fnr.getPid() != null) {
                if (this.fnr.getPid().equalsIgnoreCase(fnr.getPid())) {
                    result = Boolean.TRUE;
                }
            }
        }

        return result;
    }

    /**
     * Do not manipulate List directly, use {@link #addLeveattestHistorikk(LeveattestHistorikk)}.
     */
    public List<LeveattestHistorikk> getLeveattestHistorikkListe() {
        return leveattestHistorikkListe;
    }

    /**
     * Do not manipulate List directly, use {@link #addLeveattestHistorikk(LeveattestHistorikk)}.
     */
    public void setLeveattestHistorikkListe(List<LeveattestHistorikk> leveattestHistorikkListe) {
        this.leveattestHistorikkListe = leveattestHistorikkListe;
    }

    public void addLeveattestHistorikk(LeveattestHistorikk leveattestHistorikk) {
        if (leveattestHistorikk != null) {
            getLeveattestHistorikkListe().add(leveattestHistorikk);
            leveattestHistorikk.setPenPerson(this);
        }
    }

    public void addInstOpphReduksjonsperiode(InstOpphReduksjonsperiode instOpphReduksjonsperiode) {
        if (instOpphReduksjonsperiode != null) {
            instOpphReduksjonsperiodePenPersonListe.add(instOpphReduksjonsperiode);
            instOpphReduksjonsperiode.setPenPerson(this);
        }
    }

    public void addVurdertSivilstand(VurdertSivilstand vurdertSivilstand) {
        if (vurdertSivilstand != null) {
            vurdertSivilstandListe.add(vurdertSivilstand);
            vurdertSivilstand.setPenPerson(this);
        }
    }

    public List<VurdertSivilstand> getVurdertSivilstandListe() {
        return vurdertSivilstandListe;
    }

    public void setVurdertSivilstandListe(List<VurdertSivilstand> vurdertSivilstandListe) {
        this.vurdertSivilstandListe.clear();
        if (isNotEmpty(vurdertSivilstandListe)) {
            for (VurdertSivilstand vurdertSivilstand : vurdertSivilstandListe) {
                addVurdertSivilstand(vurdertSivilstand);
            }
        }
    }

    public VurdertSivilstand getLatestVurdertSivilstand() {
        vurdertSivilstandListe.sort(new VurdertSivilstandComparator());
        return vurdertSivilstandListe.get(vurdertSivilstandListe.size() - 1);
    }

    public List<InstOpphReduksjonsperiode> getInstOpphReduksjonsperiodePenPersonListe() {
        return instOpphReduksjonsperiodePenPersonListe;
    }

    public void setInstOpphReduksjonsperiodePenPersonListe(List<InstOpphReduksjonsperiode> instOpphReduksjonsperiodePenPersonListe) {
        this.instOpphReduksjonsperiodePenPersonListe = instOpphReduksjonsperiodePenPersonListe;
    }

    /**
     * Helper inner class for sort of VurderSivilstand objects. Objects are sorted by field "fomDato".
     */
    private static class VurdertSivilstandComparator implements Comparator<VurdertSivilstand>, Serializable {
        private static final long serialVersionUID = -4874090763334813273L;

        @Override
        public int compare(VurdertSivilstand o1, VurdertSivilstand o2) {
            return o1.getFomDato().compareTo(o2.getFomDato());
        }
    }

    /**
     * EPEN097 hentUforeperiodeMedGyldigUforegradFor - Prøver å finne uføreperioden på uførehistorikken som har en av UforeTypene som sendes inn.
     * Vil returnere den første Uføreperioden som er gyldig på datoen, og som er en av UforeTypene.
     *
     * @param dato       Datoen Uføreperioden skal være gyldig på.
     * @param uforetyper De uføretypene man ønsker å velge blant. NB! Metoden returnere den første Uføreperioden den treffer.
     * @return Uføreperiode
     */
    public Uforeperiode hentUforeperiodeMedGyldigUforegradFor(Date dato, UforeTypeCti... uforetyper) {
        if (uforehistorikk == null || uforehistorikk.isEmpty()) {
            return null;
        }
        return uforehistorikk.get(0).hentUforeperiodeMedGyldigUforegradFor(dato, uforetyper);
    }

    /**
     * EPEN124 hentKjonn - returnerer KjonnCode som angir kjønnet til en Person. Hvis 9. siffer i fødselsnummeret er et
     * partall returneres KjonnCode.K ellers returneres KjonnCode.M
     */
    public KjonnCode hentKjonn() {
        String fnrString = getFnr().toString();
        int ninthDiginInPid = Character.getNumericValue(fnrString.charAt(8));
        return ninthDiginInPid % 2 == 0 ? KjonnCode.K : KjonnCode.M;
    }

    /**
     * EPEN141 returnerer datoen brukeren får aldersovergang fra UT til AP.
     *
     * @return 1. i mnd. som er 1 måned og 67 år etter brukeren ble født.
     */
    public Date hentDatoForAldersovergang67() {
        return new LocalDate(fodselsDato).plusYears(67).plusMonths(1).withDayOfMonth(1).toDate();
    }

    /**
     * @return den forste i maneden etter at personen fyller 62 ar.
     */
    public Date hentDatoForAldersovergang62() {
        return new LocalDate(fodselsDato).plusYears(62).plusMonths(1).withDayOfMonth(1).toDate();
    }

    public Integer calculateAgeAtDate(Date compareDate) {
        java.time.LocalDate convertedCompareDate = compareDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        java.time.LocalDate convertedFodselsdato = fodselsDato.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return Period.between(convertedFodselsdato, convertedCompareDate).getYears();
    }
}
