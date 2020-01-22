package no.nav.consumer.pensjon.pselv.person;

import no.nav.consumer.pensjon.pselv.person.to.HentEnhetIdRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentEnhetIdResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonerRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonerResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonsHistorikkRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentFamilierelasjonsHistorikkResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentKontoinformasjonRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentKontoinformasjonResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonListeRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonListeResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonResponse;
import no.nav.consumer.pensjon.pselv.person.to.HentSamboerforholdRequest;
import no.nav.consumer.pensjon.pselv.person.to.HentSamboerforholdResponse;
import no.nav.consumer.pensjon.pselv.person.to.LagreSprakRequest;
import no.nav.consumer.pensjon.pselv.person.to.LagreSprakResponse;
import no.nav.consumer.pensjon.pselv.person.to.OpprettSamboerforholdRequest;
import no.nav.consumer.pensjon.pselv.person.to.OpprettSamboerforholdResponse;
import no.nav.consumer.pensjon.pselv.person.to.SlettSamboerRequest;
import no.nav.consumer.pensjon.pselv.person.to.SlettSamboerforholdResponse;
import no.nav.domain.pensjon.common.exception.person.AlleredeRegistrertSamboerforholdException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.exception.person.SamboerDodException;
import no.nav.domain.pensjon.common.exception.person.SamboerIFamilieException;
import no.nav.domain.pensjon.common.exception.person.SamboerIkkeFunnetException;
import no.nav.domain.pensjon.common.exception.person.SamboerValideringFeiletException;

public interface PersonServiceBi {

    HentPersonResponse hentPerson(HentPersonRequest personRequest) throws PersonIkkeFunnetException;

    HentPersonResponse hentPerson(HentPersonRequest personRequest, boolean refreshCache)
            throws PersonIkkeFunnetException;

    HentPersonListeResponse hentPersonListe(HentPersonListeRequest personListeRequest) throws PersonIkkeFunnetException;

    HentFamilierelasjonerResponse hentFamilierelasjoner(HentFamilierelasjonerRequest hentFamilierelasjonserRequest)
            throws PersonIkkeFunnetException;

    OpprettSamboerforholdResponse opprettSamboerforhold(OpprettSamboerforholdRequest opprettSamboerforholdRequest)
            throws SamboerIkkeFunnetException, SamboerIFamilieException, AlleredeRegistrertSamboerforholdException,
            SamboerDodException, PersonIkkeFunnetException, SamboerValideringFeiletException;

    HentSamboerforholdResponse hentSamboerforhold(HentSamboerforholdRequest hentSamboerforholdRequest)
            throws PersonIkkeFunnetException;

    HentEnhetIdResponse hentEnhetId(HentEnhetIdRequest hentEnhetIdRequest) throws PersonIkkeFunnetException;

    SlettSamboerforholdResponse slettSamboerForhold(SlettSamboerRequest request)
            throws PersonIkkeFunnetException, SamboerIkkeFunnetException;

    HentFamilierelasjonsHistorikkResponse hentFamilierelasjonsHistorikk(
            HentFamilierelasjonsHistorikkRequest hentFamilierelasjonsHistorikkRequest) throws PersonIkkeFunnetException;

    HentKontoinformasjonResponse hentKontoinformasjon(HentKontoinformasjonRequest hentKontoinformasjonRequest)
            throws PersonIkkeFunnetException;

    LagreSprakResponse lagreSprak(LagreSprakRequest lagreSprakRequest) throws PersonIkkeFunnetException;
}
