package no.nav.domain.pensjon.kjerne.beregning;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * Base class for motregning hierarchy
 */
@MappedSuperclass
public abstract class MotregningYtelseskomponent<T extends MotregningYtelseskomponent<T>> extends BeregningYtelseskomponent<T> {

    private static final long serialVersionUID = -5304281561367739726L;

    /**
     * Dagsats annen ytelse
     */
    @Column(name = "DAGSATS", nullable = true)
    private Integer dagsats = 0;

    /**
     * Antall dager annen ytelse
     */
    @Column(name = "ANTALL_DAGER", nullable = true)
    private Integer antallDager = 0;

    public MotregningYtelseskomponent() {
        super();
    }

    /**
     * @deprecated Use {@link #copy}-method in stead.
     */
    @Deprecated
    public MotregningYtelseskomponent(MotregningYtelseskomponent motregningYtelseskomponent) {
        dagsats = motregningYtelseskomponent.dagsats;
        antallDager = motregningYtelseskomponent.antallDager;
    }

    /**
     * PK-15485 GetRelatertBeregning for motregninger on gammel beregningstruktur.
     * If motregning is on ny beregningstruktur, the method will return null as expected
     */
    @Override
    public Beregning getRelatertBeregning() {
        return getBeregning();
    }

    @Override
    public T copy() {
        T copy = super.copy();
        copy.setDagsats(dagsats);
        copy.setAntallDager(antallDager);
        return copy;
    }

    public Integer getDagsats() {
        return dagsats;
    }

    public void setDagsats(Integer dagsats) {
        this.dagsats = dagsats;
    }

    public Integer getAntallDager() {
        return antallDager;
    }

    public void setAntallDager(Integer antallDager) {
        this.antallDager = antallDager;
    }
}
