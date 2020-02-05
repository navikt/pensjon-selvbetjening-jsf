package no.nav.domain.pensjon.annotations;

public final class IgnoreOnCopyReason {

    private IgnoreOnCopyReason() {
    }

    public static final String PRIMARY_KEY = "Primary key";

    public static final String INVERSE_REFERENCE = "Inverse reference";

    public static final String MAPPING_REFERENCE = "Mapping reference";
}
