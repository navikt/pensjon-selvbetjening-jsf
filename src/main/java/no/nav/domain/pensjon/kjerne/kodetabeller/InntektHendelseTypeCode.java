package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum InntektHendelseTypeCode implements CtiConvertable<InntektHendelseTypeCti, InntektHendelseTypeCode> {
    /**
     * Registrert
     */
    REGISTRERT,
    /**
     * Benyttet
     */
    BENYTTET,
    /**
     * Varslet
     */
    VARSLET;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<InntektHendelseTypeCti> getCti() {
        return InntektHendelseTypeCti.class;
    }
}
