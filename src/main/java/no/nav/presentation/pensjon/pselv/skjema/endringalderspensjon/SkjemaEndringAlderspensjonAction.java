package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import java.util.Calendar;
import java.util.List;

import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.common.exception.ImplementationUnrecoverableException;
import no.nav.domain.pensjon.common.exception.person.PersonIkkeFunnetException;
import no.nav.domain.pensjon.common.person.Person;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.common.session.AbstractSessionMapWrapper;
import no.nav.presentation.pensjon.pselv.common.CommonActionDelegate;
import no.nav.presentation.pensjon.pselv.common.enums.EndringAlderspensjonState;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.StepHolder;
import no.nav.presentation.pensjon.pselv.common.utils.PersonUtil;

/**
 * Action class controlling the flow between the different views in the skjema-endring-flow. Also responsible for setting up the
 * steps in the flow.
 */
public class SkjemaEndringAlderspensjonAction extends FormAction implements SkjemaEndringAlderspensjonConstants {

    private SkjemaEndringAlderspensjonPopulator skjemaEndringPopulator;
    private SkjemaEndringAlderspensjonActionDelegate skjemaEndringActionDelegate;
    private CommonActionDelegate commonActionDelegate;

    public Event init(RequestContext context) {
        Person person;

        try {
            person = commonActionDelegate.getPersonBySessionPid();
        } catch (PersonIkkeFunnetException e) {
            throw new ImplementationUnrecoverableException(e);
        }

        SkjemaEndringAlderspensjonCommonInputData skjemaEndringCommonInputData = initSkjemaEndringCommonInputData(person);
        context.getFlowScope().put(SKJEMA_INPUT_DATA, skjemaEndringCommonInputData);
        StepHolder stepHolder = skjemaEndringPopulator.createAndPopulateStepHolder(skjemaEndringCommonInputData);
        context.getFlowScope().put(STEP_HOLDER, stepHolder);
        return success();
    }

    /**
     * Creates and populate SkjemaEndringCommonInputData. SkjemaEndringCommonInputData holds all information about the skjema
     * endring alderspensjon.
     */
    private SkjemaEndringAlderspensjonCommonInputData initSkjemaEndringCommonInputData(Person person) {
        SkjemaEndringAlderspensjonCommonInputData inputData = new SkjemaEndringAlderspensjonCommonInputData();
        populateSkjemaEndringCommonInputData(person, inputData);
        return inputData;
    }

    /**
     * Populate the skjemaEndringCommonInputData with state based on user's age and user's sak and vedtak.
     */
    private void populateSkjemaEndringCommonInputData(Person person, SkjemaEndringAlderspensjonCommonInputData inputData) {
        if (isBrukerOver75()) {
            EndringAlderspensjonState state = EndringAlderspensjonState.BRUKER_FYLT_75;
            inputData.setEndringAlderspensjonState(state);
            return;
        }

        List<Sak> saker = skjemaEndringActionDelegate.getLopendeAndTilBehandlingSaker();
        List<Vedtak> vedtaksliste = skjemaEndringActionDelegate.getVedtakListe();
        skjemaEndringPopulator.findEndringAlderspensjonStateFromBrukerSakerAndVedtak(person, inputData, saker, vedtaksliste);
    }

    private boolean isBrukerOver75() {
        Pid pid = AbstractSessionMapWrapper.getBrukerPid();
        return new PersonUtil().isDateOneMonthOrMoreAfter75thBirthmonth(pid, Calendar.getInstance());
    }

    /**
     * Updates the stepHolder with values to open the next page in the list of steps. This method is executed when clicking the
     * button 'Gaa videre'
     */
    public Event openNextStep(RequestContext context) {
        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(STEP_HOLDER);
        stepHolder.updateStatusOpenNextStep();
        stepHolder.updateNextStepInFlowToBeActive();
        return success();
    }

    /**
     * Update the stepHolder with values to open the chosen step. This method is executed when clicking on the header of a step.
     */
    public Event openChosenStep(RequestContext context) {
        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(STEP_HOLDER);
        stepHolder.updateStatusOpenChosenStepNotFreeNavgiation();
        stepHolder.updateNextStepInFlowToBeActive();
        return success();
    }

    public void setSkjemaEndringPopulator(SkjemaEndringAlderspensjonPopulator skjemaEndringPopulator) {
        this.skjemaEndringPopulator = skjemaEndringPopulator;
    }

    public void setSkjemaEndringActionDelegate(SkjemaEndringAlderspensjonActionDelegate skjemaEndringActionDelegate) {
        this.skjemaEndringActionDelegate = skjemaEndringActionDelegate;
    }

    public CommonActionDelegate getCommonActionDelegate() {
        return commonActionDelegate;
    }

    public void setCommonActionDelegate(CommonActionDelegate commonActionDelegate) {
        this.commonActionDelegate = commonActionDelegate;
    }
}
