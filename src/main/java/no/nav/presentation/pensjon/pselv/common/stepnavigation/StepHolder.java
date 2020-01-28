package no.nav.presentation.pensjon.pselv.common.stepnavigation;

import java.io.Serializable;
import java.util.List;

/**
 * This class contains the list of steps to be used in the navigation framework
 * 'trinnvis navigasjon' and can be used with or without free navigation.
 */
public class StepHolder implements Serializable {

    private static final long serialVersionUID = -9073582265131397140L;
    private List<NavigationStepData> stepList;
    private String openStepFlow;
    private Integer openStepIndex;
    private Integer currentStepIndex;
    private boolean showSteps;

    public StepHolder() {
        showSteps = true;
    }

    public List<NavigationStepData> getStepList() {
        return stepList;
    }

    public void setStepList(List<NavigationStepData> stepList) {
        this.stepList = stepList;
    }

    public String getOpenStepFlow() {
        return openStepFlow;
    }

    public void setOpenStepFlow(String openStepFlow) {
        this.openStepFlow = openStepFlow;
    }

    public Integer getOpenStepIndex() {
        return openStepIndex;
    }

    public void setOpenStepIndex(Integer openStep) {
        openStepIndex = openStep - 1;
    }

    public Integer getCurrentStepIndex() {
        return currentStepIndex;
    }

    public void setCurrentStepIndex(Integer currentStepIndex) {
        this.currentStepIndex = currentStepIndex;
    }

    public NavigationStepData getCurrentStep() {
        return stepList.get(currentStepIndex);
    }

    public void setCurrentStepToFirstUnvalidatedStep() {
        NavigationStepData stepData = null;

        for (NavigationStepData item : stepList) {
            if (!item.isValidated()) {
                stepData = item;
                break;
            }
        }

        if (stepData == null) {
            return;
        }

        openStepIndex = stepList.indexOf(stepData);
        updateCurrentStepNotToBeCurrentStep();
        updateChosenStepToCurrentStep();
    }

    public void updateStatusOpenChosenStepFreeNavigation() {
        updateCurrentStepNotToBeCurrentStep();
        updateChosenStepToCurrentStep();
        updateStepsBeforeChosenStepToVisited();
    }

    public void updateStatusOpenChosenStepNotFreeNavgiation() {
        updateCurrentStepNotToBeCurrentStep();
        resetPrecedingStepsOpenStep();
        updateChosenStepToCurrentStep();
        updateNextStepInFlowToBeActive();
    }

    public void updateStatusOpenNextStep() {
        updateCurrentStepNotToBeCurrentStep();
        updateNextStepToBeCurrentStep();
    }

    public void setCurrentStepToLastStep() {
        NavigationStepData firstStep = stepList.get(0);
        firstStep.setCurrentPage(Boolean.FALSE);
        currentStepIndex = stepList.size() - 1;
        NavigationStepData lastStep = stepList.get(currentStepIndex);
        lastStep.setCurrentPage(Boolean.TRUE);
    }

    public void updateNextStepInFlowToBeActive() {
        if (isCurrentStepLastStep()) {
            return;
        }

        NavigationStepData step = stepList.get(currentStepIndex + 1);
        step.setDisabled(false);
    }

    public boolean isCurrentStepLastStep() {
        return currentStepIndex + 1 == stepList.size();
    }

    public boolean isCurrentStepFirstStep() {
        return currentStepIndex == 0;
    }

    public void updateStepsBeforeCurrentStepToVisited() {
        for (int i = 0; i < currentStepIndex; i++) {
            NavigationStepData navigationStepData = stepList.get(i);
            navigationStepData.setVisited(Boolean.TRUE);
        }
    }

    public void enableAllFutureSteps() {
        for (int i = currentStepIndex + 1; i < stepList.size(); i++) {
            NavigationStepData step = stepList.get(i);
            step.enable();
        }
    }

    public void disableAllFutureSteps() {
        for (int i = currentStepIndex + 1; i < stepList.size(); i++) {
            NavigationStepData step = stepList.get(i);
            step.disable();
        }
    }

    private void updateStepsBeforeChosenStepToVisited() {
        for (int i = 0; i < openStepIndex; i++) {
            NavigationStepData navigationStepData = stepList.get(i);
            navigationStepData.setVisited(Boolean.TRUE);
        }
    }

    private void updateCurrentStepNotToBeCurrentStep() {
        NavigationStepData currentNavigationStep = stepList.get(currentStepIndex);
        currentNavigationStep.setCurrentPage(Boolean.FALSE);
        currentNavigationStep.setVisited(Boolean.TRUE);
    }

    /**
     * Reset all preceding steps when opening a new step. Use this method when
     * pensjonskalkulator do not have free navigation.
     */
    private void resetPrecedingStepsOpenStep() {
        int lastStepToReset = currentStepIndex + 1;
        if (lastStepToReset == stepList.size()) {
            lastStepToReset = currentStepIndex;
        }
        for (int i = openStepIndex + 1; i <= lastStepToReset; i++) {
            NavigationStepData navigationStepData = stepList.get(i);
            navigationStepData.setCurrentPage(false);
            navigationStepData.setVisited(false);
            navigationStepData.setDisabled(true);
        }
    }

    private void updateChosenStepToCurrentStep() {
        NavigationStepData openNavigationStep = stepList.get(openStepIndex);
        openStepFlow = openNavigationStep.getSubflowId();
        openNavigationStep.setCurrentPage(Boolean.TRUE);
        currentStepIndex = openStepIndex;
    }

    private void updateNextStepToBeCurrentStep() {
        Integer nextStepIndex = currentStepIndex + 1;
        NavigationStepData openNavigationStep = stepList.get(nextStepIndex);
        openStepFlow = openNavigationStep.getSubflowId();
        openNavigationStep.setCurrentPage(Boolean.TRUE);
        currentStepIndex = nextStepIndex;
    }

    public boolean isShowSteps() {
        return showSteps;
    }

    public void hideSteps() {
        showSteps = false;
    }
}
