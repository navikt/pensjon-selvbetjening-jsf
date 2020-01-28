package no.nav.domain.pensjon.kjerne.grunnlag;

import static no.nav.domain.pensjon.common.util.DateCopyUtil.copyDate;
import static no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode.FORINTAND;
import static no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode.FORINTARB;
import static no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode.FORINTNAE;
import static no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode.FORINTUTL;
import static no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode.FORPENUTL;

import java.util.Date;
import java.util.EnumSet;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.lang3.BooleanUtils;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;

import no.stelvio.common.util.DateUtil;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;
//import no.nav.domain.pensjon.common.PeriodisertInformasjon;
//import no.nav.domain.pensjon.common.TypedInformation;
//import no.nav.domain.pensjon.common.Usable;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnIkkeReduksjonTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagKildeCti;
//import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagVedleggType;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektHendelseTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.InntektTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.KildeInntCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.KildeInntCti;

/**
 * Class Inntektsgrunnlag.java
 *
 */
//@Entity
//@Table(name = "T_INNTEKT")
public class Inntektsgrunnlag extends AbstractVersionedPersistentDomainObject  { //implements Comparable, Usable, PeriodisertInformasjon,
//public class Inntektsgrunnlag { //extends AbstractVersionedPersistentDomainObject implements Comparable, Usable, PeriodisertInformasjon,
//        AbstractGrunnlag<InntektsgrunnlagVedlegg>, TypedInformation<InntektTypeCode> {
    private static final long serialVersionUID = -5513339581607587422L;

    /**
     * PK
     */
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "inntekt_id")
    private Long inntektsgrunnlagId;

    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    //@Column(name = "dato_fom", nullable = false)
    private Date fomDato;

    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    //@Column(name = "dato_tom", nullable = true)
    private Date tomDato;

    //@Column(name = "belop", nullable = false)
    private Integer belop = 0;

    //@Column(name = "bruk", nullable = false)
    private Boolean bruk = false;

    //@Column(name = "kopiert_fra_gammel", nullable = false)
    private Boolean kopiertFraGammeltKrav = false;

    //@Column(name = "reg_opprettet_av", nullable = true)
    private String registerOpprettetAv;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_grunnlag_kilde", nullable = false)
    private GrunnlagKildeCti grunnlagKilde;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_kilde_innt_t")
    private KildeInntCti registerKilde;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_inntekt_t", nullable = false)
    private InntektTypeCti inntektType;

    //@SuppressWarnings("all") // used by hibernate to enable mapping
    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "GRUNNLAG_VEDLEGG_ID", nullable = true)
//    private InntektsgrunnlagVedlegg grunnlagVedlegg;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_inntekt_hendelse_t", nullable = true)
    private InntektHendelseTypeCti inntektHendelseType;

    //@Temporal(TemporalType.TIMESTAMP)
    //@Column(name = "dato_endringstidspunkt", nullable = true)
    private Date endringstidspunkt;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_innt_grunn_ikke_red_t", nullable = true)
    private GrunnIkkeReduksjonTypeCti grunnIkkeReduksjonType;

    //@ManyToOne(fetch = FetchType.LAZY)
    //@JoinColumn(name = "PERSON_GRUNNLAG_ID", nullable = true)
    //@IgnoreOnCopy(reason = "Inverse reference")
    private Persongrunnlag persongrunnlag;

    /**
     * Reference to {@link UforetrygdEtteroppgjorDetalj}. Only one of {@code etteroppgjorDetaljInntekt}
     * and {@code etteroppgjorDetaljFratrekk} will be populated for one Inntektsgrunnlag.
     */
//    @ManyToOne(optional = true)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "EO_INNTEKT_ID", nullable = true)
//    UforetrygdEtteroppgjorDetalj etteroppgjorDetaljInntekt;

    /**
     * Reference to {@link UforetrygdEtteroppgjorDetalj}. Only one of {@code etteroppgjorDetaljInntekt}
     * and {@code etteroppgjorDetaljFratrekk} will be populated for one Inntektsgrunnlag.
     */
