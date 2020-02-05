package no.nav.presentation.pensjon.pselv.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.context.i18n.LocaleContextHolder;

public class DateFormatUtil {

    public DateFormatUtil() {
    }

    /**
     * Returns the specified <code>Date</code> formatted according to <code>format</code> using the given current <code>Locale</code>. A new locale is made based on the country
     * code of the old one, as SimpleDateFormat can't handle the
     * nb_NO locale properly.
     */
    public String getFormattedDate(Date date, String format) {
        Locale currentLocale = LocaleContextHolder.getLocale();
        Locale locale = new Locale(currentLocale.getCountry(), currentLocale.getCountry());
        return new SimpleDateFormat(format, locale).format(date);
    }

    /**
     * Returns the specified <code>Date</code> formatted according to <code>format</code> using the current <code>Locale</code>. If <code>date</code>, <code>format</code> or
     * <code>locale</code> is <code>null</code> an
     * empty string is returned.
     */
    public String getFormattedDateNullSafe(Date date, String format) {
        return date == null || format == null ? "" : getFormattedDate(date, format);
    }

    /**
     * Returns the specified <code>Date</code> object formatted according to <code>format</code> using the current <code>Locale</code>. If <code>capitalizeAll</code> is set to
     * <code>true</code>, all space-separated substrings of
     * the formatted date will be capitalized. If <code>capitalizeAll</code> is set to <code>false</code> only the first
     * character of the formatted date will be capitalized.
     */
    public String getCapitalizedFormattedDate(Date date, String format, boolean capitalizeAll) {
        StringBuffer buffer = new StringBuffer();
        String formattedDate = getFormattedDate(date, format);

        if (capitalizeAll) {
            for (String token : formattedDate.split(" ")) {
                buffer.append(token.substring(0, 1).toUpperCase());
                buffer.append(token.substring(1));
                buffer.append(" ");
            }
        } else {
            buffer.append(formattedDate.substring(0, 1).toUpperCase());
            buffer.append(formattedDate.substring(1));
        }

        return buffer.toString().trim();
    }
}
