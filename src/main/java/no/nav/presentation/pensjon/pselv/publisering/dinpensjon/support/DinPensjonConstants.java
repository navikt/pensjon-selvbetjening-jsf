package no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support;

import java.util.EnumSet;
import java.util.Set;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;

/**
 * Class with constants for module PUS002 Din pensjon.
 *
 */
public final class DinPensjonConstants {

    /**
     * Private constructor to prevent the class from being instantiated.
     */
    private DinPensjonConstants() {
    }

    // Byggekloss A

    /**
     * Id of byggekloss A
     */
    public static final char ID_BYGGEKLOSS_A = 'A';

    /**
     * Action of byggekloss A
     */
    public static final String ACTION_BYGGEKLOSS_A = "goToSaksoversikt";

    /**
     * Key (in resources.properties) to alt text of byggekloss A
     */
    public static final String ALT_KEY_BYGGEKLOSS_A = "pselv.pus002.alt_tekst.saksoversikt_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss A
     */
    public static final String TITLE_KEY_BYGGEKLOSS_A = "pselv.pus002.tooltip.dinpensjonvissak_title";

    // Byggekloss B

    /**
     * Id of byggekloss B
     */
    public static final char ID_BYGGEKLOSS_B = 'B';

    /**
     * Action of byggekloss B
     */
    public static final String ACTION_BYGGEKLOSS_B = "goToDineUtbetalinger";

    /**
     * Key (in resources.properties) to alt text of byggekloss B
     */
    public static final String ALT_KEY_BYGGEKLOSS_B = "pselv.pus002.alt_tekst.utbetalinger_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss B
     */
    public static final String TITLE_KEY_BYGGEKLOSS_B = "pselv.pus002.tooltip.vismineutbetalinger_title";

    // Byggekloss C

    /**
     * Id of byggekloss C
     */
    public static final char ID_BYGGEKLOSS_C = 'C';

    /**
     * Action of byggekloss C
     */
    public static final String ACTION_BYGGEKLOSS_C = "goToDinInnboks";

    /**
     * Key (in resources.properties) to alt text of byggekloss C
     */
    public static final String ALT_KEY_BYGGEKLOSS_C = "pselv.pus002.alt_tekst.dininnboks_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss C
     */
    public static final String TITLE_KEY_BYGGEKLOSS_C = "pselv.pus002.tooltip.dininnboks_title";

    // Byggekloss D

    /**
     * Id of byggekloss D
     */
    public static final char ID_BYGGEKLOSS_D = 'D';

    /**
     * Action of byggekloss D
     */
    public static final String ACTION_BYGGEKLOSS_D = "goToPensjonskalkulator";

    /**
     * Key (in resources.properties) to alt text of byggekloss D
     */
    public static final String ALT_KEY_BYGGEKLOSS_D = "pselv.pus002.alt_tekst.pensjonskalkulator_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss D
     */
    public static final String TITLE_KEY_BYGGEKLOSS_D = "pselv.pus002.tooltip.pensjonskalkulator_title";

    // Byggekloss E

    /**
     * Id of byggekloss E
     */
    public static final char ID_BYGGEKLOSS_E = 'E';

    /**
     * Action of byggekloss E
     */
    public static final String ACTION_BYGGEKLOSS_E = "goToSoknad";

    /**
     * Key (in resources.properties) to alt text of byggekloss E
     */
    public static final String ALT_KEY_BYGGEKLOSS_E = "pselv.pus002.alt_tekst.sok_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss E
     */
    public static final String TITLE_KEY_BYGGEKLOSS_E = "pselv.pus002.tooltip.sok_title";

    // Byggekloss F

    /**
     * Id of byggekloss F
     */
    public static final char ID_BYGGEKLOSS_F = 'F';

    /**
     * Action of byggekloss F
     */
    public static final String ACTION_BYGGEKLOSS_F = "goToSkjemaoversikt";

