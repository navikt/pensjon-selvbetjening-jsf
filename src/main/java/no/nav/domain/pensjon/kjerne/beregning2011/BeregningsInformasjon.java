package no.nav.domain.pensjon.kjerne.beregning2011;

import java.util.ArrayList;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.kjerne.Merknad;
import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.beregning.Sluttpoengtall;
import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;
import no.nav.domain.pensjon.kjerne.grunnlag.Beholdning;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeregningMetodeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeregningMetodeTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagsrolleCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.JustertPeriodeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.ResultatTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstandTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;

//@Entity
//@Table(name = "T_BEREGNING_INFO")
public class BeregningsInformasjon extends AbstractMerknadDomainObject {

    private static final long serialVersionUID = 7847799779873286680L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BEREGNING_INFO_ID")
    private Long beregningsinformasjonId;

    /**
     * Basis grunnpensjon, hentes fra Basispensjon i beregningKapittel19
     */
    @Column(name = "BASISGP", nullable = true)
    private Double basisgp = 0.0;

    /**
     * Basis pensjonstillegg, hentes fra Basispensjon i beregningKapittel19
     */
    @Column(name = "BASISPT", nullable = true)
    private Double basispt = 0.0;

    /**
     * Basis tilleggspensjon, hentes fra Basispensjon i beregningKapittel19
     */
    @Column(name = "BASISTP", nullable = true)
    private Double basistp = 0.0;

    /**
     * Om beløpet er redusert i forhold til beløpet på siste beregning på siste vedtak som er fattet
     */
    @Column(name = "BELOP_REDUSERT", nullable = true)
    private Boolean belopRedusert;

