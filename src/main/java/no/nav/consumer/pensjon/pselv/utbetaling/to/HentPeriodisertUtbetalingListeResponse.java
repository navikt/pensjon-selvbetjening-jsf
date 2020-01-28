package no.nav.consumer.pensjon.pselv.utbetaling.to;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.oppdrag.Utbetaling;

public class HentPeriodisertUtbetalingListeResponse extends ServiceResponse {

    private static final long serialVersionUID = -2012765623295721130L;
    private List<Utbetaling> responseObjectList;

    public List<Utbetaling> getResponseObjectList() {
        return responseObjectList;
    }

    public void setResponseObjectList(List<Utbetaling> responseObjectList) {
        this.responseObjectList = responseObjectList;
    }
}
