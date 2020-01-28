package no.nav.pensjon.selv.service.fake;

import no.nav.domain.pensjon.common.exception.UgyldigPeriodeException;
import no.nav.service.pensjon.grunnlag.*;

public class FakeGrunnlagService implements GrunnlagServiceBi {

    @Override
    public HentFremtidigeUtbetalingsdatoerResponse hentFremtidigeUtbetalingsdatoer(HentFremtidigeUtbetalingsdatoerRequest request) {
        return new HentFremtidigeUtbetalingsdatoerResponse();
    }

    @Override
    public HentGrunnbelopListeResponse hentGrunnbelopListe(HentGrunnbelopListeRequest request) throws UgyldigPeriodeException {
        return new HentGrunnbelopListeResponse();
    }
}
