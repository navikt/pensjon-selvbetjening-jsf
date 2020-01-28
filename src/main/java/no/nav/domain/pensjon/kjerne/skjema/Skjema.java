package no.nav.domain.pensjon.kjerne.skjema;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.PenPerson;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SkjemaStatusCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;

import java.io.Serializable;

//@Entity
//@NamedQuery(name = "Skjema.findSkjemaListeByPidOrderedByUpdatedDate", query = "select skjema from Skjema skjema where skjema.penPerson.fnr = :pid "
//        + "order by skjema.changeStamp.updatedDate desc")
//@Table(name = "T_SKJEMA")
public class Skjema extends AbstractVersionedPersistentDomainObject {
    private static final long serialVersionUID = -3354047574921088911L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "skjema_id", nullable = false)
    private Long skjemaId;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_innledn")
    private SkjemaInnledning skjemaInnledning;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_pers_oppl")
    private SkjemaPersonopplysninger skjemaPersonopplysninger;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_fam_forh_id")
    private SkjemaFamilieforhold skjemaFamilieforhold;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_barn_opp_id")
    private SkjemaBarneopplysninger skjemaBarneopplysninger;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_utland_id")
    private SkjemaUtland skjemaUtland;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_frem_innt")
    private SkjemaFremtidigInntekt skjemaFremtidigInntekt;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_pen_an_y_id")
    private SkjemaPensjonAndreYtelser skjemaPensjonAndreYtelser;

    /**
     * Skjemaobjekt som inneholder AFP-privat
     * spesifikk informasjon bruker har oppgitt når hun søker om AP med AFP-tillegg.
     */
    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "SKJEMA_AFP_PRIVAT_ID")
    private SkjemaAFPPrivat skjemaAFPPrivat;

    //@OneToOne(cascade = CascadeType.REFRESH)
    //@JoinColumn(name = "kravhode_id")
    private KravHode kravhode;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_skjema_pselv_s", nullable = false)
    private SkjemaStatusCti skjemaPselvStatus;

    //@ManyToOne
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "k_skjema_pselv_t", nullable = false)
    private ElektroniskSkjemaTypeCti skjemaPselvType;

    //@ManyToOne(fetch = javax.persistence.FetchType.EAGER)
    //@JoinColumn(name = "PERSON_ID", nullable = false)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    private PenPerson penPerson;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_arbeidsforhold_id")
    private SkjemaArbiedsforhold skjemaArbeidsforhold;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_forstegtjen_id")
    private SkjemaForstegangstjeneste skjemaForstegangstjeneste;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "skjema_yrkesskade_id")
    private SkjemaYrkesskade skjemaYrkesskade;

    /**
     * @return the kravhode
     */
    public KravHode getKravhode() {
        return kravhode;
    }

    /**
     * @param kravhode the kravhode to set
     */
    public void setKravhode(KravHode kravhode) {
        this.kravhode = kravhode;
    }

    /**
     * @return the skjemaBarneopplysninger
     */
    public SkjemaBarneopplysninger getSkjemaBarneopplysninger() {
        return skjemaBarneopplysninger;
    }

    /**
     * @param skjemaBarneopplysninger the skjemaBarneopplysninger to set
     */
    public void setSkjemaBarneopplysninger(SkjemaBarneopplysninger skjemaBarneopplysninger) {
        this.skjemaBarneopplysninger = skjemaBarneopplysninger;
    }

    /**
     * @return the skjemaFamilieforhold
     */
    public SkjemaFamilieforhold getSkjemaFamilieforhold() {
        return skjemaFamilieforhold;
    }

    /**
     * @param skjemaFamilieforhold the skjemaFamilieforhold to set
     */
    public void setSkjemaFamilieforhold(SkjemaFamilieforhold skjemaFamilieforhold) {
        this.skjemaFamilieforhold = skjemaFamilieforhold;
    }

    /**
     * @return the skjemaFremtidigInntekt
     */
    public SkjemaFremtidigInntekt getSkjemaFremtidigInntekt() {
        return skjemaFremtidigInntekt;
    }

    /**
     * @param skjemaFremtidigInntekt the skjemaFremtidigInntekt to set
     */
    public void setSkjemaFremtidigInntekt(SkjemaFremtidigInntekt skjemaFremtidigInntekt) {
        this.skjemaFremtidigInntekt = skjemaFremtidigInntekt;
    }

    /**
     * @return the skjemaId
     */
    public Long getSkjemaId() {
        return skjemaId;
    }

    /**
     * @param skjemaId the skjemaId to set
     */
    public void setSkjemaId(Long skjemaId) {
        this.skjemaId = skjemaId;
    }

    /**
     * @return the skjemaInnledning
     */
    public SkjemaInnledning getSkjemaInnledning() {
        return skjemaInnledning;
    }

    /**
     * @param skjemaInnledning the skjemaInnledning to set
     */
    public void setSkjemaInnledning(SkjemaInnledning skjemaInnledning) {
        this.skjemaInnledning = skjemaInnledning;
    }

    /**
     * @return the skjemaPensjonAndreYtelser
     */
    public SkjemaPensjonAndreYtelser getSkjemaPensjonAndreYtelser() {
        return skjemaPensjonAndreYtelser;
    }

    /**
     * @param skjemaPensjonAndreYtelser the skjemaPensjonAndreYtelser to set
     */
    public void setSkjemaPensjonAndreYtelser(SkjemaPensjonAndreYtelser skjemaPensjonAndreYtelser) {
        this.skjemaPensjonAndreYtelser = skjemaPensjonAndreYtelser;
    }

    /**
     * @return the skjemaPersonopplysninger
     */
    public SkjemaPersonopplysninger getSkjemaPersonopplysninger() {
        return skjemaPersonopplysninger;
    }

    /**
     * @param skjemaPersonopplysninger the skjemaPersonopplysninger to set
     */
    public void setSkjemaPersonopplysninger(SkjemaPersonopplysninger skjemaPersonopplysninger) {
        this.skjemaPersonopplysninger = skjemaPersonopplysninger;
    }

    /**
     * @return the skjemaUtland
     */
    public SkjemaUtland getSkjemaUtland() {
        return skjemaUtland;
    }

    /**
     * @param skjemaUtland the skjemaUtland to set
     */
    public void setSkjemaUtland(SkjemaUtland skjemaUtland) {
        this.skjemaUtland = skjemaUtland;
    }

    public SkjemaStatusCti getSkjemaPselvStatus() {
        return skjemaPselvStatus;
    }

    public void setSkjemaPselvStatus(SkjemaStatusCti skjemaPselvStatus) {
        this.skjemaPselvStatus = skjemaPselvStatus;
    }

    public ElektroniskSkjemaTypeCti getSkjemaPselvType() {
        return skjemaPselvType;
    }

    public void setSkjemaPselvType(ElektroniskSkjemaTypeCti skjemaPselvType) {
        this.skjemaPselvType = skjemaPselvType;
    }

    /**
     * @return the penPerson
     */
    public PenPerson getPenPerson() {
        return penPerson;
    }

    /**
     * @param penPerson the penPerson to set
     */
    public void setPenPerson(PenPerson penPerson) {
        this.penPerson = penPerson;
    }

    /**
     * @return the skjemaAFPPrivat
     */
    public SkjemaAFPPrivat getSkjemaAFPPrivat() {
        return skjemaAFPPrivat;
    }

    public void setSkjemaAFPPrivat(SkjemaAFPPrivat skjemaAFPPrivat) {
        this.skjemaAFPPrivat = skjemaAFPPrivat;
    }

    public SkjemaArbiedsforhold getSkjemaArbeidsforhold() {
        return skjemaArbeidsforhold;
    }

    public void setSkjemaArbeidsforhold(SkjemaArbiedsforhold skjemaArbeidsforhold) {
        this.skjemaArbeidsforhold = skjemaArbeidsforhold;
    }

    public SkjemaForstegangstjeneste getSkjemaForstegangstjeneste() {
        return skjemaForstegangstjeneste;
    }

    public void setSkjemaForstegangstjeneste(SkjemaForstegangstjeneste skjemaForstegangstjeneste) {
        this.skjemaForstegangstjeneste = skjemaForstegangstjeneste;
    }

    public SkjemaYrkesskade getSkjemaYrkesskade() {
        return skjemaYrkesskade;
    }

    public void setSkjemaYrkesskade(SkjemaYrkesskade skjemaYrkesskade) {
        this.skjemaYrkesskade = skjemaYrkesskade;
    }
}