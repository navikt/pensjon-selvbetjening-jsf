package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;
import no.stelvio.common.codestable.support.IllegalCodeEnum;

/**
 * Generated class, do not edit.
 */
public enum InntektTypeCode implements CtiConvertable<InntektTypeCti, InntektTypeCode>, IllegalCodeEnum {
    /**
     * Antatt inntekt
     */
    AI,
    /**
     * Pensjonsgivende inntekt (Ligning)
     */
    ARBLIGN,
    /**
     * Arbeidsinntekt (L&oslash;nn og trekk)
     */
    ARBLTO,
    /**
     * Kapitalinntekt - bidrag SA
     */
    P_BI_x_KAP("BI-KAP"),
    /**
     * Ligningsinntekt (selvangivelse) - bidrag SA
     */
    P_BI_x_LIGN("BI-LIGN"),
    /**
     * Ligningsinntekt (l&oslash;nn og trekk) - bidrag LT
     */
    P_BI_x_LTR("BI-LTR"),
    /**
     * Forventet bidrag o.l
     */
    FBI,
    /**
     * Forventet kapitalinntekt
     */
    FKI,
    /**
     * Forventet n&aelig;ringsinntekt
     */
    FORINTNAE,
    /**
     * Forventet arbeidsinntekt
     */
    FORINTARB,
    /**
     * Forventet utenlandsinntekt
     */
    FORINTUTL,
    /**
     * Forventet arbeidsinntekt
     */
    FPI,
    /**
     * Hypotetisk forventet arbeidsinntekt
     */
    HYPF,
    /**
     * Hypotetisk forventet arbeidsinntekt 2G
     */
    HYPF2G,
    /**
     * Inntekt Mnd F&oslash;r Uttak
     */
    IMFU,
    /**
     * Kapitalinntekt (ligning)
     */
    KAP,
    /**
     * Pensjonsgivende inntekt (l&oslash;nn og trekk)
     */
    P_PE_x_PGILT("PE-PGILT"),
    /**
     * St&oslash;nadsinntekt inkludert f&oslash;dsels- og sykepenger (l&oslash;nn og trekk)
     */
    P_PE_x_SILT("PE-SILT"),
    /**
     * Pensjonsinntekt fra folketrygden
     */
    PENF,
    /**
     * Pensjonsinntekt (ikke folketrygd)
     */
    PENSKD,
    /**
     * Forventet pensjonsinntekt (ikke folketrygd)
     */
    PENT,
    /**
     * Forventet pensjonsinntekt (utland)
     */
    PENTU,
    /**
     * Forel&oslash;pig pensjonsgivende inntekt
     */
    PGI,
    /**
     * Sum av forventet arbeids-, kapital- og pensjonsinntekt
     */
    SFAKPI,
    /**
     * Ukjent inntektstype fra inntektsregisteret
     */
    UKJENT,
    /**
     * Inntekt til fradrag
     */
    IKKE_RED,
    /**
     * Innrapportert arbeidsinntekt
     */
    RAP_ARB,
    /**
     * Innrapportert næringsinntekt
     */
    RAP_NAR,
    /**
     * Forventet pensjon fra utlandet
     */
    FORPENUTL,
    /**
     * Forventet andre pensjoner og ytelser
     */
    FORINTAND,
    /**
     * Innrapporterte andre pensjoner og ytelser
     */
    RAP_AND;

    private String value = null;

    /**
     * Value constructor for illegal enums.
     *
     * @param value the illegal value
     */
    private InntektTypeCode(String value) {
        this.value = value;
    }

    /**
     * Default constructor.
     */
    private InntektTypeCode() {
    }

    /**
     * @return enum value
     */
    @Override
    public String getIllegalCode() {
        return value == null ? name() : value;
    }

    /**
     * Make toString behave the same for normal code enums and illegal code enums.
     * This makes the toString method synonymous with the getIllegalCode() method.
     */
    @Override
    public String toString() {
        return getIllegalCode();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<InntektTypeCti> getCti() {
        return InntektTypeCti.class;
    }
}
