package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.util.ArrayList;
import java.util.List;

import no.nav.presentation.pensjon.pselv.common.stepnavigation.NavigationStepData;
import no.nav.presentation.pensjon.pselv.common.stepnavigation.StepHolder;

public class SkjemaAlderspensjonPopulator {

    private static final Integer INDEX_OF_FIRST_STEP = 0;
    private static final String INNLEDNING_TITLE = "pselv.sks001.skjermbildetittel.skjemasideinnledningoverskrift";
    private static final String INNLEDNING_FLOW = "skjemainnledning";
    private static final String PERSONOPPLYSNINGER_TITLE = "pselv.sks002.statisk_tekst.overskriftopplysninger";
    private static final String PERSONOPPLYSNINGER_FLOW = "skjemapersonopplysninger";
    private static final String FAMILIEFORHOLD_TITLE = "pselv.sks003.statisk_tekst.overskriftfamilieforhold";
    private static final String FAMILIEFORHOLD_FLOW = "skjemafamilieforhold";
    private static final String BARNEOPPLYSNINGER_TITLE = "pselv.sks004.skjermbildetittel.tittel";
    private static final String BARNEOPPLYSNINGER_FLOW = "skjemabarneopplysninger";
    private static final String UTLAND_TITLE = "pselv.sks006.statisk_tekst.overskriftopplysninger";
    private static final String UTLAND_FLOW = "skjemautland";
    private static final String FREMTIDIGINNTEKT_TITLE = "pselv.sks007.skjermbildetittel.tittel";
    private static final String FREMTIDIGINNTEKT_FLOW = "skjemafremtidiginntekt";
    private static final String PENSJONOGANDREYTELSER_TITLE = "pselv.sks008.statisk_tekst.overskriftopplysninger";
    private static final String PENSJONOGANDREYTELSER_FLOW = "skjemapensjonogandreytelser";
    private static final String BEKREFTELSE_TITLE = "pselv.sks013.statisk_tekst.bekreftoverskrift";
    private static final String BEKREFTELSE_FLOW = "skjemabekreft";
    private static final String AFPPRIVAT_TITLE = "pselv.sks015.statisk_tekst.overskriftafp";
    private static final String AFPPRIVAT_FLOW = "skjemaafpprivat";

    public void populateStepHolderWithInnledning(StepHolder stepHolder) {
        List<NavigationStepData> stepList = new ArrayList<>();
        addInnledning(stepList);
        stepHolder.setStepList(stepList);
        stepHolder.setCurrentStepIndex(INDEX_OF_FIRST_STEP);
    }

    /**
     * Sets up the step holder with the right steps based on the user's choices in view SKS001 Skjemaside innledning
     */
    public void populateStepHolderWithStepsBasedOnChoices(StepHolder stepHolder, SkjemaAlderspensjonCommonInputData inputData) {
        List<NavigationStepData> stepList = stepHolder.getStepList();
        removeAllNavigationStepsExceptTheFirstFromStepHolder(stepList);

        if (inputData.getAlderspensjonSoknad()) {
            initAlderspensjon(stepList, inputData);
        } else {
            initForsorgertillegg(stepList, inputData);
        }

        stepHolder.updateNextStepInFlowToBeActive();
    }

    private void initAlderspensjon(List<NavigationStepData> stepList, SkjemaAlderspensjonCommonInputData inputData) {
        addPersonopplysninger(stepList);
        addFamilieforhold(stepList);
        addUtland(stepList);

        if (inputData.isAfpPrivat()) {
            addAfpPrivat(stepList);
        }

        addBekreftelse(stepList);
    }

    private void initForsorgertillegg(List<NavigationStepData> stepList, SkjemaAlderspensjonCommonInputData inputData) {
        addPersonopplysninger(stepList);
        addFamilieforhold(stepList);

        if (inputData.getBarnetilleggIsChosen()) {
            addBarneopplysninger(stepList);
        }

        addFremtidigInntekt(stepList);
        addPensjonogAndreytelser(stepList);
        addBekreftelse(stepList);
    }

    /**
     * SKS001
     */
    private void addInnledning(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(INNLEDNING_TITLE);
        step.setSubflowId(INNLEDNING_FLOW);
        step.setCurrentPage(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS002
     */
    private void addPersonopplysninger(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(PERSONOPPLYSNINGER_TITLE);
        step.setSubflowId(PERSONOPPLYSNINGER_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS003
     */
    private void addFamilieforhold(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(FAMILIEFORHOLD_TITLE);
        step.setSubflowId(FAMILIEFORHOLD_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS004
     */
    private void addBarneopplysninger(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(BARNEOPPLYSNINGER_TITLE);
        step.setSubflowId(BARNEOPPLYSNINGER_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS006
     */
    private void addUtland(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(UTLAND_TITLE);
        step.setSubflowId(UTLAND_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS007
     */
    private void addFremtidigInntekt(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(FREMTIDIGINNTEKT_TITLE);
        step.setSubflowId(FREMTIDIGINNTEKT_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS008
     */
    private void addPensjonogAndreytelser(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(PENSJONOGANDREYTELSER_TITLE);
        step.setSubflowId(PENSJONOGANDREYTELSER_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS015
     */
    private void addAfpPrivat(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(AFPPRIVAT_TITLE);
        step.setSubflowId(AFPPRIVAT_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    /**
     * SKS013
     */
    private void addBekreftelse(List<NavigationStepData> stepList) {
        NavigationStepData step = new NavigationStepData();
        step.setTitle(BEKREFTELSE_TITLE);
        step.setSubflowId(BEKREFTELSE_FLOW);
        step.setDisabled(true);
        stepList.add(step);
        step.setStep(stepList.indexOf(step) + 1);
    }

    private void removeAllNavigationStepsExceptTheFirstFromStepHolder(List<NavigationStepData> stepList) {
        NavigationStepData firstNavigationStep = stepList.get(INDEX_OF_FIRST_STEP);
        stepList.clear();
        stepList.add(firstNavigationStep);
    }
}
