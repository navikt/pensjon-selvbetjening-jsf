package no.nav.service.pensjon.sak;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

public class HentSakSammendragListeRequest extends ServiceRequest {

    private static final long serialVersionUID = -7919885228412991997L;
    private Pid pid;

    public HentSakSammendragListeRequest(Pid pid) {
        this.pid = pid;
    }

    public Pid getPid() {
        return pid;
    }
}
