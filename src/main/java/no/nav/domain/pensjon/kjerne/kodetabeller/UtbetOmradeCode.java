package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum UtbetOmradeCode implements CtiConvertable<UtbetOmradeCti, UtbetOmradeCode> {
    /**
     * Enslig fors&oslash;rger - Overgangsst&oslash;nad
     */
    E1,
    /**
     * Familiepleier Virkemidler
     */
    FM,
    /**
     * Familiepleier Virkemidler
     */
    FV,
    /**
     * Gjenlevendepensjon Virkemidler
     */
    GV,
    /**
     * Avtalefestet pensjon
     */
    M3,
    /**
     * Pensjonstrygden for sj&oslash;menn
     */
    M4,
    /**
     * Kommunal tilleggspensjon
     */
    M5,
    /**
     * Statens pensjonskasse
     */
    M6,
    /**
     * N&oslash;dutbetaling
     */
    M7,
    /**
     * Andre utbetalinger
     */
    M8,
    /**
     * Alderspensjon
     */
    MA,
    /**
     * Barnepensjon
     */
    MB,
    /**
     * Diverse utbetalinger
     */
    MD,
    /**
     * Tidligere familiepleierpensjon
     */
    MF,
    /**
     * Gjenlevendepensjon
     */
    MG,
    /**
     * Krigspensjon
     */
    MK,
    /**
     * Uf&oslash;repensjon
     */
    MU,
    /**
     * Gammel yrkesskadepensjon
     */
    MY,
    /**
     * Enslig fors&oslash;rgerpensjon
     */
    P0,
    /**
     * Gjenlevendepensjon yrkesskade
     */
    P1,
    /**
     * Gammel yrkesskadepensjon
     */
    P2,
    /**
     * Avtalefestet pensjon
     */
    P3,
    /**
     * Pensjonstrygden for sj&oslash;menn
     */
    P4,
    /**
     * Kommunal tilleggspensjon
     */
    P5,
    /**
     * Statens pensjonskasse
     */
    P6,
    /**
     * AFP Privat
     */
    P7,
    /**
     * Alderspensjon
     */
    PA,
    /**
     * Barnepensjon
     */
    PB,
    /**
     * Pensjon
     */
    PE,
    /**
     * Tidligere familiepleierpensjon
     */
    PF,
    /**
     * Gjenlevendepensjon
     */
    PG,
    /**
     * Krigspensjon
     */
    PK,
    /**
     * Uf&oslash;repensjon
     */
    PU,
    /**
     * Uf&oslash;repensjon yrkesskade
     */
    PY,
    /**
     * Manuell postering Uf&oslash;retrygd
     */
    UM,
    /**
     * Uf&oslash;retrygd
     */
    UT,
    /**
     * Gjenlevendepensjon Virkemidler
     */
    VM,
    /**
     * Uf&oslash;repensjon fra SPK
     */
    U3,
    /**
     * Uf&oslash;repensjon fra SPK, manuell postering
     */
    U4,
    /**
     * Ny overgangsstønad
     */
    EN,
    /**
     * Ny overgangsstønad, manuell postering
     */
    E5,
    /**
     * Nødutbetaling
     */
    UN;

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<UtbetOmradeCti> getCti() {
        return UtbetOmradeCti.class;
    }
}
