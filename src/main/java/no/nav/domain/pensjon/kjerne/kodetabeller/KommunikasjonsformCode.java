package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum KommunikasjonsformCode implements CtiConvertable<KommunikasjonsformCti, KommunikasjonsformCode> {
    /**
     * Elektronisk
     */
    ELEK,
    /**
     * Vanlig post
     */
    PAPR;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<KommunikasjonsformCti> getCti() {
        return KommunikasjonsformCti.class;
    }
}
