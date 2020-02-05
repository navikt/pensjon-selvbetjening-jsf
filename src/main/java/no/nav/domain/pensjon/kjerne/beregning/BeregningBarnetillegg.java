package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

//import no.nav.domain.pensjon.annotations.IgnoreOnCopy;

//@MappedSuperclass
public abstract class BeregningBarnetillegg<T extends BeregningBarnetillegg<T>> extends Barnetillegg<T> {

    private static final long serialVersionUID = 1L;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_ID", nullable = true)
//    @IgnoreOnCopy(reason = "Beregning is the owner of ytelseskomponent and should not be copied")
    private Beregning beregning;

    public BeregningBarnetillegg() {
        super();
    }

    protected Beregning getBeregning() {
        return beregning;
    }

    protected void setBeregning(Beregning beregning) {
        this.beregning = beregning;
    }

//    @Override
    @Transient
    public Beregning getRelatertBeregning() {
        return getBeregning();
    }
}
