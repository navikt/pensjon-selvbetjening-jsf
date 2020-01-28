package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.joda.time.DateTime;

import no.stelvio.domain.person.Pid;

import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;

public final class PersonUtil {
    //TODO make methods static
    private static int age70 = 70;
    private static int age75 = 75;

    /**
     * Calculates the exact age based upon the birth date and chosen retirement date.
     */
    public int calculateAge(Date fodselsdato, Date compareDate) {
        /* Persons Date Of Birth (DOB) */
        GregorianCalendar calDOB = new GregorianCalendar();
        calDOB.setTime(fodselsdato);

        /* Date to compare DOB with */
        GregorianCalendar calCompareDate = new GregorianCalendar();
        calCompareDate.setTime(compareDate);

        /* Calculate temporary age */
        int age = calCompareDate.get(Calendar.YEAR) - calDOB.get(Calendar.YEAR);

        int compareMonth = calCompareDate.get(Calendar.MONTH) + 1;
        int compareDay = calCompareDate.get(Calendar.DAY_OF_MONTH);
        int birthMonth = calDOB.get(Calendar.MONTH) + 1;
        int birthDay = calDOB.get(Calendar.DAY_OF_MONTH);

        /* Has the persons month and day of birth passed. */
        if (birthMonth > compareMonth || birthMonth == compareMonth && birthDay > compareDay) {
            /* day of birth not yet passed */
            age--;
        }

        return age < 0 ? 0 : age;
    }

    /**
     * Checks whether the user is under 21 year.
     */
    public boolean isUserUnder21Year() {
        DateTime birthDate = new DateTime(PselvSessionMapWrapper.getBrukerPid().getFodselsdato());
        return birthDate.plusYears(21).isAfter(DateTime.now());
    }

    /**
     * Checks if the month of birth is more than 70 years ago. Only year and month counts.
     * Example: <li>If today is 2010-02-10 and the user is born 1940-02-01, the method will return false. <li>If today is 2010-02-10 and the user is born 1940-01-31, the method
     * will return true.
     */
    public boolean isDateOneMonthOrMoreAfter70thBirthmonth(Pid userPid, Calendar now) {
        return isDateOneMonthOrMoreAfterXthBirthmonth(userPid, now, age70);
    }

    /**
     * Checks if the month of birth is more than 75 years ago. Only year and month counts.
     * Example: <li>If today is 2015-02-10 and the user is born 1940-02-01, the method will return false. <li>If today is 2010-02-10 and the user is born 1940-01-31, the method
     * will return true.
     */
    public boolean isDateOneMonthOrMoreAfter75thBirthmonth(Pid userPid, Calendar now) {
        return isDateOneMonthOrMoreAfterXthBirthmonth(userPid, now, age75);
    }

    /**
     * Checks if the month of birth is more than 70 years ago. Only year and month counts.
     */
    private boolean isDateOneMonthOrMoreAfterXthBirthmonth(Pid userPid, Calendar now, int age) {
        // Set the birthdate + 70years + next month.
        Calendar dateLimit = Calendar.getInstance();
        dateLimit.setTime(userPid.getFodselsdato());
        dateLimit.add(Calendar.YEAR, age);
        dateLimit.add(Calendar.MONTH, 1);
        dateLimit.getTime();

        return dateLimit.get(Calendar.YEAR) == now.get(Calendar.YEAR)
                && dateLimit.get(Calendar.MONTH) <= now.get(Calendar.MONTH)
                || dateLimit.get(Calendar.YEAR) < now.get(Calendar.YEAR);
        /* true dersom: år er samme og md samme eller større. || åre er større */
    }
}
