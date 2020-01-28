package no.nav.presentation.pensjon.pselv.common.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.springframework.context.i18n.LocaleContextHolder;

public class NumberFormatUtil {

    /**
     * Pattern to format number with correct grouping. E.g. integer 100000 will be formatted as "100 000".
     */
    private static final String GROUPING_PATTERN = "##,###";
    private static final char GROUPING_SEPARATOR = ' ';
    private static final char DECIMAL_SEPARATOR = ',';

    public NumberFormatUtil() {
    }

    /**
     * Formats a number to a Norwegian integer amount, using white space as thousand separator character and correct grouping.
     * Decimal numbers will be rounded. E.g. integer value 100000 will be formatted as "100 000", and double value 9399.99 will
     * be formatted as "9 400".
     */
    public static String formatAsIntegerAmount(Number number) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(GROUPING_SEPARATOR);
        DecimalFormat format = new DecimalFormat(GROUPING_PATTERN, symbols);
        return format.format(number);
    }

    public static String formatAsThreeDigitsDecimal(Number number) {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols(LocaleContextHolder.getLocale());
        decimalFormatSymbols.setDecimalSeparator(DECIMAL_SEPARATOR);
        decimalFormatSymbols.setGroupingSeparator(GROUPING_SEPARATOR);
        DecimalFormat decimalFormat = new DecimalFormat(GROUPING_PATTERN, decimalFormatSymbols);
        decimalFormat.setMinimumFractionDigits(3);
        decimalFormat.setMaximumFractionDigits(3);
        decimalFormat.setMinimumIntegerDigits(1);
        return decimalFormat.format(number);
    }

    /**
     * Formats a number to a Norwegian integer amount, using white space as thousand separator character and correct grouping.
     * Decimal numbers will be rounded. E.g. integer value 100000 will be formatted as "100 000", and double value 9399.99 will
     * be formatted as "9 400". If <code>number</code> is <code>null</code> the literal "0" is returned.
     */
    public static String formatAsIntegerAmountNullSafe(Number number) {
        return number == null ? "0" : formatAsIntegerAmount(number);
    }
}
