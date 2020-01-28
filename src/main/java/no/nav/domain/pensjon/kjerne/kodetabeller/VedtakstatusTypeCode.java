package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum VedtakstatusTypeCode implements CtiConvertable<VedtakstatusTypeCti, VedtakstatusTypeCode> {
    /**
     * Attestert
     */
    ATT,
    /**
     * Avbrutt
     */
    AVBR,
    /**
     * Avventer oppdragskvittering
     */
    AVV_OS_KVITT,
    /**
     * Fastsettes
     */
    FASTS,
    /**
     * Iverksatt
     */
    IVERKS,
    /**
     * Reaktiviseres
     */
    REAK,
    /**
     * Samordnet
     */
    SAMORDN,
    /**
     * Stoppes
     */
    STOPPES,
    /**
     * Stoppet
     */
    STOPPET,
    /**
     * Til attestering
     */
    TIL_ATT,
    /**
     * Til iverksettelse
     */
    TIL_IVERKS,
    /**
     * Til samordning
     */
    TIL_SAMORDN;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<VedtakstatusTypeCti> getCti() {
        return VedtakstatusTypeCti.class;
    }

    public boolean isCodeEqualTo(VedtakstatusTypeCti vedtakstatusTypeCti) {
        return vedtakstatusTypeCti != null && name().equals(vedtakstatusTypeCti.getCodeAsString());
    }
}
