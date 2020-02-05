package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum JustertPeriodeCode implements CtiConvertable<JustertPeriodeCti, JustertPeriodeCode> {
    /**
     * &Oslash;kning
     */
    OKNING,
    /**
     * Reduksjon
     */
    REDUKSJON,
    /**
     * Friperiode
     */
    REDUKSJON_FRIPERIODE,
    /**
     * Tilstøtende er på institusjon
     */
    EPS_INST,
    /**
     * Reduksjon helseinstitusjon
     */
    REDUKSJON_HS,
    /**
     * Reduksjon fengsel
     */
    REDUKSJON_FO;

    @Override
    public Class<JustertPeriodeCti> getCti() {
        return JustertPeriodeCti.class;
    }
}
