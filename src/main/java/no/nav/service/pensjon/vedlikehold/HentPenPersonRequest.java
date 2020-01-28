package no.nav.service.pensjon.vedlikehold;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

/**
 * Request object for TPEN825 VedlikeholdServiceBI.hentPenPerson
 *
 */
public class HentPenPersonRequest extends ServiceRequest {
    private static final long serialVersionUID = 6547684447766571111L;

    private Pid fnr;

    /**
     * Default constructor
     */
    public HentPenPersonRequest() {
    }

    /**
     * @param fnr of the person to get
     */
    public HentPenPersonRequest(Pid fnr) {
        this.fnr = fnr;
    }

    /**
     * @return Pid
     */
    public Pid getFnr() {
        return fnr;
    }

    /**
     * @param fnr of the person to get
     */
    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }
}
