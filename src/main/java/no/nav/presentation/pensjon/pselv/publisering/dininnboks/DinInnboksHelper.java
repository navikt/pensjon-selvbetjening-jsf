package no.nav.presentation.pensjon.pselv.publisering.dininnboks;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.error.FunctionalUnrecoverableException;
import no.stelvio.consumer.exception.ConsumerSystemException;
import no.stelvio.domain.person.Pid;

//import no.nav.domain.pensjon.common.exception.GenericConsumerException;
//import no.nav.domain.pensjon.common.henvendelse.Henvendelse;
//import no.nav.domain.pensjon.common.journal.Journalpost;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;
//import no.nav.presentation.pensjon.pselv.common.utils.JournalpostUtil;
import no.nav.presentation.pensjon.pselv.publisering.korrespondanse.DinKorrespondanse;
import no.nav.presentation.pensjon.pselv.publisering.korrespondanse.DinKorrespondanseCommonActionDelegate;
//import no.nav.presentation.pensjon.pselv.publisering.korrespondanse.DinKorrespondanseFormData;

/**
 * Utility class for fetching "antall uleste" brev i PUS004 Din Innboks. Exposes this logic to other flows when updating the
 * "antall uleste" in leftmenu. Potential "users" might be "Din Pensjon" or "InnloggingsAction". Calling the method
 * setAntallUlesteToSession() will set or update the "antall uleste" count in leftmenu.
 *
 */
public class DinInnboksHelper {

//    /**
//     * This class' logger
//     */
    private static final Log LOGGER = LogFactory.getLog(DinInnboksHelper.class);
//
//    /**
//     * The CommonActionDelegate that performs call towards PEN and BUS for PUS004 and PUS009
//     */
    private DinKorrespondanseCommonActionDelegate korrespondanseDelegate;
//
//    /**
//     * The DinKorrespondanse class which calculates the korrespondanseListe
//     */
    private DinKorrespondanse dinKorrespondanse;

    private CodesTableManager codesTableManager;
//
//    /**
//     * Finds the number of unread elements of a korrespondanseListe and sets them to the session attribute ANTALL_ULESTE
//     *
//     * @param korrespondanseListe the korrespondanseListe
//     */
//    public void setAntallUlesteToSession(List<DinKorrespondanseFormData> korrespondanseListe) {
//        int sum = getAntallUleste(korrespondanseListe);
//
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Antall uleste elementer i korrespondanseListe: " + sum);
//        }
//
//        AbstractSessionMapWrapper.put(PselvSessionMapWrapper.ANTALL_ULESTE, sum);
//    }
//
//    /**
//     * Finds the number of unread elements in the current user's "Din Innboks" and sets them to the session variable
//     * ANTALL_ULESTE. To be called from other flows when wanting to update "antall uleste"
//     */
    public void setAntallUlesteToSession() {
//        int sum = getAntallUleste();
//
//        if (LOGGER.isDebugEnabled()) {
//            LOGGER.debug("Antall uleste elementer i korrespondanseListe: " + sum);
//        }
//
//        AbstractSessionMapWrapper.put(PselvSessionMapWrapper.ANTALL_ULESTE, sum);
    }
//
//    /**
//     * Returns the number of "uleste" brev in Din Innboks
//     *
//     * @return the number of "uleste" brev in Din Innboks
//     */
//    public int getAntallUleste() {
//        Pid pid = AbstractSessionMapWrapper.getBrukerPid();
//        int antallUleste = 0;
//        // Hent listen over henvendelser
//        List<Henvendelse> henvendelseListe = null;
//        try {
//            henvendelseListe = korrespondanseDelegate.hentHenvendelseListe(pid);
//        } catch (GenericConsumerException e) {
//            LOGGER.debug("GenericConsumerException thrown - Do nothing");
//        } catch (ConsumerSystemException e1) {
//            LOGGER.debug("ConsumerSystemException thrown - Do nothing");
//        }
//
//        List<Sak> sakList = null;
//        try {
//            sakList = korrespondanseDelegate.hentSakListe(pid);
//        } catch (FunctionalUnrecoverableException e) {
//            LOGGER.debug("FunctionalUnrecoverableException thrown - Do nothing");
//        }
//
//        if (sakList != null) {
//            // Hent listen over journalposter
//            List<Long> sakIdList = getSakIdList(sakList);
//            List<Journalpost> journalpostListe = new ArrayList<Journalpost>();
//            try {
//                journalpostListe = korrespondanseDelegate.hentJournalpostListe(sakIdList);
//            } catch (FunctionalUnrecoverableException e) {
//                LOGGER.debug("FunctionalUnrecoverableException thrown - Do nothing");
//            }
//
//            // Fjern journalposter som ikke er utgående og ferdigstilt fra journalpostlisten
//            List<Journalpost> filteredJournalpostListe = fjernInngaende(journalpostListe);
//
//            // actionDelegate.hentKorrespondanse
//            List<DinKorrespondanseFormData> korrespondanseListe = dinKorrespondanse.hentKorrespondanse(
//                    filteredJournalpostListe, henvendelseListe, pid.getPid());
//
//            // Krav cannot be "ulest", dropping beregnKrav
//
//            antallUleste = getAntallUleste(korrespondanseListe);
//        }
//
//        return antallUleste;
//    }
//
//    /**
//     * Private helper method. Gets a liste of unique saksnr from a list of saker
//     *
//     * @param sakList the sakList
//     * @return a list of unique saksnr
//     */
//    private List<Long> getSakIdList(List<Sak> sakList) {
//        List<Long> sakIdList = new ArrayList<Long>();
//
//        for (Sak sak : sakList) {
//            if (sak.getSakId() != null && !sakIdList.contains(sak.getSakId())) {
//                sakIdList.add(sak.getSakId());
//                if (LOGGER.isDebugEnabled()) {
//                    LOGGER.debug("Sak nr " + sak.getSakId() + " added to saksListe");
//                }
//            }
//        }
//        return sakIdList;
//    }
//
//    /**
//     * Returns the number of "uleste" korrespondanseobjects in a korrespondanseListe
//     *
//     * @param korrespondanseListe the list to calculate for
//     * @return the number of uleste
//     */
//    private int getAntallUleste(List<DinKorrespondanseFormData> korrespondanseListe) {
//        int sum = 0;
//
//        for (DinKorrespondanseFormData korr : korrespondanseListe) {
//            if (!korr.isVisSomLest()) {
//                sum++;
//            }
//        }
//        return sum;
//    }
//
//    /**
//     * Private helper method. Removes the Inngående journalpost from a list. Also removes Utgående without status FS/FL
//     * (Ferdigstilt)
//     */
//    private List<Journalpost> fjernInngaende(List<Journalpost> liste) {
//        List<Journalpost> result = new ArrayList<Journalpost>();
//        for (Journalpost post : liste) {
//            if (JournalpostUtil.isJournalpostFerdigStilt(post) && JournalpostUtil.isUtgaende(post)) {
//                result.add(post);
//            }
//        }
//        return result;
//    }

    /**
     * @param dinKorrespondanse the dinkorrespondanse to set
     */
    public void setDinKorrespondanse(DinKorrespondanse dinKorrespondanse) {
        this.dinKorrespondanse = dinKorrespondanse;
    }

    /**
     * @param korrespondanseDelegate the korrespondanseDelegate to set
     */
    public void setKorrespondanseDelegate(DinKorrespondanseCommonActionDelegate korrespondanseDelegate) {
        this.korrespondanseDelegate = korrespondanseDelegate;
    }

    /**
     * @param codesTableManager the codestablemanager to set
     */
    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }
}
