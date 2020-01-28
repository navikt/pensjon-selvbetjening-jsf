package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

/**
 * Request for TPEN856 HentSkjemaAfpPrivat
 */
public class HentSkjemaAfpPrivatRequest extends ServiceRequest {

    private static final long serialVersionUID = -4633780821226411269L;
    private Long kravId;

    public HentSkjemaAfpPrivatRequest() {
    }

    public HentSkjemaAfpPrivatRequest(Long kravId) {
        this.kravId = kravId;
    }

    public Long getKravId() {
        return kravId;
    }

    public void setKravId(Long kravId) {
        this.kravId = kravId;
    }
}
