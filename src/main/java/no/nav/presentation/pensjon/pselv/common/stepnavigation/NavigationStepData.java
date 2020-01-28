package no.nav.presentation.pensjon.pselv.common.stepnavigation;

import java.io.Serializable;

/**
 * Contains information about a step in the navigation framework for 'trinnvis navigasjon'.
 */
public class NavigationStepData implements Serializable {

    private static final long serialVersionUID = 253267131449439404L;
    private String title;
    private Boolean visited;
    private String typedKeyInformation;
    private Boolean currentPage;
    private Integer step;
    private Boolean disabled;
    private String subflowId;
    private Boolean hasContentToEdit;
    private NextStepButton nextStepButton;
    private CancelLink cancelLink;
    private Boolean validated;

    public NavigationStepData() {
        visited = false;
        currentPage = false;
        disabled = false;
        hasContentToEdit = true;
        validated = false;
    }

    public void enable() {
        disabled = false;
    }

    public void disable() {
        disabled = true;
    }

    public Boolean getFooterEnabled() {
        return isShowCancelLink() || isShowNextStepButton();
    }

    public boolean isShowCancelLink() {
        return cancelLink != null;
    }

    public boolean isShowNextStepButton() {
        return nextStepButton != null;
    }

    public boolean isCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(boolean currentPage) {
        this.currentPage = currentPage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypedKeyInformation() {
        return typedKeyInformation;
    }

    public void setTypedKeyInformation(String typedKeyInformation) {
        this.typedKeyInformation = typedKeyInformation;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public String getSubflowId() {
        return subflowId;
    }

    public void setSubflowId(String subflowId) {
        this.subflowId = subflowId;
    }

    public Boolean getHasContentToEdit() {
        return hasContentToEdit;
    }

    public void setHasContentToEdit(Boolean hasContentToEdit) {
        this.hasContentToEdit = hasContentToEdit;
    }

    public NextStepButton getNextStepButton() {
        return nextStepButton;
    }

    public void setNextStepButton(NextStepButton nextButton) {
        nextStepButton = nextButton;
    }

    public CancelLink getCancelLink() {
        return cancelLink;
    }

    public void setCancelLink(CancelLink cancelLink) {
        this.cancelLink = cancelLink;
    }

    public boolean isValidated() {
        return validated;
    }

    public void setValidated(boolean validated) {
        this.validated = validated;
    }
}
