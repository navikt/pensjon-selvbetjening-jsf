package no.nav.domain.pensjon.kjerne.kodetabeller;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import no.stelvio.common.codestable.CodesTablePeriodicItem;

/**
 * CodesTableItem for InntektTypeCti
 * Table-mapping for the overwritten constructor of the super class.
 *
 */
//@Entity
//@Immutable
//@Table(name = "T_K_INNTEKT_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_INNTEKT_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class InntektTypeCti extends CodesTablePeriodicItem<InntektTypeCode, String> {
    private static final long serialVersionUID = 6456081986038174476L;

    //@Column(name = "KAN_REGISTRERES", nullable = false)
    private Boolean kanRegistreres;

    //@Column(name = "LAGRES_I_PEN", nullable = false)
    private Boolean lagresIPen;

    //@Column(name = "LAGRES_I_INNT", nullable = false)
    private Boolean lagresIInnt;

    //@Column(name = "KOPIERES_FRA_INNT")
    private Boolean kopieresFraInnt;

    //@Column(name = "RELEVANT_FOR_BT")
    private Boolean relevantBt;

    /**
     * @return the kanRegistreres
     */
    public Boolean getKanRegistreres() {
        return kanRegistreres;
    }

    /**
     * @return the lagresIInnt
     */
    public Boolean getLagresIInnt() {
        return lagresIInnt;
    }

    /**
     * @return the lagresIPen
     */
    public Boolean getLagresIPen() {
        return lagresIPen;
    }

    /**
     * @return the kopieresFraInnt
     */
    public Boolean getKopieresFraInnt() {
        return kopieresFraInnt;
    }

    /**
     * @return the kopieresFraInnt
     */
    public Boolean getRelevantBt() {
        return relevantBt;
    }
}
