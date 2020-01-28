package no.nav.presentation.pensjon.pselv.common;

import static org.apache.commons.collections.CollectionUtils.isNotEmpty;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.codestable.CodesTablePeriodicItem;
import no.stelvio.common.codestable.support.AbstractCodesTablePeriodicItem;
import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.consumer.pensjon.pen.organisasjonenhet.OrganisasjonEnhetServiceBi;
import no.nav.consumer.pensjon.pen.organisasjonenhet.exception.v2.FinnNAVKontorUgyldigInputException;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorRequest;
import no.nav.consumer.pensjon.pen.organisasjonenhet.to.v2.FinnNAVKontorResponse;
import no.nav.consumer.pensjon.pen.person3.to.HentGeografiskTilknytningRequest;
import no.nav.consumer.pensjon.pen.person3.to.HentGeografiskTilknytningResponse;
import no.nav.consumer.pensjon.pselv.brukerprofil.BrukerprofilServiceBi;
import no.nav.consumer.pensjon.pselv.person.PersonServiceBi;
import no.nav.consumer.pensjon.pselv.person.to.HentPersonRequest;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.UgyldigPeriodeException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.organisasjonsenhet.Organisasjonsenhet;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.grunnlag.SatsResultat;
import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;
import no.nav.service.pensjon.grunnlag.GrunnlagServiceBi;
import no.nav.service.pensjon.grunnlag.HentGrunnbelopListeRequest;
import no.nav.service.pensjon.grunnlag.HentGrunnbelopListeResponse;
//import no.nav.tjeneste.virksomhet.person.v3.HentGeografiskTilknytningPersonIkkeFunnet; //TODO Fix JAX-WS dependencies
//import no.nav.tjeneste.virksomhet.person.v3.HentGeografiskTilknytningSikkerhetsbegrensing;

/**
 * CommonActionDelegate is a collection of convenience methods used by CommonAction and thus the Mal.
 */
public class CommonActionDelegate {

    private static final Log LOGGER = LogFactory.getLog(CommonActionDelegate.class);
    private PersonServiceBi personService;
    private GrunnlagServiceBi grunnlagService;
    private BrukerprofilServiceBi brukerprofilService;
    private OrganisasjonEnhetServiceBi organisasjonEnhetService;
    private no.nav.consumer.pensjon.pen.person3.PersonServiceBi personService3;
    private CodesTableManager codesTableManager;

    public Double hentCurrentGrunnbelop() {
        return hentGrunnbelop(new Date());
    }

    public Double hentGrunnbelop(Date fromYearDate) {
        Double currentGrunnbelop = null;
        Date fromYearday = DateUtil.setTimeToZero(fromYearDate);
        HentGrunnbelopListeRequest request = new HentGrunnbelopListeRequest(fromYearday, fromYearday);
        HentGrunnbelopListeResponse response = null;
        try {
            response = grunnlagService.hentGrunnbelopListe(request);
        } catch (UgyldigPeriodeException e) {
            throw new ImplementationUnrecoverableException(e);
        }

        if (response == null) {
            if (LOGGER.isDebugEnabled()) {
                StringBuffer msg = new StringBuffer();
                msg.append("HentGrunnbelopListeResponse is null todays date ");
                msg.append(fromYearday);
                msg.append(". Using GrunnlagServiceBi implementation ");
                msg.append(grunnlagService.getClass().getSimpleName());
                LOGGER.debug(msg);
            }
            return null;
        }
        List<SatsResultat> satsResultatListe = response.getSatsResultatList();
        if (isNotEmpty(satsResultatListe)) {
            currentGrunnbelop = satsResultatListe.get(0).getVerdi();
        }
        return currentGrunnbelop;
    }

    public Person getPersonByPid(Pid pid) throws PersonIkkeFunnetException {
        return personService.hentPerson(new HentPersonRequest(pid)).getPerson();
    }

    public Person getPersonByPid(Pid pid, boolean refreshCache) throws PersonIkkeFunnetException {
        return personService.hentPerson(new HentPersonRequest(pid), refreshCache).getPerson();
    }

    public Person getPersonBySessionPid() throws PersonIkkeFunnetException {
        Pid pid = AbstractSessionMapWrapper.getBrukerPid();
        return getPersonByPid(pid);
    }

