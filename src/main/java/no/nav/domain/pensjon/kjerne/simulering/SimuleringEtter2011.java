package no.nav.domain.pensjon.kjerne.simulering;

import java.util.Date;

import javax.persistence.JoinColumn;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import no.stelvio.domain.person.Pid;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
//import no.nav.domain.pensjon.kjerne.kodetabeller.AfpOrdningTypeCti;
//import no.nav.domain.pensjon.kjerne.kodetabeller.AnsattTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SimuleringTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstatusTypeCti;
//import no.nav.domain.pensjon.kjerne.kodetabeller.StillingsprOffCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.UttaksgradCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.UttaksgradCti;

/**
 * SimuleringEtter2011 benyttes som input til simuleringstjenestene som ble laget i Horisonten.
 * Det benyttes også til lagring av den brukeroppgitte inputen for en simulering,
 * slik at det er mulig å gjenskape simuleringen senere.
 *
 */
//@Entity
//@Table(name = "T_SIMULERING_2011")
//@NamedQueries({@NamedQuery(name = "SimuleringEtter2011.orderedFindSimuleringByPid", query = "select u from SimuleringEtter2011 u where u.fnr = :pid "
//        + "order by u.changeStamp.createdDate desc")})
public class SimuleringEtter2011 extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = 5487965547385712672L;

    /**
     * Unik identifikasjon av en SimuleringEtter2011
     */
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "SIMULERING_2011_ID", nullable = false)
    private Long simuleringId;

    /**
     * Angir hva som er simulert i den lagrede simuleringen. Se /1/ ark K_SIMULERING_T for gyldige typer.
     */
    //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "K_SIMULERING_T", nullable = true)
    private SimuleringTypeCti simuleringType;

    /**
     * Brukeroppgitt navn ved lagring. Benyttes ikke ved simulering, kun ved lagring/henting.
     */
    //@Column(name = "SIMULERING_NAVN", nullable = true)
    private String simuleringNavn;

    /**
     * Tidspunktet simuleringen ble lagret på. Benyttes ikke ved simulering, kun ved lagring/henting.
     */
    //@Column(name = "DATO_LAGRING", nullable = true)
//    @Temporal(TemporalType.DATE)
    private Date lagringstidspunkt;

    /**
     * Fødselsnummer for personen som har denne simuleringen
     */
//    @Embedded
//    @AttributeOverride(name = "pid", column = @Column(name = "FNR", nullable = true))
    private Pid fnr;

    /**
     * Avdødes fødselsnummer. Benyttes ved simulering av gjenlevenderett.
     */
//    @Embedded
//    @AttributeOverride(name = "pid", column = @Column(name = "FNR_AVDOD", nullable = true))
    private Pid fnrAvdod;

    /**
     * Brukers fødselsår. Benyttes dersom bruker ikke er innlogget.
     */
    //@Column(name = "FODSELSAR", nullable = true)
    private Integer fodselsar;

    /**
     * Brukeroppgitt ansettelsessektor. Benyttes ikke ved simulering, kun ved lagring/henting.
     * Se /1/ ark K_ANSATT_T for gyldige typer.
     */
