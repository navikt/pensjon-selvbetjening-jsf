package no.nav.consumer.pensjon.pselv.utbetaling.to;

import java.util.Calendar;

import no.stelvio.common.transferobject.ServiceRequest;

public class HentUtbetalingListeRequest extends ServiceRequest {

    private static final long serialVersionUID = 1736437021361950342L;
    private String fnrOrgnr;
    private Calendar fomDato;
    private Calendar tomDato;
    private String kodeYtelse;
    private String visHistorikk;

    public String getFnrOrgnr() {
        return fnrOrgnr;
    }

    public void setFnrOrgnr(String fnrOrgnr) {
        this.fnrOrgnr = fnrOrgnr;
    }

    public Calendar getFomDato() {
        return fomDato;
    }

    public void setFomDato(Calendar fomDato) {
        this.fomDato = fomDato;
    }

    public String getKodeYtelse() {
        return kodeYtelse;
    }

    public void setKodeYtelse(String kodeYtelse) {
        this.kodeYtelse = kodeYtelse;
    }

    public Calendar getTomDato() {
        return tomDato;
    }

    public void setTomDato(Calendar tomDato) {
        this.tomDato = tomDato;
    }

    public String getVisHistorikk() {
        return visHistorikk;
    }

    public void setVisHistorikk(String visHistorikk) {
        this.visHistorikk = visHistorikk;
    }
}
