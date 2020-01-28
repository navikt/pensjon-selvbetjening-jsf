package no.nav.presentation.pensjon.pselv.common.utils;

/**
 * Utility class for fødselsnummer.
 */
public class FnrUtil {

    public static final String FEMALE = "F";
    public static final String MALE = "M";

    public FnrUtil() {
    }

    /**
     * Formats an 11 digit fødselsnummer to the form 'xxxxxx xxxxx'. If the
     * fødselsnummer is invalid (not 11 consecutive digits possibly containing
     * spaces) the original <code>fnr</code> is returned.
     */
    public String getFormattedFnr(String fnr) {
        String ffnr = fnr.replaceAll(" ", "");

        return ffnr.matches("^\\d{11}$")
                ? String.format("%s %s", ffnr.substring(0, 6), ffnr.substring(6))
                : fnr;
    }

    /**
     * Formats an 11 digit fødselsnummer to the form 'xxxxxx xxxxx'. If the
     * fødselsnummer is invalid (not 11 consecutive digits possibly containing
     * spaces) the original <code>fnr</code> is returned. If <code>fnr</code> is <code>null</code> an empty string is returned.
     */
    public String getFormattedFnrNullSafe(String fnr) {
        return fnr == null ? "" : getFormattedFnr(fnr);
    }

    public String getSex(String fodselsnummer) {
        String number = fodselsnummer.substring(8, 9);
        return isPartall(number) ? FEMALE : MALE;
    }

    private boolean isPartall(String number) {
        return Integer.parseInt(number) % 2 == 0;
    }

    public static String hideBirthDateFromFnr(String fnr) {
        return fnr == null ? null : "xxxxxx" + fnr.substring(6);
    }
}