//    @ManyToOne(optional = true)
//    @Fetch(FetchMode.SELECT)
//    @JoinColumn(name = "EO_FRATREKK_ID", nullable = true)
//    UforetrygdEtteroppgjorDetalj etteroppgjorDetaljFratrekk;

    /**
     * Default constructor
     */
    public Inntektsgrunnlag() {
    }

    /**
     * Copy constructor.
     * Copy the src object except ChangeStamp, versjon and inntektsgrunnlagId.
     * <b>persongrunnlag will not be copied. However, the copy constructor of Persongrunnlag will
     * copy Inntektsgrunnlag</b>.
     *
     * @param src object to copy from
     */
    public Inntektsgrunnlag(Inntektsgrunnlag src) {
        this.fomDato = copyDate(src.getFomDato());
        this.tomDato = copyDate(src.getTomDato());
        this.belop = src.getBelop();
        this.bruk = src.getBruk();
        this.kopiertFraGammeltKrav = src.getKopiertFraGammeltKrav();
        this.registerOpprettetAv = src.getRegisterOpprettetAv();
        this.grunnlagKilde = src.getGrunnlagKilde();
        this.registerKilde = src.getRegisterKilde();
        this.inntektType = src.getInntektType();
        this.endringstidspunkt = copyDate(src.getEndringstidspunkt());
        this.inntektHendelseType = src.getInntektHendelseType();
        this.grunnIkkeReduksjonType = src.getGrunnIkkeReduksjonType();
    }

    /**
     * Copy constructor
     * Brukes i spesialtilfeller der vi vil opprette et inntektsgrunnlag fra Blaze hvor ID skal være null.
     * Dette brukes i enkelte batchtjenester, og kan ikke gjøres direkte i Blaze fordi Long blir mappet til integer.
     *
     * @param inntektsgrunnlag to copy
     * @param inntektsgrunnlagIdNull is <code>true</code> if id should be null
     */
    public Inntektsgrunnlag(Inntektsgrunnlag inntektsgrunnlag, boolean inntektsgrunnlagIdNull) {
        this(inntektsgrunnlag);

        if (inntektsgrunnlagIdNull) {
            inntektsgrunnlagId = null;
        }
    }

//    @Override
//    public GrunnlagVedleggType getGrunnlagVedleggType() {
//        return GrunnlagVedleggType.INNTEKT;
//    }

    /**
     * @return the belop
     */
    public Integer getBelop() {
        return belop;
    }

    /**
     * @param belop the belop to set
     */
    public void setBelop(Integer belop) {
        if (belop != null) {
            this.belop = belop;
        }
    }

    /**
     * @return the bruk
     */
    public Boolean getBruk() {
        return bruk;
    }

    /**
     * @param bruk the bruk to set
     */
    public void setBruk(Boolean bruk) {
        this.bruk = bruk;
    }

    /**
     * @return the fomDato
     */
    public Date getFomDato() {
        return copyDate(fomDato);
    }

    /**
     * @param fomDato the fomDato to set
     */
    public void setFomDato(Date fomDato) {
        this.fomDato = copyDate(fomDato);
    }

    /**
     * @return the grunnlagKilde
     */
    public GrunnlagKildeCti getGrunnlagKilde() {
        return grunnlagKilde;
    }

    /**
     * @param grunnlagKilde the grunnlagKilde to set
     */
    public void setGrunnlagKilde(GrunnlagKildeCti grunnlagKilde) {
        this.grunnlagKilde = grunnlagKilde;
    }

    /**
     * @return the inntektsgrunnlagId
     */
    public Long getInntektsgrunnlagId() {
        return inntektsgrunnlagId;
    }

    /**
     * @param inntektsgrunnlagId the inntektsgrunnlagId to set
     */
    public void setInntektsgrunnlagId(Long inntektsgrunnlagId) {
        this.inntektsgrunnlagId = inntektsgrunnlagId;
    }

    /**
     * @return the inntektType
     */
    public InntektTypeCti getInntektType() {
        return inntektType;
    }

    /**
     * @param inntektType the inntektType to set
     */
    public void setInntektType(InntektTypeCti inntektType) {
        this.inntektType = inntektType;
    }

    /**
     * @return the kildeInnt
     */
    public KildeInntCti getRegisterKilde() {
        return registerKilde;
    }

    /**
     * @param kildeInnt the kildeInnt to set
     */
    public void setRegisterKilde(KildeInntCti kildeInnt) {
        registerKilde = kildeInnt;
    }

    /**
     * @return the kopiertFraGammeltKrav
     */
    public Boolean getKopiertFraGammeltKrav() {
        return kopiertFraGammeltKrav;
    }

    /**
     * @param kopiertFraGammeltKrav the kopiertFraGammeltKrav to set
     */
    public void setKopiertFraGammeltKrav(Boolean kopiertFraGammeltKrav) {
        this.kopiertFraGammeltKrav = kopiertFraGammeltKrav;
    }

    /**
     * @return the registerOpprettetAv
     */
    public String getRegisterOpprettetAv() {
        return registerOpprettetAv;
    }

    /**
     * @param registerOpprettetAv the registerOpprettetAv to set
     */
    public void setRegisterOpprettetAv(String registerOpprettetAv) {
        this.registerOpprettetAv = registerOpprettetAv;
    }

    /**
     * @return the tomDato
     */
    public Date getTomDato() {
        return copyDate(tomDato);
    }

    /**
     * @param tomDato the tomDato to set
     */
    public void setTomDato(Date tomDato) {
        this.tomDato = copyDate(tomDato);
    }

    /**
     * @return the endringstidspunkt
     */
    public Date getEndringstidspunkt() {
        return copyDate(endringstidspunkt);
    }

    /**
     * @param endringstidspunkt the endringstidspunkt to set
     */
    public void setEndringstidspunkt(Date endringstidspunkt) {
        this.endringstidspunkt = copyDate(endringstidspunkt);
    }

