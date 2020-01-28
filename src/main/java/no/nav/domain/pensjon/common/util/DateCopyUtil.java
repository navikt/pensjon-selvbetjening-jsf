package no.nav.domain.pensjon.common.util;

import java.util.Date;

public final class DateCopyUtil {

    public static Date copyDate(Date dateToCopy) {
        return dateToCopy == null ? null : new Date(dateToCopy.getTime());
    }
}
