package no.nav.consumer.pensjon.pselv.brukerprofil.to;

import no.stelvio.common.transferobject.ServiceResponse;

public class SendEpostmeldingResponse extends ServiceResponse {

    private static final long serialVersionUID = 2229538769570540754L;
    private String responseObject;

    public String getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(String responseObject) {
        this.responseObject = responseObject;
    }
}
