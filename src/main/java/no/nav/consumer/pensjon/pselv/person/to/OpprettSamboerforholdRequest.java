package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.common.person.Person;

public class OpprettSamboerforholdRequest extends ServiceRequest {

    private static final long serialVersionUID = 1975266426563963075L;
    private Person person;

    public OpprettSamboerforholdRequest() {
        super();
    }

    public OpprettSamboerforholdRequest(Person person) {
        super();
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
