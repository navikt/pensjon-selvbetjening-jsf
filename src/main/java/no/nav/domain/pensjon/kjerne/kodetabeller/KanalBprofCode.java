package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum KanalBprofCode implements CtiConvertable<KanalBprofCti, KanalBprofCode> {
    /**
     * E-post
     */
    EPST,
    /**
     * Min side
     */
    MISI,
    /**
     * SMS
     */
    SMS;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<KanalBprofCti> getCti() {
        return KanalBprofCti.class;
    }
}