//    //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "K_ANSATT_T", nullable = true)
//    private AnsattTypeCti ansettelsessektor;

    /**
     * Brukeroppgitt rett til AFP i offentlig sektor. Benyttes ikke ved simulering, kun ved lagring/henting.
     */
    //@Column(name = "OFFENTLIG_AFP_RETT", nullable = true)
    private Boolean offentligAfpRett;

    /**
     * Brukeroppgitt rett til AFP i privat sektor. Benyttes ikke ved simulering, kun ved lagring/henting.
     */
    //@Column(name = "PRIVAT_AFP_RETT", nullable = true)
    private Boolean privatAfpRett;

    /**
     * Brukeroppgitt valg av simuleringstype for bruker med rett til AFP i offentlig sektor.
     * Benyttes ikke ved simulering, kun ved lagring/henting.
     */
    //@Column(name = "SIMVALG_OFF_AFP", nullable = true)
    private Boolean simuleringsvalgOffentligAfp;

    /**
     * Samtykke til å hente inn informasjon fra tjenestepensjonsordninger Nav har elektronisk grensesnitt mot.
     * TRUE dersom bruker eller veileder (med godkjenning fra bruker) har gitt samtykke.
     */
    //@Column(name = "SAMTYKKE", nullable = true)
    private Boolean samtykke;

    /**
     * For innlogget bruker: Brukers forventede årlige inntekt fra dagens dato og fram til første uttak av pensjon.
     * For ikke innlogget bruker: Brukers gjennomsnittlige årlig inntekt som yrkesaktiv fram til første uttak av pensjon.
     */
    //@Column(name = "FORVENTET_INTEKT", nullable = true)
    private Integer forventetInntekt;

    /**
     * Antall år bruker har hatt over 1 G i årlig pensjonsgivende inntekt som yrkesaktiv fram til første uttak av pensjon.
     * NB! Benyttes kun for ikke innlogget bruker.
     */
    //@Column(name = "ANT_AR_INNT_OVER_G", nullable = true)
    private Integer antArInntektOverG;

    /**
     * Dato for første uttak av pensjon.
     */
    //@Column(name = "FORSTE_UTTAK_DATO", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date forsteUttakDato;

    /**
     * Uttaksgrad på alderspensjon fra første uttak av pensjon. Se /1/ ark K_UTTAKSGRAD for gyldige typer.
     */
    //@ManyToOne
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_UTTAKSGRAD", nullable = true)
    private UttaksgradCti utg;

    /**
     * Brukeroppgitt årlig inntekt fra første uttak av pensjon fram til endring til 100 % pensjon.
     * Benyttes ikke dersom bruker ikke tar ut gradert pensjon.
     */
    //@Column(name = "INNT_UNDER_GRAD_UT", nullable = true)
    private Integer inntektUnderGradertUttak;

    /**
     * Dato for endring av uttaksgrad til 100 %. Benyttes ikke dersom bruker ikke tar ut gradert pensjon.
     */
    //@Column(name = "HELT_UTTAK_DATO", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date heltUttakDato;

    /**
     * Brukeroppgitt årlig inntekt fra uttak av 100 % alderspensjon
     */
    //@Column(name = "INNT_ETTER_HELT_UT", nullable = true)
    private Integer inntektEtterHeltUttak;

    /**
     * Antall år fra heltUttakDato der bruker mottar inntektEtterHeltUttak i årlig inntekt
     */
    //@Column(name = "ANT_AR_INNT_ETTER_HU", nullable = true)
    private Integer antallArInntektEtterHeltUttak;

    /**
     * Antall år bruker har bodd i utlandet etter fylte 16 år
     */
    //@Column(name = "UTLAND_OPPHOLD", nullable = true)
    private Integer utenlandsopphold;

    /**
     * Angir om bruker har flyktningstatus. Kan kun settes av interne brukere.
     */
    //@Column(name = "FLYKTNING", nullable = true)
    private Boolean flyktning;

    /**
     * Brukers sivilstatus under uttak av pensjon. Se /1/ ark K_SIVILSTATUS_T for gyldige typer.
     */
    //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "K_SIVILSTATUS_T", nullable = true)
    private SivilstatusTypeCti sivilstatus;

    /**
     * Angir om brukerens EPS mottar pensjon fra folketrygden eller AFP.
     */
    //@Column(name = "EPS_PENSJON", nullable = true)
    private Boolean epsPensjon;

    /**
     * Angir om brukers EPS har inntekt (inkludert kapitalinntekt) over 2 G
     */
    //@Column(name = "EPS_2G", nullable = true)
    private Boolean eps2G;

    /**
     * Angir hvilken AFP-ordning bruker er tilknyttet.
     * Se /1/ ark K_AFP_T for gyldige typer. Benyttes kun for simulering av AFP i offentlig sektor.
     */
      //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "K_AFP_T", nullable = true)
