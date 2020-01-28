package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum UforeTypeCode implements CtiConvertable<UforeTypeCti, UforeTypeCode> {
    /**
     * Uf&oslash;re
     */
    UFORE,
    /**
     * Uf&oslash;re m/yrkesskade
     */
    UF_M_YRKE,
    /**
     * F&oslash;rste virkningsdato, ikke uf&oslash;r
     */
    VIRK_IKKE_UFOR,
    /**
     * Yrkesskade
     */
    YRKE;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<UforeTypeCti> getCti() {
        return UforeTypeCti.class;
    }
}
