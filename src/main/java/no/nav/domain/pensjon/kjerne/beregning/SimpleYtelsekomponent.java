package no.nav.domain.pensjon.kjerne.beregning;

public abstract class SimpleYtelsekomponent<T extends Ytelseskomponent<T>> extends Ytelseskomponent<T> {

    private static final long serialVersionUID = 5662278211113005449L;

    public SimpleYtelsekomponent(T ytelseskomponent) {
        super(ytelseskomponent);
    }

    public SimpleYtelsekomponent() {
        super();
    }

    protected void setBeregning(Beregning beregning) {
    }
}
