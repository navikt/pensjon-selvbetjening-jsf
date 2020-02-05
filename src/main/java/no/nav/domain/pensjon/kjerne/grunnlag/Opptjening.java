package no.nav.domain.pensjon.kjerne.grunnlag;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.beregning.LonnsvekstInformasjon;

/**
 * Opptjeningen for beholdningen et gitt år basert på de forskjellige opptjeningskomponentene.
 * Opptjeningsåret vil være beholdningsår - 2.
 */
//@Entity
//@Table(name = "T_OPPTJENING")
public class Opptjening extends AbstractVersionedPersistentDomainObject {

    private static final long serialVersionUID = -7376225987245702986L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "OPPTJENING_ID")
    private Long opptjeningId;

    /**
     * Året opptjeningen gjelder for.
     */
    @Column(name = "AR")
    private Integer ar;

    /**
     * Opptjeningsgrunnlag. Ubegrenset opptjening (uavkortet total opptjening for det gjeldende året).
     * Kan være enten summen av dagpengeopptjeningen, forstegangstjenesteopptjeningen og enten
     * inntektopptjeningen eller uføreopptjeningen, eller omsorgsopptjeningen.
     */
    @Column(name = "BG")
    private Double opptjeningsgrunnlag;

    /**
     * Anvendt Opptjeningsgrunnlag. Opptjeningsgrunnlag avkortet til 7,1 gjennomsnittlig G for opptjeningsåret.
     */
    @Column(name = "BGA")
    private Double anvendtOpptjeningsgrunnlag;

    /**
     * Årlig opptjening. Tilsvarer 18.1% av anvendtOpptjeningsgrunnlag.
     * Dette beløpet er det som faktisk tilføres pensjonsbeholdningen.
     */
    @Column(name = "BI")
    private Double arligOpptjening;

    /**
     * Innskudd til beholdning uten omsorgsarbeid. (Etter avkorting mot 7,1G og beregning av årlig innskudd på 18,1%).
     */
    @Column(name = "ARLIG_OPPTJ_UTEN_OMS")
    private Double arligOpptjeningUtenOms;

    /**
     * Informasjon om lønnsveksten som er brukt ved regulering av verdi til opptjening, samt dato for regulering.
     * Brukes fra Horisonten.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LONNSVEKST_INFO_ID")
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private LonnsvekstInformasjon lonnsvekstInformasjon;

    /**
     * Innskudd til beholdning for omsorgsarbeid. (Etter avkorting mot 7,1G og beregning av årlig innskudd på 18,1%).
     */
    @Column(name = "ARLIG_OPPTJ_OMSORG")
    private Double arligOpptjeningOmsorg;

    /**
     * Pensjonsgivende inntekt uten dagpenger. Feltet angir hvor mye pgi bidrar til opptjening for året Dersom bruker i tillegg
     * til inntekt også hadde dagpenger så vil dagpengene (og evt. barnetillegg og ferietillegg) være fratrukket i beløpet som
     * her er presentert.
     * Endret fra PGI i CR246039
     */
    @Column(name = "inntekt_uten_dagpenger ")
    private Double inntektUtenDagpenger;

    /**
     * Angir hvor mye omsorg bidrar til opptjening for året.
     */
    @Column(name = "OMSORG")
    private Double omsorg;

    /**
     * Angir hvor mye ordinære dagpenger bidrar til opptjening for året.
     */
    @Column(name = "DAGPENGER")
    private Double dagpenger;

    /**
     * Angir hvor mye dagpenger for fiskere og fangstmenn bidrar til opptjening for året.
     * En person kan både være ordinær dagpengemottaker og motta dagpenger som fiskere og fangstmenn iløpet av ett år.
     */
    @Column(name = "DAGPENGER_FISKERE")
    private Double dagpengerFiskere;

    /**
     * Angir hvor mye forstegangstjeneste bidrar til opptjening for året.
     */
    @Column(name = "FORSTEGANGSTJENESTE")
    private Double forstegangstjeneste;

    /**
     * Angir hvor mye uførepensjon bidrar til opptjening for året. Når denne er angitt er ikke PGI benyttet.
     * Opptjeningen som er beregnet utfra informasjon om uføreperioden samt eventuell inntekt og omsorg.
     */
    // Fjernet i CR185200. Erstattes av uforeOpptjening
    @Deprecated
    @Transient
    //@Column(name = "UFORE")
    private Double ufore;

    /**
     * Beskriver del av opptjening som skyldes uføre. Angir hvor mye uførepensjon bidrar til opptjening for året.
     * Erstatter ufore
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "UFORE_OPPTJENING_ID", nullable = true)
    private Uforeopptjening uforeOpptjening;

    /**
     * Prosentsats for årlig opptjening.
     */
    @Column(name = "P_SATS_OPPTJENING")
    private Double pSatsBeholdning;

    /**
     * Gjennomsnittlig grunnbeløp for opptjeningsåret.
     */
    @Column(name = "VEIET_GRUNNBELOP")
    private Integer veietGrunnbelop;

    public Opptjening() {
    }

    public Opptjening(Opptjening opptjening) {
        this.ar = opptjening.getAr();
        this.opptjeningsgrunnlag = opptjening.getOpptjeningsgrunnlag();
        this.anvendtOpptjeningsgrunnlag = opptjening.getAnvendtOpptjeningsgrunnlag();
        this.arligOpptjening = opptjening.getArligOpptjening();
        this.arligOpptjeningOmsorg = opptjening.getArligOpptjeningOmsorg();
        this.arligOpptjeningUtenOms = opptjening.getArligOpptjeningUtenOms();
        this.lonnsvekstInformasjon = opptjening.getLonnsvekstInformasjon() == null ? null : new LonnsvekstInformasjon(opptjening.getLonnsvekstInformasjon());
        this.inntektUtenDagpenger = opptjening.getInntektUtenDagpenger();
        this.omsorg = opptjening.getOmsorg();
        this.dagpenger = opptjening.getDagpenger();
        this.dagpengerFiskere = opptjening.getDagpengerFiskere();
        this.forstegangstjeneste = opptjening.getForstegangstjeneste();
        this.uforeOpptjening = opptjening.getUforeOpptjening() == null ? null : new Uforeopptjening(opptjening.getUforeOpptjening());
        this.pSatsBeholdning = opptjening.getPSatsBeholdning();
        this.veietGrunnbelop = opptjening.getVeietGrunnbelop();
    }

    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Long getOpptjeningId() {
        return opptjeningId;
    }

    public void setOpptjeningId(Long opptjeningId) {
        this.opptjeningId = opptjeningId;
    }

    public Double getPSatsBeholdning() {
        return pSatsBeholdning;
    }

    public void setPSatsBeholdning(Double satsBeholdning) {
        pSatsBeholdning = satsBeholdning;
    }

    public Double getArligOpptjeningOmsorg() {
        return arligOpptjeningOmsorg;
    }

    public void setArligOpptjeningOmsorg(Double arligOpptjeningOmsorg) {
        this.arligOpptjeningOmsorg = arligOpptjeningOmsorg;
    }

    public Double getArligOpptjeningUtenOms() {
        return arligOpptjeningUtenOms;
    }

    public void setArligOpptjeningUtenOms(Double arligOpptjeningUtenOms) {
        this.arligOpptjeningUtenOms = arligOpptjeningUtenOms;
    }

    public Double getOpptjeningsgrunnlag() {
        return opptjeningsgrunnlag;
    }

    public void setOpptjeningsgrunnlag(Double bg) {
        opptjeningsgrunnlag = bg;
    }

    public Double getAnvendtOpptjeningsgrunnlag() {
        return anvendtOpptjeningsgrunnlag;
    }

    public void setAnvendtOpptjeningsgrunnlag(Double bga) {
        anvendtOpptjeningsgrunnlag = bga;
    }

    public Double getArligOpptjening() {
        return arligOpptjening;
    }

    public void setArligOpptjening(Double bi) {
        arligOpptjening = bi;
    }

    public Double getDagpenger() {
        return dagpenger;
    }

    public void setDagpenger(Double dagpenger) {
        this.dagpenger = dagpenger;
    }

    public Double getDagpengerFiskere() {
        return dagpengerFiskere;
    }

    public void setDagpengerFiskere(Double dagpengerFiskere) {
        this.dagpengerFiskere = dagpengerFiskere;
    }

    public Double getForstegangstjeneste() {
        return forstegangstjeneste;
    }

    public void setForstegangstjeneste(Double forstegangstjeneste) {
        this.forstegangstjeneste = forstegangstjeneste;
    }

    public LonnsvekstInformasjon getLonnsvekstInformasjon() {
        return lonnsvekstInformasjon;
    }

    public void setLonnsvekstInformasjon(LonnsvekstInformasjon lonnsvekstInformasjon) {
        this.lonnsvekstInformasjon = lonnsvekstInformasjon;
    }

    public Double getOmsorg() {
        return omsorg;
    }

    public void setOmsorg(Double omsorg) {
        this.omsorg = omsorg;
    }

    public Double getInntektUtenDagpenger() {
        return inntektUtenDagpenger;
    }

    public void setInntektUtenDagpenger(Double inntektUtenDagpenger) {
        this.inntektUtenDagpenger = inntektUtenDagpenger;
    }

    /**
     * Erstattes av uforeOpptjening
     */
    @Deprecated
    public Double getUfore() {
        return ufore;
    }

    /**
     * Erstattes av uforeOpptjening
     */
    @Deprecated
    public void setUfore(Double ufore) {
        this.ufore = ufore;
    }

    public Uforeopptjening getUforeOpptjening() {
        return uforeOpptjening;
    }

    public void setUforeOpptjening(Uforeopptjening uforeOpptjening) {
        this.uforeOpptjening = uforeOpptjening;
    }

    public Integer getVeietGrunnbelop() {
        return veietGrunnbelop;
    }

    public void setVeietGrunnbelop(Integer veietGrunnbelop) {
        this.veietGrunnbelop = veietGrunnbelop;
    }
}
