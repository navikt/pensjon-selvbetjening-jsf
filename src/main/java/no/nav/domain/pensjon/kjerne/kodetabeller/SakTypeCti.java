package no.nav.domain.pensjon.kjerne.kodetabeller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

//@Entity
//@Immutable
//@Table(name = "T_K_SAK_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_SAK_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class SakTypeCti extends CodesTablePeriodicItem<SakTypeCode, String> {
    private static final long serialVersionUID = -2850002248680911188L;

    /**
     * CR 70392
     */
//    @OneToMany(mappedBy = "sakType")
    private List<IrStonadCti> irStonadListe = new ArrayList<IrStonadCti>();

    /**
     * CR 124805
     */
//    @Column(name = "FRIST_DAGER", nullable = true)
    private Integer frist;

    /**
     * @return the irStonadListe
     */
    public List<IrStonadCti> getIrStonadListe() {
        return irStonadListe;
    }

    public Integer getFrist() {
        return frist;
    }

//    // TODO remove
//    public  SakTypeCti(SakTypeCode code){
//        setCodeAsString(code.name());
//    }
}
