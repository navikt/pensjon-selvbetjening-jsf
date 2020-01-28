package no.nav.service.pensjon.sak;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.Applikasjonsparameter;

/**
 * Response for TPEN221 HentApplikasjonsparamter
 */
public class HentApplikasjonsparameterResponse extends ServiceResponse {

    private static final long serialVersionUID = -1047478772515147057L;
    private Applikasjonsparameter applikasjonsparameter;

    public Applikasjonsparameter getApplikasjonsparameter() {
        return applikasjonsparameter;
    }

    public void setApplikasjonsparameter(Applikasjonsparameter applikasjonsparameter) {
        this.applikasjonsparameter = applikasjonsparameter;
    }
}
