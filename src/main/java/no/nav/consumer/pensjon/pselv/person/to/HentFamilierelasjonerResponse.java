package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.person.Person;

public class HentFamilierelasjonerResponse extends ServiceResponse {

    private static final long serialVersionUID = 1420605854929194566L;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
