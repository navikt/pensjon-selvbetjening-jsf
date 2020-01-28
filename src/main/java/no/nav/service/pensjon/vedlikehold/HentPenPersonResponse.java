package no.nav.service.pensjon.vedlikehold;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.kjerne.PenPerson;

/**
 * Response object for TPEN825 VedlikeholdServiceBI.hentPenPerson
 *
 */
public class HentPenPersonResponse extends ServiceResponse {
    private static final long serialVersionUID = 335687768576876884L;

    private PenPerson penPerson;

    /**
     * Default constructor
     */
    public HentPenPersonResponse() {
    }

    /**
     * @param penPerson the penPerson to return
     */
    public HentPenPersonResponse(PenPerson penPerson) {
        this.penPerson = penPerson;
    }

    /**
     * @return PenPerson
     */
    public PenPerson getPenPerson() {
        return penPerson;
    }

    /**
     * @param penPerson the penPerson to return
     */
    public void setPenPerson(PenPerson penPerson) {
        this.penPerson = penPerson;
    }
}
