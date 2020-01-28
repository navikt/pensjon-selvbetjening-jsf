package no.nav.presentation.pensjon.pselv.common.delegate;

import no.nav.consumer.pensjon.pselv.utbetaling.UtbetalingServiceBi;
import no.nav.domain.pensjon.common.oppdrag.Utbetaling;
import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.domain.person.Pid;

import java.util.ArrayList;
import java.util.List;

public class UtbetalingCommonActionDelegate {
    //TODO complete
    public void setUtbetalingService(UtbetalingServiceBi utbetalingService) {

    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {

    }

    public List<Utbetaling> hentUtbetalingsListe(Pid pid, Integer twoMonthsBack) {
        return new ArrayList<>();
    }

    public List<Utbetaling> getPensjonsutbetalinger(List<Utbetaling> utbetTwoMonthsBack) {
        return new ArrayList<>();
    }
}
