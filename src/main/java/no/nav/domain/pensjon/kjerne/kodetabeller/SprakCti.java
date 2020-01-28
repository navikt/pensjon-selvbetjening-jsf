package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.support.IdAsKeyCodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_SPRAK_2_TEGN")
//@AttributeOverrides({
//        @AttributeOverride(name = "id", column = @Column(name = "K_SPRAK_2_TEGN_ID")),
//        @AttributeOverride(name = "code", column = @Column(name = "SPRAK_2_TEGN")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))
//})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class SprakCti extends IdAsKeyCodesTablePeriodicItem<SprakCode, String> {
    private static final long serialVersionUID = -2698713189566699275L;
}
