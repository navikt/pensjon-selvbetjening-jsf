package no.nav.service.pensjon.skjema;

import no.nav.domain.pensjon.kjerne.exception.PEN150BrukerHarAktivAPSakException;
import no.nav.domain.pensjon.kjerne.exception.PEN151SkjemaSendtInnTilBehandlingException;
import no.nav.domain.pensjon.kjerne.exception.PEN152SkjemaIkkeFunnetException;
import no.nav.domain.pensjon.kjerne.exception.PEN211KravetErIkkeAFPPrivatException;

public interface SkjemaServiceBi {

    /**
     * <b>TPEN851 hentSkjemaListe</b> retrieves a list of stored skjema for the specified person.
     */
    HentSkjemaListeResponse hentSkjemaListe(HentSkjemaListeRequest request);

    /**
     * <b>TPEN852 hentSkjema</b> retrieves the stored skjema with the specified skjemaId.
     */
    HentSkjemaResponse hentSkjema(HentSkjemaRequest request);

    /**
     * <b>TPEN856 hentSkjemaAfpPrivat</b> retrieves the stored skjemaAFPPrivat for the specified kravId.
     */
    HentSkjemaAfpPrivatResponse hentSkjemaAfpPrivat(HentSkjemaAfpPrivatRequest request)
            throws PEN211KravetErIkkeAFPPrivatException;

    /**
     * <b>TPEN853 lagreSkjema</b> Updates or saves the skjema object.
     */
    LagreSkjemaResponse lagreSkjema(LagreSkjemaRequest request);

    /**
     * <b>TPEN854 slettSkjema</b> deletes the stored skjema with the specified skjemaId.
     */
    SlettSkjemaResponse slettSkjema(SlettSkjemaRequest request)
            throws PEN151SkjemaSendtInnTilBehandlingException, PEN152SkjemaIkkeFunnetException;

    /**
     * <b>TPEN855 sendSkjema</b> creates a krav and related kravlinjer based on a electronic skjema.
     */
    SendSkjemaResponse sendSkjema(SendSkjemaRequest request) throws PEN150BrukerHarAktivAPSakException;

    /**
     * <b>TPEN857 sendBrevOmsorgspoeng</b> orders a Brev with information
     * regarding Omsorgspoeng to mother or father of a child.
     */
    SendBrevOmsorgspoengResponse sendBrevOmsorgspoeng(SendBrevOmsorgspoengRequest request);

    /**
     * <b>TPEN858 opprettKravForEndretUttaksgrad</b> is responsible of bringing the data connected to a "søknadsskjema for
     * endring av uttaksgrad AP" into PEN.
     */
    OpprettKravForEndretUttaksgradResponse opprettKravForEndretUttaksgrad(OpprettKravForEndretUttaksgradRequest request);
}
