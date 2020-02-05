package no.nav.domain.pensjon.kjerne.beregning2011;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.Parameter;
import org.hibernate.annotations.Type;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;

/**
 * Sier hva det gamle beløpet til en ytelseskomponent var, og hva det nye beløpet til denne ytelseskomponenten er.
 */
//@Entity
//@Table(name = "T_BELOPSENDRING")
public class Belopsendring extends AbstractVersionedPersistentDomainObject {

    private static final long serialVersionUID = 169526416387859547L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "BELOPSENDRING_ID")
    private Long belopsendringId;

    /**
     * Angir en spesifikk ytelseskomponenttype.
     */
    @Enumerated(EnumType.STRING)
    @Type(type = "no.nav.domain.pensjon.common.util.IllegalEnumHibernateType",
            parameters = {@Parameter(name = "enumClass", value = "no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode")})
    @Column(name = "K_YTELSE_KOMP_T")
    private YtelseKomponentTypeCode ytelseskomponentType;

    /**
     * Det gamle ytelseskomponent.nettobeløpet
     */
    @Column(name = "BELOP_GAMMEL_YK")
    private Integer belopGammelYk;

    /**
     * Det nye ytelseskomponent.nettobeløpet
     */
    @Column(name = "BELOP_NY_YK")
    private Integer belopNyYk;

    @Column(name = "INNVILGET_FORRIGE_PERIODE")
    private Boolean innvilgetForrigePeriode;

    /**
     * Ikke del av domenemodellen, kun med som del av tilbakereferanse
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_INFO_ID")
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private BeregningsInformasjon beregningsInformasjon;

    /**
     * Ikke del av domenemodellen, kun med som del av tilbakereferanse
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "BEREGNING_ID")
    private Beregning beregning;

    /**
     * Tilbakereferense til det {@link AbstraktBeregningsresultat}-objektet som denne belopsendringen tilhører. Ikke del av domenemodellen.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "BEREGNING_RES_ID")
    private AbstraktBeregningsresultat beregningsresultat;

    /**
     * Tilbakereferense til det {@link AbstraktBeregningsresultat}-objektet som denne belopsendringen tilhører, for forrige periode. Ikke del av domenemodellen.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "BEREGNING_RESULTAT_FORRIGE")
    private AbstraktBeregningsresultat beregningsresultatForrige;

    /**
     * Ikke del av domenemodellen, kun med som del av tilbakereferanse for beregning forrige periode.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "BEREGNING_FORRIGE")
    private Beregning beregningForrige;

    /**
     * Ikke del av domenemodellen, kun med som del av tilbakereferanse for beregningsinformasjon forrige periode.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_INFORMASJON_FORRIGE")
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private BeregningsInformasjon beregningsInformasjonForrige;

    public Belopsendring() {
    }

    public Belopsendring(Belopsendring endring) {
        setYtelseskomponentType(endring.getYtelseskomponentType());
        setBelopGammelYk(endring.getBelopGammelYk());
        setBelopNyYk(endring.getBelopNyYk());
        setInnvilgetForrigePeriode(endring.getInnvilgetForrigePeriode());
        setBeregningForrige(endring.getBeregningForrige());
        setBeregningsInformasjonForrige(endring.getBeregningsInformasjonForrige());
        setBeregningsresultatForrige(endring.getBeregningsresultatForrige());
    }

    public Integer getBelopGammelYk() {
        return belopGammelYk;
    }

    public void setBelopGammelYk(Integer belopGammelYk) {
        this.belopGammelYk = belopGammelYk;
    }

    public Integer getBelopNyYk() {
        return belopNyYk;
    }

    public void setBelopNyYk(Integer belopNyYk) {
        this.belopNyYk = belopNyYk;
    }

    public Long getBelopsendringId() {
        return belopsendringId;
    }

    public void setBelopsendringId(Long belopsendringId) {
        this.belopsendringId = belopsendringId;
    }

    public YtelseKomponentTypeCode getYtelseskomponentType() {
        return ytelseskomponentType;
    }

    public void setYtelseskomponentType(YtelseKomponentTypeCode ytelseskomponentType) {
        this.ytelseskomponentType = ytelseskomponentType;
    }

    public Beregning getBeregning() {
        return beregning;
    }

    public void setBeregning(Beregning beregning) {
        this.beregning = beregning;
    }

    public BeregningsInformasjon getBeregningsInformasjon() {
        return beregningsInformasjon;
    }

    public void setBeregningsInformasjon(BeregningsInformasjon beregningsInformasjon) {
        this.beregningsInformasjon = beregningsInformasjon;
    }

    /**
     * Returns a backward reference to the {@link AbstraktBeregningsresultat} that owns this {@link Belopsendring}.
     */
    public AbstraktBeregningsresultat getBeregningsresultat() {
        return beregningsresultat;
    }

    /**
     * Sets a backward reference to the {@link AbstraktBeregningsresultat} that contains this {@link Belopsendring} instance.
     */
    public void setBeregningsresultat(AbstraktBeregningsresultat beregningsresultat) {
        this.beregningsresultat = beregningsresultat;
    }

    public Boolean getInnvilgetForrigePeriode() {
        return innvilgetForrigePeriode;
    }

    public void setInnvilgetForrigePeriode(Boolean innvilgetForrigePeriode) {
        this.innvilgetForrigePeriode = innvilgetForrigePeriode;
    }

    public AbstraktBeregningsresultat getBeregningsresultatForrige() {
        return beregningsresultatForrige;
    }

    public void setBeregningsresultatForrige(AbstraktBeregningsresultat beregningsresultatForrige) {
        this.beregningsresultatForrige = beregningsresultatForrige;
    }

    public Beregning getBeregningForrige() {
        return beregningForrige;
    }

    public void setBeregningForrige(Beregning beregningForrige) {
        this.beregningForrige = beregningForrige;
    }

    public BeregningsInformasjon getBeregningsInformasjonForrige() {
        return beregningsInformasjonForrige;
    }

    public void setBeregningsInformasjonForrige(BeregningsInformasjon beregningsInformasjonForrige) {
        this.beregningsInformasjonForrige = beregningsInformasjonForrige;
    }
}
