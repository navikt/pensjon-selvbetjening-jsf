package no.nav.service.pensjon.grunnlag;

import no.nav.domain.pensjon.common.exception.UgyldigPeriodeException;

public interface GrunnlagServiceBi {
//TODO complete
    HentFremtidigeUtbetalingsdatoerResponse hentFremtidigeUtbetalingsdatoer(HentFremtidigeUtbetalingsdatoerRequest request);

    HentGrunnbelopListeResponse hentGrunnbelopListe(HentGrunnbelopListeRequest request) throws UgyldigPeriodeException;
}
