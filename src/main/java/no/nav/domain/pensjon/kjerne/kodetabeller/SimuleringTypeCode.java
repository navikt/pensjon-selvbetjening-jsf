package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SimuleringTypeCode implements CtiConvertable<SimuleringTypeCti, SimuleringTypeCode> {
    /**
     * AFP
     */
    AFP,
    /**
     * AFP i offentlig sektor etterfulgt av alderspensjon
     */
    AFP_ETTERF_ALDER,
    /**
     * AFP - vedtak om fremtidig pensjonspoeng
     */
    AFP_FPP,
    /**
     * Alderspensjon
     */
    ALDER,
    /**
     * Alderspensjon
     */
    ALDER_KAP_20,
    /**
     * Alderspensjon med AFP i privat sektor
     */
    ALDER_M_AFP_PRIVAT,
    /**
     * Alderspensjon med gjenlevenderettigheter
     */
    ALDER_M_GJEN,
    /**
     * Barnepensjon
     */
    BARN,
    /**
     * Endring av alderspensjon
     */
    ENDR_ALDER,
    /**
     * Endring av alderspensjon med gjenlevenderettigheter
     */
    ENDR_ALDER_M_GJEN,
    /**
     * Endring av alderspensjon med AFP i privat sektor
     */
    ENDR_AP_M_AFP_PRIVAT,
    /**
     * Gjenlevendepensjon
     */
    GJENLEVENDE;

    /**
     * Overload the default equals method as equals does not work
     * for enums after they are serialized (e.g., across EJB calls).
     * Note that the equals(Object) method is final, so we can't override it.
     *
     * @param obj enumclass to compare with
     * @return true if the objects are equal (i.e., their ordinal is equal)
     */
    public boolean equals(Enum obj) {
        return obj != null && obj instanceof SimuleringTypeCode && obj.ordinal() == ordinal();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SimuleringTypeCti> getCti() {
        return SimuleringTypeCti.class;
    }
}
