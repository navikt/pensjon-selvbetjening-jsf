package no.nav.repository.pensjon.batch.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import no.stelvio.common.config.InvalidPropertyException;

/**
 * DateProvider is used to get todays date. The provider can be configured to return a mock date - which is useful when unit testing.
 */
public class DateProvider {

    private static final String LOCALE_NORWAY = "nb"; // ISO 639 language code
    private static final String TIME_ZONE_EUROPE_OSLO = "Europe/Oslo";
    private static final String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
    private static Provider provider = getNormalProvider();

    /**
     * Configures a DateProvider. Can be called from unit test classes.
     *
     * @param mockDate the date as a String formated as 'yyyy-MM-dd'.
     */
    public static void configure(final String mockDate) {
        configure(parseDate(mockDate));
    }

    public static void configure(final DateTime mockDate) {
        configure(mockDate.toDate());
    }

    public static void configure(final Date mockDate) {
        provider = getMockProvider(mockDate);
    }

    private static Date parseDate(final String mockDate) {
        try {
            DateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_PATTERN);
            return dateFormat.parse(mockDate);
        } catch (ParseException e) {
            throw new InvalidPropertyException("Property could not be parsed.", new String[]{"mockDate"}, e);
        }
    }

    public static void reset() {
        provider = getNormalProvider();
    }

    /**
     * Gets todays date. Real date by default, or mocked date if configured.
     */
    public static Date getToday() {
        return provider.getToday();
    }

    /**
     * Gets todays date as a <code>Calendar</code>. Real date by default, or mocked date if configured.
     */
    public static Calendar getTodayAsCalendar() {
        return provider.getTodayAsCalendar();
    }

    /**
     * Gets todays date as a {@link org.joda.time.DateTime}. Real date by default, or mocked if configured.
     */
    public static DateTime getTodayAsDateTime() {
        return provider.getTodayAsDateTime();
    }

    /**
     * Time for Norway based on the Gregorian calendar.
     */
    private static Provider getNormalProvider() {
        return new Provider() {
            @Override
            public Date getToday() {
                return new GregorianCalendar(new Locale(LOCALE_NORWAY)).getTime();
            }

            @Override
            public Calendar getTodayAsCalendar() {
                return new GregorianCalendar(new Locale(LOCALE_NORWAY));
            }

            @Override
            public DateTime getTodayAsDateTime() {
                return DateTime.now(DateTimeZone.forID(TIME_ZONE_EUROPE_OSLO));
            }
        };
    }

    private static Provider getMockProvider(final Date mockDate) {
        return new Provider() {
            @Override
            public Date getToday() {
                return mockDate;
            }

            @Override
            public Calendar getTodayAsCalendar() {
                Calendar cal = new GregorianCalendar(new Locale(LOCALE_NORWAY));
                cal.setTime(mockDate);
                return cal;
            }

            @Override
            public DateTime getTodayAsDateTime() {
                return new DateTime(mockDate);
            }
        };
    }

    interface Provider {

        Date getToday();

        Calendar getTodayAsCalendar();

        DateTime getTodayAsDateTime();
    }
}
