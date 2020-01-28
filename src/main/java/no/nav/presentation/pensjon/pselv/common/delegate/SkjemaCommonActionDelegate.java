package no.nav.presentation.pensjon.pselv.common.delegate;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.service.pensjon.skjema.SkjemaServiceBi;

public class SkjemaCommonActionDelegate {
    //TODO complete
    private SkjemaServiceBi skjemaService;

    public Skjema hentSkjema(Long skjemaId) {
        return new Skjema();
    }

    public Skjema lagreSkjema(Skjema skjema) {
        return new Skjema();
    }

    public void slettSkjema(Long skjemaId) {
    }

    public void setSkjemaService(SkjemaServiceBi skjemaService) {
        this.skjemaService = skjemaService;
    }
}
