package no.nav.presentation.pensjon.pselv.common.comparators;

import java.io.Serializable;
import java.util.Comparator;

import no.nav.domain.pensjon.kjerne.grunnlag.Utbetalingsdato;

/**
 * Local Comparator class for Utbetalingsdato.
 *
 */
public class UtbDatoComparator implements Comparator<Utbetalingsdato>, Serializable {
    private static final long serialVersionUID = -4791888165333168501L;

    private boolean ascending;

    /**
     * Constructor enabling to specify sort order.
     *
     * @param ascending true sorts in ascending order
     */
    public UtbDatoComparator(boolean ascending) {
        this.ascending = ascending;
    }

    /**
     * (non-JavaDoc)
     *
     * @param o1 lhs object to sort
     * @param o2 rhs object to sort
     * @return int see reference to get exact info
     * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
     */
    @Override
    public int compare(Utbetalingsdato o1, Utbetalingsdato o2) {
        int result = 0;
        if (o1 == null && o2 != null) {
            result = -1;
        } else if (o1 != null && o2 == null) {
            result = 1;
        } else if (o1 != null && o2 != null) {
            result = o2.getUtbetDato().compareTo(o1.getUtbetDato());
        }
        return ascending ? -result : result;
    }
}
