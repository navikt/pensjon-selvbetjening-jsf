package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SivilstatusTypeCode implements CtiConvertable<SivilstatusTypeCti, SivilstatusTypeCode> {
    /**
     * Enke/-mann
     */
    ENKE,
    /**
     * Gift
     */
    GIFT,
    /**
     * Gjenlevende etter samlivsbrudd
     */
    GJES,
    /**
     * Gjenlevende partner
     */
    GJPA,
    /**
     * Gjenlevende samboer
     */
    GJSA,
    /**
     * Gift, lever adskilt
     */
    GLAD,
    /**
     * -
     */
    NULL,
    /**
     * Registrert partner, lever adskilt
     */
    PLAD,
    /**
     * Registrert partner
     */
    REPA,
    /**
     * Samboer
     */
    SAMB,
    /**
     * Separert partner
     */
    SEPA,
    /**
     * Separert
     */
    SEPR,
    /**
     * Skilt
     */
    SKIL,
    /**
     * Skilt partner
     */
    SKPA,
    /**
     * Ugift
     */
    UGIF;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SivilstatusTypeCti> getCti() {
        return SivilstatusTypeCti.class;
    }
}
