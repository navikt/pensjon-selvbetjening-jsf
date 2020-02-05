package no.nav.domain.pensjon.kjerne.grunnlag;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.common.ArligInformasjon;
import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.common.TypedInformation;
import no.nav.domain.pensjon.common.util.EnumUtils;
import no.nav.domain.pensjon.kjerne.beregning.LonnsvekstInformasjon;
import no.nav.domain.pensjon.kjerne.beregning2011.Beholdninger;
import no.nav.domain.pensjon.kjerne.beregning2011.Beregning2011;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningsInformasjon;
import no.nav.domain.pensjon.kjerne.beregning2011.ReguleringsInformasjon;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeholdningsTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.FormelKodeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagKildeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagVedleggType;
import no.nav.domain.pensjon.kjerne.kodetabeller.RestpensjonBeholdningOppdateringArsakCode;

/**
 * En beholdning holder på den totale opptjente beholdning for en person. Det er fire ulike typer beholdning som er skilt
 * gjennom feltet beholdningsType. Disse fire typene omfatter
 * <ul>
 * <li>Pensjonsbeholdning
 * <li>Garantipensjonsbeholdning
 * <li>Garantitilleggsbeholdning
 * <li>AFP beholdning
 * </ul>
 * AFP beholdning gjelder for brukere som er født i 1948 eller senere. De øvrige beholdningene gjelder for brukere født i 1954
 * eller senere og som har pensjonsopptjening. Pensjonsbeholdning bygges opp av førstegangsavtjening, omsorgsarbeid,
 * dagpengegrunnlag, pensjonsgivende inntekt og perioder med uførepensjon. Pensjonsbeholdning under opptjening reguleres årlig
 * med lønnsveksten. Godskriving av ny opptjening til beholdning skjer med virkning fra og med 01.januar året etter likningen
 * foreligger.
 * Beholdninger av typene garantipensjonsbeholdning og garantitilleggsbeholdning vil først oppstå ved uttak, og hvor sistnevnte
 * kun kan oppstå etter fylte 67år. Garantipensjonsbeholdningen kan ta negative verdier.
 */
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "K_BEHOLDNING_T", discriminatorType = DiscriminatorType.STRING, length = 20)
//@DiscriminatorOptions(force = true)
//@Table(name = "T_BEHOLDNING")
public abstract class Beholdning extends AbstractMerknadDomainObject
        implements PeriodisertInformasjon, ArligInformasjon, TypedInformation<BeholdningsTypeCode>, AbstractGrunnlag<BeholdningVedlegg> {

    private static final long serialVersionUID = -7601772280297775161L;

    /**
     * Unik id for en beholdning
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BEHOLDNING_ID", nullable = false)
    private Integer beholdningId;

    /**
     * Året beholdningen gjelder for.
     */
    @Column(name = "AR", nullable = true)
    private Integer ar;

    /**
     * Angir verdi for beholdning.
     */
    @Column(name = "TOTALBELOP", nullable = true)
    private Double totalbelop;

    /**
     * Opptjening skal alltid være beregnet.
     */
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "OPPTJENING_ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    private Opptjening opptjening;

    /**
     * Beskriver hvordan en beholdning er regulert. CR207092.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "regulering_info_id", nullable = true)
    @Fetch(FetchMode.SELECT)
    private ReguleringsInformasjon reguleringsinformasjon;

    /**
     * Beskriver informasjon i forbindelse med regulering. CR207092.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LONNSVEKST_INFO_ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    private LonnsvekstInformasjon lonnsvekstInformasjon;

    /**
     * Kun tilbakereferanse, ikke del av domenemodellen
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_GRUNNLAG_ID", nullable = true, updatable = false)
    @Fetch(FetchMode.SELECT)
    private Persongrunnlag persongrunnlag;

    /**
     * Tilbakereferanse til Beholdninger. Mulig vi må gjøre noe a la getBeregning2011 for at dette skal virke for subklassene
     * til beholdning
     */
    @ManyToOne()
    @JoinColumn(name = "BEHOLDNINGER_ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    private Beholdninger beholdninger;

    /**
     * Formelkoder inneholder en kode som forteller hvordan ytelseskomponenten er beregnet hos PREG.
     * Formelkodenen benyttes av PSELV som bruker koden til å slå opp i kodeverket for å avgjøre hvilket
     * formelbilde som skal benyttes for en ytelseskomponent ved visning av beregningsdetaljer for alderspensjon.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_FORMELKODE", nullable = true)
    private FormelKodeCode formelKode;

    /**
     * Tilbakereferanse til BeregningsInformasjon. Mulig vi må gjøre noe a la getBeregning2011 for at dette skal virke for subklassene
     * til beholdning
     */
    @ManyToOne()
    @JoinColumn(name = "BEREGNING_INFO_ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    private BeregningsInformasjon beregningsInformasjon;

    /**
     * Dato beholdningen er gyldig fra og med.
     */
    @Column(name = "DATO_FOM", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date fomDato;

    /**
     * Dato beholdningen er gyldig til og med.
     */
    @Column(name = "DATO_TOM", nullable = true)
    @Type(type = "no.stelvio.domain.usertype.DateUserType")
    private Date tomDato;

    /**
     * Angir kilden for beholdningen.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_GRUNNLAG_KILDE", nullable = true)
    private GrunnlagKildeCode grunnlagKilde;

    /**
     * Årsaken til at beholdningen er opprettet, er koblet til K_REST_BEH_ARSAK
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_REST_BEH_ARSAK_T", nullable = true)
    private RestpensjonBeholdningOppdateringArsakCode oppdateringArsak;

    /**
     * Registerets informasjon om hvem som sist endret informasjonen. Kun definert hvis grunnlagKilde er et register.
     */
    @Column(name = "REGISTER_OPPRETTET_AV", nullable = true)
    private String registerOpprettetAv;

    @SuppressWarnings("all") // used by hibernate to enable mapping
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GRUNNLAG_VEDLEGG_ID", nullable = true)
    private BeholdningVedlegg grunnlagVedlegg;

    /**
     * Helper method for the copy constructor. Copy the src object except ChangeStamp, versjon, and persongrunnlag.
     * This behaves much like the Object.clone method, but we choose not to use clone.
     */
    public abstract Beholdning copy();

    /**
     * Hibernate workaround.
     */
    public abstract Beregning2011 getBeregning2011();

    protected void copyFields(Beholdning beholdning) {
        setAr(beholdning.getAr());
        setFomDato(beholdning.getFomDato());
        setGrunnlagKilde(beholdning.getGrunnlagKilde());
        setOppdateringArsak(beholdning.getOppdateringArsak());
        setOpptjening(beholdning.getOpptjening() == null ? null : new Opptjening(beholdning.getOpptjening()));
        setRegisterOpprettetAv(beholdning.getRegisterOpprettetAv());
        setTomDato(beholdning.getTomDato());
        setTotalbelop(beholdning.getTotalbelop());
        setLonnsvekstInformasjon(beholdning.getLonnsvekstInformasjon() == null ? null : new LonnsvekstInformasjon(beholdning.getLonnsvekstInformasjon()));
        setReguleringsinformasjon(beholdning.getReguleringsinformasjon() == null ? null : new ReguleringsInformasjon(beholdning.getReguleringsinformasjon()));

        // the list with merknads contains immutable objects persisted as string. the list is therefore only transferred from src
        setMerknadListe(beholdning.getMerknadListe());
    }

    @Override
    public GrunnlagVedleggType getGrunnlagVedleggType() {
        return GrunnlagVedleggType.BEHOLDNING;
    }

    @Override
    public void setGrunnlagVedlegg(BeholdningVedlegg grunnlagVedlegg) {
        this.grunnlagVedlegg = grunnlagVedlegg;
    }

    /**
     * Get the discriminator for this Beholdning
     *
     * @return BeholdningsTypeCode
     * @throws IllegalArgumentException if there was a problem creating the enum. Reasons for this might be errors in databasemapping (i.e.
     *                                  discriminator values with no corresponding enum codes) or trying to call this method on the Beregning2011
     *                                  abstract superclass - which has no discriminator
     */
    public BeholdningsTypeCode getBeholdningsType() {
        String diskriminator = getBeholdningsTypeAsString();

        if (diskriminator == null) {
            throw new IllegalArgumentException("No discriminator defined for class " + getClass().getName());
        }

        return EnumUtils.valueOf(BeholdningsTypeCode.class, diskriminator);
    }

    /**
     * Use reflection to get the discriminator value for this class.
     */
    private String getBeholdningsTypeAsString() {
        String discriminator = null;
        DiscriminatorValue discriminatorValue = getClass().getAnnotation(DiscriminatorValue.class);

        if (discriminatorValue != null) {
            discriminator = discriminatorValue.value();
        }

        return discriminator;
    }

    @Override
    public BeholdningsTypeCode getTypeCode() {
        return getBeholdningsType();
    }

    @Override
    public Integer getAr() {
        return ar;
    }

    public void setAr(Integer ar) {
        this.ar = ar;
    }

    public Integer getBeholdningId() {
        return beholdningId;
    }

    public void setBeholdningId(Integer beholdningId) {
        this.beholdningId = beholdningId;
    }

    public Opptjening getOpptjening() {
        return opptjening;
    }

    public void setOpptjening(Opptjening opptjening) {
        this.opptjening = opptjening;
    }

    public Double getTotalbelop() {
        return totalbelop;
    }

    public void setTotalbelop(Double totalbelop) {
        this.totalbelop = totalbelop;
    }

    @Override
    public Date getFomDato() {
        return fomDato == null ? null : new Date(fomDato.getTime());
    }

    @Override
    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato == null ? null : new Date(fomDato.getTime());
    }

    public GrunnlagKildeCode getGrunnlagKilde() {
        return grunnlagKilde;
    }

    public void setGrunnlagKilde(GrunnlagKildeCode grunnlagKilde) {
        this.grunnlagKilde = grunnlagKilde;
    }

    public RestpensjonBeholdningOppdateringArsakCode getOppdateringArsak() {
        return oppdateringArsak;
    }

    public void setOppdateringArsak(RestpensjonBeholdningOppdateringArsakCode oppdateringArsak) {
        this.oppdateringArsak = oppdateringArsak;
    }

    public String getRegisterOpprettetAv() {
        return registerOpprettetAv;
    }

    public void setRegisterOpprettetAv(String registerOpprettetAv) {
        this.registerOpprettetAv = registerOpprettetAv;
    }

    @Override
    public Date getTomDato() {
        return tomDato == null ? null : new Date(tomDato.getTime());
    }

    @Override
    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato == null ? null : new Date(tomDato.getTime());
    }

    public Persongrunnlag getPersongrunnlag() {
        return persongrunnlag;
    }

    public void setPersongrunnlag(Persongrunnlag persongrunnlag) {
        this.persongrunnlag = persongrunnlag;
    }

    public Beholdninger getBeholdninger() {
        return beholdninger;
    }

    public void setBeholdninger(Beholdninger beholdninger) {
        this.beholdninger = beholdninger;
    }

    public BeregningsInformasjon getBeregningsInformasjon() {
        return beregningsInformasjon;
    }

    public void setBeregningsInformasjon(BeregningsInformasjon beregningsInformasjon) {
        this.beregningsInformasjon = beregningsInformasjon;
    }

    public ReguleringsInformasjon getReguleringsinformasjon() {
        return reguleringsinformasjon;
    }

    public void setReguleringsinformasjon(ReguleringsInformasjon reguleringsinformasjon) {
        this.reguleringsinformasjon = reguleringsinformasjon;
    }

    public LonnsvekstInformasjon getLonnsvekstInformasjon() {
        return lonnsvekstInformasjon;
    }

    public void setLonnsvekstInformasjon(LonnsvekstInformasjon lonnsvekstInformasjon) {
        this.lonnsvekstInformasjon = lonnsvekstInformasjon;
    }

    public FormelKodeCode getFormelKode() {
        return formelKode;
    }

    public void setFormelKode(FormelKodeCode formelKode) {
        this.formelKode = formelKode;
    }
}
