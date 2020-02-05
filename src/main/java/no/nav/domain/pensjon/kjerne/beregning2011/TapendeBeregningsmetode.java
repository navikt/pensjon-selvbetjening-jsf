package no.nav.domain.pensjon.kjerne.beregning2011;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.kodetabeller.BeregningMetodeTypeCode;

//@Entity
//@Table(name = "T_TAP_BER_METODE")
public class TapendeBeregningsmetode extends AbstractVersionedPersistentDomainObject {

    private static final long serialVersionUID = -7522049058033125047L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "TAP_BER_METODE_ID", nullable = false)
    private Integer tapendeBeregningsmetodeId;

    /**
     * Beregningsmetode. Kodeverk: K_BEREG_METODE_T
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "K_BEREG_METODE_T", nullable = false)
    private BeregningMetodeTypeCode beregningsMetode;

    @ManyToOne(fetch = javax.persistence.FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_INFO_ID", nullable = true)
    private BeregningsInformasjon beregningsInformasjon;

    public BeregningMetodeTypeCode getBeregningsMetode() {
        return beregningsMetode;
    }

    public void setBeregningsMetode(BeregningMetodeTypeCode beregningsMetode) {
        this.beregningsMetode = beregningsMetode;
    }

    public Integer getTapendeBeregningsmetodeId() {
        return tapendeBeregningsmetodeId;
    }

    public void setTapendeBeregningsmetodeId(Integer tapendeBeregningsmetodeId) {
        this.tapendeBeregningsmetodeId = tapendeBeregningsmetodeId;
    }

    public BeregningsInformasjon getBeregningsInformasjon() {
        return beregningsInformasjon;
    }

    public void setBeregningsInformasjon(BeregningsInformasjon beregningsInformasjon) {
        this.beregningsInformasjon = beregningsInformasjon;
    }
}
