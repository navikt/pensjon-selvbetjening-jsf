package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum InntektPensjonsopptjeningTypeCode implements CtiConvertable<InntektPensjonsopptjeningTypeCti, InntektPensjonsopptjeningTypeCode> {
    /**
     * Antatt inntekt
     */
    AI,
    /**
     * Diplomatinntekt - Jord/Skog/Fisk
     */
    DIP_JSF,
    /**
     * Diplomatinntekt - L&oslash;nn
     */
    DIP_LON,
    /**
     * Diplomatinntekt - Selvstendig
     */
    DIP_SEL,
    /**
     * Innenlandsinntekt - Jord/Skog/Fisk
     */
    INN_JSF,
    /**
     * Innenlandsinntekt - L&oslash;nn
     */
    INN_LON,
    /**
     * Innenlandsinntekt - Selvstendig
     */
    INN_SEL,
    /**
     * PGI innland fastsatt av NAV
     */
    PGI_NAV,
    /**
     * Pensjonsgivende inntekt 1966 - Konv
     */
    PI66,
    /**
     * Reduksjonsinntekt
     */
    RED_INT,
    /**
     * Sj&oslash;inntekt - Jord/Skog/Fisk
     */
    SJO_JSF,
    /**
     * Sj&oslash;inntekt - L&oslash;nn
     */
    SJO_LON,
    /**
     * Sj&oslash;inntekt - Selvstendig
     */
    SJO_SEL,
    /**
     * Sum pensjonsgivende inntekt
     */
    SUM_PI,
    /**
     * Svalbardinntekt - Jord/Skog/Fisk
     */
    SVA_JSF,
    /**
     * Svalbardinntekt - L&oslash;nn
     */
    SVA_LON,
    /**
     * Svalbardinntekt - Selvstendig
     */
    SVA_SEL,
    /**
     * Utenlandsinntekt - Jord/Skog/Fisk
     */
    UTE_JSF,
    /**
     * Utenlandsinntekt - L&oslash;nn
     */
    UTE_LON,
    /**
     * Utenlandsinntekt - Selvstendig
     */
    UTE_SEL;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<InntektPensjonsopptjeningTypeCti> getCti() {
        return InntektPensjonsopptjeningTypeCti.class;
    }
}
