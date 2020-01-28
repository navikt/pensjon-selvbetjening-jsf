package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SivilstandTypeCode implements CtiConvertable<SivilstandTypeCti, SivilstandTypeCode> {
    /**
     * Enke/-mann
     */
    ENKE,
    /**
     * Gift
     */
    GIFT,
    /**
     * Gjenlevende partner
     */
    GJPA,
    /**
     * Uoppgitt
     */
    NULL,
    /**
     * Registrert partner
     */
    REPA,
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
    public Class<SivilstandTypeCti> getCti() {
        return SivilstandTypeCti.class;
    }
}
