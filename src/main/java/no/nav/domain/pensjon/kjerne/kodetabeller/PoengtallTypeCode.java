package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum PoengtallTypeCode implements CtiConvertable<PoengtallTypeCti, PoengtallTypeCode> {
    /**
     * Framtidig pensjonspoengtall
     */
    FPP,
    /**
     * Omsorg for barn under 6 &aring;r
     */
    G,
    /**
     * Omsorg for barn over 6 &aring;r (uf&oslash;r)
     */
    H,
    /**
     * Inntekt f&oslash;r uttak av AFP
     */
    IFUT,
    /**
     * Omsorgspoeng sm&aring; barn
     */
    J,
    /**
     * Omsorgspoeng forh&oslash;yet hjelpest&oslash;nad
     */
    K,
    /**
     * Omsorgspoeng for pleie av eldre syke
     */
    L,
    /**
     * Ordin&aelig;rt poengtall
     */
    PI;

    @Override
    public Class<PoengtallTypeCti> getCti() {
        return PoengtallTypeCti.class;
    }
}
