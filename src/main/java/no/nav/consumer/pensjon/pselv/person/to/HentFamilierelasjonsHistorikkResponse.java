package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.person.Person;

public class HentFamilierelasjonsHistorikkResponse extends ServiceResponse {

    private static final long serialVersionUID = 6150000367713055912L;
    private Person responseObject;

    public HentFamilierelasjonsHistorikkResponse() {
    }

    public HentFamilierelasjonsHistorikkResponse(Person responseObject) {
        this.responseObject = responseObject;
    }

    public Person getResponseObject() {
        return responseObject;
    }

    public void setResponseObject(Person responseObject) {
        this.responseObject = responseObject;
    }
}
