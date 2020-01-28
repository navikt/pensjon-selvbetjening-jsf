package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum BeholdningsTypeCode implements CtiConvertable<BeholdningsTypeCti, BeholdningsTypeCode> {
    /**
     * AFP beholdning
     */
    AFP,
    /**
     * Garantipensjonsbeholdning
     */
    GAR_PEN_B,
    /**
     * Garantitilleggsbeholdning
     */
    GAR_T_B,
    /**
     * Pensjonsbeholdning
     */
    PEN_B;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<BeholdningsTypeCti> getCti() {
        return BeholdningsTypeCti.class;
    }
}
