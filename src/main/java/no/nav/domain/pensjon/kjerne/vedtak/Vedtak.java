package no.nav.domain.pensjon.kjerne.vedtak;

import no.nav.domain.pensjon.kjerne.beregning.Beregning;
import no.nav.domain.pensjon.kjerne.beregning2011.BeregningHoveddel;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.krav.KravHode;

import java.util.Date;

public class Vedtak {

    private SakTypeCti saksType;

    public SakTypeCti getSakstype() {
        return saksType;
    }

    public Date getVirkFom() {
        return new Date();
    }

    public KravHode getKravhode() {
        return new KravHode();
    }

    public Beregningsperiode[] getBeregningsperiodeListe() {
        return new Beregningsperiode[0];
    }

    public Beregning[] getBeregningListe() {
        return new Beregning[0];
    }

    public boolean erAvTypenUforetrygd() {
        return false;
    }

    public BeregningHoveddel[] getBeregningsresultatListe() {
        return new BeregningHoveddel[0];
    }
}
