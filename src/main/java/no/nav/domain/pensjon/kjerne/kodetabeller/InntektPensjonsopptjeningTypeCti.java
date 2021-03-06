package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

/**
 * Table-mapping for the overwritten constructor of the super class.
 *
 */
//@Entity
//@Immutable
//@Table(name = "T_K_INNTEKT_POPP_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_INNTEKT_POPP_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class InntektPensjonsopptjeningTypeCti extends CodesTablePeriodicItem<InntektPensjonsopptjeningTypeCode, String> {
    private static final long serialVersionUID = -4582581964550602901L;
}
