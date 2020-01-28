package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum SakTypeCode implements CtiConvertable<SakTypeCti, SakTypeCode> {
    /**
     * AFP
     */
    AFP,
    /**
     * AFP Privat
     */
    AFP_PRIVAT,
    /**
     * Alderspensjon
     */
    ALDER,
    /**
     * Barnepensjon
     */
    BARNEP,
    /**
     * Familiepleierytelse
     */
    FAM_PL,
    /**
     * Gammel yrkesskade
     */
    GAM_YRK,
    /**
     * Generell
     */
    GENRL,
    /**
     * Gjenlevendeytelse
     */
    GJENLEV,
    /**
     * Grunnblanketter
     */
    GRBL,
    /**
     * Krigspensjon
     */
    KRIGSP,
    /**
     * Omsorgsopptjening
     */
    OMSORG,
    /**
     * Uf&oslash;retrygd
     */
    UFOREP;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SakTypeCti> getCti() {
        return SakTypeCti.class;
    }
}
