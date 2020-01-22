package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import java.util.Calendar;

public class HentSamboerforholdRequest extends ServiceRequest {

    private static final long serialVersionUID = 1664543881163264014L;
    private String fodselsnummer;
    private Calendar samboerDato;

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
    }

    public Calendar getSamboerDato() {
        return samboerDato;
    }

    public void setSamboerDato(Calendar samboerDato) {
        this.samboerDato = samboerDato;
    }
}
