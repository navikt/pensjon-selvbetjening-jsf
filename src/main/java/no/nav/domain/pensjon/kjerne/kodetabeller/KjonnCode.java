package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum KjonnCode implements CtiConvertable<KjonnCti, KjonnCode> {
    /**
     * Kvinne
     */
    K,
    /**
     * Mann
     */
    M;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<KjonnCti> getCti() {
        return KjonnCti.class;
    }
}
