package no.nav.domain.pensjon.kjerne.vedtak;

import no.nav.domain.pensjon.kjerne.beregning2011.BeregningHoveddel;
import no.nav.pensjon.selv.service.fake.FakeBeregningHoveddel;

import java.util.Date;

public class Beregningsperiode {

    public Date getFomDato() {
        return new Date();
    }

    public Date getTomDato() {
        return new Date();
    }

    public BeregningHoveddel getBeregningsresultat() {
        return new FakeBeregningHoveddel();
    }
}
