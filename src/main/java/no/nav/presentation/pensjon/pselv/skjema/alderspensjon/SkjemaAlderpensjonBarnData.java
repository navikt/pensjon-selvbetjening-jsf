package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.io.Serializable;

public class SkjemaAlderpensjonBarnData implements Serializable {

    private static final long serialVersionUID = -5709956676947214131L;
    private String fnr;
    private Boolean isFellesBarn;
    private Boolean hasInntekt;

    public Boolean getHasInntekt() {
        return hasInntekt;
    }

    public void setHasInntekt(Boolean hasInntekt) {
        this.hasInntekt = hasInntekt;
    }

    public boolean isFellesBarn() {
        return isFellesBarn;
    }

    public void setIsFellesBarn(Boolean isFellesBarn) {
        this.isFellesBarn = isFellesBarn;
    }

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }
}