//    private AfpOrdningTypeCti afpOrdning;

    /**
     * Angir brukers inntekt måneden før uttak av AFP. Benyttes kun for simulering av AFP i offentlig sektor.
     */
    //@Column(name = "AFP_INNT_MND_UTTAK", nullable = true)
    private Integer afpInntektMndForUttak;

    /**
     * Brukers egenregistrerte tjenestepensjoner. NB! Samordnes ikke gjennom elektronisk grensesnitt.
     * Benyttes ikke ved simulering, kun ved lagring/henting, men vises i skjermbildet.
     */
//    @OneToMany(mappedBy = "simuleringEtter2011", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    private List<BrukerRegTjenestepensjon> brukerRegTPListe = new ArrayList<BrukerRegTjenestepensjon>();

    /**
     * Offentlig stillingsprosent når bruker har 100% uttak. Vises kun dersom bruker er offentlig ansatt og har valgt gradert første uttak.
     * TP ordningene og TPEN630 trenger denne for å beregne offentlig TP.
     * Feltet brukes ikke til beregning av folketrygd.
     * Verdier: 0,10,20,30,40,50,60,70,75,80,90,100. Se K_STILLINGPR_OFF
     */
    //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "STILLINGPR_OFF_HELT_UTTAK", nullable = true)
//    private StillingsprOffCti stillingsprosentOffHeltUttak;

    /**
     * Offentlig stillingsprosent når bruker har gradert uttak.
     * TP ordningene og TPEN630 trenger denne for å beregne offentlig TP.
     * Feltet brukes ikke til beregning av folketrygd.
     * Verdier: 0,10,20,30,40,50,60,70,75,80,90,100. Se K_STILLINGPR_OFF
     */
    //@ManyToOne
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "STILLINGPR_OFF_GRADERT_UTTAK", nullable = true)
//    private StillingsprOffCti stillingsprosentOffGradertUttak;

    /**
     * Dato for ektefelles dødsfall
     */
    //@Column(name = "DODSDATO", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date dodsdato;

    /**
     * Antall år i utlandet for avdød e/p/s
     */
    //@Column(name = "AVDOD_ANT_AR_UTLAND", nullable = true)
    private Integer avdodAntallArIUtlandet;

    /**
     * Inntekten til avdød e/p/s året før dødsfall
     */
    //@Column(name = "AVDOD_INNT_FOR_DOD", nullable = true)
    private Integer avdodInntektForDod;

    /**
     * Om avdød e/p/s hadde inntekt over 1G
     */
    //@Column(name = "INNT_AVDOD_OVER_1G", nullable = true)
    private Boolean inntektAvdodOver1G;

    /**
     * Om avdød e/p/s var medlem av folketrygden
     */
    //@Column(name = "AVDOD_MEDLEM_FTRYGD", nullable = true)
    private Boolean avdodMedlemAvFolketrygden;

    /**
     * Om avdød e/p/s var flyktning
     */
    //@Column(name = "AVDOD_FLYKTNING", nullable = true)
    private Boolean avdodFlyktning;

    /**
     * Flagg som forteller om simuleringstjenesten kalles for simulering av input til en simulering av tjenestepensjon.
     * Benyttes kun for personer på 2025-regelverk (regelverk lik N_REG_N_OPPTJ).
     */
    //@Column(name = "SIMULER_FOR_TP", nullable = true)
    private Boolean simulerForTp;

    /**
     * List of utenlandsperioder used in simulering.
     */
//    @OneToMany(mappedBy = "simuleringEtter2011", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    private List<UtenlandsperiodeForSimulering> utenlandsperiodeForSimuleringList = new ArrayList<>();

    /**
     * List of fremtidigInntekt used in simulering.
     */
