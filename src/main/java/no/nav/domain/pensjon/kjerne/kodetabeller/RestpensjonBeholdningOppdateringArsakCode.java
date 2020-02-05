package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum RestpensjonBeholdningOppdateringArsakCode implements CtiConvertable<RestpensjonBeholdningOppdateringArsakCti, RestpensjonBeholdningOppdateringArsakCode> {
    /**
     * Oppdatert p&aring; grunn av ny opptjening
     */
    NY_OPPTJENING,
    /**
     * Oppdatert p&aring; grunn av regulering
     */
    REGULERING,
    /**
     * Oppdatert p&aring; grunn av nytt vedtak
     */
    VEDTAK;

    @Override
    public Class<RestpensjonBeholdningOppdateringArsakCti> getCti() {
        return RestpensjonBeholdningOppdateringArsakCti.class;
    }
}
