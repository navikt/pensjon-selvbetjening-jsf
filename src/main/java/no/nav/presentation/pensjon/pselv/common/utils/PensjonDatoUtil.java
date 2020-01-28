package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.Calendar;
import java.util.Date;

import no.stelvio.common.util.DateUtil;

import no.nav.domain.pensjon.kjerne.beregning.Beregning;

public class PensjonDatoUtil {

    /**
     * Calculate the age of a person with the given date of birth. The user is considered to be of a certain age from the first
     * of the month after the birthday month.
     */
    public static int calculateAgeAtFirstOfNextMonth(Date fodselsdato) {
        Date today = new Date();
        Date firstDayOfBirthMonth = DateUtil.getFirstDayOfMonth(fodselsdato);
        Date firstDayOfCurrentMonth = DateUtil.getFirstDayOfMonth(today);
        int[] ageInDaysMonthsYears = DateUtil.getDaysMonthsYears(firstDayOfBirthMonth, firstDayOfCurrentMonth, false);
        return ageInDaysMonthsYears[2];
    }

    /**
     * Calculates the date that is the first of the month after the user turns the given age.
     */
    public static Date firstDayOfMonthAfterUserTurnsByAge(Date fodselsdato, int age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fodselsdato);
        calendar.add(Calendar.YEAR, age);
        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return DateUtil.setTimeToZero(calendar.getTime());
    }

    /**
     * Calculates the date that is the last of the month the user turns the given age.
     */
    public static Date lastDayOfMonthUserTurnsByAge(Date fodselsdato, int age) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(fodselsdato);
        calendar.add(Calendar.YEAR, age);
        int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
        return DateUtil.setTimeToZero(calendar.getTime());
    }

    /**
     * Calculate the startmåned of the given AFP Offentlig beregning, given as an offset relative to the date of birth. The
     * month after the birthmonth = 0.
     */
    public static Integer calculateStartMnd0To11AfpOffentlig(Beregning beregning, Date fodselsdato) {
        Date virkFom = beregning.getVirkDatoFom();
        Date firstOfMonthAfterFodselsdato = DateUtil.getRelativeDateByDays(DateUtil.getFirstDayOfMonth(fodselsdato), 1);
        return DateUtil.getDaysMonthsYears(firstOfMonthAfterFodselsdato, virkFom, false)[1];
    }
}
