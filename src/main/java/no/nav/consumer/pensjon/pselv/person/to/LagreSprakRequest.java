package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.common.person.Person;

public class LagreSprakRequest extends ServiceRequest {

    private static final long serialVersionUID = 2807710512466157775L;

    private Person person;

    public LagreSprakRequest(Person person) {
        super();
        this.person = person;
    }

    public Person getPerson() {
        return person;
    }
}
