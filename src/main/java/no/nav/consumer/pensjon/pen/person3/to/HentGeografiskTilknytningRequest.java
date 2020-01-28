package no.nav.consumer.pensjon.pen.person3.to;

import no.stelvio.domain.person.Pid;

public class HentGeografiskTilknytningRequest {
    private Pid fnr;

    public HentGeografiskTilknytningRequest(Pid fnr) {
        this.fnr = fnr;
    }

    public Pid getFnr() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }
}
