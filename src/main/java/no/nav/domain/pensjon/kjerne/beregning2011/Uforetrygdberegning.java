package no.nav.domain.pensjon.kjerne.beregning2011;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

//import no.nav.domain.pensjon.kjerne.beregning.Brok;
//import no.nav.domain.pensjon.kjerne.kodetabeller.FormelKodeCode;
//import no.nav.domain.pensjon.kjerne.kodetabeller.JustertPeriodeCode;
//import no.nav.domain.pensjon.kjerne.kodetabeller.YtelseVedDodCti;

/**
 * Domeneklasse for beregningen til Uføretrygd (fra 01.01.2015)
 *
 */
//@Entity
//@DiscriminatorValue("UT")
public class Uforetrygdberegning extends Beregning2011 {

    private static final long serialVersionUID = 4185598924129781995L;

    public Integer getUforegrad() {
        return 50;
    }

//    @Column(name = "BRUTTO_PER_AR", nullable = true)
//    private Integer bruttoPerAr;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "K_FORMELKODE", nullable = true)
//    private FormelKodeCode formelKode;
//
//    @Fetch(FetchMode.SELECT)
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "EGENOPPTJN_UT_ID", nullable = true)
//    private EgenopptjentUforetrygd egenopptjentUforetrygd;
//
//    @Column(name = "UFOREGRAD", nullable = true)
//    private Integer uforegrad;
//
//    @Column(name = "DATO_UFORETIDSPUNKT", nullable = true)
//    private Date uforetidspunkt;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "MINSTEYTELSE_ID", nullable = true)
//    private Minsteytelse minsteytelse;
//
//    @Column(name = "EGENOPPTJN_UT_BEST", nullable = true)
//    private Boolean egenopptjentUforetrygdBest;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "PRORATA_BROK_ID", nullable = true)
//    private Brok prorataBrok;
//
//    @Column(name = "YRKESSKADEGRAD", nullable = true)
//    private Integer yrkesskadegrad;
//
//    @Column(name = "DATO_YRKESSKADETIDSPUNKT", nullable = true)
//    private Date yrkesskadetidspunkt;
//
//    @Column(name = "MOTTAR_MINSTEYTELSE", nullable = false)
//    private Boolean mottarMinsteytelse;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "UFORE_EXTRA_UT_ID", nullable = true)
//    private UforeEkstraUT uforeEkstra;
//
//    @ManyToOne
//    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
//    @JoinColumn(name = "K_YTELSE_VED_DOD", nullable = true)
//    private YtelseVedDodCti ytelseVedDodCti;
//
//    @Enumerated(EnumType.STRING)
//    @Column(name = "K_JUST_PERIODE")
//    private JustertPeriodeCode instOppholdType;
//
//    @Column(name = "INST_OPPHOLD_ANVENDT", nullable = true)
//    private Boolean instOpphAnvendt;
//
//    public Uforetrygdberegning() {
//        super();
//        setMottarMinsteytelse(false);
//    }
//
//    public Integer getBruttoPerAr() {
//        return bruttoPerAr;
//    }
//
//    public void setBruttoPerAr(Integer bruttoPerAr) {
//        this.bruttoPerAr = bruttoPerAr;
//    }
//
//    public FormelKodeCode getFormelKode() {
//        return formelKode;
//    }
//
//    public void setFormelKode(FormelKodeCode formelKode) {
//        this.formelKode = formelKode;
//    }
//
//    public Integer getUforegrad() {
//        return uforegrad;
//    }
//
//    public void setUforegrad(Integer uforegrad) {
//        this.uforegrad = uforegrad;
//    }
//
//    public Date getUforetidspunkt() {
//        return uforetidspunkt;
//    }
//
//    public void setUforetidspunkt(Date uforetidspunkt) {
//        this.uforetidspunkt = uforetidspunkt;
//    }
//
//    public Boolean getEgenopptjentUforetrygdBest() {
//        return egenopptjentUforetrygdBest;
//    }
//
//    public void setEgenopptjentUforetrygdBest(Boolean egenopptjentUforetrygdBest) {
//        this.egenopptjentUforetrygdBest = egenopptjentUforetrygdBest;
//    }
//
//    public EgenopptjentUforetrygd getEgenopptjentUforetrygd() {
//        return egenopptjentUforetrygd;
//    }
//
//    public void setEgenopptjentUforetrygd(EgenopptjentUforetrygd egenopptjentUforetrygd) {
//        this.egenopptjentUforetrygd = egenopptjentUforetrygd;
//    }
//
//    public Minsteytelse getMinsteytelse() {
//        return minsteytelse;
//    }
//
//    public void setMinsteytelse(Minsteytelse minsteytelse) {
//        this.minsteytelse = minsteytelse;
//    }
//
//    public Integer getYrkesskadegrad() {
//        return yrkesskadegrad;
//    }
//
//    public void setYrkesskadegrad(Integer yrkesskadegrad) {
//        this.yrkesskadegrad = yrkesskadegrad;
//    }
//
//    public Date getYrkesskadetidspunkt() {
//        return yrkesskadetidspunkt;
//    }
//
//    public void setYrkesskadetidspunkt(Date yrkesskadetidspunkt) {
//        this.yrkesskadetidspunkt = yrkesskadetidspunkt;
//    }
//
//    public Brok getProrataBrok() {
//        return prorataBrok;
//    }
//
//    public void setProrataBrok(Brok prorataBrok) {
//        this.prorataBrok = prorataBrok;
//    }
//
//    public Boolean isMottarMinsteytelse() {
//        return mottarMinsteytelse;
//    }
//
//    public void setMottarMinsteytelse(boolean mottarMinsteytelse) {
//        this.mottarMinsteytelse = mottarMinsteytelse;
//    }
//
//    public UforeEkstraUT getUforeEkstra() {
//        return uforeEkstra;
//    }
//
//    public void setUforeEkstra(UforeEkstraUT uforeEkstra) {
//        this.uforeEkstra = uforeEkstra;
//    }
//
//    /**
//     * Satt på de beregninger hvor avdødes ytelse har påvirket beregningen
//     *
//     * @return
//     */
//    public YtelseVedDodCti getYtelseVedDodCti() {
//        return ytelseVedDodCti;
//    }
//
//    public void setYtelseVedDodCti(YtelseVedDodCti ytelseVedDodCti) {
//        this.ytelseVedDodCti = ytelseVedDodCti;
//    }
//
//    public Boolean getInstOpphAnvendt() {
//        return instOpphAnvendt;
//    }
//
//    public void setInstOpphAnvendt(Boolean instOpphAnvendt) {
//        this.instOpphAnvendt = instOpphAnvendt;
//    }
//
//    public JustertPeriodeCode getInstOppholdType() {
//        return instOppholdType;
//    }
//
//    public void setInstOppholdType(JustertPeriodeCode instOppholdType) {
//        this.instOppholdType = instOppholdType;
//    }
}
