package no.nav.presentation.pensjon.pselv.publisering.dinpensjon;

import java.util.ArrayList;
import java.util.List;

import javax.faces.event.AjaxBehaviorEvent;

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;
import org.springframework.webflow.execution.RequestContextHolder;

import no.stelvio.common.codestable.CodesTableManager;
import no.stelvio.common.error.FunctionalRecoverableException;
import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.consumer.pensjon.pen.inntekt.inntektskomponenten.InntektServiceBi;
import no.nav.consumer.pensjon.pselv.opptjening.beholdning.BeholdningServiceBi;
import no.nav.consumer.pensjon.pselv.opptjening.opptjeningsgrunnlag.OpptjeningsGrunnlagServiceBi;
import no.nav.consumer.pensjon.pselv.utbetaling.UtbetalingServiceBi;
import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.kodetabeller.ElektroniskSkjemaTypeCode;
import no.nav.domain.pensjon.kjerne.krav.KravHode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.simulering.Pensjonsperiode;
import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.CommonAction;
import no.nav.presentation.pensjon.pselv.common.delegate.UtbetalingCommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.session.PselvSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.publisering.dininnboks.DinInnboksHelper;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.DinPensjonConstants;
import no.nav.presentation.pensjon.pselv.rightcolumn.RightColumnHelper;
import no.nav.presentation.pensjon.pselv.rightcolumn.RightColumnPensjonFormData;
import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;
import no.nav.service.pensjon.grunnlag.GrunnlagServiceBi;
import no.nav.service.pensjon.vedtak.VedtakServiceBi;

/**
 * Action class for module PUS002 Din pensjon
 */
public class DinPensjonAction extends CommonAction {

    private DinPensjonActionDelegate dinPensjonActionDelegate;
    private DinPensjonFormPopulator dinPensjonFormPopulator;

    /**
     * Transfer object, used to map values between PUS002 Din pensjon and PUS009 Saksoversikt
     */
    private PselvTransferObject pselvTransferObject;

    private CodesTableManager codesTableManager;
    private VedtakServiceBi vedtakService;
    private GrunnlagServiceBi grunnlagService;
    private OpptjeningsGrunnlagServiceBi opptjeningsGrunnlagService;
    private BeholdningServiceBi beholdningService;
    private UtbetalingServiceBi utbetalingService;
    private InntektServiceBi inntektskomponentService;
    private UtbetalingCommonActionDelegate utbetCommonActionDelegate;
    private Boolean isSelvbetjeningssonen;

    /**
     * Helper used to fin number of unread items in inbox
     */
    private DinInnboksHelper dinInnboksHelper;

    public Event initForm(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("Begin initializing PUS002 Din pensjon");
        }

        DinPensjonForm dinPensjonForm = (DinPensjonForm) getFormObject(context);
        dinPensjonForm.setShowFremdriftsindikator(false);

        Person person = dinPensjonForm.getBruker();

        // Only do service calls and populate form if the view should actually be rendered. If the user has role
        // KOMMUNE_FULLMAKT, variant 3 of view should be rendered, and this only contains an information message.
        if (!PSELVSecurityFunctions.isUserKommuneFullmakt()) {

            List<Sak> lopendeSakList = dinPensjonActionDelegate.hentLopendeSakListe(person.getPid());
            List<Vedtak> lopenedeAlderspensjonList = dinPensjonActionDelegate.bestemGjeldendeVedtak(person.getPid());

            boolean sakFailed = false;

            List<KravHode> kravHodeList = dinPensjonActionDelegate.getKrav(person.getPid());

            boolean skjemaFailed = false;

            List<Skjema> skjemaList = dinPensjonActionDelegate.getSkjema(person.getPid());

            // handeling an error when both fail
            if (skjemaFailed && sakFailed) {
                setErrorMessage(DinPensjonConstants.IKKE_SAK_OG_SKJEMA_ERROR_KEY);
                if (logger.isDebugEnabled()) {
                    logger.debug("Both retrieval of sak and skjema failed, setting error message on faces context.");
                }
            }

            /* Initialize the rightColumn */
            RightColumnHelper rightColumnHelper = new RightColumnHelper(
                    getMessageSource(),
                    codesTableManager,
                    vedtakService,
                    grunnlagService,
                    opptjeningsGrunnlagService,
                    utbetalingService,
                    utbetCommonActionDelegate,
                    inntektskomponentService,
                    beholdningService
            );
            RightColumnPensjonFormData rightColumn = rightColumnHelper.fetchRightColumnFormData(person);
            dinPensjonForm.setRightColumnFormData(rightColumn);

            // Ask the form populator to init the form:
            dinPensjonFormPopulator.populateForm(dinPensjonForm, kravHodeList, skjemaList, lopendeSakList,
                    lopenedeAlderspensjonList);

            // Set the number of unread items in inbox in session
            dinInnboksHelper.setAntallUlesteToSession();

            // EESSI_PENSJON_SELVBETJENING and AKTOER_ID is used at multiple locations in Pselv, and are therefore set initially when DinPensjon is loaded
            if (PselvSessionMapWrapper.get(PselvSessionMapWrapper.EESSI_PENSJON_SELVBETJENING) == null) {
                PselvSessionMapWrapper.put(PselvSessionMapWrapper.EESSI_PENSJON_SELVBETJENING, dinPensjonActionDelegate.isEESSIPensjonSelvbetjening());
            }

            if (shouldSetAktoerId()) {
                PselvSessionMapWrapper.put(PselvSessionMapWrapper.AKTOER_ID, dinPensjonActionDelegate.getAktoerId(person.getPid()));
            }

            List<List<Pensjonsperiode>> simulationResults =
                    (List<List<Pensjonsperiode>>) PselvSessionMapWrapper.get(PselvSessionMapWrapper.SIMULATION_RESULTS);

            dinPensjonForm.setSimulationResults(simulationResults);
            if (dinPensjonForm.getSimulationResults() != null) {
                beregnPensjon(null);
            }
        }

