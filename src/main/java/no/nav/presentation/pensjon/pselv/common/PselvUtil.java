package no.nav.presentation.pensjon.pselv.common;

import no.stelvio.presentation.binding.context.MessageContextUtil;

public final class PselvUtil {

    public static int fetchSperreEndringAvUttaksgrad() {
        return Integer.parseInt(MessageContextUtil.getMessage(PselvConstants.SPERRE_ENDR_UTTAKSGRAD).trim());
    }

    public static int fetchSperreVedtakFremITid() {
        return Integer.parseInt(MessageContextUtil.getMessage(PselvConstants.SPERREFRIST).trim());
    }

    private PselvUtil() {
    }
}
