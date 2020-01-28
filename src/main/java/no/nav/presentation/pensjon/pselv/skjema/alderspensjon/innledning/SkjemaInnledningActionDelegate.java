package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.domain.person.Pid;

import no.nav.consumer.pensjon.pselv.tjenestepensjon.TjenestepensjonServiceBi;
import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdRequest;
import no.nav.consumer.pensjon.pselv.tjenestepensjon.to.FinnTjenestepensjonForholdResponse;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.exception.tjenestepensjon.ElementetFinnesIkkeException;
import no.nav.domain.pensjon.common.exception.tjenestepensjon.TomDatoForanFomDatoException;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.common.tjenestepensjon.TjenestepensjonForhold;
import no.nav.domain.pensjon.common.tjenestepensjon.TjenestepensjonsYtelse;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.delegate.SamhandlerCommonActionDelegate;
import no.nav.service.pensjon.sak.SakServiceBi;
import no.nav.service.pensjon.skjema.SkjemaServiceBi;
import no.nav.service.pensjon.sak.HentSakListeRequest;
import no.nav.service.pensjon.sak.HentSakListeResponse;
import no.nav.service.pensjon.skjema.HentSkjemaListeRequest;
import no.nav.service.pensjon.skjema.HentSkjemaListeResponse;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakResponse;
import no.nav.service.pensjon.vedtak.HentVedtakListeRequest;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * Action Delegate for SkjemaInnledningAction
 */
public class SkjemaInnledningActionDelegate {

    private SamhandlerCommonActionDelegate samhandlerCommonActionDelegate;
    private SakServiceBi sakService;
    private VedtakServiceBi vedtakService;
    private SkjemaServiceBi skjemaService;
    private TjenestepensjonServiceBi tjenestepensjonService;
    private PersonCommonActionDelegate personCommonActionDelegate;
    private final Log log = LogFactory.getLog(SkjemaInnledningAction.class);

    public List<Sak> hentSakListe(Pid pid) {
        HentSakListeRequest request = new HentSakListeRequest();
        request.setPid(pid);
        HentSakListeResponse response = sakService.hentSakListe(request);
        return response.getSaker();
    }

