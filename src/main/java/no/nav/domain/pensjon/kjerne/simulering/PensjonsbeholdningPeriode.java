package no.nav.domain.pensjon.kjerne.simulering;

import java.io.Serializable;
import java.util.Date;

public class PensjonsbeholdningPeriode implements Serializable {

    private static final long serialVersionUID = -1L;

    private Double pensjonsbeholdning;

    private Double garantipensjonsbeholdning;

    private Double garantitilleggsbeholdning;

    private Date datoFom;

    public Double getPensjonsbeholdning() {
        return pensjonsbeholdning;
    }

    public void setPensjonsbeholdning(Double pensjonsbeholdning) {
        this.pensjonsbeholdning = pensjonsbeholdning;
    }

    public Double getGarantipensjonsbeholdning() {
        return garantipensjonsbeholdning;
    }

    public void setGarantipensjonsbeholdning(Double garantipensjonsbeholdning) {
        this.garantipensjonsbeholdning = garantipensjonsbeholdning;
    }

    public Double getGarantitilleggsbeholdning() {
        return garantitilleggsbeholdning;
    }

    public void setGarantitilleggsbeholdning(Double garantitilleggsbeholdning) {
        this.garantitilleggsbeholdning = garantitilleggsbeholdning;
    }

    public void setDatoFom(Date datoFom) {
        if (datoFom != null) {
            this.datoFom = new Date(datoFom.getTime());
        }
    }

    public Date getDatoFom() {
        if (datoFom != null) {
            return new Date(datoFom.getTime());
        }
        return null;
    }

    @Override
    public String toString() {
        return "PensjonsbeholdningPeriode{" +
                ", datoFom=" + datoFom +
                ", pensjonsbeholdning=" + pensjonsbeholdning +
                ", garantipensjonsbeholdning=" + garantipensjonsbeholdning +
                ", garantitilleggsbeholdning=" + garantitilleggsbeholdning +
                '}';
    }
}