    public Person getPersonBySessionPid(boolean refreshCache) throws PersonIkkeFunnetException {
        Pid pid = AbstractSessionMapWrapper.getBrukerPid();
        return getPersonByPid(pid, refreshCache);
    }

    @SuppressWarnings("unchecked")
    public String getDecodePeriodic(Class<? extends AbstractCodesTablePeriodicItem> codesTableItemClass, Enum code) {
        return (String) codesTableManager.getCodesTablePeriodic(codesTableItemClass).getCodesTableItem(code).getDecode();
    }

    @SuppressWarnings("unchecked")
    public String getDecodePeriodic(Class<? extends AbstractCodesTablePeriodicItem> codesTableItemClass, String code) {
        return (String) codesTableManager.getCodesTablePeriodic(codesTableItemClass).getCodesTableItem(code).getDecode();
    }

    /**
     * This method provides an easy way to decode a collection of CTI values.
     */
    public <K extends Enum<K>, C extends CodesTablePeriodicItem<K, String>> List<String> decodeCtiCollection(Class<C> ctiClass,
                                                                                                             Collection<K> ctiCollection) {
        List<String> decodedList = new ArrayList<>();
        for (K code : ctiCollection) {
            decodedList.add(getDecodePeriodic(ctiClass, code));
        }
        return decodedList;
    }

    public void setPersonService(PersonServiceBi personService) {
        this.personService = personService;
    }

    protected PersonServiceBi getPersonService() {
        return this.personService;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public void setGrunnlagService(GrunnlagServiceBi grunnlagService) {
        this.grunnlagService = grunnlagService;
    }

    public BrukerprofilServiceBi getBrukerprofilService() {
        return brukerprofilService;
    }

    public void setBrukerprofilService(BrukerprofilServiceBi brukerprofilService) {
        this.brukerprofilService = brukerprofilService;
    }

    public void setOrganisasjonEnhetService(OrganisasjonEnhetServiceBi organisasjonEnhetService) {
        this.organisasjonEnhetService = organisasjonEnhetService;
    }

    public void setPersonService3(no.nav.consumer.pensjon.pen.person3.PersonServiceBi personService3) {
        this.personService3 = personService3;
    }

    public CodesTableManager getCodesTableManager() {
        return codesTableManager;
    }

    public Organisasjonsenhet finnNavKontorForBruker(Pid pid) {
        HentGeografiskTilknytningResponse hentGeografiskTilknytningResponse = getGeografiskTilknytning(pid);
        String geografiskTilknytning = hentGeografiskTilknytningResponse.getGeografiskTilknytning();
        String diskresjonskoder = hentGeografiskTilknytningResponse.getDiskresjonskoder();
        FinnNAVKontorResponse finnNAVKontorResponse = findNAVKontor(geografiskTilknytning, diskresjonskoder);
        return finnNAVKontorResponse.getOrganisasjonsenhet();
    }

    private FinnNAVKontorResponse findNAVKontor(String geografiskTilknytning, String diskresjonskoder) {
        FinnNAVKontorRequest finnNAVKontorRequest = new FinnNAVKontorRequest(geografiskTilknytning, diskresjonskoder);
        FinnNAVKontorResponse finnNAVKontorResponse;

        try {
            finnNAVKontorResponse = organisasjonEnhetService.finnNAVKontor(finnNAVKontorRequest);
        } catch (FinnNAVKontorUgyldigInputException finnNAVKontorUgyldigInput) {
            throw new ImplementationUnrecoverableException(finnNAVKontorUgyldigInput);
        }
        return finnNAVKontorResponse;
    }

    private HentGeografiskTilknytningResponse getGeografiskTilknytning(Pid pid) {
        HentGeografiskTilknytningRequest hentGeografiskTilknytningRequest = new HentGeografiskTilknytningRequest(pid);
        HentGeografiskTilknytningResponse hentGeografiskTilknytningResponse;

//        try {
//            hentGeografiskTilknytningResponse = personService3.hentGeografiskTilknytning(hentGeografiskTilknytningRequest);
//        } catch (HentGeografiskTilknytningSikkerhetsbegrensing | HentGeografiskTilknytningPersonIkkeFunnet e) {
//            throw new ImplementationUnrecoverableException(e);
//        } //TODO Restore
        hentGeografiskTilknytningResponse = personService3.hentGeografiskTilknytning(hentGeografiskTilknytningRequest);

        return hentGeografiskTilknytningResponse;
    }
}
