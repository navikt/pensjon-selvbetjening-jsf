package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum ElektroniskSkjemaTypeCode implements CtiConvertable<ElektroniskSkjemaTypeCti, ElektroniskSkjemaTypeCode> {
    /**
     * Alderspensjon
     */
    AP,
    /**
     * EndreAlderspensjon
     */
    EA,
    /**
     * Fors&oslash;rgingstillegg
     */
    FT,
    /**
     * Omsorgspoeng
     */
    OO,
    /**
     * Uforetrygd
     */
    UT,
    /**
     * Digital
     */
    DT;
    /**
     * {@inheritDoc}
     */
    @Override
    public Class<ElektroniskSkjemaTypeCti> getCti() {
        return ElektroniskSkjemaTypeCti.class;
    }
}
