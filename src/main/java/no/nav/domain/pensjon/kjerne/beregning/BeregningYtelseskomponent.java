package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import no.nav.domain.pensjon.annotations.IgnoreOnCopy;

@SuppressWarnings("serial")
//@Entity
public abstract class BeregningYtelseskomponent<T extends BeregningYtelseskomponent<T>> extends Ytelseskomponent<T> {
    //Make a read-only copy of discriminator value available for use as MapKey in Beregning:
    @Column(name = "K_YTELSE_KOMP_T", nullable = true, insertable = false, updatable = false)
    private String ytelseKomponentTypeName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BEREGNING_ID", nullable = true)
    @IgnoreOnCopy(reason = "Beregning is the owner of ytelseskomponent and should not be copied")
    private Beregning beregning;

    public BeregningYtelseskomponent() {
        super();
    }

    public BeregningYtelseskomponent(T komponent) {
        super(komponent);
        ytelseKomponentTypeName = komponent.getYtelseKomponentTypeName();
    }

    protected Beregning getBeregning() {
        return beregning;
    }

    protected void setBeregning(Beregning beregning) {
        this.beregning = beregning;
    }

    String getYtelseKomponentTypeName() {
        return ytelseKomponentTypeName;
    }

    void setYtelseKomponentTypeName(String ytelseKomponentTypeName) {
        this.ytelseKomponentTypeName = ytelseKomponentTypeName;
    }

    @Override
    public T copy() {
        T copy = super.copy();
        copy.setYtelseKomponentTypeName(this.ytelseKomponentTypeName);
        return copy;
    }
}
