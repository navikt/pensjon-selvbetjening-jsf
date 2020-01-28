package no.nav.presentation.pensjon.pselv.publisering.dinpensjon;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.error.FunctionalUnrecoverableException;
import no.stelvio.domain.person.Pid;

import no.nav.consumer.pensjon.pen.aktoerregister.AktoerRegisterServiceBi;
import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdRequest;
import no.nav.consumer.pensjon.pen.aktoerregister.to.HentAktoerIdResponse;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.common.util.EnumUtils;
import no.nav.domain.pensjon.kjerne.exception.PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering;
import no.nav.domain.pensjon.kjerne.exception.PEN222BeregningstjenesteFeiletException;
import no.nav.domain.pensjon.kjerne.exception.PEN223BrukerHarIkkeLopendeAlderspensjonException;
import no.nav.domain.pensjon.kjerne.exception.PEN224AvslagVilkarsprovingForKortTrygdetidException;
import no.nav.domain.pensjon.kjerne.exception.PEN225AvslagVilkarsprovingForLavtTidligUttakException;
import no.nav.domain.pensjon.kjerne.exception.PEN226BrukerHarLopendeAPPaGammeltRegelverkException;
import no.nav.domain.pensjon.kjerne.exception.PEN227AvslagVilkarsprovingForKortBotidException;
import no.nav.domain.pensjon.kjerne.exception.PEN228BrukerErFodtFor1943Exception;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.SimuleringTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstatusTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SivilstatusTypeCti;
import no.nav.domain.pensjon.kjerne.kodetabeller.UttaksgradCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.simulering.Pensjonsperiode;
import no.nav.domain.pensjon.kjerne.simulering.SimuleringEtter2011;
import no.nav.domain.pensjon.kjerne.simulering.SimuleringEtter2011Resultat;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.utils.RelasjonUtil;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.DinPensjonConstants;
import no.nav.service.pensjon.krav.KravServiceBi;
import no.nav.service.pensjon.sak.SakServiceBi;
import no.nav.service.pensjon.simulering.SimuleringServiceBi;
import no.nav.service.pensjon.skjema.SkjemaServiceBi;
import no.nav.service.pensjon.krav.HentKravListeRequest;
import no.nav.service.pensjon.krav.HentKravListeResponse;
import no.nav.service.pensjon.sak.HentApplikasjonsparameterRequest;
import no.nav.service.pensjon.sak.HentApplikasjonsparameterResponse;
import no.nav.service.pensjon.sak.HentSakListeRequest;
import no.nav.service.pensjon.sak.HentSakListeResponse;
import no.nav.service.pensjon.simulering.SimuleringEtter2011Request;
import no.nav.service.pensjon.simulering.SimuleringEtter2011Response;
import no.nav.service.pensjon.skjema.HentSkjemaListeRequest;
import no.nav.service.pensjon.skjema.HentSkjemaListeResponse;
import no.nav.service.pensjon.skjema.SlettSkjemaRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakResponse;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * Action delegate class for module PUS002 Din pensjon.
 */
public class DinPensjonActionDelegate {

    private static final Log LOGGER = LogFactory.getLog(DinPensjonActionDelegate.class);
    private static final String VALUE_FOR_EESSIPENSJON_SELVBETJENING = "1";
    private CodesTableManager codesTableManager;
    private SkjemaServiceBi skjemaService;
    private KravServiceBi kravService;
    private SimuleringServiceBi simuleringService;
    private SakServiceBi sakService;
    private VedtakServiceBi vedtakService;
    private PersonCommonActionDelegate personCommonActionDelegate;
    private AktoerRegisterServiceBi aktoerRegister;

