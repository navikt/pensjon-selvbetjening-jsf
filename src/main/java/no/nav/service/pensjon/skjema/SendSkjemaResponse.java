package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceResponse;

public class SendSkjemaResponse extends ServiceResponse {

    private static final long serialVersionUID = -292364082518315054L;
    private Boolean maskinellBehandling;
    private Long kravId;

    public SendSkjemaResponse() {
    }

    public SendSkjemaResponse(Long kravId, Boolean maskinellBehandling) {
        this.kravId = kravId;
        this.maskinellBehandling = maskinellBehandling;
    }

    public Long getKravId() {
        return kravId;
    }

    public void setKravId(Long kravId) {
        this.kravId = kravId;
    }

    public Boolean getMaskinellBehandling() {
        return maskinellBehandling;
    }

    public void setMaskinellBehandling(Boolean maskinellBehandling) {
        this.maskinellBehandling = maskinellBehandling;
    }
}