    /**
     * Key (in resources.properties) to alt text of byggekloss F
     */
    public static final String ALT_KEY_BYGGEKLOSS_F = "pselv.pus002.alt_tekst.skjemaoversikt_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss F
     */
    public static final String TITLE_KEY_BYGGEKLOSS_F = "pselv.pus002.tooltip.dinpensjonflereskjema_title";

    // Byggekloss G

    /**
     * Id of byggekloss G
     */
    public static final char ID_BYGGEKLOSS_G = 'G';

    /**
     * Action of byggekloss G
     */
    public static final String ACTION_BYGGEKLOSS_G = "goToOpptjening";

    /**
     * Key (in resources.properties) to alt text of byggekloss G
     */
    public static final String ALT_KEY_BYGGEKLOSS_G = "pselv.pus002.alt_tekst.pensjonsopptjening_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss G
     */
    public static final String TITLE_KEY_BYGGEKLOSS_G = "pselv.pus002.tooltip.pensjonsopptjening_title";

    // Byggekloss H

    /**
     * Id of byggekloss H
     */
    public static final char ID_BYGGEKLOSS_H = 'H';

    /**
     * Action of byggekloss A
     */
    public static final String ACTION_BYGGEKLOSS_H = "goToEndrePensjon";

    /**
     * Key (in resources.properties) to alt text of byggekloss A
     */
    public static final String ALT_KEY_BYGGEKLOSS_H = "pselv.pus002.alt_tekst.endreuttak_alt";

