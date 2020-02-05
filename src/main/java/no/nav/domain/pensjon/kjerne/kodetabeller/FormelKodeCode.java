package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum FormelKodeCode implements CtiConvertable<FormelKodeCti, FormelKodeCode> {

    AFP3(new Builder().withInnvilget("pselv.formel_kode.afp3")),
    AFP4(new Builder().withInnvilget("pselv.formel_kode.afp4")),
    AFPx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Forsteuttak: Beregnet hel alderspensjon ved 67 ar
     */
    AP2016_1(new Builder().withAvslag("pselv.formel_kode.ap2016_1_avsl")),
    /**
     * Endring av uttaksgrad: Beregnet hel alderspensjon ved 67 ar
     */
    AP2016_2(new Builder().withAvslag("pselv.formel_kode.ap2016_2_avsl")),
    /**
     * AP kap.3 med gjenlevenderett er lavere enn AP kap.3 uten gjenlevenderett
     */
    AP_GJT1,
    /**
     * AP kap.3 med gjenlevenderett - AP kap.3 uten gjenlevenderett
     */
    AP_GJT2(new Builder().withInnvilget("pselv.formel_kode.ap2016gjt")),
    BPUx(new Builder().withText("ukjentformelgenerell")),
    BT1(new Builder().withInnvilget("pselv.formel_kode.bt1")),
    BT2(new Builder().withInnvilget("pselv.formel_kode.bt2")),
    BTx(new Builder().withText("ukjentformelgenerell")),
    BasGP1(new Builder().withInnvilget("pselv.formel_kode.basgp1")),
    BasGPx(new Builder().withText("ukjentformelbasgp")),
    BasPenT3(new Builder().withInnvilget("pselv.formel_kode.baspentp3")),
    BasPenTx(new Builder().withText("ukjentformelgenerell")),
    BasTP1(new Builder().withInnvilget("pselv.formel_kode.bastp1")),
    BasTP2(new Builder().withInnvilget("pselv.formel_kode.bastp2")),
    BasTP3(new Builder().withInnvilget("pselv.formel_kode.bastp3")),
    BasTP4(new Builder().withInnvilget("pselv.formel_kode.bastp4")),
    BasTPx(new Builder().withText("ukjentformelbastp")),
    BerPensjonvedUttak(new Builder().withInnvilget("pselv.formel_kode.berpensjonveduttak")),
    BgOrdiner,
    ET1(new Builder().withInnvilget("pselv.formel_kode.et1")),
    ET2(new Builder().withInnvilget("pselv.formel_kode.et2")),
    ETUTA,
    ETUTB,
    ETUTC,
    ETx(new Builder().withText("ukjentformelgenerell")),
    FullPensjon672(new Builder().withInnvilget("pselv.formel_kode.fullpensjon672")),
    FullPensjon673(new Builder().withInnvilget("pselv.formel_kode.fullpensjon673")),
    FullPensjon674(new Builder().withInnvilget("pselv.formel_kode.fullpensjon674")),
    FullPensjon67x(new Builder().withText("ukjentformelgenerell")),
    /**
     * Garantipensjon forstegangsuttak
     */
    GAP1(new Builder().withInnvilget("pselv.formel_kode.gap1").withAvslag("pselv.formel_kode.gap1_avsl")),
    /**
     * Garantipensjon endring av uttaksgrad
     */
    GAP2(new Builder().withInnvilget("pselv.formel_kode.gap2").withAvslag("pselv.formel_kode.gap2_avsl")),
    GAPx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Garantipensjonsbeholdning forstegangsuttak
     */
    GAR_PEN_B1(new Builder().withInnvilget("pselv.formel_kode.gar_pen_b1").withAvslag("pselv.formel_kode.gar_pen_b1_avsl")),
    /**
     * Garantipensjonsbeholdning endring av uttaksgrad
     */
    GAR_PEN_B2(new Builder().withInnvilget("pselv.formel_kode.gar_pen_b2")),
    GAR_PEN_Bx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Garantitilleggsbeholdning forstegangsuttak
     */
    GAR_T_B1(new Builder().withInnvilget("pselv.formel_kode.gar_t_b1")),
    /**
     * Garantitilleggsbeholdning endring av uttaksgrad
     */
    GAR_T_B2(new Builder().withInnvilget("pselv.formel_kode.gar_t_b2")),
    GAR_T_Bx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Garantitillegg forstegangsuttak
     */
    GAT1(new Builder().withInnvilget("pselv.formel_kode.gat1")),
    /**
     * Garantitillegg endring av uttaksgrad
     */
    GAT2(new Builder().withInnvilget("pselv.formel_kode.gat2")),
    GATx(new Builder().withText("ukjentformelgenerell")),
    GP1(new Builder().withInnvilget("pselv.formel_kode.gp1")),
    GP2(new Builder().withInnvilget("pselv.formel_kode.gp2")),
    GP3(new Builder().withInnvilget("pselv.formel_kode.gp3")),
    /**
     * Forsteuttak: Garantipensjonsniva ved 67 ar TOM 2029
     */
    GPN_HOY_2029_1(new Builder().withAvslag("pselv.formel_kode.gpn_hoy_2029_1_avsl")),
    /**
     * Forsteuttak: Garantipensjonsniva ved 67 ar TOM 2029
     */
    GPN_HOY_2029_2(new Builder().withAvslag("pselv.formel_kode.gpn_hoy_2029_2_avsl")),
    /**
     * Endring av uttaksgrad: Garantipensjonsniva ved 67 ar TOM 2029
     */
    GPN_HOY_2030_1(new Builder().withAvslag("pselv.formel_kode.gpn_hoy_2030_1_avsl")),
    /**
     * Endring av uttaksgrad: Garantipensjonsniva ved 67 ar TOM 2029
     */
    GPN_HOY_2030_2(new Builder().withAvslag("pselv.formel_kode.gpn_hoy_2030_2_avsl")),
    GPx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Inntektspensjon forstegangsuttak
     */
    IP1(new Builder().withInnvilget("pselv.formel_kode.ip1").withAvslag("pselv.formel_kode.ip1_avsl")),
    /**
     * Inntektspensjon endring av uttaksgrad
     */
    IP2(new Builder().withInnvilget("pselv.formel_kode.ip2").withAvslag("pselv.formel_kode.ip2_avsl")),
    IPx(new Builder().withText("ukjentformelgenerell")),
    KompT1(new Builder().withInnvilget("pselv.formel_kode.afp_privat.kompt1")),
    KompT2(new Builder().withInnvilget("pselv.formel_kode.afp_privat.kompt2")),
    LivsvD1(new Builder().withInnvilget("pselv.formel_kode.afp_privat.livsvd1")),
    LivsvD2(new Builder().withInnvilget("pselv.formel_kode.afp_privat.livsvd2")),
    LivsvD3(new Builder().withInnvilget("pselv.formel_kode.afp_privat.livsvd3")),
    LivsvD4(new Builder().withInnvilget("pselv.formel_kode.afp_privat.livsvd4")),
    /**
     * Minste pensjonsniva - Samlet pensjon til utbetaling
     */
    MIN_NIVA_TILL_INDV1(new Builder().withInnvilget("pselv.formel_kode.min_niva_till_indv1")),
    /**
     * Minste pensjonsniva og Garantipensjonsniva - Samlet pensjon til utbetaling
     */
    MIN_NIVA_TILL_INDV2(new Builder().withInnvilget("pselv.formel_kode.min_niva_till_indv2")),
    /**
     * Garantipensjonsniva - Samlet pensjon til utbetaling
     */
    MIN_NIVA_TILL_INDV3(new Builder().withInnvilget("pselv.formel_kode.min_niva_till_indv3")),
    MIN_NIVA_TILL_PPAR1,
    MPN1(new Builder().withInnvilget("pselv.formel_kode.mpn1")),
    MPNUttakFullTT(new Builder().withInnvilget("pselv.formel_kode.mpnuttakfulltt")),
    MPNVilk1(new Builder().withInnvilget("pselv.formel_kode.mpnvilk1")),
    MPNVilk2(new Builder().withInnvilget("pselv.formel_kode.mpnvilk2")),
    MPNvilkx(new Builder().withText("ukjentformelgenerell")),
    MPNx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Pensjonsbeholdning forstegangsuttak
     */
    PEN_B1(new Builder().withInnvilget("pselv.formel_kode.pen_b1")),
    /**
     * Pensjonsbeholdning endring av uttaksgrad
     */
    PEN_B2(new Builder().withInnvilget("pselv.formel_kode.pen_b2")),
    PEN_Bx(new Builder().withText("ukjentformelgenerell")),
    MyBest,
    /**
     * - Eksportforbud for minsteytelse ved delvis eksport.
     */
    MyEksportforbud,
    MyOrdiner,
    MyOrdinerYrkesskade,
    MyProRata,
    MyYrkesskade,
    PenT1(new Builder().withInnvilget("pselv.formel_kode.pent1")),
    PenT2(new Builder().withInnvilget("pselv.formel_kode.pent2")),
    PenT3(new Builder().withInnvilget("pselv.formel_kode.pent3")),
    PenTx(new Builder().withText("ukjentformelgenerell")),
    /**
     * Forsteuttak: Garantipensjonsbeholdning etter uttak
     */
    RES_GAR_PEN_B1(new Builder().withAvslag("pselv.formel_kode.res_gar_pen_b1_avsl")),
    /**
     * Endring av uttaksgrad: Garantipensjonsbeholdning etter uttak
     */
    RES_GAR_PEN_B2(new Builder().withAvslag("pselv.formel_kode.res_gar_pen_b2_avsl")),
    /**
     * Forsteuttak: Pensjonsbeholdning etter uttak
     */
    RES_PEN_B1(new Builder().withAvslag("pselv.formel_kode.res_pen_b1_avsl")),
    /**
     * Endring av uttaksgrad: Pensjonsbeholdning etter uttak
     */
    RES_PEN_B2(new Builder().withAvslag("pselv.formel_kode.res_pen_b2_avsl")),
    ResGP0(new Builder().withInnvilget("pselv.formel_kode.resgp0")),
    ResGP1(new Builder().withInnvilget("pselv.formel_kode.resgp1")),
    ResGP2(new Builder().withInnvilget("pselv.formel_kode.resgp2")),
    ResGPx(new Builder().withText("ukjentformelgenerell")),
    ResP1(new Builder().withInnvilget("pselv.formel_kode.resp1")),
    ResP2(new Builder().withInnvilget("pselv.formel_kode.resp2")),
    ResP3(new Builder().withInnvilget("pselv.formel_kode.resp3")),
    ResPenT0(new Builder().withInnvilget("pselv.formel_kode.respent0")),
    ResPenT1(new Builder().withInnvilget("pselv.formel_kode.respent1")),
    ResPenT2(new Builder().withInnvilget("pselv.formel_kode.respent2")),
    ResPenTx(new Builder().withText("ukjentformelgenerell")),
    ResPx(new Builder().withText("ukjentformelgenerell")),
    ResTP0(new Builder().withInnvilget("pselv.formel_kode.restp0")),
    ResTP1(new Builder().withInnvilget("pselv.formel_kode.restp1")),
    ResTP3(new Builder().withInnvilget("pselv.formel_kode.restp3")),
    ResTPx(new Builder().withText("ukjentformelgenerell")),
    SkattKompx,
    Skjerm1(new Builder().withInnvilget("pselv.formel_kode.skjerm1")),
    Skjerm2(new Builder().withInnvilget("pselv.formel_kode.skjerm2")),
    Skjermx(new Builder().withText("ukjentformelgenerell")),
    TP1(new Builder().withInnvilget("pselv.formel_kode.tp1")),
    TP2(new Builder().withInnvilget("pselv.formel_kode.tp2")),
    TP3(new Builder().withInnvilget("pselv.formel_kode.tp3")),
    TPx(new Builder().withText("ukjentformelgenerell")),
    UTSKH1,
    UTSKH2,
    UTSKH3,
    UTSKH4,
    UTSKH5,
    UTSKH6,
    UTSKH7,
    UTSKH8,
    UTSKO1,
    UTSKO2,
    UTSKO3,
    UTSKO4,
    UTSKO5,
    UTSKO6,
    UTSKO7,
    UTSKO8,
    YgBest,
    YgOrdiner,
    YgOrdinerYrkesskade,
    YgProRata,
    YgYrkesskade;

    final private String innvilgetKey;
    final private String avslagKey;
    final private String text;

    private FormelKodeCode() {
        innvilgetKey = null;
        avslagKey = null;
        text = null;
    }

    private FormelKodeCode(Builder builder) {
        innvilgetKey = builder.innvilgetKey;
        avslagKey = builder.avslagKey;
        text = builder.text;
    }

    public String getInnvilgetKey() {
        return innvilgetKey;
    }

    public String getAvslagKey() {
        return avslagKey;
    }

    public String getText() {
        return text;
    }

    private static class Builder {
        private String innvilgetKey;
        private String avslagKey;
        private String text;

        public Builder withInnvilget(String innvilgetKey) {
            this.innvilgetKey = innvilgetKey;
            return this;
        }

        public Builder withAvslag(String avslagKey) {
            this.avslagKey = avslagKey;
            return this;
        }

        public Builder withText(String text) {
            this.text = text;
            return this;
        }
    }

    @Override
    public Class<FormelKodeCti> getCti() {
        return FormelKodeCti.class;
    }
}
