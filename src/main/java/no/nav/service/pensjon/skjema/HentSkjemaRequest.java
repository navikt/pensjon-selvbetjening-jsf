package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

public class HentSkjemaRequest extends ServiceRequest {

    private static final long serialVersionUID = -5402244309944001378L;
    private Long skjemaId;

    public HentSkjemaRequest(Long skjemaId) {
        this.skjemaId = skjemaId;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }
}
