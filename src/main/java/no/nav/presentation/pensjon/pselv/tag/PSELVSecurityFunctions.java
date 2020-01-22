package no.nav.presentation.pensjon.pselv.tag;

import no.nav.presentation.pensjon.common.security.validation.enums.Roles;
import no.stelvio.common.security.SecurityContext;
import no.stelvio.common.security.definition.Role;

import static no.stelvio.common.security.SecurityContextHolder.currentSecurityContext;

public final class PSELVSecurityFunctions {

    private PSELVSecurityFunctions() {
    }

    public static boolean isUserInLav() {
        return isUserInRoles(Roles.LAV, Roles.MIDDELS, Roles.VEILEDER);
    }

    public static boolean isUserInMedium() {
        return isUserInRoles(Roles.MIDDELS, Roles.VEILEDER, Roles.SAKSBEHANDLER);
    }

    public static boolean isUserInHoy() {
        return isUserInRoles(Roles.HOY);
    }

    public static boolean isReadOnlyBrukerhjelpa() {
        return isUserInRoles(Roles.BRUKERHJELPA);
    }

    public static boolean isSaksbehandler() {
        return isUserInRoles(Roles.SAKSBEHANDLER);
    }

    public static boolean isVeileder() {
        return isUserInRoles(Roles.VEILEDER);
    }

    public static boolean isFullmaktSamordplik() {
        return isUserInRoles(Roles.FULLMAKT_SAMORDPLIK);
    }

    public static boolean isFullmakt() {
        return isUserInRoles(Roles.FULLMAKT);
    }

    public static boolean isFullmaktFullstendig() {
        return isUserInRoles(Roles.FULLMAKT_FULLSTENDIG);
    }

    public static boolean isSamhandAdm() {
        return isUserInRoles(Roles.SAMHANDADM);
    }

    public static boolean isHarFullmakt() {
        return isUserInRoles(Roles.HAR_FULLMAKT);
    }

    public static boolean isPersonlig() {
        return isUserInRoles(Roles.PERSONLIG);
    }

    public static boolean isWorkingOnBehalfOf() {
        SecurityContext context = currentSecurityContext();
        return !context.getAutorizedAs().equals(context.getUserId());
    }

    public static boolean isReadOnly() {
        return isUserInRoles(
                Roles.BRUKERHJELPA,
                Roles.VEILEDER,
                Roles.SAKSBEHANDLER,
                Roles.OKONOMI);
    }

    public static boolean isInternBruker() {
        return isUserInRoles(
                Roles.BRUKERHJELPA,
                Roles.VEILEDER,
                Roles.SAKSBEHANDLER,
                Roles.OKONOMI,
                Roles.FULLMAKT_SAMORDPLIK,
                Roles.NDU);
    }

    public static boolean isUforeVedtak() {
        return isUserInRoles(Roles.UFORE_VEDTAK);
    }

    public static boolean isNotUforeVedtak() {
        return !isUforeVedtak();
    }

    public static boolean isUserSamordningspliktigFullmakt() {
        return isUserInRole(Roles.FULLMAKT_SAMORDPLIK);
    }

    public static boolean isUserKommuneFullmakt() {
        return isUserInRole(Roles.FULLMAKT_KOMMUNE);
    }

    private static boolean isUserInRole(Role role) {
        return currentSecurityContext().isUserInRole(role);
    }

    private static boolean isUserInRoles(Role... roles) {
        return currentSecurityContext().isUserInRoles(roles);
    }
}
