package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum RegelverkTypeCode implements CtiConvertable<RegelverkTypeCti, RegelverkTypeCode> {
    /**
     * AP kap 19 tom 2010
     */
    G_REG(false, false),
    /**
     * AP kap. 19 og kap. 20
     */
    N_REG_G_N_OPPTJ(true, true),
    /**
     * AP kap 19 fom 2011
     */
    N_REG_G_OPPTJ(true, false),
    /**
     * AP kap. 20
     */
    N_REG_N_OPPTJ(false, true);

    private boolean kap19;
    private boolean kap20;

    RegelverkTypeCode(boolean kap19, boolean kap20) {
        this.kap19 = kap19;
        this.kap20 = kap20;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<RegelverkTypeCti> getCti() {
        return RegelverkTypeCti.class;
    }

    public boolean isKap19() {
        return kap19;
    }

    public boolean isKap20() {
        return kap20;
    }

    public boolean isAlderspensjon2011() {
        return kap19 && !kap20;
    }

    public boolean isAlderspensjon2016() {
        return kap19 && kap20;
    }

    public boolean isAlderspensjon2025() {
        return !kap19 && kap20;
    }

    public boolean isAp1967() {
        return !kap19 && !kap20;
    }
}
