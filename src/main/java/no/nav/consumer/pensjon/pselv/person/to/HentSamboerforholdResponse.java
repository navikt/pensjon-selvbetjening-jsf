package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.person.Person;

public class HentSamboerforholdResponse extends ServiceResponse {

    private static final long serialVersionUID = 9023337957407253661L;
    private Person person;

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
