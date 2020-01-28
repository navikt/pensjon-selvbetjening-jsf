package no.nav.consumer.pensjon.pselv.utbetaling;

import no.nav.consumer.pensjon.pselv.utbetaling.to.HentPeriodisertUtbetalingListeRequest;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentPeriodisertUtbetalingListeResponse;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentUtbetalingListeRequest;
import no.nav.consumer.pensjon.pselv.utbetaling.to.HentUtbetalingListeResponse;

/**
 * Business interface for the Utbetaling service facade.
 */
public interface UtbetalingServiceBi {

    HentUtbetalingListeResponse hentUtbetalingListe(HentUtbetalingListeRequest request);

    HentPeriodisertUtbetalingListeResponse hentPeriodisertUtbetalingListe(HentPeriodisertUtbetalingListeRequest request);
}
