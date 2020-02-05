package no.nav.domain.pensjon.kjerne.beregning2011;

import no.nav.domain.pensjon.common.PeriodisertInformasjon;
import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;

import java.util.Date;
import java.util.List;

public interface BeregningHoveddel extends PeriodisertInformasjon, KanOverstyreResultatKilde {
//TODO complete
    Integer getUttaksgrad();

    PensjonUnderUtbetaling getPensjonUnderUtbetaling();

    List<Ytelseskomponent> hentYtelseskomponentListe(boolean hentKunYtelseskomponenterIBruk);
}