//    @OneToMany(mappedBy = "simuleringEtter2011", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    private List<FremtidigInntekt> fremtidigInntektList;

    /**
     * Default constructor.
     */
    public SimuleringEtter2011() {
        super();
    }

    /**
     * Copy constructor. All persistant fields except simuleringNavn and lagringstidspunkt are copied.
     *
     * @param orig the {@link SimuleringEtter2011} to use as basis
     */
    public SimuleringEtter2011(SimuleringEtter2011 orig) {
        super();

        setSimuleringType(orig.getSimuleringType());
        setFnr(orig.getFnr());
        setFnrAvdod(orig.getFnrAvdod());
        setFodselsar(orig.getFodselsar());
//        setAnsettelsessektor(orig.getAnsettelsessektor());
        setOffentligAfpRett(orig.getOffentligAfpRett());
        setPrivatAfpRett(orig.getPrivatAfpRett());
        setSimuleringsvalgOffentligAfp(orig.getSimuleringsvalgOffentligAfp());
        setSamtykke(orig.getSamtykke());
        setForventetInntekt(orig.getForventetInntekt());
//        setAntArInntektOverG(orig.getAntArInntektOverG());
        setForsteUttakDato(orig.getForsteUttakDato() == null ? null : (Date) orig.getForsteUttakDato().clone());
        setUtg(orig.getUtg());
        setInntektUnderGradertUttak(orig.getInntektUnderGradertUttak());
        setHeltUttakDato(orig.getHeltUttakDato() == null ? null : (Date) orig.getHeltUttakDato().clone());
        setInntektEtterHeltUttak(orig.getInntektEtterHeltUttak());
        setAntallArInntektEtterHeltUttak(orig.getAntallArInntektEtterHeltUttak());
        setUtenlandsopphold(orig.getUtenlandsopphold());
        setFlyktning(orig.getFlyktning());
        setSivilstatus(orig.getSivilstatus());
        setEpsPensjon(orig.getEpsPensjon());
        setEps2G(orig.getEps2G());
//        setAfpOrdning(orig.getAfpOrdning());
        setAfpInntektMndForUttak(orig.getAfpInntektMndForUttak());
//        setStillingsprosentOffHeltUttak(orig.getStillingsprosentOffHeltUttak());
        setDodsdato(orig.getDodsdato() == null ? null : (Date) orig.getDodsdato().clone());
        setAvdodAntallArIUtlandet(orig.getAvdodAntallArIUtlandet());
        setAvdodInntektForDod(orig.getAvdodInntektForDod());
        setInntektAvdodOver1G(orig.getInntektAvdodOver1G());
        setAvdodMedlemAvFolketrygden(orig.getAvdodMedlemAvFolketrygden());
        setAvdodFlyktning(orig.getAvdodFlyktning());
        setSimulerForTp(orig.getSimulerForTp());
//        setStillingsprosentOffGradertUttak(orig.getStillingsprosentOffGradertUttak());

//        for (BrukerRegTjenestepensjon tp : orig.getBrukerRegTPListe()) {
//            addBrukerRegTjenestepensjon(new BrukerRegTjenestepensjon(tp));
//        }
//
//        for (UtenlandsperiodeForSimulering utenlandsperiodeForSimulering : orig.getUtenlandsperiodeForSimuleringList()) {
//            addUtenlandsperiodeForSimulering(utenlandsperiodeForSimulering);
//        }
//
//        if (orig.getFremtidigInntektList() != null) {
//            for (FremtidigInntekt fremtidigInntekt : orig.getFremtidigInntektList()) {
//                addFremtidigInntekt(fremtidigInntekt);
//            }
//        }
    }

    /**
     * @return the afpInntektMndForUttak
     */
    public Integer getAfpInntektMndForUttak() {
        return afpInntektMndForUttak;
    }

    /**
     * @param afpInntektMndForUttak the afpInntektMndForUttak to set
     */
    public void setAfpInntektMndForUttak(Integer afpInntektMndForUttak) {
        this.afpInntektMndForUttak = afpInntektMndForUttak;
    }

    /**
     * @return the afpOrdning
     */
