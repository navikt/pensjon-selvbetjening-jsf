package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum ResultatTypeCode implements CtiConvertable<ResultatTypeCti, ResultatTypeCode> {
    /**
     * AFP
     */
    AFP,
    /**
     * AFP i privat sektor
     */
    AFP_PRIVAT,
    /**
     * Alderspensjon
     */
    AP,
    /**
     * Alderspensjon
     */
    AP2011,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP2011_GJP,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP2011_GJP_UP,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP2011_GJP_UP_YP,
    /**
     * Alderspensjon
     */
    AP2016,
    /**
     * Alderspensjon
     */
    AP2025,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP_GJP,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP_GJP_UP,
    /**
     * Alderspensjon med gjenlevenderett
     */
    AP_GJP_UP_YP,
    /**
     * Barnepensjon
     */
    BP1,
    /**
     * Barnepensjon med avd&oslash;des uf&oslash;reopptjening
     */
    BP1_UP,
    /**
     * Barnepensjon
     */
    BP2,
    /**
     * Barnepensjon med avd&oslash;des uf&oslash;reopptjening
     */
    BP2_UP,
    /**
     * Familiepleierytelse
     */
    FAMP,
    /**
     * Gammel yrkesskade
     */
    GAM_YRK,
    /**
     * Gjenlevendeytelse
     */
    GJP,
    /**
     * Gjenlevendeytelse etter far
     */
    GJP_FAR,
    /**
     * Gjenlevendeytelse etter mor
     */
    GJP_MOR,
    /**
     * Gjenlevendepensjon med avd&oslash;des uf&oslash;reopptjening
     */
    GJP_UP,
    /**
     * Gjenlevendeytelse med avd&oslash;des uf&oslash;reopptjening
     */
    GJP_UP_YP,
    /**
     * Gjenlevenderett
     */
    GJR,
    /**
     * Konvertert AFP
     */
    KONV_AFP,
    /**
     * Konvertert alderspensjon
     */
    KONV_ALDER,
    /**
     * Konvertert barnepensjon
     */
    KONV_BARNEP1,
    /**
     * Konvertert barnepensjon
     */
    KONV_BARNEP2,
    /**
     * Konvertert familiepleierytelse
     */
    KONV_FAM_PLEIE,
    /**
     * Konvertert gammel yrkesskade
     */
    KONV_GAM_YRK,
    /**
     * Konvertert gjenlevendeytelse
     */
    KONV_GJENL,
    /**
     * Konvertert krigspensjon
     */
    KONV_KRIG,
    /**
     * Konvertert uf&oslash;repensjon
     */
    KONV_UFORE,
    /**
     * Krigspensjon
     */
    KP,
    /**
     * Uf&oslash;repensjon
     */
    UP,
    /**
     * Uf&oslash;repensjon med gjenlevenderett
     */
    UP_GJP,
    /**
     * Uf&oslash;repensjon med gjenlevenderett
     */
    UP_GJP_UP,
    /**
     * Uf&oslash;repensjon med gjenlevenderett
     */
    UP_GJP_UP_YP,
    /**
     * Uf&oslash;repensjon med yrkesskadefordeler
     */
    UP_YP,
    /**
     * Uf&oslash;retrygd
     */
    UT,
    /**
     * Uf&oslash;retrygd med yrkesskadefordeler
     */
    UT_YT,
    /**
     * Uf&oslash;retrygd med gjenlevendetillegg
     */
    UT_GJT,
    /**
     * Yrkesskade
     */
    YP,
    /**
     * Yrkesskade
     */
    YT;

    @Override
    public Class<ResultatTypeCti> getCti() {
        return ResultatTypeCti.class;
    }
}
