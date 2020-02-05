package no.nav.domain.pensjon.kjerne.beregning;

import static java.util.Base64.getDecoder;
import static java.util.Base64.getEncoder;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DiscriminatorOptions;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import no.nav.domain.pensjon.AbstractMerknadDomainObject;
import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
import no.nav.domain.pensjon.annotations.IgnoreOnCopyReason;
import no.nav.domain.pensjon.common.Copyable;
import no.nav.domain.pensjon.common.TypedInformation;
import no.nav.domain.pensjon.common.Usable;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningsInformasjon;
import no.nav.domain.pensjon.kjerne.beregning2011.PensjonUnderUtbetaling;
import no.nav.domain.pensjon.kjerne.beregning2011.ReguleringsInformasjon;
import no.nav.domain.pensjon.kjerne.kodetabeller.FormelKodeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.ResultatKildeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;

/**
 * Ytelseskomponent represents a delytelse on beregning.
 * Note that the back reference to {@link Beregning} must be handled in each of the subclasses. See
 * http://clarkupdike.blogspot.com/2007/01/hibernate-mappedby-to-superclass.html.
 */
//@Entity
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name = "K_YTELSE_KOMP_T", discriminatorType = DiscriminatorType.STRING, length = 20)
//@DiscriminatorOptions(force = true)
//@Table(name = "T_YTELSE_KOMP")
public abstract class Ytelseskomponent<T extends Ytelseskomponent<T>> extends AbstractMerknadDomainObject
        implements Copyable<T>, TypedInformation<YtelseKomponentTypeCode>, Usable {
    private static final long serialVersionUID = 1820326924777300111L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "YTELSE_KOMP_ID")
    @IgnoreOnCopy(reason = IgnoreOnCopyReason.PRIMARY_KEY)
    private Long ytelseskomponentId;

    /**
     * Beløp før avkorting
     */
    @Column(name = "BRUTTO", nullable = true)
    private Integer brutto = 0;

    /**
     * Avrundet månedsbeløp etter avkorting
     */
    @Column(name = "NETTO", nullable = true)
    private Integer netto = 0;

    /**
     * Avrundet årsbeløp etter avkorting
     */
    @Column(name = "NETTO_PER_AR", nullable = true)
    private Integer nettoPerAr = 0;

    /**
     * Fradraget: brutto  netto
     */
    @Column(name = "FRADRAG", nullable = true)
    private Integer fradrag = 0;

    /**
     * Fradrag per år: (brutto  netto) * 12
     */
    @Column(name = "FRADRAG_PER_AR", nullable = true)
    private Double fradragPerAr = 0.0;

    /**
     * Sier om denne komponenten er brukt i utregningen av pensjonen
     */
    @Column(name = "BRUK", nullable = false)
    private Boolean erBrukt = false;

    /**
     * Flagg som angir om dette er en representant for en opphort ytelseskompenent
     */
    @Column(name = "OPPHORT", nullable = true)
    private Boolean opphort = false;

    /**
     * Id fra kvittering på overført transaksjon fra OS
     */
    @Column(name = "OS_LINJE_ID_FK", nullable = true)
    private Integer osLinjeIdFk = 0;

    /**
     * Kilden til beregningen som angir om beregningen er utført automatisk av regelmotoren, manuelt av en saksbehandler eller
     * om den er konvertert inn.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_RESULTAT_KILDE", nullable = false)
    private ResultatKildeCode resultatKilde;

    /**
     * Angir type sak yteleskomponenten gjelder.
     */
    @ManyToOne
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_SAK_T")
    private SakTypeCti sakType;

    /**
     * Hvis true F-transe, ellers T-transe.
     */
    @Column(name = "FRADRAGS_TRANS", nullable = true)
    private Boolean fradragsTransaksjon = false;

    /**
     * The PensjonUnderUtbetaling (Beregning2011 related) of the ytelseskomponent
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PEN_UNDER_UTBET_ID", nullable = true)
    @IgnoreOnCopy(reason = "PensjonUnderUtbetaling is the owner of ytelseskomponent and should not be copied")
    private PensjonUnderUtbetaling pensjonUnderUtbetaling;

    /**
     * Årsbeløp
     */
    @Column(name = "BRUTTO_PER_AR", nullable = true)
    private Double bruttoPerAr = 0d;

    /**
     * Formelkoder inneholder en kode som forteller hvordan ytelseskomponenten er beregnet hos PREG.
     * Formelkodenen benyttes av PSELV som bruker koden til å slå opp i kodeverket for å avgjøre hvilket
     * formelbilde som skal benyttes for en ytelseskomponent ved visning av beregningsdetaljer for alderspensjon.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_FORMELKODE", nullable = true)
    private FormelKodeCode formelKode;

    /**
     * Tilbakereferanse til beregningsinformasjon
     */
    @ManyToOne()
    @JoinColumn(name = "BEREGNING_INFO_ID", nullable = true)
    @Fetch(FetchMode.SELECT)
    @IgnoreOnCopy(reason = "BeregningsInformasjon is the owner of ytelseskomponent and should not be copied")
    private BeregningsInformasjon beregningsInformasjon;

    @ManyToOne(cascade = CascadeType.ALL)
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "REGULERING_INFO_ID")
    private ReguleringsInformasjon reguleringsInformasjon;

    /**
     * CR 160808 - kun tilbakereferanse
     */
    @ManyToOne()
    @JoinColumn(name = "FRADRAG_YK_BEREGNING", nullable = true)
    @Fetch(FetchMode.SELECT)
    @IgnoreOnCopy(reason = "fradragBeregning is the owner of ytelseskomponent and should not be copied")
    @SuppressWarnings("all") // brukes av hibernate
    private Beregning fradragBeregning;

    /**
     * The entire formelMap (Serialized to String)
     */
    @Lob
    @Column(name = "FORMEL")
    private String formel;

    /**
     * This object is not persisted, but is serialized and persisted as <i>formel</i>
     * When using formelMap, it is a deserialized <i>formel</i> through <code>getFormelMap()</code>
     */
    @Transient
    @IgnoreOnCopy(reason = "Do not copy formelMap.")
    private Map<String, Formel> formelMap = new HashMap<>();

    public Ytelseskomponent(T komponent) {
        setBrutto(komponent.getBrutto());
        setNetto(komponent.getNetto());
        setFradrag(komponent.getFradrag());
        setErBrukt(komponent.getErBrukt());
        setOpphort(komponent.getOpphort());
        setOsLinjeIdFk(komponent.getOsLinjeIdFk());
        setResultatKilde(komponent.getResultatKilde());
        setMerknadListe(komponent.getMerknadListe());
        setSakType(komponent.getSakType());
        setFradragsTransaksjon(komponent.getFradragsTransaksjon());
        setBruttoPerAr(komponent.getBruttoPerAr());
        setFormelKode(komponent.getFormelKode());
        setReguleringsInformasjon(komponent.getReguleringsInformasjon() == null ? null : new ReguleringsInformasjon(komponent.getReguleringsInformasjon()));
        setFormelCopy(komponent.getFormel());
    }

    public Ytelseskomponent() {
        super();
    }

    /**
     * Note: <b>This method must be overridden in all subclasses!</b>
     */
    @Transient
    public Beregning getRelatertBeregning() {
        return null;
    }

    /**
     * Dummy som brukes i dozer mapping - ikke slett denne!
     */
    @SuppressWarnings("unused")
    public void dummyMethod(Object dummy) {
    }

    protected abstract void setBeregning(Beregning beregning);

    /**
     * Creates a clone of Ytelseskomponent. Note that {@link Beregning} must be set by caller.
     */
    @Override
    public T copy() {
        T copy = innerCopy();
        copy.setNettoPerAr(nettoPerAr);
        copy.setFradragPerAr(fradragPerAr);
        copy.setBrutto(brutto);
        copy.setNetto(netto);
        copy.setFradrag(fradrag);
        copy.setErBrukt(erBrukt);
        copy.setResultatKilde(resultatKilde);
        copy.setOpphort(opphort);
        copy.setOsLinjeIdFk(osLinjeIdFk);
        copy.setSakType(sakType);
        copy.setFradragsTransaksjon(fradragsTransaksjon);
        copy.setFormelKode(formelKode);
        copy.setBruttoPerAr(bruttoPerAr);
        copy.setReguleringsInformasjon(reguleringsInformasjon == null ? null : new ReguleringsInformasjon(reguleringsInformasjon));
        copy.setMerknadListe(getMerknadListe()); // merknader from immutable strings in database, only a transfer of these values is necessary
        copy.setFormelCopy(formel);
        return copy;
    }

    protected abstract T innerCopy();

    /**
     * Get the YtelsesKomponentType for this Ytelsekomponent.
     *
     * @return YtelseKomponentType
     * @throws IllegalArgumentException if there was a problem creating the enum. Reasons for this might be errors in databasemapping (i.e.
     * discriminator values with no corresponding enum codes) or trying to call this method on the Ytelseskomponent
     * superclass - which has no disriminator
     */
    public YtelseKomponentTypeCode getYtelseKomponentType() {
        String ytelsesKomponentType = getYtelsesKomponentTypeAsString();

        if (ytelsesKomponentType == null) {
            throw new IllegalArgumentException("No discriminator defined for class " + getClass().getName());
        }

        try {
            return YtelseKomponentTypeCode.valueOf(ytelsesKomponentType);
        } catch (IllegalArgumentException e) {
            // Assume that ytelsesKomponentType uses an illegal code
            return YtelseKomponentTypeCode.valueOf("P_" + ytelsesKomponentType);
        }
    }

    @Override
    public YtelseKomponentTypeCode getTypeCode() {
        return getYtelseKomponentType();
    }

    public boolean isMotregning() {
        return this instanceof MotregningYtelseskomponent;
    }

    /**
     * Use reflection to get the discriminator value for this class.
     */
    private String getYtelsesKomponentTypeAsString() {
        String ytelsesKomponentType = null;
        DiscriminatorValue discriminatorValue = getClass().getAnnotation(DiscriminatorValue.class);

        if (discriminatorValue != null) {
            ytelsesKomponentType = discriminatorValue.value();
        }

        return ytelsesKomponentType;
    }

    public Integer getBrutto() {
        return brutto;
    }

    public void setBrutto(Integer brutto) {
        this.brutto = brutto;
    }

    public Boolean getErBrukt() {
        return erBrukt;
    }

    public void setErBrukt(Boolean erBrukt) {
        this.erBrukt = erBrukt;
    }

    public Integer getFradrag() {
        return fradrag;
    }

    public void setFradrag(Integer fradrag) {
        this.fradrag = fradrag;
    }

    public Integer getNetto() {
        return netto;
    }

    public void setNetto(Integer netto) {
        this.netto = netto;
    }

    public Double getFradragPerAr() {
        return fradragPerAr;
    }

    public void setFradragPerAr(Double fradragPerAr) {
        this.fradragPerAr = fradragPerAr;
    }

    public Integer getNettoPerAr() {
        return nettoPerAr;
    }

    public void setNettoPerAr(Integer nettoPerAr) {
        this.nettoPerAr = nettoPerAr;
    }

    public Long getYtelseskomponentId() {
        return ytelseskomponentId;
    }

    public void setYtelseskomponentId(Long ytelseskomponentId) {
        this.ytelseskomponentId = ytelseskomponentId;
    }

    public ResultatKildeCode getResultatKilde() {
        return resultatKilde;
    }

    public void setResultatKilde(ResultatKildeCode resultatKilde) {
        this.resultatKilde = resultatKilde;
    }

    public Boolean getOpphort() {
        return opphort;
    }

    public void setOpphort(Boolean opphort) {
        this.opphort = opphort;
    }

    public Integer getOsLinjeIdFk() {
        return osLinjeIdFk;
    }

    public void setOsLinjeIdFk(Integer osLinjeIdFk) {
        this.osLinjeIdFk = osLinjeIdFk;
    }

    /**
     * EPEN070 hentSamletInntektBruktIAvkortning
     * <p>
     * For ytelseskomponentene Ektefelletillegg, BarnetilleggfellesBarn og Barnetilleggserkullsbarn returneres samletInntektAvkort. For andre ytelseskomponenter returneres 0.
     * </p>
     */
    public Integer hentSamletInntektBruktIAvkortning() {
        return 0;
    }

    public Boolean getFradragsTransaksjon() {
        return fradragsTransaksjon;
    }

    public void setFradragsTransaksjon(Boolean fradragsTransaksjon) {
        this.fradragsTransaksjon = fradragsTransaksjon;
    }

    public SakTypeCti getSakType() {
        return sakType;
    }

    public void setSakType(SakTypeCti sakType) {
        this.sakType = sakType;
    }

    public Double getBruttoPerAr() {
        return bruttoPerAr;
    }

    public void setBruttoPerAr(Double bruttoPerAr) {
        this.bruttoPerAr = bruttoPerAr;
    }

    public PensjonUnderUtbetaling getPensjonUnderUtbetaling() {
        return pensjonUnderUtbetaling;
    }

    public void setPensjonUnderUtbetaling(PensjonUnderUtbetaling pensjonUnderUtbetaling) {
        this.pensjonUnderUtbetaling = pensjonUnderUtbetaling;
    }

    public FormelKodeCode getFormelKode() {
        return formelKode;
    }

    public void setFormelKode(FormelKodeCode formelKode) {
        this.formelKode = formelKode;
    }

    @Transient
    @SuppressWarnings("unused") // brukes i pselv
    public boolean hasFormelKode(FormelKodeCode formelKode) {
        return this.formelKode == formelKode;
    }

    public BeregningsInformasjon getBeregningsInformasjon() {
        return beregningsInformasjon;
    }

    public void setBeregningsInformasjon(BeregningsInformasjon beregningsInformasjon) {
        this.beregningsInformasjon = beregningsInformasjon;
    }

    public ReguleringsInformasjon getReguleringsInformasjon() {
        return reguleringsInformasjon;
    }

    public void setReguleringsInformasjon(ReguleringsInformasjon reguleringsInformasjon) {
        this.reguleringsInformasjon = reguleringsInformasjon;
    }

    protected void setFradragBeregning(Beregning fradragBeregning) {
        this.fradragBeregning = fradragBeregning;
    }

    @Override
    public Boolean getBruk() {
        return getErBrukt();
    }

    public String getFormel() {
        return this.formel;
    }

    /**
     * This should only be used by copy-constructors and test.
     * The variable will be set when deserializing object retrieved from PREG after mapping through Dozer
     * See method <code>setFormelMap()</code>
     */
    public void setFormelCopy(String formel) {
        this.formel = formel;
    }

    public Map<String, Formel> getFormelMap() {
        if (formelMap.isEmpty() && formel != null) {
            formelMap = deSerializeFormler();
        }

        return new HashMap<>(formelMap);
    }

    public void setFormelMap(Map<String, Formel> formelMap) throws IOException {
        this.formelMap.clear();
        this.formelMap.putAll(formelMap);
        serializeFormel(formelMap);
    }

    private void serializeFormel(Map<String, Formel> formelMap) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream)) {
            objectOutputStream.writeObject(formelMap);
        }

        this.formel = getEncoder().encodeToString(byteArrayOutputStream.toByteArray());
    }

    @SuppressWarnings("unchecked")
    private Map<String, Formel> deSerializeFormler() {
        Map<String, Formel> object;

        try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(getDecoder().decode(this.formel.getBytes()))) {
            ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
            object = (Map<String, Formel>) objectInputStream.readObject();
        } catch (Exception e) {
            object = new HashMap<>();
        }

        return object;
    }
}
