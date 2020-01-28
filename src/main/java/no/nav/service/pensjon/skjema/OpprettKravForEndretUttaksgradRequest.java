package no.nav.service.pensjon.skjema;

import java.util.Date;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

/**
 * Request object for TPEN858.
 */
public class OpprettKravForEndretUttaksgradRequest extends ServiceRequest {

    private static final long serialVersionUID = 2631642625034786502L;
    private Pid fnr;
    private Integer uttaksgrad;
    private Date onsketVirkningsDato;

    public OpprettKravForEndretUttaksgradRequest(Pid fnr, Integer uttaksgrad, Date onsketVirkningsDato) {
        super();
        this.fnr = fnr;
        this.uttaksgrad = uttaksgrad;
        this.onsketVirkningsDato = onsketVirkningsDato;
    }

    public Pid getPid() {
        return fnr;
    }

    public void setFnr(Pid fnr) {
        this.fnr = fnr;
    }

    public Date getOnsketVirkningsDato() {
        return onsketVirkningsDato;
    }

    public void setOnsketVirkningsDato(Date onsketVirkningsDato) {
        this.onsketVirkningsDato = onsketVirkningsDato;
    }

    public Integer getUttaksgrad() {
        return uttaksgrad;
    }

    public void setUttaksgrad(Integer uttaksgrad) {
        this.uttaksgrad = uttaksgrad;
    }
}
