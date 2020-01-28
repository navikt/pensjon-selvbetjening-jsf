package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.skjema.SkjemaAFPPrivat;

/**
 * Response for TPEN856 HentSkjemaAfpPrivat
 */
public class HentSkjemaAfpPrivatResponse extends ServiceResponse {

    private static final long serialVersionUID = -3407754630723541264L;
    SkjemaAFPPrivat skjemaAFPPrivat;

    public HentSkjemaAfpPrivatResponse() {
    }

    public HentSkjemaAfpPrivatResponse(SkjemaAFPPrivat skjemaAFPPrivat) {
        this.skjemaAFPPrivat = skjemaAFPPrivat;
    }

    public SkjemaAFPPrivat getSkjemaAFPPrivat() {
        return skjemaAFPPrivat;
    }

    public void setSkjemaAFPPrivat(SkjemaAFPPrivat skjemaAFPPrivat) {
        this.skjemaAFPPrivat = skjemaAFPPrivat;
    }
}
