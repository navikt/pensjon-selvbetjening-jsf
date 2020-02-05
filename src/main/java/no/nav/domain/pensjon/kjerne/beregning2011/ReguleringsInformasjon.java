package no.nav.domain.pensjon.kjerne.beregning2011;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import no.nav.domain.AbstractVersionedPersistentDomainObject;

//@Entity
//@Table(name = "T_REGULERING_INFO")
public class ReguleringsInformasjon extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = 6367489936669460145L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "REGULERING_INFO_ID")
    private Long reguleringsInfoId;

    @Column(name = "GJ_SN_UTTAKSGRAD_SA")
    private Double gjennomsnittligUttaksgradSisteAr;

    /**
     * Lønnsveksten i %
     */
    @Column(name = "LONNSVEKST")
    private Double lonnsvekst;

    /**
     * Hvis gjennomsnittlig uttaksgrad siste 12 måneder er større enn uttaksgraden
     * ved regulering og SisteAldersberegning hadde en restpensjon, vil denne faktoren være utfylt
     */
    @Column(name = "FRATREKKSFAKTOR")
    private Double fratrekksfaktor;

    @Column(name = "GAMMEL_G")
    private Integer gammelG;

    @Column(name = "NY_G")
    private Integer nyG;

    @Column(name = "REGULERINGSFAKTOR")
    private Double reguleringsfaktor;

    /**
     * Reguleringsbeløp som beholdning er regulert med. (kronebeløp).
     */
    @Column(name = "reguleringsbelop")
    private Double reguleringsbelop;

    public ReguleringsInformasjon() {
        super();
    }

    public ReguleringsInformasjon(ReguleringsInformasjon info) {
        if (info == null) {
            return;
        }
        setGjennomsnittligUttaksgradSisteAr(info.getGjennomsnittligUttaksgradSisteAr());
        setLonnsvekst(info.getLonnsvekst());
        setFratrekksfaktor(info.getFratrekksfaktor());
        setGammelG(info.getGammelG());
        setNyG(info.getNyG());
        setReguleringsbelop(info.getReguleringsbelop());
        setReguleringsfaktor(info.getReguleringsfaktor());
    }

    public Double getFratrekksfaktor() {
        return fratrekksfaktor;
    }

    public void setFratrekksfaktor(Double fratrekksfaktor) {
        this.fratrekksfaktor = fratrekksfaktor;
    }

    public Integer getGammelG() {
        return gammelG;
    }

    public void setGammelG(Integer gammelG) {
        this.gammelG = gammelG;
    }

    public Double getGjennomsnittligUttaksgradSisteAr() {
        return gjennomsnittligUttaksgradSisteAr;
    }

    public void setGjennomsnittligUttaksgradSisteAr(Double gjennomsnittligUttaksgradSisteAr) {
        this.gjennomsnittligUttaksgradSisteAr = gjennomsnittligUttaksgradSisteAr;
    }

    public Double getLonnsvekst() {
        return lonnsvekst;
    }

    public void setLonnsvekst(Double lonnsvekst) {
        this.lonnsvekst = lonnsvekst;
    }

    public Integer getNyG() {
        return nyG;
    }

    public void setNyG(Integer nyG) {
        this.nyG = nyG;
    }

    public Double getReguleringsfaktor() {
        return reguleringsfaktor;
    }

    public void setReguleringsfaktor(Double reguleringsfaktor) {
        this.reguleringsfaktor = reguleringsfaktor;
    }

    public Long getReguleringsInfoId() {
        return reguleringsInfoId;
    }

    public void setReguleringsInfoId(Long reguleringsInfoId) {
        this.reguleringsInfoId = reguleringsInfoId;
    }

    public Double getReguleringsbelop() {
        return reguleringsbelop;
    }

    public void setReguleringsbelop(Double reguleringsbelop) {
        this.reguleringsbelop = reguleringsbelop;
    }
}
