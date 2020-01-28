package no.nav.domain.pensjon.kjerne;

import java.io.Serializable;
import java.util.Date;

//import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.kodetabeller.BorMedTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagKildeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.GrunnlagsrolleCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstandTypeCode;

/**
 * I pensjonsløsningen skiller vi mellom faktisk sivilstand og vurdert
 * sivilstand. Med faktisk sivilstand menes det sivilstanden bruker er
 * registrert med i TPS. Vurdert sivilstand er derimot en skjønnsvurdering gjort
 * av saksbehandler. I forbindelse med pensjon taes det hensyn til om par lever
 * sammen og har felles husbeholdning eller ikke. Vurdert sivilstand kan derfor
 * være ulik faktisk sivilstand. Et eksempel er gifte par som lever atskilt
 * vurderes av saksbehandler som enslige.
 */
//@Entity
//@Table(name = "T_VURDERT_SIVILST")
//public class VurdertSivilstand extends AbstractVersionedPersistentDomainObject {
public class VurdertSivilstand implements Serializable {
    private static final long serialVersionUID = -7047825125101832489L;

    // Unik identifikator av en vurdert sivilstand
    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "VURDERT_SIVILST_ID", nullable = false)
    private Long vurdertSivilstandId;

    /**
     * Ikke del av domenemodellen, men trengs som del av fremmednøkkel fra PenPerson
     */
    //@ManyToOne(optional = false)
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "BRUKERS_PEN_PERS_ID")
    private PenPerson penPerson;

    // Den andre personen med relasjonen. Altså personen brukersPenPerson har en
    // relasjon til.
    //@ManyToOne()
    //@org.hibernate.annotations.Fetch(org.hibernate.annotations.FetchMode.SELECT)
    //@JoinColumn(name = "RELASJON_PEN_PERS_ID")
    private PenPerson relasjonsPenPerson;

    // Sivilstanden i TPS iht kodeverkstabell K_SIVILSTAND_T
    //@Enumerated(EnumType.STRING)
    //@Column(name = "K_SIVILSTAND")
    private SivilstandTypeCode sivilstand;

    // Kode som angir hvilke rolle relasjonsPenPerson har i den vurderte
    // sivilstanden iht kodeverkstabellen K_GRNL_ROLLE_T
    //@Enumerated(EnumType.STRING)
    //@Column(name = "K_GRUNNLAGSROLLE")
    private GrunnlagsrolleCode grunnlagsrolle;

    // Angir hvilke kilde den vurderte sivilstanden vises med iht
    // kodeverkstabellen K_GRUNNLAG_KILDE
    //@Enumerated(EnumType.STRING)
    //@Column(name = "K_GRUNNLAGSKILDE")
    private GrunnlagKildeCode grunnlagsKilde;

    // Den vurderte sivilstanden bruker vurderes med i forhold til
    // relasjonsPenPerson iht kodeverkstabellen K_BOR_MED_T
    //@Enumerated(EnumType.STRING)
    //@Column(name = "K_VURDERT_SIVILSTAND")
    private BorMedTypeCode vurdertSivilstand;

    // Angir når den vurderte sivilstanden starter
    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    //@Column(name = "FOM_DATO")
    private Date fomDato;

    // Angir når den vurderte sivilstanden slutter
    //@Type(type = "no.stelvio.domain.usertype.DateUserType")
    //@Column(name = "TOM_DATO")
    private Date tomDato;

    // Registerets informasjon om hvem som sist endret informasjonen. Kun
    // definert hvis grunnlagKilde er et register.
    //@Column(name = "REGISTER_ENDRET_AV")
    private String registerEndretAv;

    // Registerets kilde til informasjonen. Kun definert hvis grunnlagKilde er
    // et register.
    //@Column(name = "REGISTER_OPPR_AV")
    private String registerOpprettetAv;

    // Angir opprinnelig kilde iht kodeverkstabellen
    //@Enumerated(EnumType.STRING)
    //@Column(name = "opprin_grlag_kilde", nullable = true)
    private GrunnlagKildeCode opprinneligGrunnlagskilde;

    /**
     * Sivilstand rapportert fra TPS via endringsmelding om endret sivilstand.
     * Brukes av datavarehus for å ha statistikk på hvor ofte gjeldende sivilstand i PEN ikke er synkronisert med TPS.
     */
    //@Enumerated(EnumType.STRING)
    //@Column(name = "TPS_SIVILSTAND")
    private SivilstandTypeCode tpsSivilstand;

    /**
     * @return the fomDato
     */
    public Date getFomDato() {
        return fomDato;
    }

    /**
     * @param fomDato the fomDato to set
     */
    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    /**
     * @return the grunnlagsKilde
     */
    public GrunnlagKildeCode getGrunnlagsKilde() {
        return grunnlagsKilde;
    }

    /**
     * @param grunnlagsKilde the grunnlagsKilde to set
     */
    public void setGrunnlagsKilde(GrunnlagKildeCode grunnlagsKilde) {
        this.grunnlagsKilde = grunnlagsKilde;
    }

    /**
     * @return the grunnlagsrolle
     */
    public GrunnlagsrolleCode getGrunnlagsrolle() {
        return grunnlagsrolle;
    }

    /**
     * @param grunnlagsrolle the grunnlagsrolle to set
     */
    public void setGrunnlagsrolle(GrunnlagsrolleCode grunnlagsrolle) {
        this.grunnlagsrolle = grunnlagsrolle;
    }

    /**
     * @return the registerEndretAv
     */
    public String getRegisterEndretAv() {
        return registerEndretAv;
    }

    /**
     * @param registerEndretAv the registerEndretAv to set
     */
    public void setRegisterEndretAv(String registerEndretAv) {
        this.registerEndretAv = registerEndretAv;
    }

    /**
     * @return the registerKilde
     */
    public GrunnlagKildeCode getOpprinneligGrunnlagskilde() {
        return opprinneligGrunnlagskilde;
    }

    /**
     * @param registerKilde the registerKilde to set
     */
    public void setOpprinneligGrunnlagskilde(GrunnlagKildeCode registerKilde) {
        opprinneligGrunnlagskilde = registerKilde;
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
     * @return the relasjonsPenPerson
     */
    public PenPerson getRelasjonsPenPerson() {
        return relasjonsPenPerson;
    }

    /**
     * @param relasjonsPenPerson the relasjonsPenPerson to set
     */
    public void setRelasjonsPenPerson(PenPerson relasjonsPenPerson) {
        this.relasjonsPenPerson = relasjonsPenPerson;
    }

    /**
     * @return the tomDato
     */
    public Date getTomDato() {
        return tomDato;
    }

    /**
     * @param tomDato the tomDato to set
     */
    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato;
    }

    /**
     * @return the vurdertSivilstand
     */
    public BorMedTypeCode getVurdertSivilstand() {
        return vurdertSivilstand;
    }

    /**
     * @param vurdertSivilstand the vurdertSivilstand to set
     */
    public void setVurdertSivilstand(BorMedTypeCode vurdertSivilstand) {
        this.vurdertSivilstand = vurdertSivilstand;
    }

    /**
     * @return the vurdertSivilstandId
     */
    public Long getVurdertSivilstandId() {
        return vurdertSivilstandId;
    }

    /**
     * @param vurdertSivilstandId the vurdertSivilstandId to set
     */
    public void setVurdertSivilstandId(Long vurdertSivilstandId) {
        this.vurdertSivilstandId = vurdertSivilstandId;
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
     * @return the sivilstand
     */
    public SivilstandTypeCode getSivilstand() {
        return sivilstand;
    }

    /**
     * @param sivilstand the sivilstand to set
     */
    public void setSivilstand(SivilstandTypeCode sivilstand) {
        this.sivilstand = sivilstand;
    }

    /**
     * @return the tpsSivilstand
     */
    public SivilstandTypeCode getTpsSivilstand() {
        return tpsSivilstand;
    }

    /**
     * @param tpsSivilstand the tpsSivilstand to set
     */
    public void setTpsSivilstand(SivilstandTypeCode tpsSivilstand) {
        this.tpsSivilstand = tpsSivilstand;
    }
}
