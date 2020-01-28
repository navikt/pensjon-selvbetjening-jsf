package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum InitiertAvCode implements CtiConvertable<InitiertAvCti, InitiertAvCode> {
    /**
     * Advokat
     */
    ADVOKAT,
    /**
     * Annet
     */
    ANNET,
    /**
     * Bruker
     */
    BRUKER,
    /**
     * Inntektskontroll
     */
    INNTKON,
    /**
     * NAV (maskinelt)
     */
    INTERN,
    /**
     * Konvertert
     */
    KONV,
    /**
     * NAV
     */
    NAV,
    /**
     * Sosialkontoret
     */
    SOSIALKONTOR,
    /**
     * Tips
     */
    TIPS,
    /**
     * Verge
     */
    VERGE;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<InitiertAvCti> getCti() {
        return InitiertAvCti.class;
    }
}
