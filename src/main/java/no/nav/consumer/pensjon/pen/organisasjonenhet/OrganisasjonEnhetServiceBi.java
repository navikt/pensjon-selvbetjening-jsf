package no.nav.consumer.pensjon.pen.organisasjonenhet;

import no.nav.consumer.pensjon.pen.organisasjonenhet.exception.v2.FinnNAVKontorUgyldigInputException;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorRequest;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorResponse;

/**
 * Spring interface for CPEN_OrganisasjonEnhet
 */
public interface OrganisasjonEnhetServiceBi {

    FinnNAVKontorResponse finnNAVKontor(FinnNAVKontorRequest finnNAVKontorRequest)
            throws FinnNAVKontorUgyldigInputException;
}
