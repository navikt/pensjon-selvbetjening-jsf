package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_GRNL_ROLLE_T")
//@AttributeOverrides({
//        @AttributeOverride(name = "code", column = @Column(name = "K_GRNL_ROLLE_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class GrunnlagsrolleCti extends CodesTablePeriodicItem<GrunnlagsrolleCode, String> {
    private static final long serialVersionUID = -8027439642266237444L;

//    @Column(name = "SORTERING", nullable = true)
    private Integer sortering;

    /**
     * @return the sortering
     */
    public Integer getSortering() {
        return sortering;
    }
}
