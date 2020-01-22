package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.common.person.Person;

public class HentEnhetIdRequest extends ServiceRequest {

    private static final long serialVersionUID = 5089257164104719240L;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
