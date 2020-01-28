package no.nav.service.pensjon.vedtak;

public interface VedtakServiceBi {

    HentVedtakListeResponse hentVedtakListe(HentVedtakListeRequest request);

    BestemGjeldendeVedtakResponse bestemGjeldendeVedtak(BestemGjeldendeVedtakRequest request);
}
