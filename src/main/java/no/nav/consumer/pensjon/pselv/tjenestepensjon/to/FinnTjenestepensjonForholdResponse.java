package no.nav.consumer.pensjon.pselv.tjenestepensjon.to;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.tjenestepensjon.Tjenestepensjon;

public class FinnTjenestepensjonForholdResponse extends ServiceResponse {

    private static final long serialVersionUID = -6620510322710182768L;
    private Tjenestepensjon responseObject;

    public FinnTjenestepensjonForholdResponse() {
    }

    public FinnTjenestepensjonForholdResponse(Tjenestepensjon responseObject) {
        this.responseObject = responseObject;
    }

    public Tjenestepensjon getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Tjenestepensjon responseObject) {
        this.responseObject = responseObject;
    }
}
