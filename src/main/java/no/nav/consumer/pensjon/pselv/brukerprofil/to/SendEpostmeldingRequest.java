package no.nav.consumer.pensjon.pselv.brukerprofil.to;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.common.brukerprofil.Epostmelding;

public class SendEpostmeldingRequest extends ServiceRequest {

    private static final long serialVersionUID = -207881963862891282L;
    private Epostmelding epostmelding;


    public SendEpostmeldingRequest() {
    }

    public Epostmelding getEpostmelding() {
        return epostmelding;
    }

    public void setEpostmelding(Epostmelding epostmelding) {
        this.epostmelding = epostmelding;
    }
}
