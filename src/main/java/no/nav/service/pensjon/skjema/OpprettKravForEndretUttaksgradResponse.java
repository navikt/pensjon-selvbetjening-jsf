package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceResponse;

/**
 * Response object for TPEN858.
 */
public class OpprettKravForEndretUttaksgradResponse extends ServiceResponse {

    private static final long serialVersionUID = 2733563371760162212L;
    private Long kravId;

    public OpprettKravForEndretUttaksgradResponse() {
        super();
    }

    public OpprettKravForEndretUttaksgradResponse(Long kravId) {
        super();
        this.kravId = kravId;
    }

    public Long getKravId() {
        return kravId;
    }

    public void setKravId(Long kravId) {
        this.kravId = kravId;
    }
}
