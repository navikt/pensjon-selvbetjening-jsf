package no.nav.presentation.pensjon.common.security.validation.enums;

import no.stelvio.common.security.validation.ValidRole;

/**
 * Enumeration representing all the valid application role names used in the Pensjon project. As this enumeration implements the
 * ValidRole interface it can be used in a RoleValidator to validate that developers use the correct roles when conducting role
 * checks. Each individual role also contains information about the scope it is used in (INTERN or EKSTERN), the context or
 * application it is used in (PSAK and PSELV) and in which version the role was introduced (REKRUTTEN, KJEMPEN or MYGGEN).
 */
public enum Roles implements ValidRole {

    VEILEDER(Version.REKRUTTEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),

    // Extended, e.g. see own employee or inhabil sak
    UTVIDET(Version.REKRUTTEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),

    KODE_6(Version.REKRUTTEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV), // Discretion code 6
    KODE_7(Version.REKRUTTEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV), // Discretion code 7
    BEGRENSET_VEILEDER(Version.REKRUTTEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    EKSTERN(Version.REKRUTTEN, RoleScope.EKSTERN, RoleContext.PSELV),
    LAV(Version.REKRUTTEN, RoleScope.EKSTERN, RoleContext.PSELV),
    MIDDELS(Version.REKRUTTEN, RoleScope.EKSTERN, RoleContext.PSELV),
    HOY(Version.REKRUTTEN, RoleScope.EKSTERN, RoleContext.PSELV),
    SAKSBEHANDLER(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    KLAGEBEHANDLER(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    BRUKERHJELPA(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    KUNDESENTER(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    NDU(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    SPESIAL(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV), // Spesialberegning

    // Allowed to add, change or delete pensjonsgivende inntekt
    SPESIAL_PGI(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),

    OKONOMI(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    UTLAND(Version.KJEMPEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    PERSONLIG(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    HAR_FULLMAKT(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    SAMHANDADM(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    FULLMAKT(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    FULLMAKT_FULLSTENDIG(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    FULLMAKT_SAMORDPLIK(Version.MYGGEN, RoleScope.EKSTERN, RoleContext.PSELV),
    UFORE_VEDTAK(Version.UFORE3, RoleScope.EKSTERN, RoleContext.PSELV),
    FULLMAKTADM(Version.KILDEN, RoleScope.INTERN, RoleContext.PSAK, RoleContext.PSELV),
    FULLMAKT_KOMMUNE(Version.KILDEN, RoleScope.INTERN, RoleContext.PSELV),
    PERSONOPPLYSNING_ENDRER(Version.KILDEN, RoleScope.INTERN, RoleContext.PSAK),
    SPESIAL_BEREGNING_RESTPENSJON_PENSJONSBEHOLDNING(Version.FYRET, RoleScope.INTERN, RoleContext.PSAK),
    ATTESTERER(Version.FYRET, RoleScope.INTERN, RoleContext.PSAK),
    NASJONAL_M_LOGG(Version.HORISONTEN, RoleScope.INTERN, RoleContext.PSAK),
    NASJONAL_U_LOGG(Version.HORISONTEN, RoleScope.INTERN, RoleContext.PSAK),
    UFORE(Version.V7_2, RoleScope.INTERN, RoleContext.PSAK),
    PESYS_CHROME(Version.V9_6, RoleScope.INTERN, RoleContext.PSAK);

    private final RoleScope scope;
    private final RoleContext[] context;
    private final Version version;

    Roles(Version version, RoleScope scope, RoleContext... context) {
        this.scope = scope;
        this.context = context;
        this.version = version;
    }

    @Override
    public String getRoleName() {
        return name();
    }

    public RoleScope getRoleScope() {
        return scope;
    }

    public RoleContext[] getRoleContext() {
        return context;
    }

    public Version getIntroducedVersion() {
        return version;
    }
}
