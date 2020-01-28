package no.nav.consumer.pensjon.pselv.utbetaling.to;

import java.util.Collection;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.oppdrag.Utbetaling;

public class HentUtbetalingListeResponse extends ServiceResponse {

    private static final long serialVersionUID = 5728451168488952508L;
    private Collection<Utbetaling> utbetalinger;

    public Collection<Utbetaling> getUtbetalinger() {
        return utbetalinger;
    }

    public boolean isEmptyResult() {
        return utbetalinger == null ? true : utbetalinger.isEmpty() ? true
                : false;
    }

    public void setUtbetalinger(Collection<Utbetaling> utbetalinger) {
        this.utbetalinger = utbetalinger;
    }
}
