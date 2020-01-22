package no.nav.pensjon.selv.service.fake.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import no.stelvio.domain.person.Pid;

import static java.lang.Integer.parseInt;
import static no.stelvio.common.util.DateUtil.*;
import static no.stelvio.domain.person.Pid.isValidPid;

public final class PidGenerator {

    public static Pid generatePidAtAge(int age) {
        return generatePid(createDate(currentYear() - age, Calendar.JANUARY, 1));
    }

    public static Pid generatePidAtAgeRelativeToDate(int age, Date date) {
        int year = getYear(date);
        return generatePid(createDate(year - age, Calendar.JANUARY, 1));
    }

    public static Pid generatePidAtAge(int age, int month, int day) {
        return generatePid(createDate(currentYear() - age, month, day));
    }

    public static Pid generatePid(Date date) {
        return generatePid(
                format(date),
                isAfterByDay(createDate(2000, Calendar.JANUARY, 1), date, false));
    }

    /**
     * D-number documentation: https://www.skatteetaten.no/person/utenlandsk/norsk-identitetsnummer/d-nummer/
     */
    public static Pid generatePidAsDNummer(Date date) {
        String dateAsString = format(date);
        dateAsString = (parseInt(dateAsString.substring(0, 1)) + 4) + dateAsString.substring(1);

        return generatePid(
                dateAsString,
                isAfterByDay(createDate(2000, Calendar.JANUARY, 1), date, false));
    }

    private static Pid generatePid(String date, boolean isBornBefore2000) {
        Random random = new Random();
        int start = isBornBefore2000 ? 0 : 900;
        int bound = isBornBefore2000 ? 500 : 100;

        for (int index = 0; index < 100; index++) {
            int number = random.nextInt(bound) + start;
            String pidCandidate = date + getIndividnummer(number);
            pidCandidate = pidCandidate + getK1K2(pidCandidate);

            if (isValidPid(pidCandidate)) {
                return new Pid(pidCandidate);
            }
        }

        throw new RuntimeException("Could not generate a valid PID for " + date);
    }

    private static String getIndividnummer(int individnummer) {
        StringBuilder builder = new StringBuilder();

        if (individnummer < 10) {
            builder.append("00");
        } else if (individnummer < 100) {
            builder.append("0");
        }

        builder.append(individnummer);
        return builder.toString();
    }

    /**
     * Finds the two last control numbers, as explained at matematikk.org
     */
    private static String getK1K2(String fnr) {
        // Format: DDMMYYiiikk
        int d1 = parseInt(fnr.substring(0, 1));
        int d2 = parseInt(fnr.substring(1, 2));
        int m1 = parseInt(fnr.substring(2, 3));
        int m2 = parseInt(fnr.substring(3, 4));
        int a1 = parseInt(fnr.substring(4, 5));
        int a2 = parseInt(fnr.substring(5, 6));
        int i1 = parseInt(fnr.substring(6, 7));
        int i2 = parseInt(fnr.substring(7, 8));
        int i3 = parseInt(fnr.substring(8, 9));

        // control 1
        int v1 = 3 * d1 + 7 * d2 + 6 * m1 + m2 + 8 * a1 + 9 * a2 + 4 * i1 + 5 * i2 + 2 * i3;
        int tmp = v1 / 11;
        int rest1 = v1 - tmp * 11;
        int k1 = rest1 == 0 ? 0 : 11 - rest1;

        // control 2
        int v2 = 5 * d1 + 4 * d2 + 3 * m1 + 2 * m2 + 7 * a1 + 6 * a2 + 5 * i1 + 4 * i2 + 3 * i3 + 2 * k1;
        tmp = v2 / 11;
        int rest2 = v2 - tmp * 11;
        int k2 = rest2 == 0 ? 0 : 11 - rest2;

        return k1 + "" + k2;
    }

    private static int currentYear() {
        return getYear(new Date());
    }

    private static String format(Date date) {
        return new SimpleDateFormat("ddMMyy").format(date);
    }
}
