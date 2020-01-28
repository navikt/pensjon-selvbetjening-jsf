package no.nav.presentation.pensjon.pselv.skjema;

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.nav.presentation.pensjon.pselv.common.CommonAction;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.NavigationStepData;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.StepHolder;

public abstract class SkjemaCommonAction extends CommonAction {

    public abstract Event setTypedKeyInformation(RequestContext context);

    public void setTypedKeyInformationCurrentStep(RequestContext context, String typedKeyInformation) {
        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(SkjemaCommonConstants.STEP_HOLDER);
        NavigationStepData currentStep = stepHolder.getCurrentStep();
        currentStep.setTypedKeyInformation(typedKeyInformation);
        context.getFlowScope().put(SkjemaCommonConstants.STEP_HOLDER, stepHolder);
    }

    public Event setOpenStepIndex(RequestContext context) {
        Integer openStepIndex = (Integer) context.getFlowScope().get(SkjemaCommonConstants.OPEN_STEP_INDEX);
        StepHolder stepHolder = (StepHolder) context.getFlowScope().get(SkjemaCommonConstants.STEP_HOLDER);
        stepHolder.setOpenStepIndex(openStepIndex);
        return success();
    }
}