//    public AfpOrdningTypeCti getAfpOrdning() {
//        return afpOrdning;
//    }

    /**
     * @param afpOrdning the afpOrdning to set
     */
//    public void setAfpOrdning(AfpOrdningTypeCti afpOrdning) {
//        this.afpOrdning = afpOrdning;
//    }

//    /**
//     * @return the ansettelsessektor
//     */
//    public AnsattTypeCti getAnsettelsessektor() {
//        return ansettelsessektor;
//    }
//
//    /**
//     * @param ansettelsessektor the ansettelsessektor to set
//     */
//    public void setAnsettelsessektor(AnsattTypeCti ansettelsessektor) {
//        this.ansettelsessektor = ansettelsessektor;
//    }

    /**
     * @return the antallArInntektEtterHeltUttak
     */
    public Integer getAntallArInntektEtterHeltUttak() {
        return antallArInntektEtterHeltUttak;
    }

    /**
     * @param antallArInntektEtterHeltUttak the antallArInntektEtterHeltUttak to set
     */
    public void setAntallArInntektEtterHeltUttak(Integer antallArInntektEtterHeltUttak) {
        this.antallArInntektEtterHeltUttak = antallArInntektEtterHeltUttak;
    }

//    /**
//     * @return the antArInntektOverG
//     */
//    public Integer getAntArInntektOverG() {
//        return antArInntektOverG;
//    }
//
//    /**
//     * @param antArInntektOverG the antArInntektOverG to set
//     */
//    public void setAntArInntektOverG(Integer antArInntektOverG) {
//        this.antArInntektOverG = antArInntektOverG;
//    }

    /**
     * @return the avdodAntallArIUtlandet
     */
    public Integer getAvdodAntallArIUtlandet() {
        return avdodAntallArIUtlandet;
    }

    /**
     * @param avdodAntallArIUtlandet the avdodAntallArIUtlandet to set
     */
    public void setAvdodAntallArIUtlandet(Integer avdodAntallArIUtlandet) {
        this.avdodAntallArIUtlandet = avdodAntallArIUtlandet;
    }

    /**
     * @return the avdodFlyktning
     */
    public Boolean getAvdodFlyktning() {
        return avdodFlyktning;
    }

    /**
     * @param avdodFlyktning the avdodFlyktning to set
     */
    public void setAvdodFlyktning(Boolean avdodFlyktning) {
        this.avdodFlyktning = avdodFlyktning;
    }

    /**
     * @return the avdodInntektForDod
     */
    public Integer getAvdodInntektForDod() {
        return avdodInntektForDod;
    }

    /**
     * @param avdodInntektForDod the avdodInntektForDod to set
     */
    public void setAvdodInntektForDod(Integer avdodInntektForDod) {
        this.avdodInntektForDod = avdodInntektForDod;
    }

    /**
     * @return the avdodMedlemAvFolketrygden
     */
    public Boolean getAvdodMedlemAvFolketrygden() {
        return avdodMedlemAvFolketrygden;
    }

    /**
     * @param avdodMedlemAvFolketrygden the avdodMedlemAvFolketrygden to set
     */
    public void setAvdodMedlemAvFolketrygden(Boolean avdodMedlemAvFolketrygden) {
        this.avdodMedlemAvFolketrygden = avdodMedlemAvFolketrygden;
    }