        return success();
    }

    private boolean shouldSetAktoerId() {

        return (boolean) PselvSessionMapWrapper.get(PselvSessionMapWrapper.EESSI_PENSJON_SELVBETJENING)
                && !isSelvbetjeningssonen && PselvSessionMapWrapper.get(PselvSessionMapWrapper.AKTOER_ID) == null;
    }

    public Event deleteSkjema(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("In method deleteSkjema()");
        }
        DinPensjonForm minPensjonForm = (DinPensjonForm) getFormObject(context);

        if (ElektroniskSkjemaTypeCode.AP.name().equals(minPensjonForm.getSelectedSkjemaOrKrav().getType())) {
            if (logger.isDebugEnabled()) {
                logger.debug("Trying to delete a skjema of type AP, resetting values on form");
            }
            minPensjonForm.setAlderspensjonSkjemaId(null);
            minPensjonForm.setAlderspensjonSkjemaStarted(false);
        } else {
            if (logger.isDebugEnabled()) {
                logger.debug("Trying to delete a skjema of type FT, resetting values on form");
            }
            minPensjonForm.setForsorgertilleggSkjemaId(null);
            minPensjonForm.setForsorgertilleggSkjemaStarted(false);
        }
        dinPensjonActionDelegate.slettSkjema(minPensjonForm.getSelectedSkjemaOrKrav().getId());

        return success();
    }

    public Event openSkjema(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("In method openSkjema()");
        }
        DinPensjonForm minPensjonForm = (DinPensjonForm) getFormObject(context);

        context.getFlowScope().put(DinPensjonConstants.SKJEMAID, minPensjonForm.getSelectedSkjemaOrKrav().getId());
        context.getFlowScope().put(DinPensjonConstants.SKJEMATYPE, minPensjonForm.getSelectedSkjemaOrKrav().getType());

        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Putting values on flow. ");
            builder.append("Skjema id: ");
            builder.append(minPensjonForm.getSelectedSkjemaOrKrav().getId());
            builder.append(" Skjema type: ");
            builder.append(minPensjonForm.getSelectedSkjemaOrKrav().getType());
            logger.debug(builder.toString());
        }
        return success();
    }

    public Event openSak(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("In method openSak()");
        }
        DinPensjonForm minPensjonForm = (DinPensjonForm) getFormObject(context);
        pselvTransferObject.put(PselvTransferObject.SAKSID, minPensjonForm.getSelectedSkjemaOrKrav().getId());
        if (logger.isDebugEnabled()) {
            StringBuilder builder = new StringBuilder();
            builder.append("Putting value on transfer object. ");
            builder.append("Saksid: ");
            builder.append(minPensjonForm.getSelectedSkjemaOrKrav().getId());
            logger.debug(builder.toString());
        }
        return success();
    }

    /**
     * Alderpensjon redirect link.
     */
    public Event openSoknadAlderspensjon(RequestContext context) {
        if (logger.isDebugEnabled()) {
            logger.debug("In method soknadAlderspensjon");
        }
        DinPensjonForm dinPensjonForm = (DinPensjonForm) getFormObject(context);

        context.getFlowScope().put(DinPensjonConstants.SKJEMATYPE, ElektroniskSkjemaTypeCode.AP.toString());

        // if true the user hit cancel
        if (dinPensjonForm.isAlderspensjonSkjemaStarted()) {
            if (logger.isDebugEnabled()) {
                StringBuilder builder = new StringBuilder();
                builder.append("Opening a previously saved skjema of type AP. ");
                builder.append("Skjema id is: ");
                builder.append(dinPensjonForm.getAlderspensjonSkjemaId());
                logger.debug(builder.toString());
            }
            context.getFlowScope().put(DinPensjonConstants.SKJEMAID, dinPensjonForm.getAlderspensjonSkjemaId().toString());
        } else {
            context.getFlowScope().put(DinPensjonConstants.SKJEMAID, null);
        }

        return success();
    }

    /**
     * Simulates user's alderspensjon at retirement ages calculated based on user's age.
     */
    public void beregnPensjon(AjaxBehaviorEvent evt) {
        RequestContext ctx = RequestContextHolder.getRequestContext();
        DinPensjonForm form = (DinPensjonForm) getFormObject(ctx);
        form.setHurtigberegning(true);

        int[] simulationAgeList = dinPensjonFormPopulator.createListOfSimulationAges(form.getBruker());

        if (form.getSimulationResults() == null) {
            try {
                // Updated via SIR222098 / CR207203
                form.setSimulationResults(dinPensjonActionDelegate.simulateAlderspensjon(form, simulationAgeList));
                /* CR 216667 */
                addAnSimuationResultsCopyOnSession(form.getSimulationResults());
            } catch (FunctionalRecoverableException e) {
                if (logger.isDebugEnabled()) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Exception in beregnPensjon. ");
                    builder.append(e.getMessage());
                    logger.debug(builder.toString());
                }
                form.setShowErrorMessageServiceGraf(true);
            } catch (ImplementationUnrecoverableException e) {
                if (logger.isDebugEnabled()) {
                    StringBuilder builder = new StringBuilder();
                    builder.append("Exception in beregnPensjon. ImplementationUnrecoverableException ");
                    builder.append(e);
                    logger.debug(builder.toString());
                }
                form.setShowErrorMessageServiceGraf(true);
            }
        }
        if (form.getSimulationResults() != null) {
            dinPensjonFormPopulator.populateSimuleringResult(form, form.getSimulationResults(), simulationAgeList[0]);
        }
    }

    /**
     * Adds a copy of the simulationResults from TPEN620 simulerFleksibelAP (though without the
     * simulertBeregningsinformasjonListe) on the session.
     */
    private void addAnSimuationResultsCopyOnSession(List<List<Pensjonsperiode>> simulationResults) {
        List<List<Pensjonsperiode>> simulationResultsCopy = new ArrayList<>();
        for (List<Pensjonsperiode> ppList : simulationResults) {
            List<Pensjonsperiode> ppListCopy = new ArrayList<>();
            for (Pensjonsperiode pp : ppList) {
                Pensjonsperiode ppCopy = new Pensjonsperiode();
                ppCopy.setAlder(pp.getAlder());
                ppCopy.setBelop(pp.getBelop());
                ppListCopy.add(ppCopy);
            }
            simulationResultsCopy.add(ppListCopy);
        }
        PselvSessionMapWrapper.put(PselvSessionMapWrapper.SIMULATION_RESULTS, simulationResultsCopy);
    }

    /**
     * Convenience method for setting error message on faces context
     */
    private void setErrorMessage(String messageKey) {
        MessageContextUtil.setGlobalMessageOnMessageContext(messageKey);
    }

    public void setDinPensjonActionDelegate(DinPensjonActionDelegate dinPensjonActionDelegate) {
        this.dinPensjonActionDelegate = dinPensjonActionDelegate;
    }

    public void setDinPensjonFormPopulator(DinPensjonFormPopulator dinPensjonFormPopulator) {
        this.dinPensjonFormPopulator = dinPensjonFormPopulator;
    }

    public void setPselvTransferObject(PselvTransferObject pselvTransferObject) {
        this.pselvTransferObject = pselvTransferObject;
    }

    public void setDinInnboksHelper(DinInnboksHelper dinInnboksHelper) {
        this.dinInnboksHelper = dinInnboksHelper;
    }

    public void setCodesTableManager(CodesTableManager codesTableManager) {
        this.codesTableManager = codesTableManager;
    }

    public void setVedtakService(VedtakServiceBi vedtakService) {
        this.vedtakService = vedtakService;
    }

    public void setGrunnlagService(GrunnlagServiceBi grunnlagService) {
        this.grunnlagService = grunnlagService;
    }

    public void setUtbetalingService(UtbetalingServiceBi utbetalingService) {
        this.utbetalingService = utbetalingService;
    }

    public void setInntektskomponentService(InntektServiceBi inntektskomponentService) {
        this.inntektskomponentService = inntektskomponentService;
    }

    public void setUtbetalingCommonActionDelegate(UtbetalingCommonActionDelegate utbetCommonActionDelegate) {
        this.utbetCommonActionDelegate = utbetCommonActionDelegate;
    }

    public void setIsSelvbetjeningssonen(Boolean isSelvbetjeningssonen) {
        this.isSelvbetjeningssonen = isSelvbetjeningssonen;
    }

    public void setOpptjeningsGrunnlagService(OpptjeningsGrunnlagServiceBi opptjeningsGrunnlagService) {
        this.opptjeningsGrunnlagService = opptjeningsGrunnlagService;
    }

    public void setBeholdningService(BeholdningServiceBi beholdningService) {
        this.beholdningService = beholdningService;
    }
}
