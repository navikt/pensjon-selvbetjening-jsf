package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pen.organisasjonenhet.OrganisasjonEnhetServiceBi;
import no.nav.consumer.pensjon.pen.organisasjonenhet.exception.v2.FinnNAVKontorUgyldigInputException;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorRequest;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorResponse;
import no.nav.domain.pensjon.common.organisasjonsenhet.Organisasjonsenhet;

public class FakeOrganisasjonEnhetService implements OrganisasjonEnhetServiceBi {

    @Override
    public FinnNAVKontorResponse finnNAVKontor(FinnNAVKontorRequest finnNAVKontorRequest) throws FinnNAVKontorUgyldigInputException {
        return new FinnNAVKontorResponse(new Organisasjonsenhet());
    }
}
