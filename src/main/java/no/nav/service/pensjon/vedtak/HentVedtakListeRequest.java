package no.nav.service.pensjon.vedtak;

import java.util.Date;
import java.util.List;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.VedtakstatusTypeCti;

/**
 * Request object for TPEN410 hentVedtakListe
 * Pid and alleVedtak are required fields
 */
public class HentVedtakListeRequest extends ServiceRequest {

    private static final long serialVersionUID = 1105166097125496414L;
    private Pid pid;
    private Boolean alleVedtak;
    private Long sakId;
    private SakTypeCti saksType;
    private Date fomDato;
    private Date tomDate;
    private Long kravId;
    private Boolean inklBeregningInfo;
    private List<VedtakstatusTypeCti> vedtakStatusList;

    public Boolean getAlleVedtak() {
        return alleVedtak;
    }

    public void setAlleVedtak(Boolean alleVedtak) {
        this.alleVedtak = alleVedtak;
    }

    public Date getFomDato() {
        return fomDato;
    }

    public void setFomDato(Date fomDato) {
        this.fomDato = fomDato;
    }

    public Boolean getInklBeregningInfo() {
        return inklBeregningInfo;
    }

    public void setInklBeregningInfo(Boolean inklBeregningInfo) {
        this.inklBeregningInfo = inklBeregningInfo;
    }

    public Long getKravId() {
        return kravId;
    }

    public void setKravId(Long kravId) {
        this.kravId = kravId;
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

    public SakTypeCti getSaksType() {
        return saksType;
    }

    public void setSaksType(SakTypeCti saksType) {
        this.saksType = saksType;
    }

    public Date getTomDate() {
        return tomDate;
    }

    public void setTomDate(Date tomDate) {
        this.tomDate = tomDate;
    }

    public List<VedtakstatusTypeCti> getVedtakStatusList() {
        return vedtakStatusList;
    }

    public void setVedtakStatusList(List<VedtakstatusTypeCti> vedtakStatusList) {
        this.vedtakStatusList = vedtakStatusList;
    }
}
