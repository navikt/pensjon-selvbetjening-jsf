package no.nav.service.pensjon.sak;

public interface SakServiceBi {

    HentSakListeResponse hentSakListe(HentSakListeRequest request);

    HentApplikasjonsparameterResponse hentApplikasjonsparameter(HentApplikasjonsparameterRequest hentApplikasjonsparameterRequest);
}
