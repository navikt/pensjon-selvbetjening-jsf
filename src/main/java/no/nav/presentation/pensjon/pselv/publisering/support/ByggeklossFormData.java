package no.nav.presentation.pensjon.pselv.publisering.support;

import java.io.Serializable;

public class ByggeklossFormData implements Serializable {
    private static final long serialVersionUID = 1L;

    private String action;

    private char id;

    private String title;

    private String alt;

    /**
     * Getter for the action
     *
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * Setter for the action
     *
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * Getter for the id
     *
     * @return the id
     */
    public char getId() {
        return id;
    }

    /**
     * Setter for the id
     *
     * @param id the id to set
     */
    public void setId(char id) {
        this.id = id;
    }

    /**
     * Getter for the alt
     *
     * @return the alt
     */
    public String getAlt() {
        return alt;
    }

    /**
     * Setter for the alt
     *
     * @param alt the alt to set
     */
    public void setAlt(String alt) {
        this.alt = alt;
    }

    /**
     * Getter for the tooltip
     *
     * @return the tooltip
     */
    public String getTitle() {
        return title;
    }

    /**
     * Setter for the tooltip
     *
     * @param tooltip the tooltip to set
     */
    public void setTitle(String tooltip) {
        title = tooltip;
    }
}
