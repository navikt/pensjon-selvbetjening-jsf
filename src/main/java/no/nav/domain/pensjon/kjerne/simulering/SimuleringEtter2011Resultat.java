package no.nav.domain.pensjon.kjerne.simulering;

import no.nav.domain.pensjon.kjerne.exception.*;

public class SimuleringEtter2011Resultat {
    public SimulertAlderspensjon getAp() throws PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering, PEN222BeregningstjenesteFeiletException,
            PEN223BrukerHarIkkeLopendeAlderspensjonException, PEN224AvslagVilkarsprovingForKortTrygdetidException,
            PEN225AvslagVilkarsprovingForLavtTidligUttakException, PEN226BrukerHarLopendeAPPaGammeltRegelverkException,
            PEN227AvslagVilkarsprovingForKortBotidException, PEN228BrukerErFodtFor1943Exception {
        return new SimulertAlderspensjon();
    }
}
