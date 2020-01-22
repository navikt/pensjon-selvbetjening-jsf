package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import java.util.Calendar;

public class HentFamilierelasjonsHistorikkRequest extends ServiceRequest {

    private static final long serialVersionUID = 6916836887017398972L;
    private String fnr;
    private Boolean hentSamboerforhold;
    private Calendar fom;

    public HentFamilierelasjonsHistorikkRequest() {
    }

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public Boolean getHentSamboerforhold() {
        return hentSamboerforhold;
    }

    public void setHentSamboerforhold(Boolean hentSamboerforhold) {
        this.hentSamboerforhold = hentSamboerforhold;
    }

    public Calendar getFom() {
        return fom;
    }

    public void setFom(Calendar fom) {
        this.fom = fom;
    }
}
