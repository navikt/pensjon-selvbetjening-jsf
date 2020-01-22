package no.nav.consumer.pensjon.pselv.person.to;

import java.util.List;

import no.stelvio.common.transferobject.ServiceResponse;

import no.nav.domain.pensjon.common.person.Person;

public class HentPersonListeResponse extends ServiceResponse {

    private static final long serialVersionUID = -8606466198328954520L;
    private List<Person> personListe;

    public HentPersonListeResponse() {
    }

    public HentPersonListeResponse(List<Person> personListe) {
        this.personListe = personListe;
    }

    public List<Person> getPersonListe() {
        return personListe;
    }

    public void setPersonListe(List<Person> personListe) {
        this.personListe = personListe;
    }
}
