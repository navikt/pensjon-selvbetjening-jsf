package no.nav.presentation.pensjon.common.security.tag;

import no.stelvio.common.security.SecurityContextHolder;

import no.nav.domain.pensjon.common.person.Person;
import no.nav.presentation.pensjon.common.security.PensjonSecurityConstants;

/**
 * Common security functions related to pensjon. Created to be used by the taglib JsfSecurityLibrary, however the functions of
 * this class can be called from any java code in the presentation layer that can use <code>FacesContext</code>, such as action
 * and form classes.
 * <p>
 * This class contains security functions related to the following pensjon specific subjects:
 * </p>
 * <p>
 * <li>Diskresjon</li>
 * <li>Person er død</li>
 * <li>Person er umyndigjort</li>
 * </p>
 * <br>
 */
public abstract class CommonPensjonSecurityFunctions {

    /**
     * Indicates wheter or not the user is allowed to view the supplied person if this person has diskresjon. Returns true if
     * the person does not have diskresjon or if the person has diskresjon and the user has the supplied role. Returns false
     * otherwise.
     */
    public static boolean visDiskresjonForRoller(Person person, String rolleKode6, String rolleKode7) {
        if (person == null) {
            return false;
        }

        if (harDiskresjonsKode6(person)) {
            return isUserInRole(rolleKode6);
        }

        if (harDiskresjonsKode7(person)) {
            return isUserInRole(rolleKode7);
        }

        return true;
    }

    /**
     * Indicates wheter or not the user is allowed to view the supplied person if this person has diskresjon. Returns true if
     * the person does not have diskresjon or if the person has diskresjon and the user has the required role which is specified
     * in the <code>PensjonSecurityConstants</code> class. Returns false otherwise.
     */
    public static boolean visDiskresjon(Person person) {
        return visDiskresjonForRoller(person,
                PensjonSecurityConstants.SPES_REG_KODE_6_ROLE,
                PensjonSecurityConstants.SPES_REG_KODE_7_ROLE);
    }

    /**
     * Indicates wheter or not the supplied person has diskresjon or not.
     */
    public static boolean harDiskresjon(Person person) {
        return harDiskresjonsKode6(person) || harDiskresjonsKode7(person);
    }

    /**
     * Indicates heter or not the user is allowed to view the supplied person if this person has Kode 6. Returns true if the person does not have Kode 6 or if the person has Kode 6
     * and the user has the required role <code>SPES_REG_KODE_6_ROLE</code>
     */
    public static boolean visDiskresjonForRolle6(Person person) {
        if (harDiskresjonsKode6(person)) {
            return isUserInRole(PensjonSecurityConstants.SPES_REG_KODE_6_ROLE);
        }

        return true;
    }

    /**
     * Indicates wheter or not the supplied person has diskresjonskode 6 or not.
     */
    public static boolean harDiskresjonsKode6(Person person) {
        if (person == null) {
            return false;
        }

        return person.getDiskresjonskode() != null ? person.getDiskresjonskode().equals(PensjonSecurityConstants.SPES_REG_KODE_6) : false;
    }

    /**
     * Indicates wheter or not the supplied person has diskresjonskode 7 or not.
     */
    public static boolean harDiskresjonsKode7(Person person) {
        if (person == null) {
            return false;
        }

        return person.getDiskresjonskode() != null ? person.getDiskresjonskode().equals(PensjonSecurityConstants.SPES_REG_KODE_7) : false;
    }

    /**
     * Indicates wheter or not the person represented by the Person object is 'umyndigjort'.
     */
    public static boolean personUmyndigjort(Person person) {
        return person != null && person.getUmyndiggjort();

    }

    /**
     * Indicates wheter or not the person represented by the Person object is dead.
     */
    public static boolean personErDod(Person person) {
        return person != null && person.getErDod();
    }

    /**
     * Checks if the logged in user has the supplied role in the <code>SecurityContext</code>.
     */
    protected static boolean isUserInRole(String role) {
        return SecurityContextHolder.currentSecurityContext().isUserInRole(role);
    }
}
