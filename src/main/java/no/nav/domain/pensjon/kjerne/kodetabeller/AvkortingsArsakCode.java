package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum AvkortingsArsakCode implements CtiConvertable<AvkortingsArsakCti, AvkortingsArsakCode> {
    /**
     * Fors&oslash;rgingstillegget er redusert p&aring; bakgrunn av inntekten til bruker og ektefelle
     */
    BRUKER_EKTEF_INNT,
    /**
     * Fors&oslash;rgingstillegget er redusert p&aring; bakgrunn av inntekten til bruker
     */
    BRUKER_INNT,
    /**
     * Fors&oslash;rgingstillegget er redusert p&aring; bakgrunn av inntekten til ektefelle
     */
    EKTEF_INNT;

    @Override
    public Class<AvkortingsArsakCti> getCti() {
        return AvkortingsArsakCti.class;
    }
}