    /**
     * En liste med beløpsendringobjekter. Sier hva som er endring på ytelseskomponentnivå i forhold til ytelseskomponentene på
     * siste beregning på siste vedtak som er fattet
     */
    @OneToMany(mappedBy = "beregningsInformasjon", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Belopsendring> belopsendringListe = new ArrayList<>();

    @OneToMany(mappedBy = "beregningsInformasjonForrige", orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Belopsendring> belopsEndringFraForrigePeriode = new ArrayList<>();

    /**
     * Flagg som angir om bruker er beregnet som enslig fordi vedkommende eller EPS har vært på institusjon i perioden
     * beregningen gjelder.
     */
    @Column(name = "enslig_pen_inst_opph", nullable = true)
    private Boolean ensligPensjonInstOpph;

    /**
     * Flagg som sier om beløpet er økt i forhold til beløpet på siste beregning på siste vedtak som er fattet.
     */
    @Column(name = "BELOP_OKT", nullable = true)
    private Boolean belopOkt;

    /**
     * Flagg som sier om det har vært en hjemmelsendring på siste beregning på siste vedtak som er fattet.
     */
    @Column(name = "HJEMMELSENDRING", nullable = true)
    private Boolean hjemmelsendring;

    @Column(name = "BELOP_OKT_FRA_FORRIGE_PERIODE")
    private Boolean belopOktFraForrigePeriode;

    @Column(name = "BELOP_RED_FRA_FORRIGE_PERIODE")
    private Boolean belopRedusertFraForrigePeriode;

    @Column(name = "HJEMMELS_ENDRING_FORRIGE")
    private Boolean hjemmelsEndringForrigePeriode;

    /**
     * Eventuelt sluttpoengtall med overkompensasjon tilleggspensjonen som er i bruk på (del)beregning nøkkelinformasjonen
     * gjelder for.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "SLUTTPOENGTALL_ID_OPT", nullable = true)
    private Sluttpoengtall opt;

    /**
     * Eventuelt sluttpoengtall på tilleggspensjonen som er i bruk på (del)beregningen nøkkelinformasjonen gjelder for
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "SLUTTPOENGTALL_ID_SPT", nullable = true)
    private Sluttpoengtall spt;

    /**
     * Eventuelt sluttpoengtall ved yrkesskade på tilleggspensjonen.
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = true, cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "SLUTTPOENGTALL_ID_YPT", nullable = true)
    private Sluttpoengtall ypt;

    /**
     * Minstenivåtillegg pensjonistpar til utbetaling
     */
    @Column(name = "MINSTENIVA_PPAR", nullable = true)
    private Integer minstenivatilleggPensjonistpar = 0;

    /**
     * Angir burkers delingstall
     */
    @Column(name = "DELINGSTALL", nullable = true)
    private Double delingstall = 0.0;

    /**
     * Angir om brukers eps mottar pensjon
     */
    @Column(name = "EPS_MOTTAR_PENSJON", nullable = true)
    private Boolean epsMottarPensjon = false;

    /**
     * Angir om brukers eps har inntekt over 2G (GrunnpensjonKapittel19.ektefelleInntektOver2g)
     */
    // SIR224694: Removed default value to avoid that TPEN544 kompletterBeregningsinformasjon skips this field.
    @Column(name = "EPS_OVER_2G", nullable = true)
    private Boolean epsOver2G;

    /**
     * Om bruker er flyktning, og status som flyktning er blitt brukt i beregningen, må denne settes til true.
     */
    @Column(name = "FLYKTNING", nullable = true)
    private Boolean flyktning = false;

    /**
     * Brukers forholdstall ved 67 (tre desimaler)
     */
    @Column(name = "FORHOLDSTALL_67", nullable = true)
    private Double forholdstall67 = 0.0;

    /**
     * Brukers forholdstall ved uttak (tre desimaler)
     */
    @Column(name = "FORHOLDSTALL_UTTAK", nullable = true)
    private Double forholdstallUttak = 0.0;

    /**
     * Brutto grunnpensjon etter leveladersjustering pr år
     */
    @Column(name = "GP", nullable = true)
    private Double gp = 0.0;

    /**
     * Ved fleksibelt uttak ønsker vi å vite restpensjonen av grunnpensjonen pr år
     */
    @Column(name = "GP_RESTPENSJON", nullable = true)
    private Double gpRestpensjon = 0.0;

    /**
     * Grunnbeløpet brukt i beregningen
     */
    @Column(name = "GRUNNBELOP")
    private int grunnbelop = 0;

    /**
     * Settes til true hvis grunnpensjonen har blitt avkortet pga EPS tjener mer enn 2G eller mottar pensjon
     */
    @Column(name = "GRUNNPENSJON_AVKORTET", nullable = true)
    private Boolean grunnpensjonAvkortet = false;

    @Column(name = "MOTTAR_MIN_PENSJONSNIVA", nullable = true)
    private Boolean mottarMinstePensjonsniva;

    /**
     * Ønsker å sjekke om bruker mottar minste pensjonsnivå.
     */
    @Column(name = "MPN", nullable = true)
    private Boolean mpn = false;

    /**
     * Viser brukerens pensjonsnivå ved anvendt trygdetid
     */
    @Column(name = "MPN_UTTAK_ANV_TRYGDETID", nullable = true)
    private Double mpnUttakAnvTrygdetid = 0.0;

    /**
     * Viser brukerens pensjonsnivå ved full trygdetid
     */
    @Column(name = "MPN_UTTAK_FULL_TRYGDETID", nullable = true)
    private Double mpnUttakFullTrygdetid = 0.0;

    /**
     * Viser brukerens pensjonsbeholdning
     */
    @Column(name = "PENSJONSBEHOLDNING", nullable = true)
    private Double pensjonsbeholdning = 0.0;

    /**
     * Brutto pensjonstillegg etter levealdersjustering pr år Kan være negativt beløp.
     */
    @Column(name = "PT", nullable = true)
    private Double pt = 0.0;

    /**
     * Ved fleksibelt uttak ønsker vi å vite restpensjonen av pensjonstillegget pr år. Kan være negativt beløp
     */
    @Column(name = "PT_RESTPENSJON", nullable = true)
    private Double ptRestpensjon = 0.0;

    /**
     * Brukerens sivilstand
     */
    @ManyToOne(optional = true)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_SIVILSTAND_T", nullable = true)
    private SivilstandTypeCti sivilstand;

    /**
     * Brutto tilleggspensjonen etter levealdersjustering pr år
     */
    @Column(name = "TP", nullable = true)
    private Double tp = 0.0;

    /**
     * True hvis tilleggspensjon er innvilget
     */
    @Column(name = "TP_INNVILGET", nullable = true)
    private Boolean tpInnvilget = false;

    /**
     * Ved fleksibelt uttak ønsker vi å vite restpensjonen av tilleggspensjonen pr år
     */
    @Column(name = "TP_RESTPENSJON", nullable = true)
    private Double tpRestpensjon = 0.0;

    /**
     * Opptjent trygdetid
     */
    @Column(name = "TRYGDETID", nullable = true)
    private Integer trygdetid = 0;

    /**
     * uttaksgrad
     */
    @Column(name = "UTTAKSGRAD", nullable = true)
    private Integer uttaksgrad = 0;

    /**
     * Viser datoen beregningen gjelder fra og med (Beregningsresultat.virkFom)
     */
    @Column(name = "DATO_VIRK_FOM", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date virkFom;

    /**
     * Viser datoen beregningen gjelder til og med (Beregningsresultat.virkTom)
     */
    @Column(name = "DATO_VIRK_TOM", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date virkTom;

    @ManyToOne(optional = true)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_BEREG_METODE_T", nullable = true)
    private BeregningMetodeCti beregningsMetode;

    /**
     * Angir årsaken til at beregningen ble som den ble. Kodeverk K_RESULTAT_T benyttes
     */
    @ManyToOne(optional = true)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_RESULTAT_T", nullable = true)
    private ResultatTypeCti resultatType;

    @ManyToOne(optional = true)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_GRNL_ROLLE_T", nullable = true)
    private GrunnlagsrolleCti grunnlagsrolle;

    /**
     * Personen beregningsinformasjonen er knyttet til
     */
    @ManyToOne(cascade = {CascadeType.REFRESH}, optional = true)
    @JoinColumn(name = "PERSON_ID", nullable = true)
    private PenPerson penPerson;

    /**
     * Den trygdetiden som er anvendt i pensjonsberegningen.
     */
    @Column(name = "TT_ANV", nullable = true)
    private Integer tt_anv;

    /**
     * Den avdødes grunnlagsinformasjon er anvendt i beregning av tilleggspensjon på bruker.
     */
    @Column(name = "GJENLEVRETT_ANV", nullable = true)
    private Boolean gjenlevenderettAnvendt;

    /**
     * Liste med tapendeBeregningsmetode
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "beregningsInformasjon", cascade = CascadeType.ALL)
    @Fetch(FetchMode.SELECT)
    private List<TapendeBeregningsmetode> tapendeBeregningsmetodeListe = new ArrayList<>();

    // 3.2b Attributes

    @Column(name = "ANT_BARN_SOKT_BTILLEGG", nullable = true)
    private Integer antallBarnSoktBarnetillegg;

    @Column(name = "ANT_SERKULLSBARN", nullable = true)
    private Integer antallSerkullsbarn;

    @Column(name = "ANT_FELLESBARN", nullable = true)
    private Integer antallFellesbarn;

    @Column(name = "BASISPENSJON", nullable = true)
    private Double basispensjon;

    @Column(name = "BASIS_GP_FORRIGE", nullable = true)
    private Double basisGpForrige;

    /**
     * Basis pensjonstillegg beregnet før en revurdering.
     */
    @Column(name = "BASIS_PT_FORRIGE", nullable = true)
    private Double basisPtForrige;

    /**
     * Basis tilleggspensjon beregnet før en revurdering.
     */
    @Column(name = "BASIS_TP_FORRIGE", nullable = true)
    private Double basisTpForrige;

    // CR149557 andret navn fra brukersOpptjeningBruktTrygdetid til trygdetidAnvendt
    @Column(name = "TRYGDETID_ANV", nullable = true)
    private Boolean trygdetidAnvendt;

    @Column(name = "FREMSKRIVINGSFAKTOR", nullable = true)
    private Double fremskrivingsfaktor;

    @Column(name = "GJ_AFP_UTTAKSGRAD", nullable = true)
    private Integer gjennomsnittligAfpUttaksgrad;

    @Column(name = "GP_FORRIGE", nullable = true)
    private Double gpForrige;

    @Column(name = "INST_OPPH_ANV", nullable = true)
    private Boolean instOpphAnvendt;

    @ManyToOne
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_JUST_PERIODE", nullable = true)
    private JustertPeriodeCti instOppholdType;

    /**
     * Avdødes poengår før 1991. Disse årene benyttes 45% av G.
     */
    @Column(name = "PA_F92", nullable = true)
    private Integer PaF92;

    /**
     * Avdødes poengår etter 1991. Disse årene benyttes 42% av G.
     */
    @Column(name = "PA_F91", nullable = true)
    private Integer PaE91;

    /**
     * Proratabrøk brukt i reduksjon av minste pensjonsnivå
     */
    @Column(name = "PRORATA_NEVNER_MND", nullable = true)
    private Integer prorata_nevner_mnd;

    /**
     * Proratabrøk brukt i reduksjon av minste pensjonsnivå
     */
    @Column(name = "PRORATA_TELLER_MND", nullable = true)
    private Integer prorata_teller_mnd;

    @Column(name = "PT_FORRIGE", nullable = true)
    private Double ptForrige;

    @Column(name = "RESTPENSJON", nullable = true)
    private Double restpensjon;

    @Column(name = "RESTPENSJON_FORRIGE", nullable = true)
    private Double restpensjonForrige;

    @Column(name = "RESTPENSJON_GP_FORRIGE", nullable = true)
    private Double restpensjonGpForrige;

    @Column(name = "RESTPENSJON_TP_FORRIGE", nullable = true)
    private Double restpensjonTpForrige;

    @Column(name = "RESTPENSJON_PT_FORRIGE", nullable = true)
    private Double restpensjonPtForrige;

    @Column(name = "RETT_PA_GJLEVENDERETT", nullable = true)
    private Boolean rettPaGjenlevenderett;

    @Column(name = "TP_FORRIGE", nullable = true)
    private Double tpForrige;

    /**
     * Faktisk opptjeningstid i Norge
     */
    @Column(name = "TT_FA_MDN", nullable = true)
    private Integer tt_fa_mdn;

    /**
     * Faktisk opptjeningstid i EØS-området
     */
    @Column(name = "TT_EOS_PRO_RATA_MND", nullable = true)
    private Integer tt_eos_pro_rata_mnd;

    /**
     * HVIS barnetillegg er satt OG valgt trygdeavtale er EØS/Nordisk konvensjon OG Norge er behandlende land SÅ beregnes
     * barnetillegg med trygdetid fra EØS
     */
    @Column(name = "TT_EOS_ANV_AR", nullable = true)
    private Integer tt_eos_anv_ar;

    @Column(name = "UNG_UFOR", nullable = true)
    private Boolean ungUfor;

    @Column(name = "UNG_UFOR_ANV", nullable = true)
    private Boolean ungUforAnvendt;

    @Column(name = "UTBETALT_FORRIGE", nullable = true)
    private Double utbetaltForrige;

    /**
     * Bostedsland slik regelmotoren har vurdert det ved beregning.
     */
    @ManyToOne(optional = true)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "VURDERT_BOSATT_LAND", nullable = true)
    private Land3TegnCti vurdertBosattLand;

    @Column(name = "YRKSK_REG", nullable = true)
    private Boolean yrkesskadeRegistrert;

    @Column(name = "YRKSK_ANV", nullable = true)
    private Boolean yrkesskadeAnvendt;

    @Column(name = "YRKSK_GRAD", nullable = true)
    private Double yrkesskadegrad;

    /**
     * Settes kun på bruker. Aktuell bare dersom rettPaGjenlevenderett er satt TRUE
     */
    @Column(name = "AVDODES_TP_BRUKT", nullable = true)
    private Boolean avdodesTilleggspensjonBrukt;

    /**
     * Liste av forrige ytelseskomponenter
     */
    @OneToMany(mappedBy = "beregningsInformasjon", cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<Ytelseskomponent> ytelseskomponenterForrige = new ArrayList<>();

    /**
     * Liste over forrige beholdninger
     */
    @OneToMany(mappedBy = "beregningsInformasjon", cascade = {CascadeType.ALL})
    @Fetch(FetchMode.SELECT)
    private List<Beholdning> beholdningerForrige = new ArrayList<>();

    /**
     * CR196797
     * PREG vil sette ungUforGarantiFrafalt til SANN dersom denne garantien ikke gjelder på beregningen (vil gjelde ved eksport).
     */
    @Column(name = "UNG_UFOR_GAR_FRAFALT")
    private Boolean ungUforGarantiFrafalt;

    /**
     * Beregnet trygdetid som kun gjelder for grunnlagsrollen (eks. søker).
     * Det vil si at det ikke ligger noen gjenlevendedel i denne trygdetiden.
     * Det er heller nødvendigvis ikke denne trygdetiden som er benyttet i beregningen.
     * CR 193714
     */
    @Column(name = "TT_BER_FOR_GRN_ROLLE")
    private Integer ttBeregnetForGrunnlagsrolle;

    /**
     * Angir om avdødes trygdetid er benyttet i den vinnende beregningen
     */
    @Column(name = "AVDODES_TRYGDETID_BRUKT")
    private Boolean avdodesTrygdetidBrukt;

    /**
     * Angir om det er gjennomført en eksportberegning.
     */
    @Column(name = "EKSPORT")
    private Boolean eksport;

    public BeregningsInformasjon() {
    }

    /**
     * Initializing constructor. Originally used by PREG, cancidate for deprecation and removal
     */
    @SuppressWarnings("unused") // parametre til konstruktør er ubrukte...
    public BeregningsInformasjon(Double basisgp, Double basispt, Double basistp, Sluttpoengtall opt, Sluttpoengtall spt, Integer minstenivatilleggPensjonistpar,
                                 Boolean epsMottarPensjon, Boolean epsOver2G, Boolean flyktning, Double forholdstall67, Double forholdstallMpn67, Double forholdstallMpnUttak,
                                 Double forholdstallUttak, Double gp, Double gpGradertBasispensjon, Double gpRestpensjon, int grunnbelop, Boolean grunnpensjonAvkortet,
                                 Boolean mpn, Double mpnUttakAnvTrygdetid, Double mpnUttakFullTrygdetid, Double pt, Double ptGradertBasispensjon, Double ptRestpensjon,
                                 SivilstandTypeCti sivilstand, Double tp, Double tpGradertBasispensjon, Boolean tpInnvilget, Double tpRestpensjon, Integer trygdetid,
                                 Integer uttaksgrad, Date virkFom, Date virkTom, BeregningMetodeCti beregningsMetode, ResultatTypeCti resultatType,
                                 GrunnlagsrolleCti grunnlagsrolle, PenPerson penPerson, Integer tt_anv, List<Merknad> gpAvkortingsArsakList) {
        this.basisgp = basisgp;
        this.basispt = basispt;
        this.basistp = basistp;
        this.opt = opt;
        this.spt = spt;
        this.minstenivatilleggPensjonistpar = minstenivatilleggPensjonistpar;
        this.epsMottarPensjon = epsMottarPensjon;
        this.epsOver2G = epsOver2G;
        this.flyktning = flyktning;
        this.forholdstall67 = forholdstall67;
        this.forholdstallUttak = forholdstallUttak;
        this.gp = gp;
        this.gpRestpensjon = gpRestpensjon;
        this.grunnbelop = grunnbelop;
        this.grunnpensjonAvkortet = grunnpensjonAvkortet;
        this.mpn = mpn;
        this.mpnUttakAnvTrygdetid = mpnUttakAnvTrygdetid;
        this.mpnUttakFullTrygdetid = mpnUttakFullTrygdetid;
        this.pt = pt;
        this.ptRestpensjon = ptRestpensjon;
        this.sivilstand = sivilstand;
        this.tp = tp;
        this.tpInnvilget = tpInnvilget;
        this.tpRestpensjon = tpRestpensjon;
        this.trygdetid = trygdetid;
        this.uttaksgrad = uttaksgrad;
        this.virkFom = virkFom;
        this.virkTom = virkTom;
        this.beregningsMetode = beregningsMetode;
        this.resultatType = resultatType;
        this.grunnlagsrolle = grunnlagsrolle;
        this.penPerson = penPerson;
        this.tt_anv = tt_anv;
        setMerknadListe(gpAvkortingsArsakList);
    }

    public BeregningsInformasjon(BeregningsInformasjon info) {
        setBasisgp(info.getBasisgp());
        setBasispt(info.getBasispt());
        setBasistp(info.getBasistp());
        setOpt(info.getOpt() == null ? null : new Sluttpoengtall(info.getOpt()));
        setSpt(info.getSpt() == null ? null : new Sluttpoengtall(info.getSpt()));
        setMinstenivatilleggPensjonistpar(info.getMinstenivatilleggPensjonistpar());
        setDelingstall(info.getDelingstall());
        setEpsMottarPensjon(info.getEpsMottarPensjon());
        setEpsOver2G(info.getEpsOver2G());
        setFlyktning(info.getFlyktning());
        setForholdstall67(info.getForholdstall67());
        setForholdstallUttak(info.getForholdstallUttak());
        setGp(info.getGp());
        setGpRestpensjon(info.getGpRestpensjon());
        setGrunnbelop(info.getGrunnbelop());
        setGrunnpensjonAvkortet(info.getGrunnpensjonAvkortet());
        setMpn(info.getMpn());
        setMpnUttakAnvTrygdetid(info.getMpnUttakAnvTrygdetid());
        setMpnUttakFullTrygdetid(info.getMpnUttakFullTrygdetid());
        setPensjonsbeholdning(info.getPensjonsbeholdning());
        setPt(info.getPt());
        setPtRestpensjon(info.getPtRestpensjon());
        setSivilstand(info.getSivilstand());
        setTp(info.getTp());
        setTpInnvilget(info.getTpInnvilget());
        setTpRestpensjon(info.getTpRestpensjon());
        setTrygdetid(info.getTrygdetid());
        setUttaksgrad(info.getUttaksgrad());
        setVirkFom(info.getVirkFom());
        setVirkTom(info.getVirkTom());
        setBeregningsMetode(info.getBeregningsMetode());
        setResultatType(info.getResultatType());
        setGrunnlagsrolle(info.getGrunnlagsrolle());
        setPenPerson(info.getPenPerson());
        setTt_anv(info.getTt_anv());
        setBelopOktFraForrigePeriode(info.getBelopOktFraForrigePeriode());
        setBelopRedusertFraForrigePeriode(info.getBelopRedusertFraForrigePeriode());
        setBelopsEndringFraForrigePeriode(info.getBelopsEndringFraForrigePeriode());
        setHjemmelsEndringForrigePeriode(info.getHjemmelsEndringForrigePeriode());
        setEksport(info.getEksport());
        info.setGpAvkortingsArsakList(getGpAvkortingsArsakList()); // list of merknad is immutable strings that does not need to be copied
    }

    @SuppressWarnings("unused") // brukes av dozer?
    public List<BeregningMetodeTypeCode> getTapendeBeregningsmetodeCtiList() {
        List<BeregningMetodeTypeCode> tapere = new ArrayList<>();

        for (TapendeBeregningsmetode taper : getTapendeBeregningsmetodeListe()) {
            tapere.add(taper.getBeregningsMetode());
        }

        return tapere;
    }

    public void addTapendeBeregningsmetode(TapendeBeregningsmetode tapendeBeregningsmetode) {
        if (tapendeBeregningsmetode == null) {
            return;
        }

        tapendeBeregningsmetodeListe.add(tapendeBeregningsmetode);
        tapendeBeregningsmetode.setBeregningsInformasjon(this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getBasisgp() {
        return basisgp;
    }

    public void setBasisgp(Double basisgp) {
        this.basisgp = basisgp;
    }

    public Double getBasispt() {
        return basispt;
    }

    public void setBasispt(Double basispt) {
        this.basispt = basispt;
    }

    public Double getBasistp() {
        return basistp;
    }

    public void setBasistp(Double basistp) {
        this.basistp = basistp;
    }

    public BeregningMetodeCti getBeregningsMetode() {
        return beregningsMetode;
    }

    public void setBeregningsMetode(BeregningMetodeCti beregningsMetode) {
        this.beregningsMetode = beregningsMetode;
    }

    public Integer getMinstenivatilleggPensjonistpar() {
        return minstenivatilleggPensjonistpar;
    }

    public void setMinstenivatilleggPensjonistpar(Integer minstenivatilleggPensjonistpar) {
        this.minstenivatilleggPensjonistpar = minstenivatilleggPensjonistpar;
    }

    public Double getDelingstall() {
        return delingstall;
    }

    public void setDelingstall(Double delingstall) {
        this.delingstall = delingstall;
    }

    public Boolean getEpsMottarPensjon() {
        return epsMottarPensjon;
    }

    public void setEpsMottarPensjon(Boolean epsMottarPensjon) {
        this.epsMottarPensjon = epsMottarPensjon;
    }

    public Boolean getEpsOver2G() {
        return epsOver2G;
    }

    public void setEpsOver2G(Boolean epsOver2G) {
        this.epsOver2G = epsOver2G;
    }

    public Boolean getFlyktning() {
        return flyktning;
    }

    public void setFlyktning(Boolean flyktning) {
        this.flyktning = flyktning;
    }

    public Double getForholdstall67() {
        return forholdstall67;
    }

    public void setForholdstall67(Double forholdstall67) {
        this.forholdstall67 = forholdstall67;
    }

    public Double getForholdstallUttak() {
        return forholdstallUttak;
    }

    public void setForholdstallUttak(Double forholdstallUttak) {
        this.forholdstallUttak = forholdstallUttak;
    }

    public Double getGp() {
        return gp;
    }

    public void setGp(Double gp) {
        this.gp = gp;
    }

    public List<Merknad> getGpAvkortingsArsakList() {
        return getMerknadListe();
    }

    public void addGpAvkortingsArsak(Merknad m) {
        addMerknad(m);
    }

    public void setGpAvkortingsArsakList(List<Merknad> gpAvkortingsArsakList) {
        setMerknadListe(gpAvkortingsArsakList);
    }

    public Double getGpRestpensjon() {
        return gpRestpensjon;
    }

    public void setGpRestpensjon(Double gpRestpensjon) {
        this.gpRestpensjon = gpRestpensjon;
    }

    public int getGrunnbelop() {
        return grunnbelop;
    }

    public void setGrunnbelop(int grunnbelop) {
        this.grunnbelop = grunnbelop;
    }

    public GrunnlagsrolleCti getGrunnlagsrolle() {
        return grunnlagsrolle;
    }

    public void setGrunnlagsrolle(GrunnlagsrolleCti grunnlagsrolle) {
        this.grunnlagsrolle = grunnlagsrolle;
    }

    public Boolean getGrunnpensjonAvkortet() {
        return grunnpensjonAvkortet;
    }

    public void setGrunnpensjonAvkortet(Boolean grunnpensjonAvkortet) {
        this.grunnpensjonAvkortet = grunnpensjonAvkortet;
    }

    public Boolean getMpn() {
        return mpn;
    }

    public void setMpn(Boolean mpn) {
        this.mpn = mpn;
    }

    public Double getMpnUttakAnvTrygdetid() {
        return mpnUttakAnvTrygdetid;
    }

    public void setMpnUttakAnvTrygdetid(Double mpnUttakAnvTrygdetid) {
        this.mpnUttakAnvTrygdetid = mpnUttakAnvTrygdetid;
    }

    public Double getMpnUttakFullTrygdetid() {
        return mpnUttakFullTrygdetid;
    }

    public void setMpnUttakFullTrygdetid(Double mpnUttakFullTrygdetid) {
        this.mpnUttakFullTrygdetid = mpnUttakFullTrygdetid;
    }

    public Sluttpoengtall getOpt() {
        return opt;
    }

    public void setOpt(Sluttpoengtall opt) {
        this.opt = opt;
    }

    public PenPerson getPenPerson() {
        return penPerson;
    }

    public void setPenPerson(PenPerson penPerson) {
        this.penPerson = penPerson;
    }

    public Double getPensjonsbeholdning() {
        return pensjonsbeholdning;
    }

    public void setPensjonsbeholdning(Double pensjonsbeholdning) {
        this.pensjonsbeholdning = pensjonsbeholdning;
    }

    public Double getPt() {
        return pt;
    }

    public void setPt(Double pt) {
        this.pt = pt;
    }

    public Double getPtRestpensjon() {
        return ptRestpensjon;
    }

    public void setPtRestpensjon(Double ptRestpensjon) {
        this.ptRestpensjon = ptRestpensjon;
    }

    public ResultatTypeCti getResultatType() {
        return resultatType;
    }

    public void setResultatType(ResultatTypeCti resultatType) {
        this.resultatType = resultatType;
    }

    public SivilstandTypeCti getSivilstand() {
        return sivilstand;
    }

    public void setSivilstand(SivilstandTypeCti sivilstand) {
        this.sivilstand = sivilstand;
    }

    public Sluttpoengtall getSpt() {
        return spt;
    }

    public void setSpt(Sluttpoengtall spt) {
        this.spt = spt;
    }

    public Double getTp() {
        return tp;
    }

    public void setTp(Double tp) {
        this.tp = tp;
    }

    public Boolean getTpInnvilget() {
        return tpInnvilget;
    }

    public void setTpInnvilget(Boolean tpInnvilget) {
        this.tpInnvilget = tpInnvilget;
    }

    public Double getTpRestpensjon() {
        return tpRestpensjon;
    }

    public void setTpRestpensjon(Double tpRestpensjon) {
        this.tpRestpensjon = tpRestpensjon;
    }

    public Integer getTrygdetid() {
        return trygdetid;
    }

    public void setTrygdetid(Integer trygdetid) {
        this.trygdetid = trygdetid;
    }

    public Integer getTt_anv() {
        return tt_anv;
    }

    public void setTt_anv(Integer tt_anv) {
        this.tt_anv = tt_anv;
    }

    public Integer getUttaksgrad() {
        return uttaksgrad;
    }

    public void setUttaksgrad(Integer uttaksgrad) {
        this.uttaksgrad = uttaksgrad;
    }

    public Date getVirkFom() {
        return virkFom;
    }

    public void setVirkFom(Date virkFom) {
        this.virkFom = virkFom;
    }

    public Date getVirkTom() {
        return virkTom;
    }

    public void setVirkTom(Date virkTom) {
        this.virkTom = virkTom;
    }

    public Boolean getMottarMinstePensjonsniva() {
        return mottarMinstePensjonsniva;
    }

    public void setMottarMinstePensjonsniva(Boolean mottarMinstePensjonsniva) {
        this.mottarMinstePensjonsniva = mottarMinstePensjonsniva;
    }

    public Long getBeregningsinformasjonId() {
        return beregningsinformasjonId;
    }

    public void setBeregningsinformasjonId(Long beregningsinformasjonId) {
        this.beregningsinformasjonId = beregningsinformasjonId;
    }

    public List<TapendeBeregningsmetode> getTapendeBeregningsmetodeListe() {
        return tapendeBeregningsmetodeListe;
    }

    public void setTapendeBeregningsmetodeListe(List<TapendeBeregningsmetode> tapendeBeregningsmetodeListe) {
        this.tapendeBeregningsmetodeListe = tapendeBeregningsmetodeListe;
    }

    public Integer getAntallBarnSoktBarnetillegg() {
        return antallBarnSoktBarnetillegg;
    }

    public void setAntallBarnSoktBarnetillegg(Integer antallBarnSoktBarnetillegg) {
        this.antallBarnSoktBarnetillegg = antallBarnSoktBarnetillegg;
    }

    public Integer getAntallFellesbarn() {
        return antallFellesbarn;
    }

    public void setAntallFellesbarn(Integer antallFellesbarn) {
        this.antallFellesbarn = antallFellesbarn;
    }

    public Integer getAntallSerkullsbarn() {
        return antallSerkullsbarn;
    }

    public void setAntallSerkullsbarn(Integer antallSerkullsbarn) {
        this.antallSerkullsbarn = antallSerkullsbarn;
    }

    public Double getBasisGpForrige() {
        return basisGpForrige;
    }

    public void setBasisGpForrige(Double basisGpForrige) {
        this.basisGpForrige = basisGpForrige;
    }

    public Double getBasispensjon() {
        return basispensjon;
    }

    public void setBasispensjon(Double basispensjon) {
        this.basispensjon = basispensjon;
    }

    @SuppressWarnings("unused") // brukes av pselv
    public Boolean getTrygdetidAnvendt() {
        return trygdetidAnvendt;
    }

    public void setTrygdetidAnvendt(Boolean brukersOpptjeningBruktTrygdetid) {
        trygdetidAnvendt = brukersOpptjeningBruktTrygdetid;
    }

    public Double getFremskrivingsfaktor() {
        return fremskrivingsfaktor;
    }

    public void setFremskrivingsfaktor(Double fremskrivingsfaktor) {
        this.fremskrivingsfaktor = fremskrivingsfaktor;
    }

    public Integer getGjennomsnittligAfpUttaksgrad() {
        return gjennomsnittligAfpUttaksgrad;
    }

    public void setGjennomsnittligAfpUttaksgrad(Integer gjennomsnittligAfpUttaksgrad) {
        this.gjennomsnittligAfpUttaksgrad = gjennomsnittligAfpUttaksgrad;
    }

    public Double getGpForrige() {
        return gpForrige;
    }

    public void setGpForrige(Double gpForrige) {
        this.gpForrige = gpForrige;
    }

    public Boolean getInstOpphAnvendt() {
        return instOpphAnvendt;
    }

    public void setInstOpphAnvendt(Boolean instOpphAnvendt) {
        this.instOpphAnvendt = instOpphAnvendt;
    }

    public JustertPeriodeCti getInstOppholdType() {
        return instOppholdType;
    }

    public void setInstOppholdType(JustertPeriodeCti instOppholdType) {
        this.instOppholdType = instOppholdType;
    }

    public Integer getPaE91() {
        return PaE91;
    }

    public void setPaE91(Integer paE91) {
        PaE91 = paE91;
    }

    public Integer getPaF92() {
        return PaF92;
    }

    public void setPaF92(Integer paF92) {
        PaF92 = paF92;
    }

    public Integer getProrata_nevner_mnd() {
        return prorata_nevner_mnd;
    }

    public void setProrata_nevner_mnd(Integer prorata_nevner_mnd) {
        this.prorata_nevner_mnd = prorata_nevner_mnd;
    }

    public Integer getProrata_teller_mnd() {
        return prorata_teller_mnd;
    }

    public void setProrata_teller_mnd(Integer prorata_teller_mnd) {
        this.prorata_teller_mnd = prorata_teller_mnd;
    }

    public Double getPtForrige() {
        return ptForrige;
    }

    public void setPtForrige(Double ptForrige) {
        this.ptForrige = ptForrige;
    }

    public Double getRestpensjon() {
        return restpensjon;
    }

    public void setRestpensjon(Double restpensjon) {
        this.restpensjon = restpensjon;
    }

    public Double getRestpensjonForrige() {
        return restpensjonForrige;
    }

    public void setRestpensjonForrige(Double restpensjonForrige) {
        this.restpensjonForrige = restpensjonForrige;
    }

    public Double getRestpensjonGpForrige() {
        return restpensjonGpForrige;
    }

    public void setRestpensjonGpForrige(Double restpensjonGpForrige) {
        this.restpensjonGpForrige = restpensjonGpForrige;
    }

    public Double getRestpensjonPtForrige() {
        return restpensjonPtForrige;
    }

    public void setRestpensjonPtForrige(Double restpensjonPtForrige) {
        this.restpensjonPtForrige = restpensjonPtForrige;
    }

    public Double getRestpensjonTpForrige() {
        return restpensjonTpForrige;
    }

    public void setRestpensjonTpForrige(Double restpensjonTpForrige) {
        this.restpensjonTpForrige = restpensjonTpForrige;
    }

    public Boolean getRettPaGjenlevenderett() {
        return rettPaGjenlevenderett;
    }

    public void setRettPaGjenlevenderett(Boolean rettPaGjenlevenderett) {
        this.rettPaGjenlevenderett = rettPaGjenlevenderett;
    }

    public Double getTpForrige() {
        return tpForrige;
    }

    public void setTpForrige(Double tpForrige) {
        this.tpForrige = tpForrige;
    }

    public Integer getTt_eos_anv_ar() {
        return tt_eos_anv_ar;
    }

    public void setTt_eos_anv_ar(Integer tt_eos_anv_ar) {
        this.tt_eos_anv_ar = tt_eos_anv_ar;
    }

    @SuppressWarnings("unused") // brukes av dozer
    public Integer getTt_eos_pro_rata_mnd() {
        return tt_eos_pro_rata_mnd;
    }

    public void setTt_eos_pro_rata_mnd(Integer tt_eos_pro_rata_mnd) {
        this.tt_eos_pro_rata_mnd = tt_eos_pro_rata_mnd;
    }

    @SuppressWarnings("unused") // brukes av dozer ?
    public Integer getTt_fa_mdn() {
        return tt_fa_mdn;
    }

    public void setTt_fa_mdn(Integer tt_fa_mdn) {
        this.tt_fa_mdn = tt_fa_mdn;
    }

    public Boolean getUngUfor() {
        return ungUfor;
    }

    public void setUngUfor(Boolean ungUfor) {
        this.ungUfor = ungUfor;
    }

    public Boolean getUngUforAnvendt() {
        return ungUforAnvendt;
    }

    public void setUngUforAnvendt(Boolean ungUforAnvendt) {
        this.ungUforAnvendt = ungUforAnvendt;
    }

    public Double getUtbetaltForrige() {
        return utbetaltForrige;
    }

    public void setUtbetaltForrige(Double utbetaltForrige) {
        this.utbetaltForrige = utbetaltForrige;
    }

    public Boolean getYrkesskadeAnvendt() {
        return yrkesskadeAnvendt;
    }

    public void setYrkesskadeAnvendt(Boolean yrkesskadeAnvendt) {
        this.yrkesskadeAnvendt = yrkesskadeAnvendt;
    }

    public Double getYrkesskadegrad() {
        return yrkesskadegrad;
    }

    public void setYrkesskadegrad(Double yrkesskadegrad) {
        this.yrkesskadegrad = yrkesskadegrad;
    }

    @SuppressWarnings("unused") // brukes av dozer
    public Boolean getYrkesskadeRegistrert() {
        return yrkesskadeRegistrert;
    }

    public void setYrkesskadeRegistrert(Boolean yrkesskadeRegistrert) {
        this.yrkesskadeRegistrert = yrkesskadeRegistrert;
    }

    public Boolean getGjenlevenderettAnvendt() {
        return gjenlevenderettAnvendt;
    }

    public void setGjenlevenderettAnvendt(Boolean gjenlevenderettAnvendt) {
        this.gjenlevenderettAnvendt = gjenlevenderettAnvendt;
    }

    public Sluttpoengtall getYpt() {
        return ypt;
    }

    public void setYpt(Sluttpoengtall ypt) {
        this.ypt = ypt;
    }

    public Boolean getBelopRedusert() {
        return belopRedusert;
    }

    public void setBelopRedusert(Boolean belopRedusert) {
        this.belopRedusert = belopRedusert;
    }

    /**
     * Do not manipulate list directly, use {@link #addBelopsendring(Belopsendring)}
     */
    public List<Belopsendring> getBelopsendringListe() {
        return belopsendringListe;
    }

    /**
     * Do not manipulate list directly, use {@link #addBelopsendring(Belopsendring)}
     */
    public void setBelopsendringListe(List<Belopsendring> belopsendringListe) {
        this.belopsendringListe = belopsendringListe;
    }

    public List<Belopsendring> getBelopsEndringFraForrigePeriode() {
        return belopsEndringFraForrigePeriode;
    }

    public void setBelopsEndringFraForrigePeriode(List<Belopsendring> belopsEndringFraForrigePeriode) {
        this.belopsEndringFraForrigePeriode = belopsEndringFraForrigePeriode;
    }

    public Boolean getBelopOktFraForrigePeriode() {
        return belopOktFraForrigePeriode;
    }

    public void setBelopOktFraForrigePeriode(Boolean belopOktFraForrigePeriode) {
        this.belopOktFraForrigePeriode = belopOktFraForrigePeriode;
    }

    public Boolean getBelopRedusertFraForrigePeriode() {
        return belopRedusertFraForrigePeriode;
    }

    public void setBelopRedusertFraForrigePeriode(Boolean belopRedusertFraForrigePeriode) {
        this.belopRedusertFraForrigePeriode = belopRedusertFraForrigePeriode;
    }

    public Boolean getHjemmelsEndringForrigePeriode() {
        return hjemmelsEndringForrigePeriode;
    }

    public void setHjemmelsEndringForrigePeriode(Boolean hjemmelsEndringForrigePeriode) {
        this.hjemmelsEndringForrigePeriode = hjemmelsEndringForrigePeriode;
    }

    public void replaceBelopsendringListe(List<Belopsendring> nyBelopsendringListe) {
        if (nyBelopsendringListe == null) {
            return;
        }

        belopsendringListe.clear();

        for (Belopsendring b : nyBelopsendringListe) {
            addBelopsendring(b);
        }
    }

    public void replaceBelopsendringFraForrigePeriode(List<Belopsendring> nyBelopsendringFraForrigePeriode) {
        if (nyBelopsendringFraForrigePeriode == null) {
            return;
        }

        belopsEndringFraForrigePeriode.clear();

        for (Belopsendring endring : nyBelopsendringFraForrigePeriode) {
            addBelopsendringForrigePeriode(endring);
        }
    }

    public void addBelopsendring(Belopsendring belopsendring) {
        if (belopsendring == null) {
            return;
        }

        belopsendringListe.add(belopsendring);
        belopsendring.setBeregningsInformasjon(this);
    }

    public void addBelopsendringForrigePeriode(Belopsendring belopsendring) {
        if (belopsendring == null) {
            return;
        }

        belopsEndringFraForrigePeriode.add(belopsendring);
        belopsendring.setBeregningsInformasjonForrige(this);
    }

    public Boolean getEnsligPensjonInstOpph() {
        return ensligPensjonInstOpph;
    }

    public void setEnsligPensjonInstOpph(Boolean ensligPensjonInstOpph) {
        this.ensligPensjonInstOpph = ensligPensjonInstOpph;
    }

    public Double getBasisPtForrige() {
        return basisPtForrige;
    }

    public void setBasisPtForrige(Double basisPtForrige) {
        this.basisPtForrige = basisPtForrige;
    }

    public Double getBasisTpForrige() {
        return basisTpForrige;
    }

    public void setBasisTpForrige(Double basisTpForrige) {
        this.basisTpForrige = basisTpForrige;
    }

    public Boolean getAvdodesTilleggspensjonBrukt() {
        return avdodesTilleggspensjonBrukt;
    }

    public void setAvdodesTilleggspensjonBrukt(Boolean avdodesTilleggspensjonBrukt) {
        this.avdodesTilleggspensjonBrukt = avdodesTilleggspensjonBrukt;
    }

    public Boolean getBelopOkt() {
        return belopOkt;
    }

    public void setBelopOkt(Boolean belopOkt) {
        this.belopOkt = belopOkt;
    }

    public Boolean getHjemmelsendring() {
        return hjemmelsendring;
    }

    public void setHjemmelsendring(Boolean hjemmelsendring) {
        this.hjemmelsendring = hjemmelsendring;
    }

    public List<Beholdning> getBeholdningerForrige() {
        return beholdningerForrige;
    }

    public void setBeholdningerForrige(List<Beholdning> beholdningerForrige) {
        this.beholdningerForrige = beholdningerForrige;
    }

    public void addBeholdningForrige(Beholdning beholdning) {
        if (beholdning == null) {
            return;
        }

        beholdningerForrige.add(beholdning);
        beholdning.setBeregningsInformasjon(this);
    }

    public List<Ytelseskomponent> getYtelseskomponenterForrige() {
        return ytelseskomponenterForrige;
    }

    public void setYtelseskomponenterForrige(List<Ytelseskomponent> ytelseskomponenterForrige) {
        this.ytelseskomponenterForrige = ytelseskomponenterForrige;
    }

    public void addYtelseskomponentForrige(Ytelseskomponent ytelseskomponent) {
        if (ytelseskomponent == null) {
            return;
        }

        ytelseskomponent.setBeregningsInformasjon(this);
        ytelseskomponenterForrige.add(ytelseskomponent);
    }

    @SuppressWarnings("unused") // brukes av dozer
    public Land3TegnCti getVurdertBosattLand() {
        return vurdertBosattLand;
    }

    @SuppressWarnings("unused") // brukes av dozer
    public void setVurdertBosattLand(Land3TegnCti vurdertBosattLand) {
        this.vurdertBosattLand = vurdertBosattLand;
    }

    public Boolean getUngUforGarantiFrafalt() {
        return ungUforGarantiFrafalt;
    }

    public void setUngUforGarantiFrafalt(Boolean ungUforGarantiFrafalt) {
        this.ungUforGarantiFrafalt = ungUforGarantiFrafalt;
    }

    @SuppressWarnings("unused") // brukes av dozer?
    public Integer getTtBeregnetForGrunnlagsrolle() {
        return ttBeregnetForGrunnlagsrolle;
    }

    public void setTtBeregnetForGrunnlagsrolle(Integer ttBeregnetForGrunnlagsrolle) {
        this.ttBeregnetForGrunnlagsrolle = ttBeregnetForGrunnlagsrolle;
    }

    public Boolean getAvdodesTrygdetidBrukt() {
        return avdodesTrygdetidBrukt;
    }

    public void setAvdodesTrygdetidBrukt(Boolean avdodesTrygdetidBrukt) {
        this.avdodesTrygdetidBrukt = avdodesTrygdetidBrukt;
    }

    public Boolean getEksport() {
        return eksport;
    }

    public void setEksport(Boolean eksport) {
        this.eksport = eksport;
    }

    /**
     * EPEN190
     * <p>
     * Returnerer sann dersom en ytelseskomponent er innvilget (finnes, nettobeløp > 0, ikke opphørt, bruk = sann),
     * men ikke var det på forrige periode på samme vedtak for en gitt ytelseskomponent
     * </p>
     */
    public boolean isYtelseskomponentInnvilget(YtelseKomponentTypeCode code) {
        for (Belopsendring belopsendring : belopsEndringFraForrigePeriode) {
            if (belopsendring.getYtelseskomponentType() == code && !belopsendring.getInnvilgetForrigePeriode()) {
                for (Ytelseskomponent yk : belopsendring.getBeregningsresultat().hentYtelseskomponentListe(false)) {
                    if (isInnvilgetOfType(code, yk)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean isInnvilgetOfType(YtelseKomponentTypeCode code, Ytelseskomponent yk) {
        return yk.getYtelseKomponentType() == code && yk.getNetto() > 0 && !yk.getOpphort() && yk.getBruk();
    }
}

