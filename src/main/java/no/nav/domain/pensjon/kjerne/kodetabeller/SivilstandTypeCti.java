package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_SIVILSTAND_T")
//@AttributeOverrides({
//        @AttributeOverride(name = "code", column = @Column(name = "K_SIVILSTAND_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))
//})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class SivilstandTypeCti extends CodesTablePeriodicItem<SivilstandTypeCode, String> {
    private static final long serialVersionUID = -7423132567356889304L;
}
