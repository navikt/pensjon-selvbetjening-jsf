package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;

import no.nav.domain.pensjon.common.person.Person;

public class HentKontoinformasjonRequest extends ServiceRequest {

    private static final long serialVersionUID = -9157757375612811696L;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
