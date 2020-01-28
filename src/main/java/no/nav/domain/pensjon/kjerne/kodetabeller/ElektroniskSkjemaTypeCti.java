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
//@Table(name = "T_K_SKJEMA_PSELV_T")
//@AttributeOverrides({@AttributeOverride(name = "code", column = @Column(name = "K_SKJEMA_PSELV_T")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG"))})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class ElektroniskSkjemaTypeCti extends CodesTablePeriodicItem<ElektroniskSkjemaTypeCode, String> {
    private static final long serialVersionUID = 2182873760544414451L;

    /**
     * The skjemanavn the item is related to.
     */
//    @Column(name = "SKJEMA_NAVN", nullable = false)
    private String skjemanavn;

    /**
     * @return skjemanavn henvendelse type is related to
     */
    public String getSkjemanavn() {
        return skjemanavn;
    }

    /**
     * The skjemaId the item is related to.
     */
//    @Column(name = "SKJEMA_ID", nullable = false)
    private String skjemaId;

    /**
     * @return skjemaid henvendelse type is related to
     */
    public String getSkjemaId() {
        return skjemaId;
    }

    //!!!!!!!!!!!!!!!!!

//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setCodeAsString(String code) {
//        this.code = code;
//    }

    //!!!!!!!!!!!!!!!!!
}
