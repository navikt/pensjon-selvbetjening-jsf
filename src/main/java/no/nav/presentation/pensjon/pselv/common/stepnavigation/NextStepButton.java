package no.nav.presentation.pensjon.pselv.common.stepnavigation;

import java.io.Serializable;

/**
 * Button navigation to next step in the framework 'trinnvis navigasjon'.
 */
public class NextStepButton implements Serializable {

    private static final long serialVersionUID = -9073662265131397140L;

    /* Displayed text of the button */
    private String value;

    /* The alternative text */
    private String alt;

    private String title;

    private boolean enable;

    public NextStepButton(String value, String alt, String title) {
        this.value = value;
        this.alt = alt;
        this.title = title;
        enable = true;
    }

    public void disableButton() {
        enable = false;
    }

    public void enableButton() {
        enable = true;
    }

    public String getAlt() {
        return alt;
    }

    public String getTitle() {
        return title;
    }

    public String getValue() {
        return value;
    }

    public boolean getEnable() {
        return enable;
    }

    public boolean getDisable() {
        return !enable;
    }
}
