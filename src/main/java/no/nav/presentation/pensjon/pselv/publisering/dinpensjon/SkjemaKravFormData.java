package no.nav.presentation.pensjon.pselv.publisering.dinpensjon;

import java.io.Serializable;
import java.util.Date;

import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;

/**
 * Form data class for module PUS002 Din pensjon. Contains data about a skjema or sak, used in the list of the user's sak and
 * skjema.
 *
 */
public class SkjemaKravFormData implements Serializable {
    private static final long serialVersionUID = -7329624702889321893L;

    private String id;

    private String navn;

    private String status;

    private Date dato;

    private Boolean erSkjema;

    private Boolean erKrav;

    private String type;

    public boolean isAlderspensjon() {
        return ElektroniskSkjemaTypeCode.AP.name().equals(type);
    }

    public boolean isUforetrygd() {
        return ElektroniskSkjemaTypeCode.UT.name().equals(type);
    }

    /**
     * Getter for the dato
     *
     * @return the dato
     */
    public Date getDato() {
        return dato;
    }

    /**
     * Setter for the dato
     *
     * @param dato the dato to set
     */
    public void setDato(Date dato) {
        this.dato = dato;
    }

    /**
     * Getter for the erKrav
     *
     * @return the erKrav
     */
    public Boolean getErKrav() {
        return erKrav;
    }

    /**
     * Setter for the erKrav
     *
     * @param erKrav the erKrav to set
     */
    public void setErKrav(Boolean erKrav) {
        this.erKrav = erKrav;
    }

    /**
     * Getter for the erSkjema
     *
     * @return the erSkjema
     */
    public Boolean getErSkjema() {
        return erSkjema;
    }

    /**
     * Setter for the erSkjema
     *
     * @param erSkjema the erSkjema to set
     */
    public void setErSkjema(Boolean erSkjema) {
        this.erSkjema = erSkjema;
    }

    /**
     * Getter for the id
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Setter for the id
     *
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Getter for the navn
     *
     * @return the navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Setter for the navn
     *
     * @param navn the navn to set
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Getter for the status
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Setter for the status
     *
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Getter for the type
     *
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for the type
     *
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }
}
