package no.nav.service.pensjon.vedtak;

import java.util.Date;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;

/**
 * Request class for TPEN486 BestemGjeldendeVedtak.
 */
public class BestemGjeldendeVedtakRequest extends ServiceRequest {

    private Pid pid;
    private Date fomDato;
    private Date tomDato;
    private Long sakId;
    private SakTypeCti sakType;

    public Date getFomDato() {
        return fomDato;
    }

    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    public Pid getPid() {
        return pid;
    }

    public void setPid(Pid pid) {
        this.pid = pid;
    }

    public Long getSakId() {
        return sakId;
    }

    public void setSakId(Long sakId) {
        this.sakId = sakId;
    }

    public Date getTomDato() {
        return tomDato;
    }

    public void setTomDato(Date tomDato) {
        this.tomDato = tomDato;
    }

    public SakTypeCti getSakType() {
        return sakType;
    }

    public void setSakType(SakTypeCti sakType) {
        this.sakType = sakType;
    }
}
