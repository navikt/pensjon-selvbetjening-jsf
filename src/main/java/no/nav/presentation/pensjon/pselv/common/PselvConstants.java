package no.nav.presentation.pensjon.pselv.common;

import java.util.Date;

import no.stelvio.common.util.DateUtil;
import no.stelvio.presentation.binding.context.MessageContextUtil;

import static java.lang.Integer.parseInt;

public final class PselvConstants {

    public static final String PALOGGINGS_KODE_NIVA_LAV = "L";
    public static final String PALOGGINGS_KODE_NIVA_MIDDELS = "M";
    public static final String PALOGGINGS_KODE_NIVA_LAV_TEKST = "LAV";
    public static final String PALOGGINGS_KODE_NIVA_MIDDELS_TEKST = "MIDDELS";
    public static final int MINIMUM_ALLOWED_AGE = 18; // minimum allowed age for a user loggin in
    public static final Date DATO_NYTT_REGELVERK = DateUtil.parse("01.01.2011");
    public static final int SPERREFRIST = getIntegerPropertyValue("SPERRE_VEDTAK_FREM_I_TID");

    // Sperre for setting the ammount of months to compare last uttaksgrad with
    public static final int SPERRE_ENDR_UTTAKSGRAD = getIntegerPropertyValue("SPERRE_ENDR_UTTAKSGRAD");

    // Name of the url parameter defining which context the user is in (Din Uføretrygd or Din Pensjon)
    public static final String CONTEXT_REQ_PARAM = "context";

    // Name of the context attribute in the session defining which context the user is in (Din Uføretrygd or Din Pensjon)
    public static final String CONTEXT_SESSION_ATTR = "no.nav.presentation.pensjon.pselv.app_context";

    // URL context parameter value for Din Uføretrygd
    public static final String CONTEXT_UT = "ut";

    // URL context parameter value for Din Pensjon
    public static final String CONTEXT_PENSJON = "pensjon";

    public static final String LEFT_MENU_PENSJON_XML = "menu_pensjon.xml";
    public static final String LEFT_MENU_UT_XML = "menu_ut.xml"; // ut = uføretrygd

    // Application scope attribute name holding the left menu for Din Pensjon
    public static final String LEFT_MENU_PENSJON = "leftMenuPensjon";

    // Application-scope attribute name holding the left menu for Din Uføretrygd
    public static final String LEFT_MENU_UT = "leftMenuUT";

    private static int getIntegerPropertyValue(String key) {
        return parseInt(MessageContextUtil.getMessage(key).trim());
    }

    private PselvConstants() {
    }
}
