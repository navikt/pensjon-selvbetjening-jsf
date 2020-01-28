package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SakStatusCode implements CtiConvertable<SakStatusCti, SakStatusCode> {
    /**
     * Avsluttet
     */
    AVSLUTTET,
    /**
     * L&oslash;pende
     */
    LOPENDE,
    /**
     * Opprettet
     */
    OPPRETTET,
    /**
     * Til behandling
     */
    TIL_BEHANDLING;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SakStatusCti> getCti() {
        return SakStatusCti.class;
    }
}
