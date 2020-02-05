package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring;

import java.util.Date;
import java.util.EnumSet;
import java.util.List;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakStatusCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.service.pensjon.sak.HentSakListeRequest;
import no.nav.service.pensjon.sak.HentSakListeResponse;
import no.nav.service.pensjon.sak.SakServiceBi;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakRequest;
import no.nav.service.pensjon.vedtak.BestemGjeldendeVedtakResponse;
import no.nav.service.pensjon.vedtak.HentVedtakListeRequest;
import no.nav.service.pensjon.vedtak.HentVedtakListeResponse;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * The delegate for endringsalderspensjon SKS016
 *
 */
public class EndringAlderspensjonActionDelegate {

    /**
     * The sak service business interface
     */
    private SakServiceBi sakService;

    /**
     * The vedtak service business interface
     */
    private VedtakServiceBi vedtakService;

    private CodesTableManager codesTableManager;

    /**
     * Return the Sak List from the SakService
     *
     * @param pid the person id
     * @return the list of sak
     */
    public Sak getAlderspensjonSakListeLopendeAndTilBehandling(Pid pid) {
        HentSakListeRequest request = new HentSakListeRequest();
        request.setPid(pid);
        request.setSakType(codesTableManager.getCti(SakTypeCode.ALDER));
        HentSakListeResponse respons = sakService.hentSakListe(request);
        List<Sak> saker = respons.getSaker();
        filterSaker(saker);
        return saker.get(0);
    }

    /**
     * Returns the Vedtak List from the VedtakService
     *
     * @param pid the person id
     * @return the list of vedtak
     */
    public List<Vedtak> getAlleVedtak(Pid pid) {
        HentVedtakListeRequest request = createHentVedtakListeRequest(pid);
        HentVedtakListeResponse response = vedtakService.hentVedtakListe(request);
        return response.getVedtakListe();
    }

    /**
     * Returns the Vedtak List from the VedtakService from the given Date
     *
     * @param pid the person id
     * @return the list of vedtak
     */
    private HentVedtakListeRequest createHentVedtakListeRequest(Pid pid) {
        HentVedtakListeRequest request = new HentVedtakListeRequest();
        request.setPid(pid);
        request.setAlleVedtak(true);
        request.setInklBeregningInfo(true);
        request.setFomDato(new Date());
        return request;
    }

    public List<Vedtak> getLopendeVedtakAt67M(Pid pid, Date fomDato) {

        BestemGjeldendeVedtakRequest request = new BestemGjeldendeVedtakRequest();
        request.setPid(pid);
        request.setFomDato(fomDato);

        BestemGjeldendeVedtakResponse respons = vedtakService.bestemGjeldendeVedtak(request);

        return respons.getVedtakListe();
    }

    /**
     * Removes Opprettet and Avsluttet Saker from the list of Sak.
     *
     * @param saker the list of Sak
     */
    private void filterSaker(List<Sak> saker) {
        saker.removeIf(sak -> EnumSet.of(SakStatusCode.AVSLUTTET, SakStatusCode.OPPRETTET).contains(sak.getSakStatus().getCode()));
    }

    /**
     * Sets the SakService Business Interface
     *
     * @param sakService the sak service instance
     */
    public void setSakService(SakServiceBi sakService) {
        this.sakService = sakService;
    }

    /**
     * Sets the Vedtakservice Business Interface
     *
     * @param vedtakService the vedtak service instance
     */
    public void setVedtakService(VedtakServiceBi vedtakService) {
        this.vedtakService = vedtakService;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }
}
