package no.nav.domain.pensjon.kjerne.skjema;

import static org.hibernate.annotations.FetchMode.SELECT;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

//import no.nav.domain.AbstractVersionedPersistentDomainObject;
import no.nav.domain.pensjon.kjerne.kodetabeller.InitiertAvCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.Land3TegnCti;

//@Entity
//@Table(name = "T_SKJEMA_INNLEDN")
//public class SkjemaInnledning extends AbstractVersionedPersistentDomainObject {
public class SkjemaInnledning  implements Serializable {
    private static final long serialVersionUID = 6339749143229265139L;

    //@Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "skjema_innledn", nullable = false)
    private Long skjemaInnledningId;

    /**
     * Dato ytelsen ønskes iverksatt.
     */
    //@Column(name = "dato_virk_onsket", nullable = false)
    private Date iverksettelsesdato;

    /**
     * Flagg som angir om det søkes om ektefelletillegg.
     */
    //@Column(name = "forsorgingstillegg", nullable = false)
    private Boolean forsorgingstilleggEPS;

    /**
     * Flagg som angir om det søkes om barnetillegg.
     */
    //@Column(name = "barnetillegg", nullable = false)
    private Boolean forsorgingstilleggBarn;

    //@Column(name = "SOKT_AFP_PRIVAT", nullable = true)
    private Boolean soktAfpPrivat;

    //@Column(name = "UTTAKSGRAD", nullable = true)
    private Integer uttaksgrad;

    //@Column(name = "MOTTATT_DATO", nullable = true)
    private Date mottattDato;

    //@Column(name = "VURDERE_TRYGDE_AVTALE", nullable = true)
    private Boolean vurdereTrygdeavtale;

    //@Column(name = "BUC_ID", nullable = true)
    private String bucId;

    //@ManyToOne
    //@Fetch(SELECT)
    //@JoinColumn(name = "BOSTEDLAND", nullable = true)
    private Land3TegnCti soknadFraLand;

    //@ManyToOne
    //@Fetch(SELECT)
    //@JoinColumn(name = "INITIERT_AV", nullable = true)
    private InitiertAvCti initiertAv;
    /**
     * @return the forsorgingstilleggBarn
     */
    public Boolean getForsorgingstilleggBarn() {
        return forsorgingstilleggBarn;
    }

    /**
     * @param forsorgingstilleggBarn the forsorgingstilleggBarn to set
     */
    public void setForsorgingstilleggBarn(Boolean forsorgingstilleggBarn) {
        this.forsorgingstilleggBarn = forsorgingstilleggBarn;
    }

    /**
     * @return the forsorgingstilleggEPS
     */
    public Boolean getForsorgingstilleggEPS() {
        return forsorgingstilleggEPS;
    }

    /**
     * @param forsorgingstilleggEPS the forsorgingstilleggEPS to set
     */
    public void setForsorgingstilleggEPS(Boolean forsorgingstilleggEPS) {
        this.forsorgingstilleggEPS = forsorgingstilleggEPS;
    }

    /**
     * @return the iverksettelsesdato
     */
    public Date getIverksettelsesdato() {
        return iverksettelsesdato;
    }

    /**
     * @param iverksettelsesdato the iverksettelsesdato to set
     */
    public void setIverksettelsesdato(Date iverksettelsesdato) {
        this.iverksettelsesdato = iverksettelsesdato;
    }

    /**
     * @return the skjemaInnledningId
     */
    public Long getSkjemaInnledningId() {
        return skjemaInnledningId;
    }

    /**
     * @param skjemaInnledningId the skjemaInnledningId to set
     */
    public void setSkjemaInnledningId(Long skjemaInnledningId) {
        this.skjemaInnledningId = skjemaInnledningId;
    }

    /**
     * @return the soktAfpPrivat
     */
    public Boolean getSoktAfpPrivat() {
        return soktAfpPrivat;
    }

    /**
     * @param soktAfpPrivat the soktAfpPrivat to set
     */
    public void setSoktAfpPrivat(Boolean soktAfpPrivat) {
        this.soktAfpPrivat = soktAfpPrivat;
    }

    /**
     * @return the uttaksgrad
     */
    public Integer getUttaksgrad() {
        return uttaksgrad;
    }

    /**
     * @param uttaksgrad the uttaksgrad to set
     */
    public void setUttaksgrad(Integer uttaksgrad) {
        this.uttaksgrad = uttaksgrad;
    }

    /**
     * @return the mottattDato
     */
    public Date getMottattDato() {
        return mottattDato;
    }

    /**
     * @param mottattDato the mottattDato to set
     */
    public void setMottattDato(Date mottattDato) {
        this.mottattDato = mottattDato;
    }

    /**
     * @return the vurdereTrygdeavtale
     */
    public Boolean getVurdereTrygdeavtale() {
        return vurdereTrygdeavtale;
    }

    /**
     * @param vurdereTrygdeavtale the vurdereTrygdeavtale to set
     */
    public void setVurdereTrygdeavtale(Boolean vurdereTrygdeavtale) {
        this.vurdereTrygdeavtale = vurdereTrygdeavtale;
    }

    /**
     * @return the bucId
     */
    public String getBucId() {
        return bucId;
    }

    /**
     * @param bucId the bucId to set
     */
    public void setBucId(String bucId) {
        this.bucId = bucId;
    }

    /**
     * @return the soknadFraLand
     */
    public Land3TegnCti getSoknadFraLand() {
        return soknadFraLand;
    }

    /**
     * @param soknadFraLand the soknadFraLand to set
     */
    public void setSoknadFraLand(Land3TegnCti soknadFraLand) {
        this.soknadFraLand = soknadFraLand;
    }

    /**
     * @return the initiertAv
     */
    public InitiertAvCti getInitiertAv() {
        return initiertAv;
    }

    /**
     * @param initiertAv the initiertAv to set
     */
    public void setInitiertAv(InitiertAvCti initiertAv) {
        this.initiertAv = initiertAv;
    }
}
