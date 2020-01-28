package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_IR_STONAD")
//@AttributeOverrides({
//        @AttributeOverride(name = "code", column = @Column(name = "K_IR_STONAD_ID")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))
//})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class IrStonadCti extends CodesTablePeriodicItem<IrStonadCode, String> {
    private static final long serialVersionUID = -5361114018268200990L;

//    @ManyToOne(optional = true)
//    @JoinColumn(name = "K_SAK_T", nullable = true)
    private SakTypeCti sakType;

//    @Column(name = "K_IR_STONAD_T_FK", nullable = false)
    private String irStonadTypeFk;

//    @Column(name = "ER_UT", nullable = false)
    private Boolean erUt;

    /**
     * @return the erUt
     */
    public Boolean getErUt() {
        return erUt;
    }

    /**
     * @return the irStonadTypeFk
     */
    public String getIrStonadTypeFk() {
        return irStonadTypeFk;
    }

    /**
     * @return the sakType
     */
    public SakTypeCti getSakType() {
        return sakType;
    }
}
