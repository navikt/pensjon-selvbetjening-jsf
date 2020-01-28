package no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2;

import no.nav.domain.pensjon.common.organisasjonsenhet.Organisasjonsenhet;

public class FinnNAVKontorResponse {

    private Organisasjonsenhet organisasjonsenhet;

    public FinnNAVKontorResponse(Organisasjonsenhet organisasjonsenhet) {
        this.organisasjonsenhet = organisasjonsenhet;
    }

    public Organisasjonsenhet getOrganisasjonsenhet() {
        return organisasjonsenhet;
    }
}
