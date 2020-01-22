package no.nav.presentation.pensjon.pselv.common;

import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstatusTypeCode;

public final class PensjonsKalkulatorConstants {

    /**
     * Current default Pensjonsalder.
     */
    public static final int PENSJONS_AAR = 67;

    /**
     * Message resource key for "Velge ordning" AFP pension plan.
     */
    public static final String MESSAGE_RESOURCE_KEY_VELG_AFPORDNING = "pselv.standardtekst.listetekst.velg";

    /**
     * Value for select items that need a default value.
     */
    public static final String DEFAULT_SELECT_ITEM_VALUE = "default";

    /**
     * Retirement start age for "AFpensjon" pension plan.
     */
    public static final int AFP_PENSJONSALDER_START = 62;

    /**
     * Retirement start age for "AFpensjon" pension plan.
     */
    public static final int AP_PENSJONSALDER_START_NY = 62;

    /**
     * Retirement start age for "Alderspensjon" pension plan.
     */
    public static final int AP_PENSJONSALDER_START = 67;

    /**
     * Retirement age for the end of a retirement period for "AFP-pensjon" pension plan.
     */
    public static final int AFP_PENSJONSALDER_SLUTT = 66;

    /**
     * Retirement age for the end of a retirement period for "Alderspensjon" pension plan.
     */
    public static final int AP_PENSJONSALDER_SLUTT = 70;

    /**
     * Retirement min age for pension plan in graf
     */
    public static final int PENSJONSALDER_MIN = 60;

    /**
     * Retirement max age for pension plan in graf
     */
    public static final int PENSJONSALDER_MAX = 75;

    public static final int AGE_LIMIT_16_YEARS = 16;
    public static final int AGE_LIMIT_18 = 18;
    public static final int AGE_LIMIT_20 = 20;
    public static final int AGE_LIMIT_21 = 21;

    /**
     * Age limit for "ektefelle/partner/samboer" for the AFP pension plan.
     */
    public static final int AFP_EKTEFELLE_PARTNER_SAMBOER_AGE_LIMIT = 60;

    public static final long MONTHS_IN_YEAR = 12;
    public static final int DECADE = 10;
    public static final int FIVE_YEARS = 5;
    public static final int MONTHS_IN_YEAR_INT = 12;

    /**
     * Age limit for calculating "ung uførepensjon"
     */
    public static final int AGE_LIMIT_FOR_UNG_UFORE = 26;

    /**
     * Factor used to multiply with G to get full minstepensjon.
     */
    public static final double FULL_MINSTEPENSJON_FAKTOR = 1.7933;

    /**
     * Default age of EPS if the EPS is unknown
     */
    public static final int UNKNOWN_EPS_DEFAULT_AGE = 59;

    /**
     * Full degree of uføre
     */
    public static final int FULL_UFOREGRAD = 100;

    /**
     * The first year to use when considering opptjening to folketrygden
     */
    public static final int START_YEAR_FOLKETRYGDEN = 1967;

    /**
     * The amount of pensjonspoeng a year with omsorg results in
     */
    public static final double PENSJONSPOENG_FROM_OMSORG = 3.0;

    /**
     * A list of sivilstatuser where the user has an EPS
     */
    public static final String[] SIVILSTATUSER_WITH_EPS = {
            SivilstatusTypeCode.GIFT.toString(),
            SivilstatusTypeCode.REPA.toString(),
            SivilstatusTypeCode.SAMB.toString()
    };

    /**
     * The age limit for when a retired person can have an income without his/her pension being reduced.
     * Changed from 68 to 69 years in CR 71457.
     */
    public static final int LIMIT_FOR_NO_INNTEKTSFRADRAG = 69;

    /**
     * Birth year of first group who has a right to new AFP
     */
    public static final int FIRST_BIRTHYEAR_WITH_NEW_AFP = 1948;

    /**
     * Birth year of first group who has a right to new Alder
     */
    public static final int FIRST_BIRTHYEAR_WITH_NEW_ALDER = 1943;

    /**
     * Lowest retirement age for new alderspensjon
     */
    public static final int NY_AP_PENSJONSALDER_START = 62;

    /**
     * Highest retirement age for new alderspensjon
     */
    public static final int NY_AP_PENSJONSALDER_SLUTT = 75;

    /**
     * The first birth year that has "overgangsregler for opptjening"
     */
    public static final int FIRST_BIRTHYEAR_WITH_OVERGANGSREGLER = 1954;

    /**
     * The last birth year that has "overgangsregler for opptjening"
     */
    public static final int LAST_BIRTHYEAR_WITH_OVERGANGSREGLER = 1962;

    /**
     * Dagens AFP offentlig
     */
    public static final String AFP_OFFENTLIG = "AFP_OFT";

    /**
     * Dagens AFP privat
     */
    public static final String AFP_PRIVAT = "AFP_PRI";

    public static final int YEAR_1943 = 1943;
    public static final int YEAR_1944 = 1944;
    public static final int YEAR_1949 = 1949;
    public static final Integer REFORM_YEAR = 2010;
    public static final int START_YEAR_NY_ALDERSPENSJON = 2011;
    public static final String TJENESTEPENSJON = "TJENESTEPENSJON";
    public static final String ALDER_KAP_20_MED_AFP = "ALDER_KAP_20_MED_AFP";
    public static final String ALDER_KAP_20_UTEN_AFP = "ALDER_KAP_20_UTEN_AFP";

    /**
     * Ansatt type ANNET
     */
    public static final String ANNET = "ANNET";

    /**
     * Ansatt type OFFENTLIG
     */
    public static final String OFFENTLIG = "OFFENTLIG";

    /**
     * Ansatt type PRIVAT
     */
    public static final String PRIVAT = "PRIVAT";

    public static final String K_MANGELBELOP_TP_LOPENDE = "LOPENDE";
    public static final String K_MANGELBELOP_TP_SPESIELLE = "SPESIELLE";
    public static final String K_MANGELBELOP_TP_REGELVERK = "REGELVERK";
    public static final String K_MANGELBELOP_TP_PRIVAT = "PRIVAT";

    /**
     * Default pensjoneringsmåned
     */
    public static final Integer NY_AP_PENSJONSMANED_START = 0;

    private PensjonsKalkulatorConstants() {
    }
}
