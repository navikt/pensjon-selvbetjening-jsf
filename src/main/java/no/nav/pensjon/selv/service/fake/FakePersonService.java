package no.nav.pensjon.selv.service.fake;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.consumer.pensjon.pselv.person.to.*;
import no.nav.domain.pensjon.common.exception.person.*;
import no.nav.domain.pensjon.common.person.BostedsAdresse;
import no.nav.domain.pensjon.common.person.Person;

import java.util.ArrayList;

import static java.util.Collections.singletonList;
import static no.nav.pensjon.selv.service.fake.util.PidGenerator.generatePidAtAge;

public class FakePersonService implements PersonServiceBi {

    @Override
    public HentPersonResponse hentPerson(HentPersonRequest request) throws PersonIkkeFunnetException {
        HentPersonResponse response = new HentPersonResponse();
        response.setPerson(newPerson());
        return response;
    }

    @Override
    public HentPersonResponse hentPerson(HentPersonRequest personRequest, boolean refreshCache) throws PersonIkkeFunnetException {
        HentPersonResponse response = new HentPersonResponse();
        response.setPerson(newPerson());
        return response;
    }

    @Override
    public HentPersonListeResponse hentPersonListe(HentPersonListeRequest personListeRequest) throws PersonIkkeFunnetException {
        HentPersonListeResponse response = new HentPersonListeResponse();
        response.setPersonListe(singletonList(newPerson()));
        return response;
    }

    @Override
    public HentFamilierelasjonerResponse hentFamilierelasjoner(HentFamilierelasjonerRequest request) {
        HentFamilierelasjonerResponse response = new HentFamilierelasjonerResponse();
        Person person = new Person();
        person.setRelasjoner(new ArrayList<>());
        response.setPerson(person);
        return response;
    }

    @Override
    public OpprettSamboerforholdResponse opprettSamboerforhold(OpprettSamboerforholdRequest opprettSamboerforholdRequest) throws SamboerIkkeFunnetException, SamboerIFamilieException, AlleredeRegistrertSamboerforholdException, SamboerDodException, PersonIkkeFunnetException, SamboerValideringFeiletException {
        return new OpprettSamboerforholdResponse();
    }

    @Override
    public HentSamboerforholdResponse hentSamboerforhold(HentSamboerforholdRequest hentSamboerforholdRequest) throws PersonIkkeFunnetException {
        return new HentSamboerforholdResponse();
    }

    @Override
    public HentEnhetIdResponse hentEnhetId(HentEnhetIdRequest hentEnhetIdRequest) throws PersonIkkeFunnetException {
        return new HentEnhetIdResponse();
    }

    @Override
    public SlettSamboerforholdResponse slettSamboerForhold(SlettSamboerRequest request) throws PersonIkkeFunnetException, SamboerIkkeFunnetException {
        return new SlettSamboerforholdResponse();
    }

    @Override
    public HentFamilierelasjonsHistorikkResponse hentFamilierelasjonsHistorikk(HentFamilierelasjonsHistorikkRequest request) throws PersonIkkeFunnetException {
        return new HentFamilierelasjonsHistorikkResponse();
    }

    @Override
    public HentKontoinformasjonResponse hentKontoinformasjon(HentKontoinformasjonRequest hentKontoinformasjonRequest) throws PersonIkkeFunnetException {
        return new HentKontoinformasjonResponse();
    }

    @Override
    public LagreSprakResponse lagreSprak(LagreSprakRequest lagreSprakRequest) throws PersonIkkeFunnetException {
        return new LagreSprakResponse();
    }

    private static Person newPerson() {
        Person person = new Person();
        person.setPid(generatePidAtAge(69));
        person.setFornavn("Phor");
        person.setEtternavn("Nawn");
        person.setBostedsAdresse(newBostedsAdresse());
        person.setSivilstand("UGIFT");
        return person;
    }

    private static BostedsAdresse newBostedsAdresse() {
        BostedsAdresse adresse = new BostedsAdresse();
        adresse.setBoadresse1("Gata 12");
        adresse.setPoststed("Byen");
        adresse.setPostnummer("0999");
        return adresse;
    }
}