    /**
     * Key (in resources.properties) to tooltip text of byggekloss A
     */
    public static final String TITLE_KEY_BYGGEKLOSS_H = "pselv.pus002.tooltip.endreuttak_title";

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 10
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_10 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_E};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 20
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_20 = new char[] {ID_BYGGEKLOSS_B, ID_BYGGEKLOSS_A, ID_BYGGEKLOSS_C};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 30
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_30 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_E};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 40
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_40 = new char[] {ID_BYGGEKLOSS_B, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_E};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 50
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_50 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_E, ID_BYGGEKLOSS_B};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 60
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_60 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_F};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 70
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_70 = new char[] {ID_BYGGEKLOSS_B, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_A};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 80
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_80 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_G, ID_BYGGEKLOSS_B};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 90
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_90 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_H, ID_BYGGEKLOSS_B};

    /**
     * List of ids of byggekloss elements to show if the user is in usergroup 91
     */
    public static final char[] BYGGEKLOSS_LIST_USERGROUP_91 = new char[] {ID_BYGGEKLOSS_A, ID_BYGGEKLOSS_B, ID_BYGGEKLOSS_H};

    /**
     * List of ids of byggekloss elements to show if the view is in variant 2
     */
    public static final char[] BYGGEKLOSS_LIST_VARIANT_2 = new char[] {ID_BYGGEKLOSS_D, ID_BYGGEKLOSS_G};

    // Generic variables representing age (at retirement)
    /**
     * Age 62
     */
    public static final int PENSJONALDER_62 = 62;

    /**
     * Age 63
     */
    public static final int PENSJONALDER_63 = 63;

    /**
     * Age 66
     */
    public static final int PENSJONALDER_66 = 66;

    /**
     * Age 67
     */
    public static final int PENSJONALDER_67 = 67;

    /**
     * Age 68
     */
    public static final int PENSJONALDER_68 = 68;

    /**
     * Age 69
     */
    public static final int PENSJONALDER_69 = 69;

    /**
     * Age 70
     */
    public static final int PENSJONALDER_70 = 70;

    /**
     * Age 75
     */
    public static final int PENSJONALDER_74 = 74;

    /**
     * Age 75
     */
    public static final int PENSJONALDER_75 = 75;

    /**
     * The label used by "rett til AFP"-radiobutton, option 'Nei'
     */
    public static final String AFP_RADIO_NEI = "pselv.pus002.statisk_tekst.nei";

    /**
     * The label used by "rett til AFP"-radiobutton, option 'Ja'
     */
    public static final String AFP_RADIO_JA = "pselv.pus002.statisk_tekst.ja";

    // Variables used by action delegate class

    /**
     * Default value for utenlandsopphold
     */
    public static final int UTENLANDSOPPHOLD_DEFAULT_VALUE = 0;
    /**
     * The number of months to get utbetalinger back in time
     */
    public static final int NUMBER_OF_MONTHS = 4;

    /**
     * The retirement degree if no pension
     */
    public static final double NO_PENSJON = 0.0;

    /**
     * The retirement degree if full pension
     */
    public static final double FULL_PENSJON = 1.0;

    /**
     * Set of SakTypeCodes that are relevant in view. If the user has a sak that is not one of these types, he/she is treated
     * like users without a sak
     */
    public static final Set<SakTypeCode> RELEVANT_SAK_TYPE_CODES =
            EnumSet.of(SakTypeCode.AFP, SakTypeCode.ALDER, SakTypeCode.BARNEP, SakTypeCode.FAM_PL, SakTypeCode.GJENLEV,
                    SakTypeCode.UFOREP);

    /**
     * Set of SakStatusCodes that are relevant in view. If the user has a sak that doesn't have any of these states, he/she is
     * treated like users without a sak
     */
    public static final Set<SakStatusCode> RELEVANT_SAK_STATUS_CODES = EnumSet.of(SakStatusCode.TIL_BEHANDLING,
            SakStatusCode.LOPENDE);

    // Variables used by action class

    /**
     * The name of the skjema id value on flow
     */
    public static final String SKJEMAID = "skjemaId";

    /**
     * The name of the skjema type value on flow
     */
    public static final String SKJEMATYPE = "skjemaType";

    /**
     * Key to error message in resources.properties that should be displayed if both retrieval of skjema and krav failed
     */
    public static final String IKKE_SAK_OG_SKJEMA_ERROR_KEY = "pselv.pus002.avviksmelding.kanikkeviseskjemasaker";

    // Variables used by form populator class

    /**
     * The default number of rows in one page of the sortable lists
     */
    public static final int NUMBER_OF_ROWS = 10;

    /**
     * The default sort column in the sortable lists
     */
    public static final String DEFAULT_SORT_COLUMN = "navn";

    /**
     * Legend label, used in graph
     */
    public static final String GRAF_FORKLARING_FULLPENSJON = "pselv.pus002.statisk_tekst.grafforklaringfullvalg";

    /**
     * The value to add to graph for years with no pension
     */
    public static final Integer ZERO_PENSJON = 0;

    /**
     * The default text to display in view if the user doesn't have a pensjonsbeholdning
     */
    public static final String INGEN_PENSJONSBEHOLDNING = "0";

    /**
     * The first year in graph
     */
    public static final Integer GRAPH_START_YEAR = 60;

    /**
     * The last year in graph
     */
    public static final Integer GRAPH_END_YEAR = 75;

    /**
     * The interval between years in graph
     */
    public static final Integer INTERVAL_POSITION_FACTOR = 1;

    /**
     * The help key if the view is in variant 2
     */
    public static final String HELP_KEY_VARIANT_2 = "pselv.pus002.helppopup.hjelpbrukavvariant2";

    /**
     * The help key if the view is in variant 1 and the user is in user group
     */
    public static final String HELP_KEY = "pselv.pus002.helppopup.hjelpbrukav";

    /**
     * The help key if the view is in variant 1 and the user is in user group
     */
    public static final String HELP_KEY_NYTT = "pselv.pus002.helppopup.hjelpbrukavnytt";

    /**
     * The help key if the view is in variant 1 and the user is in user group
     */
    public static final String HELP_KEY_LOPENDE = "pselv.pus002.helppopup.hjelpbrukavlopende";
}
