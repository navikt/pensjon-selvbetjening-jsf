package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
import no.nav.domain.pensjon.kjerne.kodetabeller.AvkortingsArsakCode;

//@Entity
//@DiscriminatorValue("ET")
public class Ektefelletillegg extends Ytelseskomponent<Ektefelletillegg> {

    private static final long serialVersionUID = -2093843713651086002L;

    @Column(name = "FRIBELOP", nullable = true)
    private Double fribelop = 0d;

    /**
     * Summen av inntektene som kan bli lagt til grunn ved avkorting, selv når det ikke fører til avkorting.
     */
    @Column(name = "SAML_INNTEKT_AVKORT", nullable = true)
    private Integer samletInntektAvkort;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_ID", nullable = true)
    @IgnoreOnCopy(reason = "Beregning is the owner of ytelseskomponent and should not be copied")
    private Beregning beregning;

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

    /**
     * Flagg som forteller om ektefelletillegget er skattefritt.
     */
    @Column(name = "SKATTEFRITAK")
    private Boolean skattefritak;

    public Double getFribelop() {
        return fribelop;
    }

    public void setFribelop(Double fribelop) {
        this.fribelop = fribelop;
    }

    protected Beregning getBeregning() {
        return beregning;
    }

    protected void setBeregning(Beregning beregning) {
        this.beregning = beregning;
    }

    @Override
    protected Ektefelletillegg innerCopy() {
        Ektefelletillegg copy = new Ektefelletillegg();
        copy.setFribelop(fribelop);
        copy.setAvkortingsArsak(avkortingsArsak);
        copy.setMpnSatsFT(mpnSatsFT);
        copy.setProratanevner(proratanevner);
        copy.setProratateller(proratateller);
        copy.setTt_anv(tt_anv);
        copy.setSamletInntektAvkort(samletInntektAvkort);
        copy.setSkattefritak(skattefritak);
        return copy;
    }

    @Override
    @Transient
    public Beregning getRelatertBeregning() {
        return getBeregning();
    }

    public AvkortingsArsakCode getAvkortingsArsak() {
        return avkortingsArsak;
    }

    public void setAvkortingsArsak(AvkortingsArsakCode avkortingsArsak) {
        this.avkortingsArsak = avkortingsArsak;
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

    public Boolean getSkattefritak() {
        return skattefritak;
    }

    public void setSkattefritak(Boolean skattefritak) {
        this.skattefritak = skattefritak;
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
