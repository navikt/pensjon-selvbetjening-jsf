package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
//@DiscriminatorValue("TSB")
public class BarnetilleggSerkullsbarn extends BeregningBarnetillegg<BarnetilleggSerkullsbarn> {

    private static final long serialVersionUID = 5144790042903518349L;

    public BarnetilleggSerkullsbarn() {
        super();
    }

    @Override
    protected BarnetilleggSerkullsbarn innerCopy() {
        return new BarnetilleggSerkullsbarn();
    }
}
