package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 *
 */
public enum KildeInntCode implements CtiConvertable<KildeInntCti, KildeInntCode> {
    /**
     * Annen ytelse fra NAV
     */
    ANNEN_NAV,
    /**
     * Applikasjonsbruker
     */
    APPBRK,
    /**
     * Arbeidsgiver
     */
    ARBGIV,
    /**
     * BISYS
     */
    BISYS,
    /**
     * DSF
     */
    DSF,
    /**
     * Infotrygd
     */
    IT,
    /**
     * Konvertering
     */
    KONVERT,
    /**
     * PEN
     */
    PEN,
    /**
     * Pesys
     */
    PESYS,
    /**
     * Pensjon
     */
    PP01,
    /**
     * Selvbetjening
     */
    SELVBETJ,
    /**
     * Skattedirektoratet
     */
    SKD,
    /**
     * TP-ordning
     */
    TP_ORDN,
    /**
     * Utenlandsk trygdemyndighet
     */
    UTL_TRYGD,
    /**
     * G-omregning
     */
    GOMR,
    /**
     * A-ordning
     */
    A_ORDNING;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<KildeInntCti> getCti() {
        return KildeInntCti.class;
    }
}
