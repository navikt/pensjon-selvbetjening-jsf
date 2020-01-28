package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum GrunnlagsrolleCode implements CtiConvertable<GrunnlagsrolleCti, GrunnlagsrolleCode> {
    /**
     * Avd&oslash;des barn
     */
    ABARN,
    /**
     * Avd&oslash;des ektefelle
     */
    AEKTEF,
    /**
     * Avd&oslash;des fosterbarn
     */
    AFBARN,
    /**
     * Avd&oslash;des partner
     */
    APARTNER,
    /**
     * Avd&oslash;des samboer
     */
    ASAMBO,
    /**
     * Avd&oslash;d
     */
    AVDOD,
    /**
     * Barn
     */
    BARN,
    /**
     * Ektefelle
     */
    EKTEF,
    /**
     * Far
     */
    FAR,
    /**
     * Fosterbarn
     */
    FBARN,
    /**
     * Mor
     */
    MOR,
    /**
     * Partner
     */
    PARTNER,
    /**
     * Samboer
     */
    SAMBO,
    /**
     * Bruker
     */
    SOKER,
    /**
     * S&oslash;sken
     */
    SOSKEN;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<GrunnlagsrolleCti> getCti() {
        return GrunnlagsrolleCti.class;
    }
}
