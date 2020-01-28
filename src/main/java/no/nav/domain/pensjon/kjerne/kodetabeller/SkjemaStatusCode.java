package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SkjemaStatusCode implements CtiConvertable<SkjemaStatusCti, SkjemaStatusCode> {
    /**
     * Skjema sendt til manuell behandling
     */
    MANUELL,
    /**
     * Skjema sendt til maskinell behandling
     */
    MASKINELL,
    /**
     * Skjema opprettet
     */
    OPPRETTET;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SkjemaStatusCti> getCti() {
        return SkjemaStatusCti.class;
    }
}
