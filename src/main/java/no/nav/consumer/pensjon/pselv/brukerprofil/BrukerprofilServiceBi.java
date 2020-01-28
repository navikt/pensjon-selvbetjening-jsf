package no.nav.consumer.pensjon.pselv.brukerprofil;

import no.nav.consumer.pensjon.pselv.brukerprofil.to.SendEpostmeldingRequest;
import no.nav.consumer.pensjon.pselv.brukerprofil.to.SendEpostmeldingResponse;

public interface BrukerprofilServiceBi {

    SendEpostmeldingResponse sendEpostmelding(SendEpostmeldingRequest req);
}
