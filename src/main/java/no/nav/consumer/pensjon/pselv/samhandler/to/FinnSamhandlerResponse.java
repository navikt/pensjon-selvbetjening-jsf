package no.nav.consumer.pensjon.pselv.samhandler.to;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.samhandler.Samhandler;

public class FinnSamhandlerResponse extends ServiceResponse {

    private static final long serialVersionUID = 4971286915175489457L;
    private List<Samhandler> responseObject;

    public FinnSamhandlerResponse() {
    }

    public FinnSamhandlerResponse(List<Samhandler> responseObject) {
        this.responseObject = responseObject;
    }

    public List<Samhandler> getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(List<Samhandler> responseObject) {
        this.responseObject = responseObject;
    }
}
