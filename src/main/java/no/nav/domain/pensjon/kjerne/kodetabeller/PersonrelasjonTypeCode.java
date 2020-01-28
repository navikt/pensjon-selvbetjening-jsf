package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum PersonrelasjonTypeCode implements CtiConvertable<PersonrelasjonTypeCti, PersonrelasjonTypeCode> {
    /**
     * Barn
     */
    BARN,
    /**
     * Ektefelle
     */
    EKTE,
    /**
     * Far
     */
    FARA,
    /**
     * Mor
     */
    MORA,
    /**
     * Registrert partner
     */
    REPA,
    /**
     * Samboer
     */
    SAMB,
    /**
     * S&oslash;sken
     */
    SOSK;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<PersonrelasjonTypeCti> getCti() {
        return PersonrelasjonTypeCti.class;
    }
}
