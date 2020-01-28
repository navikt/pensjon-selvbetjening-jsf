package no.nav.service.pensjon.sak;

import no.stelvio.common.transferobject.ServiceRequest;

/**
 * Request object for TPEN221 HentApplikasjonsparamter
 */
public class HentApplikasjonsparameterRequest extends ServiceRequest {

    private static final long serialVersionUID = -7298056525068002889L;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
