package no.nav.presentation.pensjon.common.security;

import no.nav.domain.pensjon.common.person.Person;

public final class PensjonSecurityConstants {

    /**
     * Sperret adresse, strengt fortrolig
     */
    public static final String SPES_REG_KODE_6 = Person.DISKRESJON_SPES_REG_KODE_6;

    /**
     * Sperret adresse, fortrolig
     */
    public static final String SPES_REG_KODE_7 = Person.DISKRESJON_SPES_REG_KODE_7;

    public static final String SPES_REG_KODE_6_ROLE = "KODE_6";
    public static final String SPES_REG_KODE_7_ROLE = "KODE_7";

    /**
     * Rolename for utvidet rolle, dvs for å vise egen ansatt og inhabil sak
     */
    public static final String UTVIDET_ROLE = "UTVIDET";

    private PensjonSecurityConstants() {
    }
}
