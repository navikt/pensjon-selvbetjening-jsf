package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

public class SlettSkjemaRequest extends ServiceRequest {

    private static final long serialVersionUID = 6797297238199428351L;
    private Long skjemaId;

    public SlettSkjemaRequest() {
    }

    public SlettSkjemaRequest(Long skjemaId) {
        this.skjemaId = skjemaId;
    }

    public Long getSkjemaId() {
        return skjemaId;
    }

    public void setSkjemaId(Long skjemaId) {
        this.skjemaId = skjemaId;
    }
}
