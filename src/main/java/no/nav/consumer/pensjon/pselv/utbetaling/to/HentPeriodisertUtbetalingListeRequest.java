package no.nav.consumer.pensjon.pselv.utbetaling.to;

import java.util.Calendar;

import no.stelvio.common.transferobject.ServiceRequest;

public class HentPeriodisertUtbetalingListeRequest extends ServiceRequest {

    private static final long serialVersionUID = -5088554892230383096L;
    private String fnrOrgnr;
    private Calendar fomDato;
    private Calendar tomDato;
    private String kodeYtelse;
    private String visHistorikk;

    public String getFnrOrgnr() {
        return fnrOrgnr;
    }

    public Calendar getFomDato() {
        return fomDato;
    }

    public String getKodeYtelse() {
        return kodeYtelse;
    }

    public Calendar getTomDato() {
        return tomDato;
    }

    public String getVisHistorikk() {
        return visHistorikk;
    }

    public void setFnrOrgnr(String fnrOrgnr) {
        this.fnrOrgnr = fnrOrgnr;
    }

    public void setFomDato(Calendar fomDato) {
        this.fomDato = fomDato;
    }

    public void setKodeYtelse(String kodeYtelse) {
        this.kodeYtelse = kodeYtelse;
    }

    public void setTomDato(Calendar tomDato) {
        this.tomDato = tomDato;
    }

    public void setVisHistorikk(String visHistorikk) {
        this.visHistorikk = visHistorikk;
    }
}
