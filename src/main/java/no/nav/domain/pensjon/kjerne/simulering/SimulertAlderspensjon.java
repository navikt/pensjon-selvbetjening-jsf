package no.nav.domain.pensjon.kjerne.simulering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Objekt som inneholder en simulering av alderspensjon.
 * Dette objektet består av en liste som innholder pensjonsperioder med simulert utbetaling for hvert år,
 * og hvor stor del av utbetalingen som er basert på henholdsvis kapittel 19 og kapittel 20 regelverk.
 * Objektet skal ikke persisteres.
 */
public class SimulertAlderspensjon implements Serializable {
    private static final long serialVersionUID = -8003893213424810558L;

    /**
     * Liste med pensjonsperioder som beskriver brukers simulerte pensjonsutbetaling for hver alder.
     */
    private List<Pensjonsperiode> pensjonsperiodeListe = new ArrayList<>();

    /**
     * Andel av pensjonen som er beregnet etter kap20
     */
    private Double andelNytt;

    /**
     * Andel av pensjonen som er beregnet etter kap19
     */
    private Double andelDagens;

    private List<no.nav.domain.pensjon.kjerne.sak.Uttaksgrad> uttaksgradListe;

    private List<PensjonsbeholdningPeriode> pensjonsbeholdningListe = new ArrayList<>();

    private List<SimulertBeregningsinformasjon> simulertBeregningsinformasjonListe = new ArrayList<>();

    /**
     * @return the uttaksgradListe
     */
    public List<no.nav.domain.pensjon.kjerne.sak.Uttaksgrad> getUttaksgradListe() {
        return uttaksgradListe;
    }

    /**
     * @param uttaksgradListe the uttaksgradListe to set
     */
    public void setUttaksgradListe(List<no.nav.domain.pensjon.kjerne.sak.Uttaksgrad> uttaksgradListe) {
        this.uttaksgradListe = uttaksgradListe;
    }

    /**
     * @return the andelDagens
     */
    public Double getAndelDagens() {
        return andelDagens;
    }

    /**
     * @param andelDagens the andelDagens to set
     */
    public void setAndelDagens(Double andelDagens) {
        this.andelDagens = andelDagens;
    }

    /**
     * @return the andelNytt
     */
    public Double getAndelNytt() {
        return andelNytt;
    }

    /**
     * @param andelNytt the andelNytt to set
     */
    public void setAndelNytt(Double andelNytt) {
        this.andelNytt = andelNytt;
    }

    /**
     * @return the pensjonsperiodeListe
     */
    public List<Pensjonsperiode> getPensjonsperiodeListe() {
        return pensjonsperiodeListe;
    }

    /**
     * @param pensjonsperiodeListe the pensjonsperiodeListe to set
     */
    public void setPensjonsperiodeListe(List<Pensjonsperiode> pensjonsperiodeListe) {
        this.pensjonsperiodeListe = pensjonsperiodeListe;
    }

    /**
     * @param p
     */
    public void addPensjonsperiode(Pensjonsperiode p) {
        if (p != null) {
            pensjonsperiodeListe.add(p);
        }
    }

    /**
     * @return the pensjonsbeholdningListe
     */

    public List<PensjonsbeholdningPeriode> getPensjonsbeholdningListe() {
        return pensjonsbeholdningListe;
    }

    /**
     * @param pensjonsbeholdningListe the pensjonsbeholdningListe to set
     */

    public void setPensjonsbeholdningListe(List<PensjonsbeholdningPeriode> pensjonsbeholdningListe) {
        this.pensjonsbeholdningListe = pensjonsbeholdningListe;
    }

    public void addToPensjonsbeholdningListe(PensjonsbeholdningPeriode pb) {
        if (pb != null) {
            pensjonsbeholdningListe.add(pb);
        }
    }

    public List<SimulertBeregningsinformasjon> getSimulertBeregningsinformasjonListe() {
        return new ArrayList<>(simulertBeregningsinformasjonListe);
    }

    public void setSimulertBeregningsinformasjonListe(List<SimulertBeregningsinformasjon> simulertBeregningsinformasjonListe) {
        this.simulertBeregningsinformasjonListe = simulertBeregningsinformasjonListe != null ? simulertBeregningsinformasjonListe : new ArrayList<>();
    }
}
