package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCti;

public class HentSkjemaListeRequest extends ServiceRequest {

    private static final long serialVersionUID = -8781179629418679456L;
    private Pid fnr;
    private ElektroniskSkjemaTypeCti skjemaPselvType;

    public HentSkjemaListeRequest(Pid fnr) {
        this.fnr = fnr;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public ElektroniskSkjemaTypeCti getSkjemaPselvType() {
        return skjemaPselvType;
    }

    public void setSkjemaPselvType(ElektroniskSkjemaTypeCti skjemaPselvType) {
        this.skjemaPselvType = skjemaPselvType;
    }
}
