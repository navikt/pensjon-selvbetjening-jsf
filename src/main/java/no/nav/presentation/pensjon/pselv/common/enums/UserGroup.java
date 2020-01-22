package no.nav.presentation.pensjon.pselv.common.enums;

public enum UserGroup {
    USER_GROUP_1(true),
    USER_GROUP_2(false),
    USER_GROUP_3(false),
    USER_GROUP_4(false),
    USER_GROUP_5(false);

    private final Boolean useDagensAlderspensjon;

    UserGroup(Boolean useDagensAlderspensjon) {
        this.useDagensAlderspensjon = useDagensAlderspensjon;
    }

    public Boolean getUseDagensAlderspensjon() {
        return useDagensAlderspensjon;
    }

    public Boolean getUseNyAlderspensjon() {
        return !useDagensAlderspensjon;
    }
}
