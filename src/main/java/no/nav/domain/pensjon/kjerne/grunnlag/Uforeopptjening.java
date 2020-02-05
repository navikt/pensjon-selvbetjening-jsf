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

import org.hibernate.annotations.Fetch;

import no.nav.domain.AbstractVersionedPersistentDomainObject;

/**
 * Dette objektet representerer detaljer knyttet til en opptjening av type uføre.
 * Objektet forteller hvor mye uførepensjonen bidrar til opptjening for året gitt av Opptjening.ar
 * Endring av uføreopptjening må gjøres ved revurdering av uførepensjonen.
 */
//@Entity
//@Table(name = "T_UFORE_OPPTJENING")
public class Uforeopptjening extends AbstractVersionedPersistentDomainObject {

    private static final long serialVersionUID = -8907933485161628153L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UFORE_OPPTJENING_ID")
    private Long uforeopptjeningId;

    /**
     * Den beregnede uføreopptjeningen. Tidligere lå dette i opptjening.ufore.
     * Angir hvor mye uførepensjon bidrar til opptjening for året. Når denne er angitt er ikke PGI benyttet.
     * Opptjeningen som er beregnet utfra informasjon om uføreperioden samt eventuell inntekt og omsorg.
     * Ordinær uførepensjon angir både opptjening.ufore og opptjening.omsorg.
     * Den høyeste er angitt i opptjening.opptjeningsgrunnlag.
     */
    @Column(name = "BELOP")
    private Double belop;

    /**
     * Angir om året er basert på proRata-beregnet uførepensjon.
     * Hvis true, er felter med postfix "_proRata" aktuelle.
     * (Pro rata er en type beregning som går ut på følgende prinsipp:
     * Pensjonene fra de forskjellige EØS-statene skal stå i forhold til den opptjeningstiden
     * som pensjonssøkeren har hatt i de forskjellige EØS-statenes pensjons- og trygdesystemer).
     */
    @Column(name = "PRORATA_BEREGNET_UP")
    private Boolean proRataBeregnetUP;

    /**
     * Angir poengtallet antatt inntekt er beregnet av.
     * Tilsvarer benyttet FPP i år med ordinær uførepensjon og Sluttpoengtall ved proRata-beregnet uførepensjon.
     */
    @Column(name = "POENGTALL")
    private Double poengtall;

    /**
     * Høyeste uføregrad for året.
     */
    @Column(name = "UFG")
    private Integer ufg;

    /**
     * Antatt Inntekt for Uførepensjon.
     */
    @Column(name = "ANTATT_INNTEKT")
    private Double antattInntekt;

    /**
     * Antatt Inntekt for proRata-beregnet uførepensjon.
     */
    @Column(name = "ANTATT_INNTEKT_PRORATA")
    private Double antattInntekt_proRata;

    /**
     * Andel av opptjening som blir benyttet i året. Kan kalles Andel av antatt inntekt.
     */
    @Column(name = "ANDEL_PRORATA")
    private Double andel_proRata;

    /**
     * Poengår teller. Brukes for å beregne proRata brøk.
     */
    @Column(name = "POENGAR_TELLER_PRORATA")
    private Integer poengarTeller_proRata;

    /**
     * Poengår nevner. Brukes for å beregne proRata brøk.
     */
    @Column(name = "POENGAR_NEVNER_PRORATA")
    private Integer poengarNevner_proRata;

    /**
     * Antall teoretisk mulige fremtidige år.
     */
    @Column(name = "ANT_FREMTIDIGE_AR_PRORATA")
    private Integer antFremtidigeAr_proRata;

    /**
     * Angir om opptjening er basert på uføretrygd (true) eller uførepensjon (false)
     */
    @Column(name = "UFORETRYGD", nullable = true)
    private Boolean uforetrygd;

    /**
     * Detaljer for yrkesskadeopptjening. Er null dersom det ikke foreligger yrkesskade.
     */
    @ManyToOne(cascade = CascadeType.ALL)
    @Fetch(org.hibernate.annotations.FetchMode.SELECT)
    @JoinColumn(name = "YRKESSKADE_OPPTJN_ID", nullable = true)
    private Yrkesskadeopptjening yrkesskadeopptjening;

    /**
     * Angir om årets uføreopptjening er beregnet som uføreår (true) eller mellomliggende år (false).
     */
    @Column(name = "UFOREAR")
    private Boolean uforear;

