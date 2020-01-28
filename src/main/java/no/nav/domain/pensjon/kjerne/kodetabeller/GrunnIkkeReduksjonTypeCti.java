package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

/**
 * CodesTableItem for GrunnIkkeReduksjonTypeCti
 * Table-mapping for the overwritten constructor of the super class.
 *
 */

@Entity
@Immutable
@Table(name = "T_K_INNT_GRUNN_IKKE_RED_T")
@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_INNT_GRUNN_IKKE_RED_T")),
        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class GrunnIkkeReduksjonTypeCti extends CodesTablePeriodicItem<GrunnIkkeReduksjonTypeCode, String> {
    private static final long serialVersionUID = 6811300341587877801L;

}
