package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum BeregningMetodeCode implements CtiConvertable<BeregningMetodeCti, BeregningMetodeCode> {
    /**
     * Australia pro rata
     */
    AUSTRALIA,
    /**
     * Canada pro rata
     */
    CANADA,
    /**
     * Chile pro rata
     */
    CHILE,
    /**
     * E&Oslash;S pro rata
     */
    EOS,
    /**
     * Folketrygd
     */
    FOLKETRYGD,
    /**
     * India pro rata
     */
    INDIA,
    /**
     * Israel pro rata
     */
    ISRAEL,
    /**
     * Nordisk konvensjon
     */
    NORDISK,
    /**
     * Bilateral avtale
     */
    PRORATA,
    /**
     * S&oslash;r-Korea pro rata
     */
    SOR_KOREA,
    /**
     * Sveits pro rata
     */
    SVEITS,
    /**
     * USA pro rata
     */
    USA;

    @Override
    public Class<BeregningMetodeCti> getCti() {
        return BeregningMetodeCti.class;
    }
}
