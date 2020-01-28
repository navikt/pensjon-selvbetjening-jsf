package no.nav.presentation.pensjon.pselv.common.stepnavigation;

import java.io.Serializable;

/**
 * The cancel link in "trinnvis navigasjon" (step-navigation-framework). Each step may have an cancel link.
 */
public class CancelLink implements Serializable {
    private static final long serialVersionUID = 253267131559439404L;

    /* The displayed text/name of the link */
    private String value;

    /* Message shown in a dialog box when user press link */
    private String message;

    /* Tooltip */
    private String title;

    /* Alternative link text */
    private String alt;

    public CancelLink(String value, String message, String title, String alt) {
        this.value = value;
        this.message = message;
        this.title = title;
        this.alt = alt;
    }

    public String getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public String getTitle() {
        return title;
    }

    public String getAlt() {
        return alt;
    }
}