//    /**
//     * @return the brukerRegTPListe
//     */
//    public List<BrukerRegTjenestepensjon> getBrukerRegTPListe() {
//        return brukerRegTPListe;
//    }
//
//    /**
//     * @param brukerRegTPListe the brukerRegTPListe to set
//     */
//    public void setBrukerRegTPListe(List<BrukerRegTjenestepensjon> brukerRegTPListe) {
//        this.brukerRegTPListe = brukerRegTPListe;
//    }
//
//    /**
//     * Adds a {@link BrukerRegTjenestepensjon} to the list.
//     *
//     * @param brukerRegTjenestepensjon the brukerRegTPListe to set
//     */
//    public void addBrukerRegTjenestepensjon(BrukerRegTjenestepensjon brukerRegTjenestepensjon) {
//        brukerRegTPListe.add(brukerRegTjenestepensjon);
//        brukerRegTjenestepensjon.setSimuleringEtter2011(this);
//    }

    /**
     * @return the dodsdato
     */
    public Date getDodsdato() {
        return dodsdato;
    }

    /**
     * @param dodsdato the dodsdato to set
     */
    public void setDodsdato(Date dodsdato) {
        this.dodsdato = dodsdato;
    }

    /**
     * @return the eps2G
     */
    public Boolean getEps2G() {
        return eps2G;
    }

    /**
     * @param eps2G the eps2G to set
     */
    public void setEps2G(Boolean eps2G) {
        this.eps2G = eps2G;
    }

    /**
     * @return the epsPensjon
     */
    public Boolean getEpsPensjon() {
        return epsPensjon;
    }

    /**
     * @param epsPensjon the epsPensjon to set
     */
    public void setEpsPensjon(Boolean epsPensjon) {
        this.epsPensjon = epsPensjon;
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
     * @return the fnr
     */
    public Pid getFnr() {
        return fnr;
    }

    /**
     * @param fnr the fnr to set
     */
    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    /**
     * @return the fodselsar
     */
    public Integer getFodselsar() {
        return fodselsar;
    }

    /**
     * @param fodselsar the fodselsar to set
     */
    public void setFodselsar(Integer fodselsar) {
        this.fodselsar = fodselsar;
    }

    /**
     * @return the forsteUttakDato
     */
    public Date getForsteUttakDato() {
        return forsteUttakDato;
    }

    /**
     * @param forsteUttakDato the forsteUttakDato to set
     */
    public void setForsteUttakDato(Date forsteUttakDato) {
        this.forsteUttakDato = forsteUttakDato;
    }

    /**
     * @return the forventetInntekt
     */
    public Integer getForventetInntekt() {
        return forventetInntekt;
    }

    /**
     * @param forventetInntekt the forventetInntekt to set
     */
    public void setForventetInntekt(Integer forventetInntekt) {
        this.forventetInntekt = forventetInntekt;
    }

    /**
     * @return the heltUttakDato
     */
    public Date getHeltUttakDato() {
        return heltUttakDato;
    }

    /**
     * @param heltUttakDato the heltUttakDato to set
     */
    public void setHeltUttakDato(Date heltUttakDato) {
        this.heltUttakDato = heltUttakDato;
    }

    /**
     * @return the inntektAvdodOver1G
     */
    public Boolean getInntektAvdodOver1G() {
        return inntektAvdodOver1G;
    }

    /**
     * @param inntektAvdodOver1G the inntektAvdodOver1G to set
     */
    public void setInntektAvdodOver1G(Boolean inntektAvdodOver1G) {
        this.inntektAvdodOver1G = inntektAvdodOver1G;
    }

    /**
     * @return the inntektEtterHeltUttak
     */
    public Integer getInntektEtterHeltUttak() {
        return inntektEtterHeltUttak;
    }

    /**
     * @param inntektEtterHeltUttak the inntektEtterHeltUttak to set
     */
    public void setInntektEtterHeltUttak(Integer inntektEtterHeltUttak) {
        this.inntektEtterHeltUttak = inntektEtterHeltUttak;
    }

    /**
     * @return the inntektUnderGradertUttak
     */
    public Integer getInntektUnderGradertUttak() {
        return inntektUnderGradertUttak;
    }

    /**
     * @param inntektUnderGradertUttak the inntektUnderGradertUttak to set
     */
    public void setInntektUnderGradertUttak(Integer inntektUnderGradertUttak) {
        this.inntektUnderGradertUttak = inntektUnderGradertUttak;
    }

    /**
     * @return the lagringstidspunkt
     */
    public Date getLagringstidspunkt() {
        return lagringstidspunkt;
    }

    /**
     * @param lagringstidspunkt the lagringstidspunkt to set
     */
    public void setLagringstidspunkt(Date lagringstidspunkt) {
        this.lagringstidspunkt = lagringstidspunkt;
    }

    /**
     * @return the offentligAfpRett
     */
    public Boolean getOffentligAfpRett() {
        return offentligAfpRett;
    }

    /**
     * @param offentligAfpRett the offentligAfpRett to set
     */
    public void setOffentligAfpRett(Boolean offentligAfpRett) {
        this.offentligAfpRett = offentligAfpRett;
    }

    /**
     * @return the privatAfpRett
     */
    public Boolean getPrivatAfpRett() {
        return privatAfpRett;
    }

    /**
     * @param privatAfpRett the privatAfpRett to set
     */
    public void setPrivatAfpRett(Boolean privatAfpRett) {
        this.privatAfpRett = privatAfpRett;
    }

    /**
     * @return the samtykke
     */
    public Boolean getSamtykke() {
        return samtykke;
    }

    /**
     * @param samtykke the samtykke to set
     */
    public void setSamtykke(Boolean samtykke) {
        this.samtykke = samtykke;
    }

    /**
     * @return the simuleringId
     */
    public Long getSimuleringId() {
        return simuleringId;
    }

    /**
     * @param simuleringId the simuleringId to set
     */
    public void setSimuleringId(Long simuleringId) {
        this.simuleringId = simuleringId;
    }

    /**
     * @return the simuleringNavn
     */
    public String getSimuleringNavn() {
        return simuleringNavn;
    }

    /**
     * @param simuleringNavn the simuleringNavn to set
     */
    public void setSimuleringNavn(String simuleringNavn) {
        this.simuleringNavn = simuleringNavn;
    }

    /**
     * @return the simuleringsvalgOffentligAfp
     */
    public Boolean getSimuleringsvalgOffentligAfp() {
        return simuleringsvalgOffentligAfp;
    }

    /**
     * @param simuleringsvalgOffentligAfp the simuleringsvalgOffentligAfp to set
     */
    public void setSimuleringsvalgOffentligAfp(Boolean simuleringsvalgOffentligAfp) {
        this.simuleringsvalgOffentligAfp = simuleringsvalgOffentligAfp;
    }

    /**
     * @return the simuleringType
     */
    public SimuleringTypeCti getSimuleringType() {
        return simuleringType;
    }

    /**
     * @param simuleringType the simuleringType to set
     */
    public void setSimuleringType(SimuleringTypeCti simuleringType) {
        this.simuleringType = simuleringType;
    }

    /**
     * @return the sivilstatus
     */
    public SivilstatusTypeCti getSivilstatus() {
        return sivilstatus;
    }

    /**
     * @param sivilstatus the sivilstatus to set
     */
    public void setSivilstatus(SivilstatusTypeCti sivilstatus) {
        this.sivilstatus = sivilstatus;
    }

//    /**
//     * @return the stillingsprosentOff
//     */
//    public StillingsprOffCti getStillingsprosentOffHeltUttak() {
//        return stillingsprosentOffHeltUttak;
//    }
//
//    /**
//     * @param stillingsprosentOff the stillingsprosentOff to set
//     */
//    public void setStillingsprosentOffHeltUttak(StillingsprOffCti stillingsprosentOff) {
//        stillingsprosentOffHeltUttak = stillingsprosentOff;
//    }

    /**
     * @return the utenlandsopphold
     */
    public Integer getUtenlandsopphold() {
        return utenlandsopphold;
    }

    /**
     * @param utenlandsopphold the utenlandsopphold to set
     */
    public void setUtenlandsopphold(Integer utenlandsopphold) {
        this.utenlandsopphold = utenlandsopphold;
    }

    /**
     * @return the utg
     */
    public UttaksgradCti getUtg() {
        return utg;
    }

    /**
     * @param utg the utg to set
     */
    public void setUtg(UttaksgradCti utg) {
        this.utg = utg;
    }

    /**
     * Return value of utg as Integer
     *
     * @return value of utg as Integer
     * @see #getUtg()
     */
    @Transient
    public Integer getUtgAsInteger() {
        if (utg != null) {
            return Integer.valueOf(utg.getCodeAsString());
        }

        return null;
    }

    /**
     * @return the fnrAvdod
     */
    public Pid getFnrAvdod() {
        return fnrAvdod;
    }

    /**
     * @param fnrAvdod the fnrAvdod to set
     */
    public void setFnrAvdod(Pid fnrAvdod) {
        this.fnrAvdod = fnrAvdod;
    }

    public boolean isForenkletSimulering() {
        return getFnr() == null;
    }

    /**
     * Returns true if uttaksgrad is 100%.
     * <p>
     * CR230484 18.03.2011 OJB2812 - null is no longer interpreted as 100% (used for AFP_ETTERF_ALDER)
     *
     * @return true if uttaksgrad is 100%
     */
    public boolean isUttaksgrad100() {
        return utg != null && utg.isCodeEqualTo(UttaksgradCode.P_100);
    }

    /**
     * @return the simulerForTp
     */
    public Boolean getSimulerForTp() {
        return simulerForTp;
    }

    /**
     * @param simulerForTp the simulerForTp to set
     */
    public void setSimulerForTp(Boolean simulerForTp) {
        this.simulerForTp = simulerForTp;
    }
//
//    /**
//     * @return the stillingsprosentOffGradertUttak
//     */
//    public StillingsprOffCti getStillingsprosentOffGradertUttak() {
//        return stillingsprosentOffGradertUttak;
//    }
//
//    /**
//     * @param stillingsprosentOffGradertUttak the stillingsprosentOffGradertUttak to set
//     */
//    public void setStillingsprosentOffGradertUttak(StillingsprOffCti stillingsprosentOffGradertUttak) {
//        this.stillingsprosentOffGradertUttak = stillingsprosentOffGradertUttak;
//    }

//    /**
//     * Whether bruker has lived abroad or not.
//     *
//     * @return {@code true} if list of {@link UtenlandsperiodeForSimulering} is not empty, {@code false} otherwise.
//     */
//    public boolean isBoddIUtlandet() {
//        return !utenlandsperiodeForSimuleringList.isEmpty();
//    }
//
//    public List<UtenlandsperiodeForSimulering> getUtenlandsperiodeForSimuleringList() {
//        return utenlandsperiodeForSimuleringList;
//    }
//
//    public void addUtenlandsperiodeForSimulering(UtenlandsperiodeForSimulering utenlandsperiodeForSimulering) {
//        utenlandsperiodeForSimuleringList.add(utenlandsperiodeForSimulering);
//        utenlandsperiodeForSimulering.setSimuleringEtter2011(this);
//    }
//
//    public List<FremtidigInntekt> getFremtidigInntektList() {
//        return fremtidigInntektList;
//    }
//
//    public void addFremtidigInntekt(FremtidigInntekt fremtidigInntekt) {
//        if (this.fremtidigInntektList == null) {
//            this.fremtidigInntektList = new ArrayList<>();
//        }
//        if (fremtidigInntekt != null) {
//            this.fremtidigInntektList.add(fremtidigInntekt);
//            fremtidigInntekt.setSimuleringEtter2011(this);
//        }
//    }

    /**
     * Liste med TPNR for ordninger som er på elektronisk grensesnitt. Skal ikke persisteres i databasen.
     */
    // TEA2812 Ikke definert
    //private List<TPNR> tpnrListe = new ArrayList<TPNR>();
}

//package no.nav.domain.pensjon.kjerne.simulering;
//
//import no.nav.domain.pensjon.kjerne.kodetabeller.SimuleringTypeCti;
//import no.stelvio.domain.person.Pid;
//
//public class SimuleringEtter2011 {
//    public void setSimuleringType(SimuleringTypeCti cti) {
//
//    }
//
//    public void setFnr(Pid pid) {
//
//    }
//}
