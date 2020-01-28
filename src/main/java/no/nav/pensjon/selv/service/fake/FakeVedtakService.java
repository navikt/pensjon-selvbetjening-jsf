package no.nav.pensjon.selv.service.fake;

import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakResponse;
import no.nav.service.pensjon.vedtak.HentVedtakListeRequest;
import no.nav.service.pensjon.vedtak.HentVedtakListeResponse;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

public class FakeVedtakService implements VedtakServiceBi {

    @Override
    public HentVedtakListeResponse hentVedtakListe(HentVedtakListeRequest request) {
        return new HentVedtakListeResponse();
    }

    @Override
    public BestemGjeldendeVedtakResponse bestemGjeldendeVedtak(BestemGjeldendeVedtakRequest request) {
        return new BestemGjeldendeVedtakResponse();
    }
}
