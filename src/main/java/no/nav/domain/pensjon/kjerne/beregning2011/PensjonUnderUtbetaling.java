package no.nav.domain.pensjon.kjerne.beregning2011;

import static java.lang.Boolean.TRUE;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cascade;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;
import no.nav.domain.pensjon.kjerne.kodetabeller.FormelKodeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseKomponentTypeCode;

/**
 * Objektet inneholder den faktiske pensjonen under utbetaling, samt en liste over delytelsene som denne består av.
 */
//@Entity
//@Table(name = "T_PEN_UNDER_UTBET")
public class PensjonUnderUtbetaling extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = -2433672478010711940L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PEN_UNDER_UTBET_ID", nullable = false)
    private Long pensjonUnderUtbetalingId;

    @Column(name = "TOTAL_BELOP_NETTO", nullable = true)
    private Integer totalbelopNetto = 0;

    /**
     * Årlig beløp under utbetaling
     */
    @Column(name = "TOTAL_BELOP_NETTO_AR", nullable = true)
    private Double totalbelopNettoAr = 0.0;

    /**
     * Angir sum netto per mnd
     */
    @Column(name = "TOTAL_BELOP_BRUTTO", nullable = true)
    private Integer totalbelopBrutto = 0;

    /**
     * Angir sum netto per mnd
     */
    @Column(name = "TOTAL_BELOP_BRUTTO_AR", nullable = true)
    private Double totalbelopBruttoAr = 0.0;

    @OneToMany(mappedBy = "pensjonUnderUtbetaling", fetch = FetchType.LAZY, orphanRemoval = true)
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private List<Ytelseskomponent> ytelseskomponenter = new ArrayList<Ytelseskomponent>();

    @ManyToOne
    @org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "K_FORMELKODE", nullable = true)
    private FormelKodeCti formelKode;

    /**
     * Reguleringsfaktor. Denne blir satt til 0,0075
     */
    @Column(name = "PUB_REG_FRATREKK", nullable = true)
    private Double pubReguleringFratrekk;

    public FormelKodeCti getFormelKode() {
        return formelKode;
    }

    public void setFormelKode(FormelKodeCti formelKode) {
        this.formelKode = formelKode;
    }

    public PensjonUnderUtbetaling() {
        super();
    }

    /**
     * Copy constructor. Does not copy changestamp, id or inverse references
     */
    public PensjonUnderUtbetaling(PensjonUnderUtbetaling pensjon) {
        totalbelopNetto = pensjon.getTotalbelopNetto();
        totalbelopNettoAr = pensjon.getTotalbelopNettoAr();
        formelKode = pensjon.getFormelKode();
        pubReguleringFratrekk = pensjon.getPubReguleringFratrekk();

        for (Ytelseskomponent komponent : pensjon.getYtelseskomponenter()) {
            addYtelseskomponent(komponent.copy());
        }
    }

    /**
     * EPEN105 hasEndretNetto
     * Returns true if the difference of totalbelopNetto is greater than toleranseGrense.
     */
    public boolean hasEndretNetto(PensjonUnderUtbetaling nyttPensjonUnderUtbetaling) {
        return Math.abs(totalbelopNetto - nyttPensjonUnderUtbetaling.totalbelopNetto) > 0;
    }

    /**
     * Add a list of ytelseskomponent objects - used by dozer
     */
    public void addYtelseskomponentListe(List<Ytelseskomponent> ytelseskomponentListe) {
        if (ytelseskomponentListe == null) {
            return;
        }

        for (Ytelseskomponent komponent : ytelseskomponentListe) {
            addYtelseskomponent(komponent);
        }
    }

    public void addYtelseskomponent(Ytelseskomponent ytelseskomponent) {
        if (ytelseskomponent == null) {
            return;
        }

        ytelseskomponenter.add(ytelseskomponent);
        ytelseskomponent.setPensjonUnderUtbetaling(this);
    }

    public List<Ytelseskomponent> hentYtelseskomponenterIBruk() {
        List<Ytelseskomponent> bruktekomponenter = new ArrayList<>();

        for (Ytelseskomponent k : ytelseskomponenter) {
            if (TRUE.equals(k.getErBrukt())) {
                bruktekomponenter.add(k);
            }
        }

        return bruktekomponenter;
    }

    public List<Ytelseskomponent> findYtelseskomponenterIBrukHavingBruttoBelop() {
        return hentYtelseskomponenterIBruk().stream().filter(input -> input.getBruttoPerAr() > 0d).collect(Collectors.toList());
    }

    public List<Ytelseskomponent> findYtelseskomponenterIBrukHavingBruttoBelopAndNotOpphort() {
        return hentYtelseskomponenterIBruk().stream().filter(input -> input.getBrutto() > 0d)
                .filter(input -> !input.getOpphort()).collect(Collectors.toList());
    }

    /**
     * EPEN077 based on PK-9186 get the ytelseskomponent by type.
     */
    public List<Ytelseskomponent> hentYtelseskomponentByType(YtelseKomponentTypeCode typeCode, boolean kunIBruk) {
        return hentYtelseskomponentByType(typeCode, kunIBruk, false);
    }

    /**
     * Overload on EPEN077 - implemented with regards to PKFEIL-2674
     */
    public List<Ytelseskomponent> hentYtelseskomponentByType(YtelseKomponentTypeCode typeCode, boolean kunIBruk, boolean withoutOpphort) {
        List<Ytelseskomponent> ytelseskomponents = new ArrayList<>();

        for (Ytelseskomponent ytelseskomponent : getYtelseskomponenter()) {
            if (ytelseskomponent.getYtelseKomponentType().equals(typeCode)) {
                if (!kunIBruk) {
                    ytelseskomponents.add(ytelseskomponent);
                } else if (ytelseskomponent.getErBrukt()) {
                    ytelseskomponents.add(ytelseskomponent);
                }
            }
        }

        if (withoutOpphort) {
            ytelseskomponents.removeIf(Ytelseskomponent::getOpphort);
        }

        return ytelseskomponents;
    }

    /**
     * Overload on EPEN077 - implemented with regards to PK-54072
     */
    public List<Ytelseskomponent> hentYtelseskomponentList(boolean kunIBruk, boolean withoutOpphort) {
        List<Ytelseskomponent> ytelseskomponents = new ArrayList<>();

        for (Ytelseskomponent ytelseskomponent : getYtelseskomponenter()) {
            if (!kunIBruk) {
                ytelseskomponents.add(ytelseskomponent);
            } else if (ytelseskomponent.getErBrukt()) {
                ytelseskomponents.add(ytelseskomponent);
            }
        }

        if (withoutOpphort) {
            ytelseskomponents.removeIf(Ytelseskomponent::getOpphort);
        }

        return ytelseskomponents;
    }

    /**
     * EPEN128
     * Determines if the pensjonUnderUtbetaling has a barnetillegg ytelseskomponent that is innvilget or avslått (i.e. not opphørt).
     */
    public boolean hasBarnetilleggInkludertAvslatt() {
        return !hentYtelseskomponentByType(YtelseKomponentTypeCode.UT_TFB, true, true).isEmpty() ||
                !hentYtelseskomponentByType(YtelseKomponentTypeCode.UT_TSB, true, true).isEmpty();
    }

    /**
     * EPEN120 based on PK-9028 get the ytelseskomponent by type.
     */
    @SuppressWarnings("unchecked")
    public <T extends Ytelseskomponent<T>> T getYtelsekomponentOfType(YtelseKomponentTypeCode typeCode, boolean kunIBruk) {
        List<Ytelseskomponent> ytList = hentYtelseskomponentByType(typeCode, kunIBruk);
        return CollectionUtils.isEmpty(ytList) ? null : (T) ytList.get(0);
    }

    /**
     * EPEN078 based on PK-9186 remove the ytelseskomponent from beregningsresultat by id
     */
    public void removeYtelseskomponentById(Long ytelseskomponentId) {
        if (ytelseskomponentId == null) {
            return;
        }

        getYtelseskomponenter().removeIf(yt -> ytelseskomponentId.equals(yt.getYtelseskomponentId()));
    }

    /**
     * EPEN119
     * <p/>
     * Returns a list of Ytelseskomponenter which implements UforetrygdYtelseskomponent.
     *
     * @param erBrukt if true then only ytelseskomponents with erBrukt = True is returned, else only ytelseskomponents
     *                with erBrukt = False is returned.
     * @return a List of UforetrygdYtelseskomponent
     * @see <a href="http://jira.adeo.no/browse/SYSMOD-3388">SYSMOD-3388</a>
     * @see <a href="http://jira.adeo.no/browse/SYSMOD-3388">SYSMOD-3388</a>
     */
    public List<UforetrygdYtelseskomponent> getUforetrygdYtelseskomponenter(Boolean erBrukt) {
        List<UforetrygdYtelseskomponent> uforetrygdYtelseskomponenter = new ArrayList<>();

        for (Ytelseskomponent ytelseskomponent : ytelseskomponenter) {
            if (ytelseskomponent instanceof UforetrygdYtelseskomponent && ytelseskomponent.getErBrukt().equals(erBrukt)) {
                uforetrygdYtelseskomponenter.add((UforetrygdYtelseskomponent) ytelseskomponent);
            }
        }

        return uforetrygdYtelseskomponenter;
    }

    public boolean containsMotregning() {
        for (Ytelseskomponent ytelseskomponent : ytelseskomponenter) {
            if (ytelseskomponent.isMotregning()) {
                return true;
            }
        }

        return false;
    }

    /**
     * <b>CAUTION</b> - make sure you shouldn't filter Ytelseskomponenter with the erBrukt flag set to FALSE. Consider using
     * hentYtelseskomponenterIBruk(). Example of bug because of this: SIR 244180.
     */
    public List<Ytelseskomponent> getYtelseskomponenter() {
        return ytelseskomponenter;
    }

    public void setYtelseskomponenter(List<Ytelseskomponent> delytelser) {
        ytelseskomponenter = delytelser;
    }

    public Long getPensjonUnderUtbetalingId() {
        return pensjonUnderUtbetalingId;
    }

    public void setPensjonUnderUtbetalingId(Long pensjonUnderUtbetalingId) {
        this.pensjonUnderUtbetalingId = pensjonUnderUtbetalingId;
    }

    public Integer getTotalbelopNetto() {
        return totalbelopNetto;
    }

    public void setTotalbelopNetto(Integer totalbelopNetto) {
        this.totalbelopNetto = totalbelopNetto;
    }

    public Integer getTotalbelopBrutto() {
        return totalbelopBrutto;
    }

    public void setTotalbelopBrutto(Integer totalbelopBrutto) {
        this.totalbelopBrutto = totalbelopBrutto;
    }

    public Double getTotalbelopNettoAr() {
        return totalbelopNettoAr;
    }

    public void setTotalbelopNettoAr(Double totalbelopNettoAr) {
        this.totalbelopNettoAr = totalbelopNettoAr;
    }

    public Ytelseskomponent[] retrieveDelytelserAsArray() {
        return ytelseskomponenter.toArray(new Ytelseskomponent[ytelseskomponenter.size()]);
    }

    public Double getPubReguleringFratrekk() {
        return pubReguleringFratrekk;
    }

    public void setPubReguleringFratrekk(Double pubReguleringFratrekk) {
        this.pubReguleringFratrekk = pubReguleringFratrekk;
    }

    public Double getTotalbelopBruttoAr() {
        return totalbelopBruttoAr;
    }

    public void setTotalbelopBruttoAr(Double totalbelopBruttoAr) {
        this.totalbelopBruttoAr = totalbelopBruttoAr;
    }
}
