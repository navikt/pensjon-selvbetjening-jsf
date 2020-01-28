package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum GrunnIkkeReduksjonTypeCode implements CtiConvertable<GrunnIkkeReduksjonTypeCti, GrunnIkkeReduksjonTypeCode> {
    /**
     * Erstatning for inntektstap ved erstatningsoppgjør
     */
    ERSTATNING_INNTTAP_ERSTOPPGJ,
    /**
     * Etterslepsinntekt fra helt avsluttet aktivitet
     */
    ETTERSLEPSINNT_AVSLT_AKT,
    /**
     * Annet
     */
    IKKE_RED_ANNET,
    /**
     * Opptjent før innvilgelse UT
     */
    OPPTJENT_FOR_INNV_UT,
    /**
     * Opptjent etter opphør av UT
     */
    OPPTJENT_ETTER_INNV_UT,
    /**
     * Etterbetaling fra NAV
     */
    ETTERBETALING;


    /**
     * {@inheritDoc}
     */
    @Override
    public Class<GrunnIkkeReduksjonTypeCti> getCti() {
        return GrunnIkkeReduksjonTypeCti.class;
    }
}
