package no.nav.service.pensjon.avvik.support;

import no.nav.repository.pensjon.avvik.AvvikRepository;
import no.nav.service.pensjon.avvik.AvvikServiceBi;
import no.nav.service.pensjon.avvik.KategoriserAvvikRequest;
import no.nav.service.pensjon.avvik.KategoriserAvvikResponse;

public class SimpleAvvikService implements AvvikServiceBi {

    private AvvikRepository avvikRepository;

    @Override
    public KategoriserAvvikResponse kategoriserAvvik(KategoriserAvvikRequest kategoriserAvvikRequest) {
        return new KategoriserAvvikResponse(2L, "melding", "status");
    }

    public void setAvvikRepository(AvvikRepository avvikRepository) {
        this.avvikRepository = avvikRepository;
    }

}
