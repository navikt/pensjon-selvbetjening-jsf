package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.person.Person;

public class HentPersonResponse extends ServiceResponse {

    private static final long serialVersionUID = 1440933044496292108L;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
