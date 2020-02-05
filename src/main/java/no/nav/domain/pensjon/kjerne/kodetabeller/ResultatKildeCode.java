package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

/**
 * Generated class.
 */
public enum ResultatKildeCode implements CtiConvertable<ResultatKildeCti, ResultatKildeCode> {
    /**
     * Automatisk
     */
    AUTO,
    /**
     * Manuell
     */
    MAN;

    @Override
    public Class<ResultatKildeCti> getCti() {
        return ResultatKildeCti.class;
    }
}
