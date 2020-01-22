package no.nav.consumer.pensjon.pselv.person.to;

import java.util.List;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

public class HentPersonListeRequest extends ServiceRequest {

    private static final long serialVersionUID = 3705027319838947029L;
    private List<Pid> pidListe;

    public HentPersonListeRequest() {
    }

    public HentPersonListeRequest(List<Pid> pidListe) {
        this.pidListe = pidListe;
    }

    public List<Pid> getPidListe() {
        return pidListe;
    }

    public void setPidListe(List<Pid> pidListe) {
        this.pidListe = pidListe;
    }
}
