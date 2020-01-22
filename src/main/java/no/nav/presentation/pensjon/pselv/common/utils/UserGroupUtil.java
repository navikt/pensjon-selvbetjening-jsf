package no.nav.presentation.pensjon.pselv.common.utils;

import no.stelvio.domain.person.Pid;

import no.nav.presentation.pensjon.pselv.common.enums.UserGroup;

import static java.util.Calendar.NOVEMBER;
import static no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants.*;
import static no.nav.presentation.pensjon.pselv.common.enums.UserGroup.*;
import static no.stelvio.common.util.DateUtil.getMonth;
import static no.stelvio.domain.person.Pid.get4DigitYearOfBirth;

public class UserGroupUtil {

    public static UserGroup findUserGroup(Pid pid) {
        int year = get4DigitYearOfBirth(pid.getPid());
        int month = getMonth(pid.getFodselsdato());

        if (year < FIRST_BIRTHYEAR_WITH_NEW_ALDER) {
            return USER_GROUP_1;
        }

        if (year <= FIRST_BIRTHYEAR_WITH_NEW_AFP) {
            return year == FIRST_BIRTHYEAR_WITH_NEW_AFP && month > NOVEMBER ? USER_GROUP_3 : USER_GROUP_2;
        }

        if (year < FIRST_BIRTHYEAR_WITH_OVERGANGSREGLER) {
            return USER_GROUP_3;
        }

        return year <= LAST_BIRTHYEAR_WITH_OVERGANGSREGLER ? USER_GROUP_4 : USER_GROUP_5;
    }
}
