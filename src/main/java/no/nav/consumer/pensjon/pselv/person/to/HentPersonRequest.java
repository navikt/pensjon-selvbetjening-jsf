package no.nav.consumer.pensjon.pselv.person.to;

import no.stelvio.common.transferobject.ServiceRequest;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.person.Person;

public class HentPersonRequest extends ServiceRequest {

    private static final long serialVersionUID = -1050881518845888469L;
    private Person person;
    private Boolean hentEgenAnsatt;

    public HentPersonRequest() {
        super();
    }

    public HentPersonRequest(Person person) {
        super();
        this.person = person;
    }

    public HentPersonRequest(Person person, Boolean hentEgenAnsatt) {
        this(person);
        this.hentEgenAnsatt = hentEgenAnsatt;
    }

    public HentPersonRequest(Pid pid) {
        super();
        person = new Person();
        person.setFodselsnummer(pid.getPid());
    }

    public Person getPerson() {
        return person;
    }

    public Boolean getHentEgenAnsatt() {
        return hentEgenAnsatt;
    }

    public void setHentEgenAnsatt(Boolean hentEgenAnsatt) {
        this.hentEgenAnsatt = hentEgenAnsatt;
    }

    public void setPerson(Person person) {
        this.person = person;
    }
}