    public List<Vedtak> hentVedtakListe(Pid pid, Long sakId, Date fomDato, Date tomDato, boolean alleVedtak) {
        HentVedtakListeRequest request = createHentVedtakListeRequest(pid, sakId, fomDato, tomDato, alleVedtak);

        try {
            return vedtakService.hentVedtakListe(request).getVedtakListe();
        } catch (Exception e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    /**
     * Finds a user's lopende tjenestepensjonForholds not from Norsk Pensjon. Returns an emoty list if the user does not have
     * any tjenestepensjonsforhold. A tjenestepensjonsforhold is lopende if today's date is within the tjenestepensjonsforhold's
     * tom and fom. Return an empty list if user does not have any tjenestepensjonForhold.
     */
    public List<TjenestepensjonForhold> finnTjenestepensjonsforhold(String fnr)
            throws ElementetFinnesIkkeException, TomDatoForanFomDatoException {
        List<TjenestepensjonForhold> tjenestepensjonForholdList;
        FinnTjenestepensjonForholdRequest request = createTjenestepensjonsForholdRequest(fnr);

        FinnTjenestepensjonForholdResponse response = tjenestepensjonService.finnTjenestepensjonForhold(request);
        if (response.getResponseObject().getTjenestepensjonForholdene() == null) {
            tjenestepensjonForholdList = new ArrayList<TjenestepensjonForhold>();
            if (log.isDebugEnabled()) {
                log.debug("List of tjenestepensjonsforhold is null");
            }
        } else {
            tjenestepensjonForholdList = filterTjenestepensjonsforhold(response.getResponseObject()
                    .getTjenestepensjonForholdene());
            if (log.isDebugEnabled()) {
                log.debug("List of tjenestepensjonsforhold is not null");
            }
        }

        return tjenestepensjonForholdList;
    }

    /**
     * Filters out tjenestepensjonsForhold from Norsk Pensjon.
     */
    private List<TjenestepensjonForhold> filterTjenestepensjonsforhold(List<TjenestepensjonForhold> tjenestepensjonForholdList) {
        List<TjenestepensjonForhold> tjenestepensjonForholdNotFromNorskPensjon = new ArrayList<TjenestepensjonForhold>();
        String tssEksternIdNorskPensjon = samhandlerCommonActionDelegate.getNorskPensjonTssEksternID();
        for (TjenestepensjonForhold forhold : tjenestepensjonForholdList) {
            if (!forhold.getTssEksternId().equals(tssEksternIdNorskPensjon) && hasTjenestepensjonforholdLopendeYtelse(forhold)) {
                tjenestepensjonForholdNotFromNorskPensjon.add(forhold);
            }
        }
        return tjenestepensjonForholdNotFromNorskPensjon;
    }

    /**
     * Check if the given tjenestepensjonforhold has an lopende ytelse. A tjenestepensjonforhold can have one or more ytelser.
     */
    private boolean hasTjenestepensjonforholdLopendeYtelse(TjenestepensjonForhold tjenestepensjonForhold) {
        if (tjenestepensjonForhold.getTjenestepensjonYtelseListe() == null) {
            return false;
        }

        for (TjenestepensjonsYtelse tjenestepensjonYtelse : tjenestepensjonForhold.getTjenestepensjonYtelseListe()) {
            if (isTjenestepensjonYtelseLopende(tjenestepensjonYtelse)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Check if the given ytelse of a tjenestepensjonforhold is lopende. An tjenestepensjonytelse is lopende if the current date
     * is within it's fom and tom date.
     */
    private boolean isTjenestepensjonYtelseLopende(TjenestepensjonsYtelse ytelse) {
        Calendar today = Calendar.getInstance();
        return ytelse.getIverksattFom().before(today)
                && (ytelse.getIverksattTom() == null || ytelse.getIverksattTom().after(today));
    }

    private HentVedtakListeRequest createHentVedtakListeRequest(Pid pid, Long sakId, Date fomDato, Date tomDato, boolean alleVedtak) {
        HentVedtakListeRequest request = new HentVedtakListeRequest();
        request.setPid(pid);
        request.setSakId(sakId);
        request.setFomDato(fomDato);
        request.setTomDate(tomDato);
        request.setAlleVedtak(alleVedtak);
        return request;
    }

    protected List<Skjema> hentSkjemaListe(Pid pid) {
        HentSkjemaListeRequest request = new HentSkjemaListeRequest(pid);
        HentSkjemaListeResponse response = skjemaService.hentSkjemaListe(request);
        return response == null ? null : response.getSkjemaListe();
    }

    /**
     * Create a request for the service hentTjenestepensjonForhold. The request asks for tpnavn and tpNr from TSS.
     */
    private FinnTjenestepensjonForholdRequest createTjenestepensjonsForholdRequest(String fnr) {
        FinnTjenestepensjonForholdRequest request = new FinnTjenestepensjonForholdRequest();
        request.setFnr(fnr);
        // this have to be true in order to get tpNavn and tpNr from tss.
        request.setHentSamhandlerInfo(true);
        return request;
    }

    public List<Relasjon> getEpsRelasjoner(Pid pid) {
        try {
            return personCommonActionDelegate.getRelasjoner(pid, true, false, false, Calendar.getInstance());
        } catch (PersonIkkeFunnetException e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    public void setSakService(SakServiceBi sakService) {
        this.sakService = sakService;
    }

    public void setVedtakService(VedtakServiceBi vedtakService) {
        this.vedtakService = vedtakService;
    }

    public void setTjenestepensjonService(TjenestepensjonServiceBi tjenestepensjonService) {
        this.tjenestepensjonService = tjenestepensjonService;
    }

    public void setSamhandlerCommonActionDelegate(SamhandlerCommonActionDelegate samhandlerCommonActionDelegate) {
        this.samhandlerCommonActionDelegate = samhandlerCommonActionDelegate;
    }

    public void setSkjemaService(SkjemaServiceBi skjemaService) {
        this.skjemaService = skjemaService;
    }

    public void setPersonCommonActionDelegate(PersonCommonActionDelegate personCommonActionDelegate) {
        this.personCommonActionDelegate = personCommonActionDelegate;
    }

    public List<Vedtak> getLopendeVedtakListeAt67M(Pid pid, Date fomDato) {
        BestemGjeldendeVedtakRequest request = new BestemGjeldendeVedtakRequest();
        request.setPid(pid);

        if (fomDato != null) {
            request.setFomDato(fomDato);
        }

        BestemGjeldendeVedtakResponse response = vedtakService.bestemGjeldendeVedtak(request);
        return response.getVedtakListe();
    }
}
