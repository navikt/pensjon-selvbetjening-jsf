package no.nav.domain.pensjon.kjerne.opptjening;

import no.nav.domain.pensjon.common.ServiceDirective;

/**
 * Service Directive implementation for TPOPP006 - HentBeholdningsListe
 */
public class ServiceDirectiveTPOPP006 extends ServiceDirective {

    private static final long serialVersionUID = -2731166914089709765L;

    /**
     * Only Beholdning object is returned
     */
    public static final String ENTITY_ONLY = "ENTITY_ONLY";

    /**
     * Beholdning and OpptjeningBelop is returned
     */
    public static final String INKL_OPPTJENINGBELOP = "INKL_OPPTJENINGBELOP";

    /**
     * Beholdning, OpptjeningBeløp Grunnlag is returned
     */
    public static final String INKL_GRUNNLAG = "INKL_GRUNNLAG";

    public ServiceDirectiveTPOPP006(String fetchSetTPOPP006) {
        super(fetchSetTPOPP006);
    }

    public ServiceDirectiveTPOPP006() {
        super();
    }
}
