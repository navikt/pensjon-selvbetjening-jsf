package no.nav.service.pensjon.skjema;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.kjerne.sak.Sak;

/**
 * Request object for TPEN857
 */
public class SendBrevOmsorgspoengRequest extends ServiceRequest {

    private static final long serialVersionUID = 9074292591848104605L;
    private Sak sak;

    public SendBrevOmsorgspoengRequest(Sak sak) {
        this.sak = sak;
    }

    public Sak getSak() {
        return sak;
    }

    public void setSak(Sak sak) {
        this.sak = sak;
    }
}
