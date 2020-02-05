package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import no.nav.domain.pensjon.kjerne.kodetabeller.AvkortingsArsakCode;

@MappedSuperclass
public abstract class Barnetillegg<T extends Barnetillegg<T>> extends SimpleYtelsekomponent<T> {

    private static final long serialVersionUID = 1L;

    @Column(name = "FRIBELOP", nullable = true)
    private Double fribelop = 0d;

    /**
     * Summen av inntektene som kan bli lagt til grunn ved avkorting, selv når det ikke fører til avkorting.
     */
    @Column(name = "SAML_INNTEKT_AVKORT", nullable = true)
    private Integer samletInntektAvkort;

    @Column(name = "ANTALL_BARN", nullable = true)
    private Integer antallBarn = 0;

    @Column(name = "BT_DIFF_EOS", nullable = true)
    private Integer btDiff_eos = 0;

    @Enumerated(EnumType.STRING)
    @Column(name = "K_AVKORT_ARSAK")
    private AvkortingsArsakCode avkortingsArsak;

    /**
     * Minste pensjonsnivåsats for forsørgingstillegg. Settes av PREG.
     */
    @Column(name = "MPN_SATS_FT")
    private Double mpnSatsFT;

    @Column(name = "PRORATANEVNER")
    private Integer proratanevner;

    @Column(name = "PRORATATELLER")
    private Integer proratateller;

    @Column(name = "TT_ANV")
    private Integer tt_anv;

    protected Barnetillegg() {
        super();
    }

    @Override
    public T copy() {
        T copy = super.copy();
        copy.setFribelop(fribelop);
        copy.setAntallBarn(antallBarn);
        copy.setAvkortingsArsak(avkortingsArsak);
        copy.setBtDiff_eos(btDiff_eos);
        copy.setMpnSatsFT(mpnSatsFT);
        copy.setProratanevner(proratanevner);
        copy.setProratateller(proratateller);
        copy.setTt_anv(tt_anv);
        copy.setSamletInntektAvkort(samletInntektAvkort);
        return copy;
    }

    public AvkortingsArsakCode getAvkortingsArsak() {
        return avkortingsArsak;
    }

    public void setAvkortingsArsak(AvkortingsArsakCode avkortingsArsak) {
        this.avkortingsArsak = avkortingsArsak;
    }

    public Integer getAntallBarn() {
        return antallBarn;
    }

    public Integer getBtDiff_eos() {
        return btDiff_eos;
    }

    public Double getFribelop() {
        return fribelop;
    }

    public void setAntallBarn(Integer antallBarn) {
        this.antallBarn = antallBarn;
    }

    public void setBtDiff_eos(Integer btDiff_eos) {
        this.btDiff_eos = btDiff_eos;
    }

    public void setFribelop(Double fribelop) {
        this.fribelop = fribelop;
    }

    public Double getMpnSatsFT() {
        return mpnSatsFT;
    }

    public void setMpnSatsFT(Double mpnSatsFT) {
        this.mpnSatsFT = mpnSatsFT;
    }

    public Integer getProratanevner() {
        return proratanevner;
    }

    public void setProratanevner(Integer proratanevner) {
        this.proratanevner = proratanevner;
    }

    public Integer getProratateller() {
        return proratateller;
    }

    public void setProratateller(Integer proratateller) {
        this.proratateller = proratateller;
    }

    public Integer getTt_anv() {
        return tt_anv;
    }

    public void setTt_anv(Integer tt_anv) {
        this.tt_anv = tt_anv;
    }

    public Integer getSamletInntektAvkort() {
        return samletInntektAvkort;
    }

    public void setSamletInntektAvkort(Integer samletInntektAvkort) {
        this.samletInntektAvkort = samletInntektAvkort;
    }

    /**
     * EPEN070 hentSamletInntektBruktIAvkortning
     * <p>
     * For ytelseskomponentene Ektefelletillegg, BarnetilleggfellesBarn og Barnetilleggserkullsbarn returneres samletInntektAvkort. For andre ytelseskomponenter returneres 0.
     * </p>
     */
    @Override
    public Integer hentSamletInntektBruktIAvkortning() {
        return getSamletInntektAvkort();
    }
}
