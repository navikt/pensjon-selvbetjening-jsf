package no.nav.domain.pensjon.kjerne.beregning2011;

public class BeregningsresultatUforetrygd extends AbstraktBeregningsresultat {
    public Uforetrygdberegning getUforetrygdberegning() {
        return new Uforetrygdberegning();
    }
}
