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
//@Table(name = "T_K_YTELSE_KOMP_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_YTELSE_KOMP_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class YtelseKomponentTypeCti extends CodesTablePeriodicItem<YtelseKomponentTypeCode, String> {

    private static final long serialVersionUID = 2182873760544414451L;

    /**
     * Hvis <code>true</code> påvirker denne Yetelseskomponent basispensjon, restpensjon og beholdninger.
     */
    @Column(name = "AVH_BP_RESTP_BEH", nullable = true)
    private Boolean avhBasispRestpBeh;

    /**
     * Prioritet brukes til å sortere lister av ytelseskomponenter på en konsistent måtte.
     */
    @Column(name = "PRIORITET", nullable = true)
    private Integer prioritet;

    @Column(name = "ER_UFORETRYGD", nullable = false, columnDefinition = "CHARACTER default 0")
    private boolean uforetrygd;

    /**
     * Hvis <code>true</code> påvirker denne Yetelseskomponent basispensjon, restpensjon og beholdninger.<br/>
     */
    public Boolean getAvhBasispRestpBeh() {
        return avhBasispRestpBeh;
    }

    public Integer getPrioritet() {
        return prioritet;
    }
    public boolean isUforetrygd() {
        return uforetrygd;
    }
}
