package no.nav.presentation.pensjon.pselv.common.delegate;

import java.util.Calendar;
import java.util.List;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.domain.person.Pid;

import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonerRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonerResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonsHistorikkRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonResponse;
import no.nav.consumer.pensjon.pselv.person.to.LagreSprakRequest;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.Relasjon;

/**
 * Generic ActionDelegate class for accessing Person related services. This delegate is used by modules Personopplysninger and
 * Barneopplysninger and DinPensjon.
 */
public class PersonCommonActionDelegate {

    private PersonServiceBi personService;
    private CodesTableManager codesTableManager;

    public Person hentPerson(Pid pid) throws PersonIkkeFunnetException {
        HentPersonRequest personRequest = new HentPersonRequest(pid);
        HentPersonResponse hentPersonResponse = personService.hentPerson(personRequest);
        return hentPersonResponse.getPerson();
    }

    public Person hentPerson(Pid pid, boolean refreshCache) throws PersonIkkeFunnetException {
        HentPersonRequest personRequest = new HentPersonRequest(pid);
        HentPersonResponse hentPersonResponse = personService.hentPerson(personRequest, refreshCache);
        return hentPersonResponse.getPerson();
    }

    public void lagreSprak(Person person) throws PersonIkkeFunnetException {
        personService.lagreSprak(new LagreSprakRequest(person));
    }

    /**
     * Get all relations for user.
     * PS: use hentDetaljer = true with care. This can potentially take a long time, and return a big object graph.
     */
    public List<Relasjon> getRelasjoner(Pid pid, boolean hentSamboer, boolean hentSosken, boolean hentDetaljer,
                                        Calendar relasjonerFor) throws PersonIkkeFunnetException {
        HentFamilierelasjonerRequest hentFamilierelasjonerRequest;
        HentFamilierelasjonerResponse hentFamilierelasjonerResponse;

        // get relations to user
        hentFamilierelasjonerRequest = new HentFamilierelasjonerRequest();
        hentFamilierelasjonerRequest.setFodselsnummer(pid.getPid());

        hentFamilierelasjonerRequest.setHentSamboerforhold(hentSamboer);
        hentFamilierelasjonerRequest.setHentSoskenforhold(hentSosken);
        hentFamilierelasjonerRequest.setHentDetaljer(hentDetaljer);
        hentFamilierelasjonerRequest.setHenteDato(relasjonerFor);
        // No need to set this variable, since it is never used in PSELV
        hentFamilierelasjonerRequest.setHentEgenAnsatt(false);

        hentFamilierelasjonerResponse = personService.hentFamilierelasjoner(hentFamilierelasjonerRequest);
        return hentFamilierelasjonerResponse.getPerson().getRelasjoner();
    }

    public List<Relasjon> hentFamilierelasjonsHistorikk(Pid pid, Boolean hentSamboerforhold, Calendar fom) {
        HentFamilierelasjonsHistorikkRequest request = new HentFamilierelasjonsHistorikkRequest();
        request.setFnr(pid.getPid());
        if (hentSamboerforhold != null) {
            request.setHentSamboerforhold(hentSamboerforhold);
        }
        if (fom != null) {
            request.setFom(fom);
        }
        List<Relasjon> relasjoner;
        try {
            Person person = personService.hentFamilierelasjonsHistorikk(request).getResponseObject();
            relasjoner = person.getRelasjoner();
        } catch (PersonIkkeFunnetException e) {
            throw new ImplementationUnrecoverableException(e);
        }
        return relasjoner;
    }

    public void setPersonService(PersonServiceBi personService) {
        this.personService = personService;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }
}
