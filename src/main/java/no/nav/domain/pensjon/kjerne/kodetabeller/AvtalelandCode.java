package no.nav.domain.pensjon.kjerne.kodetabeller;

import no.stelvio.common.codestable.support.CtiConvertable;

public enum AvtalelandCode implements CtiConvertable<AvtalelandCti, AvtalelandCode> {
    /**
     * Australia
     */
    AUS,
    /**
     * &Oslash;sterrike
     */
    AUT,
    /**
     * Belgia
     */
    BEL,
    /**
     * Bulgaria
     */
    BGR,
    /**
     * Canada
     */
    CAN,
    /**
     * Sveits
     */
    CHE,
    /**
     * Chile
     */
    CHL,
    /**
     * Kypros
     */
    CYP,
    /**
     * Den Tsjekkiske Rep.
     */
    CZE,
    /**
     * Tyskland
     */
    DEU,
    /**
     * Danmark
     */
    DNK,
    /**
     * Spania
     */
    ESP,
    /**
     * Estland
     */
    EST,
    /**
     * Finland
     */
    FIN,
    /**
     * Frankrike
     */
    FRA,
    /**
     * F&aelig;r&oslash;yene
     */
    FRO,
    /**
     * Storbritannia
     */
    GBR,
    /**
     * Frankrike Guadeloupe
     */
    GLP,
    /**
     * Hellas
     */
    GRC,
    /**
     * Gr&oslash;nland
     */
    GRL,
    /**
     * Frankrike Fransk Guyana
     */
    GUF,
    /**
     * PK-13715
     * Kroatia
     */
    HRV,
    /**
     * Ungarn
     */
    HUN,
    /**
     * Storbritannia Isle Of Man
     */
    IMN,
    /**
     * India
     */
    IND,
    /**
     * Irland
     */
    IRL,
    /**
     * Island
     */
    ISL,
    /**
     * Israel
     */
    ISR,
    /**
     * Italia
     */
    ITA,
    /**
     * Storbritannia Jersey
     */
    JEY,
    /**
     * S&oslash;r-Korea
     */
    KOR,
    /**
     * Liechtenstein
     */
    LIE,
    /**
     * Litauen
     */
    LTU,
    /**
     * Luxembourg
     */
    LUX,
    /**
     * Latvia
     */
    LVA,
    /**
     * Malta
     */
    MLT,
    /**
     * Frankrike Martinique
     */
    MTQ,
    /**
     * Nederland
     */
    NLD,
    /**
     * Norge
     */
    NOR,
    /**
     * Polen
     */
    POL,
    /**
     * Portugal
     */
    PRT,
    /**
     * Quebec
     */
    QEB,
    /**
     * Frankrike Reunion
     */
    REU,
    /**
     * Romania
     */
    ROU,
    /**
     * Slovakia
     */
    SVK,
    /**
     * Slovenia
     */
    SVN,
    /**
     * Sverige
     */
    SWE,
    /**
     * USA
     */
    USA;

    @Override
    public Class<AvtalelandCti> getCti() {
        return AvtalelandCti.class;
    }
}
