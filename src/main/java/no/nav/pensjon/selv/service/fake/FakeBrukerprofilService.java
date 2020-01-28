package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.brukerprofil.BrukerprofilServiceBi;
import no.nav.consumer.pensjon.pselv.brukerprofil.to.SendEpostmeldingRequest;
import no.nav.consumer.pensjon.pselv.brukerprofil.to.SendEpostmeldingResponse;

public class FakeBrukerprofilService implements BrukerprofilServiceBi {

    @Override
    public SendEpostmeldingResponse sendEpostmelding(SendEpostmeldingRequest req) {
        SendEpostmeldingResponse response = new SendEpostmeldingResponse();
        response.setResponseObject("EpostmeldingResponse obj");
        return response;
    }
}
