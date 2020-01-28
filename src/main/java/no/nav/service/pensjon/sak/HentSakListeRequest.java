package no.nav.service.pensjon.sak;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;

/**
 * Request object for <b>TPEN204 - hentSakListe</b>.
 * <p>
 * <i>HentSakListe</i> consists of the following properties:
 * <ul>
 * <li>pid (<code>Pid</code>) Object identifying of a person assoicated with the Sak objects that should be returned.
 * <li>enhetId (<code>String</code>) Unique identification of the PenOrgEnhet associated with the Sak objects that should be returned.
 * <li>sakType (<code>SakTypeCti</code>) An object representing the type of the Sak objects that should be returned.
 * <li>sakStatus (<code>SakStatusCti</code>) An object representing the status of the Sak objects that should be returned.
 * </ul>
 * </p>
 */
public class HentSakListeRequest extends ServiceRequest {

    private static final long serialVersionUID = -593997705444316600L;
    private Pid pid;
    private String enhetId;
    private SakTypeCti sakType;
    private SakStatusCti sakStatus;

    public HentSakListeRequest() {
    }

    public HentSakListeRequest(Pid pid, String enhetId, SakTypeCti sakType, SakStatusCti sakStatus) {
        this.pid = pid;
        this.enhetId = enhetId;
        this.sakType = sakType;
        this.sakStatus = sakStatus;
    }

    /**
     * EnhetId is the unique identifier of the PenOrgEnhet
     * associated with the Sak objects that should be returned.
     */
    public String getEnhetId() {
        return enhetId;
    }

    public void setEnhetId(String enhetId) {
        this.enhetId = enhetId;
    }

    public Pid getPid() {
        return pid;
    }

    public void setPid(Pid pid) {
        this.pid = pid;
    }

    public SakStatusCti getSakStatus() {
        return sakStatus;
    }

    public void setSakStatus(SakStatusCti sakStatus) {
        this.sakStatus = sakStatus;
    }

    public SakTypeCti getSakType() {
        return sakType;
    }

    public void setSakType(SakTypeCti sakType) {
        this.sakType = sakType;
    }
}
