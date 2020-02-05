package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import java.util.Calendar;
import java.util.EnumSet;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.common.error.FunctionalRecoverableException;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Relasjon;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.sak.SakSammendrag;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.delegate.PersonCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring.EndringAlderspensjonActionDelegate;
import no.nav.service.pensjon.sak.HentSakListeRequest;
import no.nav.service.pensjon.sak.HentSakListeResponse;
import no.nav.service.pensjon.sak.HentSakSammendragListeRequest;
import no.nav.service.pensjon.sak.SakServiceBi;
import no.nav.service.pensjon.vedtak.HentVedtakListeRequest;
import no.nav.service.pensjon.vedtak.HentVedtakListeResponse;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * Handles service call used by framework endring alderspensjon.
 */
public class SkjemaEndringAlderspensjonActionDelegate {

    private static final Log LOGGER = LogFactory.getLog(EndringAlderspensjonActionDelegate.class);
    private SakServiceBi sakService;
    private VedtakServiceBi vedtakService;
    private PersonCommonActionDelegate personCommonActionDelegate;

    public List<Sak> getLopendeAndTilBehandlingSaker() {
        HentSakListeRequest request = new HentSakListeRequest();
        request.setPid(AbstractSessionMapWrapper.getBrukerPid());
        HentSakListeResponse respons = sakService.hentSakListe(request);
        List<Sak> saker = respons.getSaker();

        if (LOGGER.isDebugEnabled()) {
            logToDebug("Gets user's list of saker, and the size is ", saker.size());
        }

        // Remove Saker with status Avsluttet and Opprettet (not relevant here).
        filterSaker(saker);

        if (LOGGER.isDebugEnabled()) {
            logToDebug("List of saker after filtering out Avsluttet and Opprettet Saker: ", respons.getSaker().size());
        }

        return saker;
    }

    public List<SakSammendrag> getSakSammendragListe(Pid fnr) {
        List<SakSammendrag> sammendragList;
        HentSakSammendragListeRequest request = new HentSakSammendragListeRequest(fnr);

        try {
            sammendragList = sakService.hentSakSammendragListe(request).getSakSammendragListe();
        } catch (FunctionalRecoverableException e) {
            throw new ImplementationUnrecoverableException(e);
        }

        return sammendragList;
    }

    private void filterSaker(List<Sak> saker) {
        for (ListIterator<Sak> iterator = saker.listIterator(); iterator.hasNext(); ) {
            Sak sak = iterator.next();

            if (EnumSet.of(SakStatusCode.AVSLUTTET, SakStatusCode.OPPRETTET).contains(sak.getSakStatus().getCode())) {
                iterator.remove();

                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("Removes sak with status " + sak.getSakStatus().getDecode());
                }
            }
        }
    }

    public List<Vedtak> getVedtakListe() {
        HentVedtakListeRequest request = new HentVedtakListeRequest();
        request.setPid(AbstractSessionMapWrapper.getBrukerPid());
        request.setAlleVedtak(true);
        request.setInklBeregningInfo(true);
        request.setFomDato(Calendar.getInstance().getTime());
        HentVedtakListeResponse respons = vedtakService.hentVedtakListe(request);

        if (LOGGER.isDebugEnabled()) {
            logToDebug("Gets user's list of vedtak, and the size is ", respons.getVedtakListe().size());
        }

        return respons.getVedtakListe();
    }

    public List<Relasjon> getEpsRelasjoner(Pid pid) {
        try {
            return personCommonActionDelegate.getRelasjoner(pid, true, false, false, Calendar.getInstance());
        } catch (PersonIkkeFunnetException e) {
            throw new ImplementationUnrecoverableException(e);
        }
    }

    public SakServiceBi getSakService() {
        return sakService;
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

    private void logToDebug(String message, int integer) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(message);
        buffer.append(integer);
        LOGGER.debug(buffer);
    }
}
