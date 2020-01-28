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
//@Table(name = "T_K_SH_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_SH_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class SamhandlerTypeCti extends CodesTablePeriodicItem<SamhandlerTypeCode, String> {
    private static final long serialVersionUID = 7598881275729044656L;

//    @Column(name = "KAN_OPPRETTES_I_PEN", nullable = false)
    private Boolean kanOpprettes;

    /**
     * @return true if a Samhandler of this type can be created in PEN
     */
    public Boolean getKanOpprettes() {
        return kanOpprettes;
    }
}