    public List<Skjema> getSkjema(Pid pid) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method getSkjema(). Getting skjema for user with pid " + pid);
        }
        HentSkjemaListeRequest hentSkjemaListeRequest = new HentSkjemaListeRequest(pid);
        HentSkjemaListeResponse hentSkjemaListeResponse;

        hentSkjemaListeResponse = skjemaService.hentSkjemaListe(hentSkjemaListeRequest);
        return hentSkjemaListeResponse.getSkjemaListe();
    }

    public List<KravHode> getKrav(Pid pid) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method getKrav(). Getting krav for user with pid " + pid);
        }
        HentKravListeRequest hentKravListeRequest = new HentKravListeRequest();
        hentKravListeRequest.setPid(pid);
        hentKravListeRequest.setHentKravlinjer(true);
        HentKravListeResponse hentKravListeResponse;

        hentKravListeResponse = kravService.hentKravListe(hentKravListeRequest);
        return hentKravListeResponse.getKravListe();
    }

    public void slettSkjema(String id) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("In method slettSkjema(). Deleting skjema with id " + id);
        }
        try {
            SlettSkjemaRequest slettSkjemaRequest = new SlettSkjemaRequest(new Long(id));
            skjemaService.slettSkjema(slettSkjemaRequest);
        } catch (FunctionalUnrecoverableException e) {
            if (LOGGER.isDebugEnabled()) {
                StringBuilder builder = new StringBuilder();
                builder.append("Type exception:");
                builder.append(e.getClass());
                builder.append(".Message:");
                builder.append(e.getMessage());
                LOGGER.debug(builder.toString());
            }
            throw new ImplementationUnrecoverableException(e);
        }
    }

    /**
     * Simulates the given user's alderspensjon at the given at the given age. If the does not get 'innvilget' alderspensjon at
     * the given age, the simulation result of the first age user get 'innvilget' alderspensjon is returned.
     */
    public List<List<Pensjonsperiode>> simulateAlderspensjon(DinPensjonForm form, int[] simulationAgeList)
            throws PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering, PEN222BeregningstjenesteFeiletException,
            PEN223BrukerHarIkkeLopendeAlderspensjonException, PEN224AvslagVilkarsprovingForKortTrygdetidException,
            PEN225AvslagVilkarsprovingForLavtTidligUttakException, PEN226BrukerHarLopendeAPPaGammeltRegelverkException,
            PEN227AvslagVilkarsprovingForKortBotidException, PEN228BrukerErFodtFor1943Exception {
        List<List<Pensjonsperiode>> simulationResults = new ArrayList<>();

        for (int i = 0; i < simulationAgeList.length; i++) {
            SimuleringEtter2011Resultat simuleringResultat;
            int simulationAge = simulationAgeList[i];

            if (simulationAge < 67) {
                simuleringResultat = finnForsteMuligUttaksalderResult(form, simulationAge);
                if (simuleringResultat.getAp().getPensjonsperiodeListe().get(0).getAlder() == simulationAgeList[i + 1]) {
                    // then skip simulation of this age since this simulation is already executed.
                    i++;
                }
            } else {
                simuleringResultat = simulerFleksibelAlderspensjon(form, simulationAge);
            }
            List<Pensjonsperiode> simulationResult = simuleringResultat.getAp().getPensjonsperiodeListe();
            simulationResults.add(simulationResult);
        }

        return simulationResults;
    }

    /**
     * Finds the list of sak related to the user (pid).
     */
    public List<Sak> hentLopendeSakListe(Pid pid) {
        HentSakListeRequest hentSakListeRequest = createHentSakListeRequest(pid);
        HentSakListeResponse hentSakListeResponse = executeHentSakListe(hentSakListeRequest);
        List<Sak> filteredSakList = filterSakList(hentSakListeResponse.getSaker());
        return filteredSakList;
    }

    /**
     * Finds gjeldende alderspensjonsvedtak if exists.
     */
    public List<Vedtak> bestemGjeldendeVedtak(Pid pid) {
        BestemGjeldendeVedtakRequest request = createBestemGjeldendeVedtakRequest(pid);
        BestemGjeldendeVedtakResponse response = vedtakService.bestemGjeldendeVedtak(request);
        return response.getVedtakListe();
    }

    /**
     * Creates the request for finding active\løpende alderspensjon for given pid.
     */
    private BestemGjeldendeVedtakRequest createBestemGjeldendeVedtakRequest(Pid pid) {
        Calendar today = Calendar.getInstance();
        SakTypeCti sakTypeAlder = codesTableManager.getCodesTablePeriodic(SakTypeCti.class)
                .getCodesTableItem(SakTypeCode.ALDER);

        BestemGjeldendeVedtakRequest request = new BestemGjeldendeVedtakRequest();
        request.setPid(pid);
        request.setFomDato(today.getTime());
        request.setSakType(sakTypeAlder);
        return request;
    }

    private SimuleringEtter2011Resultat finnForsteMuligUttaksalderResult(DinPensjonForm form, int age)
            throws PEN219IkkeInnvilgetAlderspensjonVedFylte67ArVedSimulering {
        SimuleringEtter2011Request request = createFinnForsteMuligUttaksalderRequest(form, age);
        SimuleringEtter2011Response response = simuleringService.finnForsteMuligeUttaksalder(request);
        return response.getSimuleringEtter2011Resultat();
    }

    /**
     * Simulates user's 'forsteuttak av alderspensjon' at the given age by using the service TPEN620 simulerFleksibelAp.
     */
    private SimuleringEtter2011Resultat simulerFleksibelAlderspensjon(DinPensjonForm form, int age)
            throws PEN222BeregningstjenesteFeiletException, PEN223BrukerHarIkkeLopendeAlderspensjonException,
            PEN224AvslagVilkarsprovingForKortTrygdetidException, PEN225AvslagVilkarsprovingForLavtTidligUttakException,
            PEN226BrukerHarLopendeAPPaGammeltRegelverkException, PEN227AvslagVilkarsprovingForKortBotidException,
            PEN228BrukerErFodtFor1943Exception {
        SimuleringEtter2011Request request = createSimulerFleksibelAPRequest(form, age);
        SimuleringEtter2011Response response = simuleringService.simulerFleksibelAP(request);
        return response.getSimuleringEtter2011Resultat();
    }

    /**
     * Creates a request for the service TPEN623 FinnForsteMuligUttaksalder based on the given user and age.
     */
    private SimuleringEtter2011Request createFinnForsteMuligUttaksalderRequest(DinPensjonForm form, int age) {
        SimuleringEtter2011 simuleringEtter2011 = createSimuleringEtter2011(form, form.getBruker(), age);
        return new SimuleringEtter2011Request(simuleringEtter2011, false);
    }

    /**
     * Creates a request for the service TPEN620 SimulerFleksibelAlderspensjon based on the given user and the given retirement
     * age.
     */
    private SimuleringEtter2011Request createSimulerFleksibelAPRequest(DinPensjonForm form, int age) {
        SimuleringEtter2011 simuleringInput = createSimuleringEtter2011(form, form.getBruker(), age);
        return new SimuleringEtter2011Request(simuleringInput, false);
    }

    /**
     * Creates a simulation input.
     */
    // Updated via SIR222098 / CR207203
    private SimuleringEtter2011 createSimuleringEtter2011(DinPensjonForm form, Person person, int age) {
        SimuleringEtter2011 simuleringInput = new SimuleringEtter2011();
        simuleringInput.setSimuleringType(codesTableManager.getCti(SimuleringTypeCode.ALDER));
        simuleringInput.setFnr(person.getPid());

        if (form != null && form.getRightColumnFormData() != null
                && form.getRightColumnFormData().getLatestRegisteredInntekt() != null) {
            simuleringInput.setForventetInntekt(form.getRightColumnFormData().getLatestRegisteredInntekt().intValue());
        } else {
            simuleringInput.setForventetInntekt(0);
        }

        simuleringInput.setForsteUttakDato(findFirstInMonthAfterUserTurnsGivenAge(person, age));
        simuleringInput.setUtg(codesTableManager.getCti(UttaksgradCode.P_100));
        simuleringInput.setInntektEtterHeltUttak(0);
        simuleringInput.setAntallArInntektEtterHeltUttak(0);
        simuleringInput.setUtenlandsopphold(0);
        simuleringInput.setFlyktning(false);
        SivilstatusTypeCti cti = codesTableManager.getCti(EnumUtils.valueOf(SivilstatusTypeCode.class, person.getSivilstand()));
        simuleringInput.setSivilstatus(cti);
        simuleringInput.setEpsPensjon(false);
        simuleringInput.setEps2G(findEPS(person) != null);
        return simuleringInput;
    }

    /**
     * Find user's ektefelle, samboer or partner (eps).
     */
    // Added via SIR222098 / CR207203
    private Person findEPS(Person person) {
        Person eps = null;
        List<Relasjon> relasjonList;

        try {
            relasjonList = personCommonActionDelegate
                    .getRelasjoner(person.getPid(), true, false, false, Calendar.getInstance());

            if (relasjonList != null && !relasjonList.isEmpty()) {
                RelasjonUtil relasjonUtil = new RelasjonUtil();
                Relasjon relasjon = relasjonUtil.hentEpsRelasjon(relasjonList, person.getSivilstand());
                if (relasjon != null) {
                    eps = personCommonActionDelegate.hentPerson(relasjon.getPerson().getPid());
                }
            }
        } catch (PersonIkkeFunnetException e) {
            eps = null;
        }

        return eps;
    }

    /**
     * Finds the first in month after user is the given age. Example: if user is born in 20.10.1944 and age is 65, then the
     * returned date is 01.11.2009.
     */
    private Date findFirstInMonthAfterUserTurnsGivenAge(Person bruker, int simulationAge) {
        Calendar firstIntMonthAfterbrukerIsAge = Calendar.getInstance();
        firstIntMonthAfterbrukerIsAge.setTime(bruker.getPid().getFodselsdato());
        firstIntMonthAfterbrukerIsAge.add(Calendar.YEAR, simulationAge);
        firstIntMonthAfterbrukerIsAge.add(Calendar.MONTH, 1);
        firstIntMonthAfterbrukerIsAge.set(Calendar.DAY_OF_MONTH, 1);
        return firstIntMonthAfterbrukerIsAge.getTime();
    }

    /**
     * Filter the list of sak objects, and return the ones that are relevant in view
     */
    private List<Sak> filterSakList(List<Sak> saker) {
        List<Sak> filteredSakList = new ArrayList<>();

        for (Sak sak : saker) {
            if (DinPensjonConstants.RELEVANT_SAK_STATUS_CODES.contains(sak.getSakStatus().getCode())
                    && DinPensjonConstants.RELEVANT_SAK_TYPE_CODES.contains(sak.getSakType().getCode())) {
                filteredSakList.add(sak);
            }
        }
        return filteredSakList;
    }

    /**
     * Creates a request object for the service TPEN204 hentSakListe with pid as user.
     */
    private HentSakListeRequest createHentSakListeRequest(Pid pid) {
        HentSakListeRequest hentSakListeRequest = new HentSakListeRequest();
        hentSakListeRequest.setPid(pid);
        return hentSakListeRequest;
    }

    /**
     * Gets a list of sak by using the service TPEN204 hentSakListe with the hentSakListeRequest object.
     */
    private HentSakListeResponse executeHentSakListe(HentSakListeRequest hentSakListeRequest) {
        HentSakListeResponse hentSakListeResponse;
        hentSakListeResponse = sakService.hentSakListe(hentSakListeRequest);
        return hentSakListeResponse;
    }

    public boolean isEESSIPensjonSelvbetjening() {
        HentApplikasjonsparameterRequest hentApplikasjonsparameterRequest = new HentApplikasjonsparameterRequest();
        hentApplikasjonsparameterRequest.setName("EESSIPensjonSelvbetjening");
        HentApplikasjonsparameterResponse hentApplikasjonsparameterResponse = sakService.hentApplikasjonsparameter(hentApplikasjonsparameterRequest);
        return VALUE_FOR_EESSIPENSJON_SELVBETJENING.equals(hentApplikasjonsparameterResponse.getApplikasjonsparameter().getVerdi());
    }

    public String getAktoerId(Pid pid) {
        HentAktoerIdRequest request = new HentAktoerIdRequest();
        request.setPid(pid.getPid());
        HentAktoerIdResponse response = aktoerRegister.hentAktoerId(request);
        return response.getAktoerId();
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public void setKravService(KravServiceBi kravService) {
        this.kravService = kravService;
    }

    public void setSimuleringService(SimuleringServiceBi simuleringService) {
        this.simuleringService = simuleringService;
    }

    public void setSkjemaService(SkjemaServiceBi skjemaService) {
        this.skjemaService = skjemaService;
    }

    public void setSakService(SakServiceBi sakService) {
        this.sakService = sakService;
    }

    public void setVedtakService(VedtakServiceBi vedtakService) {
        this.vedtakService = vedtakService;
    }

    public void setPersonCommonActionDelegate(PersonCommonActionDelegate personCommonActionDelegate) {
        this.personCommonActionDelegate = personCommonActionDelegate;
    }

    public void setAktoerRegister(AktoerRegisterServiceBi aktoerRegister) {
        this.aktoerRegister = aktoerRegister;
    }
}
