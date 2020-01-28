package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.utbetaling.UtbetalingServiceBi;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentPeriodisertUtbetalingListeRequest;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentPeriodisertUtbetalingListeResponse;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentUtbetalingListeRequest;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentUtbetalingListeResponse;

public class FakeUtbetalingService implements UtbetalingServiceBi {

    @Override
    public HentUtbetalingListeResponse hentUtbetalingListe(HentUtbetalingListeRequest request) {
        return new HentUtbetalingListeResponse();
    }

    @Override
    public HentPeriodisertUtbetalingListeResponse hentPeriodisertUtbetalingListe(HentPeriodisertUtbetalingListeRequest request) {
        return new HentPeriodisertUtbetalingListeResponse();
    }
}
