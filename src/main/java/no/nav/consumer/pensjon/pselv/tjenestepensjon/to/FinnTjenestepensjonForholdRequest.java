package no.nav.consumer.pensjon.pselv.tjenestepensjon.to;

import no.stelvio.common.transferobject.ServiceRequest;

public class FinnTjenestepensjonForholdRequest extends ServiceRequest {

    private static final long serialVersionUID = 8286105030900260602L;
    private Boolean hentSamhandlerInfo;
    private String forholdId;
    private String ytelseId;
    private String fnr;
    private java.util.Calendar fom;
    private java.util.Calendar tom;
    private String tssEksternId;

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public java.util.Calendar getFom() {
        return fom;
    }

    public void setFom(java.util.Calendar fom) {
        this.fom = fom;
    }

    public String getForholdId() {
        return forholdId;
    }

    public void setForholdId(String forholdId) {
        this.forholdId = forholdId;
    }

    public Boolean getHentSamhandlerInfo() {
        return hentSamhandlerInfo;
    }

    public void setHentSamhandlerInfo(Boolean hentSamhandlerInfo) {
        this.hentSamhandlerInfo = hentSamhandlerInfo;
    }

    public java.util.Calendar getTom() {
        return tom;
    }

    public void setTom(java.util.Calendar tom) {
        this.tom = tom;
    }

    public String getTssEksternId() {
        return tssEksternId;
    }

    public void setTssEksternId(String tssEksternId) {
        this.tssEksternId = tssEksternId;
    }

    public String getYtelseId() {
        return ytelseId;
    }

    public void setYtelseId(String ytelseId) {
        this.ytelseId = ytelseId;
    }
}
