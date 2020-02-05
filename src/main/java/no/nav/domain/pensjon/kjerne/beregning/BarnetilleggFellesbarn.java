package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

//@Entity
//@DiscriminatorValue("TFB")
public class BarnetilleggFellesbarn extends BeregningBarnetillegg<BarnetilleggFellesbarn> {

    private static final long serialVersionUID = -3799549207653510010L;

    public BarnetilleggFellesbarn() {
        super();
    }

//    @Override
    protected BarnetilleggFellesbarn innerCopy() {
        return new BarnetilleggFellesbarn();
    }
}
