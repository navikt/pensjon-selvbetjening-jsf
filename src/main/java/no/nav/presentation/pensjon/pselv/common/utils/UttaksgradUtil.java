package no.nav.presentation.pensjon.pselv.common.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import no.stelvio.common.util.DateUtil;

import no.nav.domain.pensjon.kjerne.sak.Uttaksgrad;

public class UttaksgradUtil {

    public static Uttaksgrad getLatestUttaksgrad(List<Uttaksgrad> uttaksgradhistorikk) {
        Uttaksgrad foundUttaksgrad = null;

        for (Uttaksgrad uttaksgrad : uttaksgradhistorikk) {
            if (foundUttaksgrad == null || foundUttaksgrad.getFomDato().before(uttaksgrad.getFomDato())) {
                foundUttaksgrad = uttaksgrad;
            }
        }

        return foundUttaksgrad;
    }

    public static Uttaksgrad getLatestActiveUttaksgrad(List<Uttaksgrad> uttaksgradhistorikk) {
        Uttaksgrad foundUttaksgrad = null;

        for (Uttaksgrad uttaksgrad : uttaksgradhistorikk) {
            if (isVirkDateActive(uttaksgrad) && (foundUttaksgrad == null || foundUttaksgrad.getFomDato().before(uttaksgrad.getFomDato()))) {
                foundUttaksgrad = uttaksgrad;
            }
        }

        return foundUttaksgrad;
    }

    private static boolean isVirkDateActive(Uttaksgrad uttaksgrad) {
        Date today = Calendar.getInstance().getTime();
        return DateUtil.isBeforeByDay(uttaksgrad.getFomDato(), today, true) && DateUtil.isAfterByDay(today, uttaksgrad.getTomDato(), true);
    }
}
