package no.nav.presentation.pensjon.pselv.common.utils;

import no.nav.domain.pensjon.common.person.AnnenAdresse;
import no.nav.domain.pensjon.common.person.BostedsAdresse;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.AdresseTypePersonCode;

import static org.apache.commons.lang.StringUtils.isBlank;

/**
 * Utility class for addresses to determine if they are set. There is one method per type of address, since the criteria to
 * determine if an address is set, varies according to the type of address.
 */
public class AdresseUtil {

    public AdresseUtil() {
    }

    /**
     * Utility method finding a person's prioritized address. The priority is as follows:
     * <ol>
     * <li>Utenlandsadresse (UTAD)</li>
     * <li>Tilleggsadresse (TIAD)</li>
     * <li>Postadresse (POST)</li>
     * <li>Bostedsadresse (BOAD)</li>
     * </ol>
     */
    public AdresseTypePersonCode findPrioritertAdresse(Person person) {
        if (hasUtenlandsadresse(person.getUtenlandsAdresse())) {
            return AdresseTypePersonCode.UTAD;
        }

        if (hasTilleggsadresse(person.getTilleggsAdresse())) {
            return AdresseTypePersonCode.TIAD;
        }

        if (hasPostadresse(person.getPostAdresse())) {
            return AdresseTypePersonCode.POST;
        }

        if (hasBostedsadresse(person.getBostedsAdresse())) {
            return AdresseTypePersonCode.BOAD;
        }

        return null;
    }

    /**
     * Utility method for determining if a person has (at least) one address registered in TPS. Method added due to CR #51003.
     */
    public boolean hasRegisteredAdresse(Person person) {
        return hasBostedsadresse(person.getBostedsAdresse()) || hasPostadresse(person.getPostAdresse())
                || hasTilleggsadresse(person.getTilleggsAdresse()) || hasUtenlandsadresse(person.getUtenlandsAdresse());
    }

    /**
     * Determines if an address of type <code>BostedsAdresse</code> is set. The address is set if the first line of the
     * address is set.
     */
    public boolean hasBostedsadresse(BostedsAdresse adresse) {
        // Residential address (bostedsadresse) should always be in Norway and should therefore always have a zip code
        // (postnummer)
        return adresse != null && adresse.getPostnummer() != null && !adresse.getPostnummer().trim().isEmpty();
    }

    /**
     * Determines if a foreign address (utenlandsadresse) is set. The address is set if the county code (landkode) is set.
     */
    public boolean hasUtenlandsadresse(AnnenAdresse adresse) {
        // Foreign address (utenlandsadresse) should always have a country code (landkode)
        return adresse != null && adresse.getLandkode() != null && !adresse.getLandkode().trim().isEmpty();
    }

    /**
     * Determines if an additional address (tilleggsadresse) is set. The address is set if the zip code (postnummer) is set.
     */
    public boolean hasTilleggsadresse(AnnenAdresse adresse) {
        // Additional address is always in Norway, and should therefore always have a zip code (postnummer)
        return adresse != null && adresse.getPostnr() != null && !adresse.getPostnr().trim().isEmpty();
    }

    /**
     * Determines if a mailing address (postadresse) is set. The address is set if either the zip code is set (for mailing
     * addresses in Norway) or the country code is set (for mailing addresses abroad).
     */
    public boolean hasPostadresse(AnnenAdresse adresse) {
        // Norwegian mailing address (postadresse)
        if (adresse != null && adresse.getPostnr() != null && !adresse.getPostnr().trim().isEmpty()) {
            return true;
        }
        // Foreign mailing address (postadresse)
        return adresse != null && adresse.getLandkode() != null && !adresse.getLandkode().trim().isEmpty();
    }

    /**
     * Determines if a tilleggsadresse has changed, by comparing the three address lines and the postnummer of the old and new
     * address.
     */
    public boolean hasTilleggsadresseChanged(AnnenAdresse oldAddress, AnnenAdresse newAddress) {
        boolean line1 = isLineSame(oldAddress.getAdresselinje1(), newAddress.getAdresselinje1());
        boolean line2 = isLineSame(oldAddress.getAdresselinje2(), newAddress.getAdresselinje2());
        boolean line3 = isLineSame(oldAddress.getAdresselinje3(), newAddress.getAdresselinje3());
        boolean zip = isLineSame(oldAddress.getPostnr(), newAddress.getPostnr());
        return !line1 || !line2 || !line3 || !zip;
    }

    public boolean hasUtenlandsadresseChanged(AnnenAdresse oldAddress, AnnenAdresse newAddress) {
        boolean line1Same = isLineSame(oldAddress.getAdresselinje1(), newAddress.getAdresselinje1());
        boolean line2Same = isLineSame(oldAddress.getAdresselinje2(), newAddress.getAdresselinje2());
        boolean line3Same = isLineSame(oldAddress.getAdresselinje3(), newAddress.getAdresselinje3());
        boolean countrySame = isCountrySame(oldAddress, newAddress);
        return !line1Same || !line2Same || !line3Same || !countrySame;
    }

    private boolean isLineSame(String oldAddresselinje, String newAdresseLinje) {
        if (oldAddresselinje == null) {
            oldAddresselinje = "";
        }

        if (newAdresseLinje == null) {
            newAdresseLinje = "";
        }

        return oldAddresselinje.trim().equalsIgnoreCase(newAdresseLinje.trim());
    }

    private boolean isCountrySame(AnnenAdresse oldAddress, AnnenAdresse newAddress) {
        String oldLandkode = oldAddress.getLandkode();
        String newLandkode = newAddress.getLandkode();

        if (!isBlank(oldLandkode) && isBlank(newLandkode)) {
            return false;
        }

        if (isBlank(oldLandkode) && !isBlank(newLandkode)) {
            return false;
        }

        if (isBlank(oldLandkode) && isBlank(newLandkode)) {
            return true; // Included for completeness and null-safety
        }
        // landKode is set for both alternatives
        return oldLandkode.trim().equalsIgnoreCase(newLandkode.trim());
    }
}
