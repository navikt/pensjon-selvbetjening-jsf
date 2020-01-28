package no.nav.domain.pensjon.kjerne.beregning2011;

import no.nav.domain.pensjon.common.PeriodisertInformasjon;

import java.util.Date;

public interface BeregningHoveddel extends PeriodisertInformasjon, KanOverstyreResultatKilde {

    Integer getUttaksgrad();

    PensjonUnderUtbetaling getPensjonUnderUtbetaling();
}
