package no.nav.presentation.pensjon.pselv.common.enums;

/**
 * State of user concerning if he/she can apply endring alderspensjon and the choices he has made.
 */
public enum EndringAlderspensjonState {

    KAN_SOKE(true),
    KAN_IKKE_SOKE(false),
    KAN_IKKE_SOKE_PGA_VEDTAK_ALDER_STOPPET(false),
    KAN_IKKE_SOKE_PGA_ALDER_SOKNAD_INNE(false),
    BRUKER_FYLT_75(false),
    KAN_IKKE_SOKE_PGA_UFORE_SOKNAD_INNE(false),
    KAN_IKKE_SOKE_PGA_FOR_HOY_UFOREGRAD(false),
    KAN_SOKE_INKLUDER_NY_OPPTJENING(true),
    KAN_SOKE_ENDRE_UTTAKSGRAD(true),
    KAN_IKKE_SOKE_PGA_UFORE_FREM_I_TID(false),
    KAN_IKKE_SOKE_PGA_MANGLENDE_INFO_OM_EPS(false);

    private final Boolean userCanApplyEndringAlderspensjon;

    EndringAlderspensjonState(Boolean userCanApply) {
        userCanApplyEndringAlderspensjon = userCanApply;
    }

    public Boolean getUserCanApplyEndringAlderspensjon() {
        return userCanApplyEndringAlderspensjon;
    }
}
