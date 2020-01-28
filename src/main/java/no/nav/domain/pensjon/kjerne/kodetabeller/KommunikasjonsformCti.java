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
//@Table(name = "T_K_KOMMSJN_FORM")
//@AttributeOverrides({
//        @AttributeOverride(name = "code", column = @Column(name = "K_KOMMSJN_FORM")),
//        @AttributeOverride(name = "decode", column = @Column(name = "DEKODE")),
//        @AttributeOverride(name = "valid", column = @Column(name = "ER_GYLDIG")),
//        @AttributeOverride(name = "fromDate", column = @Column(name = "DATO_FOM")),
//        @AttributeOverride(name = "toDate", column = @Column(name = "DATO_TOM"))
//})
//@org.hibernate.annotations.Cache(usage = org.hibernate.annotations.CacheConcurrencyStrategy.READ_ONLY, region = "ctiCache")
public class KommunikasjonsformCti extends CodesTablePeriodicItem<KommunikasjonsformCode, String> {
    private static final long serialVersionUID = -8206057371496468569L;
}