//    @Override
//    public void setGrunnlagVedlegg(InntektsgrunnlagVedlegg grunnlagVedlegg) {
//        this.grunnlagVedlegg = grunnlagVedlegg;
//    }

    /**
     * You should not use this method as this is not part of the domain model.
     *
     * @return persongrunnlag Persongrunnlag
     */
    public Persongrunnlag getPersongrunnlag() {
        return persongrunnlag;
    }

    /**
     * You should not use this method as it is not part of the domain model.
     *
     * @param persongrunnlag Persongrunnlag
     */
    public void setPersongrunnlag(Persongrunnlag persongrunnlag) {
        this.persongrunnlag = persongrunnlag;
    }

    /**
     * @return the grunnIkkeReduksjonType
     */
    public GrunnIkkeReduksjonTypeCti getGrunnIkkeReduksjonType() {
        return grunnIkkeReduksjonType;
    }

    /**
     * @param grunnIkkeReduksjonType the grunnIkkeReduksjonType to set
     */
    public void setGrunnIkkeReduksjonType(GrunnIkkeReduksjonTypeCti grunnIkkeReduksjonType) {
        this.grunnIkkeReduksjonType = grunnIkkeReduksjonType;
    }

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
//    @Override
    public int compareTo(Object o) {
        Inntektsgrunnlag inntektsgrunnlag = (Inntektsgrunnlag) o;
        // null sorteres foran
        if (fomDato == null) {
            if (inntektsgrunnlag.getFomDato() == null) {
                return 0;
            } else {
                return -1;
            }
        }

        return fomDato.compareTo(inntektsgrunnlag.getFomDato());
    }

    public InntektHendelseTypeCti getInntektHendelseType() {
        return inntektHendelseType;
    }

    public void setInntektHendelseType(InntektHendelseTypeCti inntektHendelseType) {
        this.inntektHendelseType = inntektHendelseType;
    }

//    @Override
    public InntektTypeCode getTypeCode() {
        return inntektType == null ? null : inntektType.getCode();
    }

//    public void setEtteroppgjorDetaljInntekt(UforetrygdEtteroppgjorDetalj etteroppgjorDetalj) {
//        this.etteroppgjorDetaljInntekt = etteroppgjorDetalj;
//    }

//    public void setEtteroppgjorDetaljFratrekk(UforetrygdEtteroppgjorDetalj etteroppgjorDetalj) {
//        this.etteroppgjorDetaljFratrekk = etteroppgjorDetalj;
//    }

    public boolean isForventetInntekt() {
        return EnumSet.of(FORINTARB, FORINTAND, FORINTNAE, FORINTUTL, FORPENUTL).contains(getTypeCode());
    }

    public boolean isRelevantForBT() {
        return BooleanUtils.isTrue(inntektType == null ? null : inntektType.getRelevantBt());
    }

    /**
     * @param year the year to check
     * @param allowLater if later years are allowed
     * @return <code>true</code> if the inntektsgrunnlag starts in or after the given year
     */
    public boolean isValidForYear(int year, boolean allowLater) {
        return allowLater ? fetchYear() >= year : fetchYear() == year;
    }

    /**
     * @param other the other Inntektsgrunnlag
     * @return <code>true</code> if this' tomDate is after the other
     */
    public boolean isAfterByTomDate(Inntektsgrunnlag other) {
        return tomDato != null && other.tomDato != null && tomDato.after(other.tomDato);
    }

    /**
     * @return the year this innteksgrunnlag belongs to
     */
    public int fetchYear() {
        return DateUtil.getYear(fomDato);
    }

    /**
     * checks if innteksgrunlag RegisterKilde code is equal to the given code
     *
     * @return true if given KildeInntCodes is equal to RegisterKilde
     */
//    public boolean isRegisterKildeType(KildeInntCode... kildeInntCodes) {
//        for (KildeInntCode kildeInntCode : kildeInntCodes) {
//            if (this.registerKilde.isCodeEqualTo(kildeInntCode)) {
//                return true;
//            }
//        }
//        return false;
//    }

    public boolean isInntektType(InntektTypeCode ikkeRed) {
        return ikkeRed.equals(inntektType.getCode());
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", inntektsgrunnlagId)
                .append("inntektType", getTypeCode())
                .append("fomDato", DateUtil.format(fomDato))
                .append("tomDato", DateUtil.format(tomDato))
                .append("belop", belop)
                .append("bruk", bruk)
                .toString();
    }
}
