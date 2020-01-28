package no.nav.domain.pensjon.kjerne.simulering;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Objekt som inneholder samlet pensjon for en alder.
 * Med en alder menes perioden fra og med måneden etter bruker blir "alder" gammel til og med måneden bruker fyller neste "alder".
 * Samlet pensjon kan da være sammensatt av flere beregninger, dersom det har skjedd grunnlagsendringer som krever ny beregning innad i "alderen".
 * Objektet skal ikke persisteres.
 *
 */
public class Pensjonsperiode implements Serializable {
    private static final long serialVersionUID = -5121592910338912424L;

    /**
     * Totalt utbetalt beløp i perioden.
     */
    private Integer belop;

    /**
     * Brukers alder i perioden
     */
    private Integer alder;

    /**
     * Beregningsinformasjon for en beregning gjort i pensjonsperioden.
     * Vil finnes dersom der har skjedd en uttaksgradsendring og/eller bruker blir 67 år i løpet av perioden.
     */
    private List<SimulertBeregningsinformasjon> simulertBeregningsinformasjonListe = new ArrayList<SimulertBeregningsinformasjon>();

    /**
     * @return alder i perioden
     */
    public Integer getAlder() {
        return alder;
    }

    /**
     * @param alder i perioden
     */
    public void setAlder(Integer alder) {
        this.alder = alder;
    }

    /**
     * @return totalt beløp utbetalt i perioden
     */
    public Integer getBelop() {
        return belop;
    }

    /**
     * @param belop totalt beløp utbetalt i perioden
     */
    public void setBelop(Integer belop) {
        this.belop = belop;
    }

    /**
     * @return Beregningsinformasjon for en beregning gjort i pensjonsperioden
     */
    public List<SimulertBeregningsinformasjon> getSimulertBeregningsinformasjonListe() {
        return simulertBeregningsinformasjonListe;
    }

    /**
     * @param simulertBeregningsinformasjonListe Beregningsinformasjon for en beregning gjort i pensjonsperioden
     */
    public void setSimulertBeregningsinformasjonListe(List<SimulertBeregningsinformasjon> simulertBeregningsinformasjonListe) {
        this.simulertBeregningsinformasjonListe = simulertBeregningsinformasjonListe;
    }

    @Override
    public String toString() {
        StringBuffer simulertBeregningsinformasjonListeUtskrift = new StringBuffer();

        for (SimulertBeregningsinformasjon simulertBeregningsinformasjon : simulertBeregningsinformasjonListe) {
            simulertBeregningsinformasjonListeUtskrift.append(simulertBeregningsinformasjon.toString());
        }

        return "belop: " + belop + "\n" + "alder: " + alder + "\n" + "simulertBeregningsinformasjonListe: \n" + simulertBeregningsinformasjonListeUtskrift.toString();
    }
}
