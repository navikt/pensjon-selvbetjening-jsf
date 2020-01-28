package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class, do not edit.
 */
public enum AdresseTypePersonCode implements CtiConvertable<AdresseTypePersonCti, AdresseTypePersonCode> {
    /**
     * Bostedsadresse
     */
    BOAD,
    /**
     * Postadresse
     */
    POST,
    /**
     * Postadresse i utlandet
     */
    PUTL,
    /**
     * Tilleggsadresse
     */
    TIAD,
    /**
     * Utenlandsadresse
     */
    UTAD;

    @Override
    public Class<AdresseTypePersonCti> getCti() {
        return AdresseTypePersonCti.class;
    }
}
