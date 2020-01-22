package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import java.util.Calendar;

public class HentFamilierelasjonerRequest extends ServiceRequest {

    private static final long serialVersionUID = 3436566337355639026L;
    private String fodselsnummer;
    private Boolean hentSoskenforhold;
    private Boolean hentSamboerforhold;
    private Boolean hentDetaljer;
    private Boolean hentEgenAnsatt;
    private Calendar henteDato;

    public String getFodselsnummer() {
        return fodselsnummer;
    }

    public void setFodselsnummer(String fodselsnummer) {
        this.fodselsnummer = fodselsnummer;
    }

    public Boolean getHentDetaljer() {
        return hentDetaljer;
    }

    public void setHentDetaljer(Boolean hentDetaljer) {
        this.hentDetaljer = hentDetaljer;
    }

    public Calendar getHenteDato() {
        return henteDato;
    }

    public void setHenteDato(Calendar henteDato) {
        this.henteDato = henteDato;
    }

    public Boolean getHentEgenAnsatt() {
        return hentEgenAnsatt;
    }

    public void setHentEgenAnsatt(Boolean hentEgenAnsatt) {
        this.hentEgenAnsatt = hentEgenAnsatt;
    }

    public Boolean getHentSamboerforhold() {
        return hentSamboerforhold;
    }

    public void setHentSamboerforhold(Boolean hentSamboerforhold) {
        this.hentSamboerforhold = hentSamboerforhold;
    }

    public Boolean getHentSoskenforhold() {
        return hentSoskenforhold;
    }

    public void setHentSoskenforhold(Boolean hentSoskenforhold) {
        this.hentSoskenforhold = hentSoskenforhold;
    }

    public String generateCachingKey() {
        return fodselsnummer + ":" + hentDetaljer + ":" + henteDato + ":" + hentEgenAnsatt + ":" + hentSamboerforhold + ":" + hentSoskenforhold;
    }
}
