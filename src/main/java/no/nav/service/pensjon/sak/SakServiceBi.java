package no.nav.service.pensjon.sak;

import no.nav.domain.pensjon.kjerne.exception.PEN029PersonIkkeFunnetLokaltException;
import no.nav.domain.pensjon.kjerne.exception.PEN031SakManglerEierenhetException;
import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.SkjemaEndringAlderspensjonActionDelegate;

public interface SakServiceBi {

    HentSakListeResponse hentSakListe(HentSakListeRequest request);

    HentApplikasjonsparameterResponse hentApplikasjonsparameter(HentApplikasjonsparameterRequest hentApplikasjonsparameterRequest);

    HentSakSammendragListeResponse hentSakSammendragListe(HentSakSammendragListeRequest request)
            throws PEN029PersonIkkeFunnetLokaltException, PEN031SakManglerEierenhetException;
}
