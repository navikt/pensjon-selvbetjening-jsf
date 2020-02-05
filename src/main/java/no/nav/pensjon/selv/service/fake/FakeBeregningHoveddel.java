package no.nav.pensjon.selv.service.fake;

import no.nav.domain.pensjon.kjerne.beregning.Ytelseskomponent;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningHoveddel;
import no.nav.domain.pensjon.kjerne.beregning2011.PensjonUnderUtbetaling;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FakeBeregningHoveddel implements BeregningHoveddel {

    @Override
    public Date getFomDato() {
        return new Date();
    }

    @Override
    public Date getTomDato() {
        return new Date();
    }

    @Override
    public void setFomDato(Date fom) {
    }

    @Override
    public void setTomDato(Date tom) {
    }

    @Override
    public Integer getUttaksgrad() {
        return 31;
    }

    @Override
    public PensjonUnderUtbetaling getPensjonUnderUtbetaling() {
        return new PensjonUnderUtbetaling();
    }

    @Override
    public List<Ytelseskomponent> hentYtelseskomponentListe(boolean hentKunYtelseskomponenterIBruk) {
        return new ArrayList<>();
    }
}
