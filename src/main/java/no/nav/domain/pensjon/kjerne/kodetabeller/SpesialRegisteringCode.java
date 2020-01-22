package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum SpesialRegisteringCode implements CtiConvertable<SpesialRegisteringCti, SpesialRegisteringCode> {
    /**
     * Klientadresse
     */
    KLIE,
    /**
     * Milit&aelig;r
     */
    MILI,
    /**
     * Pendler
     */
    PEND,
    /**
     * Sperret adresse, fortrolig
     */
    SPFO,
    /**
     * Sperret adresse, strengt fortrolig
     */
    SPSF,
    /**
     * Svalbard
     */
    SVAL,
    /**
     * Uten fast bopel
     */
    UFB,
    /**
     * I utenrikstjeneste
     */
    URIK;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<SpesialRegisteringCti> getCti() {
        return SpesialRegisteringCti.class;
    }
}
