package no.nav.domain.pensjon.kjerne.kodetabeller;

import java.util.EnumSet;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.support.IdAsKeyCodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_LAND_3_TEGN")
//@AttributeOverrides({@AttributeOverride(name = "id", column = @Column(name = "K_LAND_3_TEGN_ID")),
//        @AttributeOverride(name = "code", column = @Column(name = "LAND_3_TEGN")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class Land3TegnCti extends IdAsKeyCodesTablePeriodicItem<Land3TegnCode, String> {
//    @Column(name = "LAND_NR", nullable = false)
    private Integer landNr;
    private static final long serialVersionUID = 8055436608831160730L;

    /**
     * @return the landNr
     */
    public Integer getLandNr() {
        return landNr;
    }

    /**
     * @return <code>true</code> if there is a matching enum constant Avtaleland for <b>this</b>
     */
    public boolean isAvtaleland() {
        return EnumSet.allOf(AvtalelandCode.class).stream().anyMatch(avtaleLand -> avtaleLand.name().equals(getCode().name()));
    }
}