    /**
     * Angir om årets uføreopptjening er relatert til uføreperiode hvor uføretidspunktet er konvertert fra uførepensjon
     */
    @Column(name = "KONVERTERT_UFT")
    private Boolean konvertertUFT;

    public Uforeopptjening() {
        super();
    }

    public Uforeopptjening(Uforeopptjening opptjening) {
        if (opptjening == null) {
            return;
        }

        this.belop = opptjening.getBelop();
        this.proRataBeregnetUP = opptjening.getProRataBeregnetUP();
        this.poengtall = opptjening.getPoengtall();
        this.ufg = opptjening.getUfg();
        this.antattInntekt = opptjening.getAntattInntekt();
        this.antattInntekt_proRata = opptjening.getAntattInntekt_proRata();
        this.andel_proRata = opptjening.getAndel_proRata();
        this.poengarTeller_proRata = opptjening.getPoengarTeller_proRata();
        this.poengarNevner_proRata = opptjening.getPoengarNevner_proRata();
        this.antFremtidigeAr_proRata = opptjening.getAntFremtidigeAr_proRata();
        this.uforetrygd = opptjening.getUforetrygd();
        this.yrkesskadeopptjening = opptjening.getYrkesskadeopptjening() == null ? null : new Yrkesskadeopptjening(opptjening.getYrkesskadeopptjening());
        this.uforear = opptjening.getUforear();
        this.konvertertUFT = opptjening.getKonvertertUFT();
    }

    public Double getAndel_proRata() {
        return andel_proRata;
    }

    public void setAndel_proRata(Double andelProRata) {
        this.andel_proRata = andelProRata;
    }

    public Double getAntattInntekt() {
        return antattInntekt;
    }

    public void setAntattInntekt(Double antattInntekt) {
        this.antattInntekt = antattInntekt;
    }

    public Double getAntattInntekt_proRata() {
        return antattInntekt_proRata;
    }

    public void setAntattInntekt_proRata(Double antattInntektProRata) {
        this.antattInntekt_proRata = antattInntektProRata;
    }

    public Integer getAntFremtidigeAr_proRata() {
        return antFremtidigeAr_proRata;
    }

    public void setAntFremtidigeAr_proRata(Integer antFremtidigeArProRata) {
        this.antFremtidigeAr_proRata = antFremtidigeArProRata;
    }

    public Double getBelop() {
        return belop;
    }

    public void setBelop(Double belop) {
        this.belop = belop;
    }

    public Integer getPoengarNevner_proRata() {
        return poengarNevner_proRata;
    }

    public void setPoengarNevner_proRata(Integer poengarNevnerProRata) {
        this.poengarNevner_proRata = poengarNevnerProRata;
    }

    public Integer getPoengarTeller_proRata() {
        return poengarTeller_proRata;
    }

    public void setPoengarTeller_proRata(Integer poengarTellerProRata) {
        this.poengarTeller_proRata = poengarTellerProRata;
    }

    public Double getPoengtall() {
        return poengtall;
    }

    public void setPoengtall(Double poengtall) {
        this.poengtall = poengtall;
    }

    public Boolean getProRataBeregnetUP() {
        return proRataBeregnetUP;
    }

    public void setProRataBeregnetUP(Boolean proRataBeregnetUP) {
        this.proRataBeregnetUP = proRataBeregnetUP;
    }

    public Integer getUfg() {
        return ufg;
    }

    public void setUfg(Integer ufg) {
        this.ufg = ufg;
    }

    public Long getUforeopptjeningId() {
        return uforeopptjeningId;
    }

    public void setUforeopptjeningId(Long uforeopptjeningId) {
        this.uforeopptjeningId = uforeopptjeningId;
    }

    public Yrkesskadeopptjening getYrkesskadeopptjening() {
        return yrkesskadeopptjening;
    }

    public void setYrkesskadeopptjening(Yrkesskadeopptjening yrkesskadeopptjening) {
        this.yrkesskadeopptjening = yrkesskadeopptjening;
    }

    public Boolean getUforetrygd() {
        return uforetrygd;
    }

    public void setUforetrygd(Boolean uforetrygd) {
        this.uforetrygd = uforetrygd;
    }

    public Boolean getUforear() {
        return uforear;
    }

    public void setUforear(Boolean uforear) {
        this.uforear = uforear;
    }

    public Boolean getKonvertertUFT() {
        return konvertertUFT;
    }

    public void setKonvertertUFT(Boolean konvertertUFT) {
        this.konvertertUFT = konvertertUFT;
    }
}
