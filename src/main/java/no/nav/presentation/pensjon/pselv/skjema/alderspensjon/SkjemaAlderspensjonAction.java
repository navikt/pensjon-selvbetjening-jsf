package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import org.springframework.webflow.action.FormAction;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.nav.presentation.pensjon.pselv.common.stepnavigation.StepHolder;

/**
 * Action class controlling the flow between the different views in the skjema-flow. Also responsible for setting up the steps
 * in the flow.
 */
public class SkjemaAlderspensjonAction extends FormAction {

    private SkjemaAlderspensjonPopulator skjemaPopulator;
    private static final String STEP_HOLDER = "stepHolder";

    public Event init(RequestContext context) {
        initAndPopulateStepHolder(context);
        return success();
    }

    /**
     * Populates the stepHolder for 'intern bruker' based on chosen 'ytelse'.
     */
    public Event populateStepHolder(RequestContext context) {
        SkjemaAlderspensjonCommonInputData inputData = (SkjemaAlderspensjonCommonInputData) context.getFlowScope().get(
                SkjemaAlderspensjonCommonAction.SKJEMA_INPUT_DATA);

        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(STEP_HOLDER);
        skjemaPopulator.populateStepHolderWithStepsBasedOnChoices(stepHolder, inputData);
        context.getFlowScope().put(STEP_HOLDER, stepHolder);
        return success();
    }

    /**
     * Updates the stepHolder with values to open the next page in the list of steps. This method is executed when clicking the
     * button 'Ga videre'
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

    private void initAndPopulateStepHolder(RequestContext context) {
        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(STEP_HOLDER);

        if (stepHolder == null) {
            stepHolder = new StepHolder();
        }

        skjemaPopulator.populateStepHolderWithInnledning(stepHolder);
        context.getFlowScope().put(STEP_HOLDER, stepHolder);
    }

    public void setSkjemaPopulator(SkjemaAlderspensjonPopulator skjemaPopulator) {
        this.skjemaPopulator = skjemaPopulator;
    }
}
