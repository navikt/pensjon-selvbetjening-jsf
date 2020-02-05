package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;
import no.stelvio.common.codestable.support.IllegalCodeEnum;

/**
 * Generated class.
 */
public enum YtelseKomponentTypeCode implements CtiConvertable<YtelseKomponentTypeCti, YtelseKomponentTypeCode>, IllegalCodeEnum {
    /**
     * 8-5-1 Tillegg
     */
    P_8_5_1_T("8_5_1_T"),
    /**
     * Arbeidsavklaringspenger
     */
    AAP,
    /**
     * AFP kompensasjonstillegg
     */
    AFP_KOMP_TILLEGG,
    /**
     * AFP kronetillegg
     */
    AFP_KRONETILLEGG,
    /**
     * AFP livsvarig del
     */
    AFP_LIVSVARIG,
    /**
     * AFP tillegg
     */
    AFP_T,
    /**
     * Gjenlevendetillegg
     */
    AP_GJT,
    /**
     * Differanseberegning
     */
    DIFF_BER,
    /**
     * Ektefelletillegg
     */
    ET,
    /**
     * Familietillegg
     */
    FAM_T,
    /**
     * Tillegg faste utgifter
     */
    FAST_UTGIFT_T,
    /**
     * Fremskrevet AFP livsvarig del
     */
    FREM_AFP_LIVSVARIG,
    /**
     * Garantipensjon
     */
    GAP,
    /**
     * Garantitillegg
     */
    GAT,
    /**
     * Grunnpensjon
     */
    GP,
    /**
     * Garantitillegg E&Oslash;S Art 50
     */
    GT_EOS,
    /**
     * Garantitillegg Nordisk Art 27
     */
    GT_NORDISK,
    /**
     * Hjelpel&oslash;shetsbidrag
     */
    HJELP_BIDRAG,
    /**
     * Tillegg til hjelp i huset
     */
    HJELP_I_HUS,
    /**
     * Inntektspensjon
     */
    IP,
    /**
     * Krigspensjon og gammel yrkesskade
     */
    KRIG_GY,
    /**
     * Mendel
     */
    MENDEL,
    /**
     * Minsteniv&aring;tillegg individuelt
     */
    MIN_NIVA_TILL_INDV,
    /**
     * Minsteniv&aring;tillegg pensjonistpar
     */
    MIN_NIVA_TILL_PPAR,
    /**
     * Pensjonstillegg
     */
    PT,
    /**
     * Skattefri grunnpensjon
     */
    SKATT_F_GP,
    /**
     * Skattefri uf&oslash;retrygd ordin&aelig;r
     */
    SKATT_F_UT_ORDINER,
    /**
     * Skjermingstillegg
     */
    SKJERMT,
    /**
     * Sykepenger
     */
    SP,
    /**
     * S&aelig;rtillegg
     */
    ST,
    /**
     * Barnetillegg fellesbarn
     */
    TFB,
    /**
     * Tilleggspensjon
     */
    TP,
    /**
     * Barnetillegg s&aelig;rkullsbarn
     */
    TSB,
    /**
     * Uforetillegg til alderspensjon
     */
    UFORETILLEGG_AP,
    /**
     * Arbeidsavklaringspenger
     */
    UT_AAP,
    /**
     * Ektefelletillegg
     */
    UT_ET,
    /**
     * Dekning av faste utgifter inst.opph
     */
    UT_FAST_UTGIFT_T,
    /**
     * Gjenlevendetillegg til uf&oslash;retrygd
     */
    UT_GJT,
    /**
     * Garantitillegg Nordisk Art 27 UT
     */
    UT_GT_NORDISK,
    /**
     * Uf&oslash;retrygd ordin&aelig;r
     */
    UT_ORDINER,
    /**
     * Sykepenger
     */
    UT_SP,
    /**
     * Barnetillegg fellesbarn
     */
    UT_TFB,
    /**
     * Barnetillegg s&aelig;rkullsbarn
     */
    UT_TSB,
    /**
     * Ventetillegg
     */
    VT;

    private String value = null;

    private YtelseKomponentTypeCode(String value) {
        this.value = value;
    }

    private YtelseKomponentTypeCode() {
    }

    @Override
    public String getIllegalCode() {
        return value == null ? name() : value;
    }

    @Override
    public String toString() {
        return getIllegalCode();
    }

    @Override
    public Class<YtelseKomponentTypeCti> getCti() {
        return YtelseKomponentTypeCti.class;
    }
}
