package no.nav.presentation.pensjon.pselv.publisering.uforetrygd;

import java.util.List;

import javax.faces.event.ActionEvent;

import no.stelvio.presentation.util.PagedSortableList;

import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.presentation.pensjon.pselv.common.CommonForm;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.SkjemaKravFormData;
import no.nav.presentation.pensjon.pselv.publisering.support.ByggeklossFormData;
import no.nav.presentation.pensjon.pselv.publisering.support.SakerAndSkjemaTablePanel;
import no.nav.presentation.pensjon.pselv.publisering.support.SakerAndSkjemaTablePanelViewHelper;
import no.nav.presentation.pensjon.pselv.rightcolumn.RightColumnCommonFormData;

public class UforetrygdForm extends CommonForm {

    private static final long serialVersionUID = 4643893047219430866L;

    private static final String PAGE_TITLE = "pselv.pus019.skjermbildetittel.tittel";

    private boolean haveLopendeUT;

    private String helpKey;

    private RightColumnCommonFormData rightColumnUforetrygdFormData;

    private final SakerAndSkjemaTablePanel sakerAndSkjemaTablePanel;

    /**
     * Determines if the user already has a started alderspensjon skjema
     */
    private boolean alderspensjonSkjemaStarted;

    private boolean forsorgertilleggSkjemaStarted;

    /**
     * Variable set when user clicks one of the links "søknad om alderspensjon" or "søknad om forsørgertillegg". Determines if
     * the user's previous skjema of the same type, should be deleted.
     */
    private boolean deleteSkjema;

    private Long alderspensjonSkjemaId;

    private Long forsorgertilleggSkjemaId;

    private String usersName;

    private String fnr;

    private Sak lopendeSak;

    private List<ByggeklossFormData> byggeklossList;

    public UforetrygdForm() {
        this.sakerAndSkjemaTablePanel = new SakerAndSkjemaTablePanelViewHelper(isUserGroup1(), this.lopendeSak);
    }

    //Setters and Gettets
    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    public String getFnr() {
        return fnr;
    }

    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    public boolean getHaveLopendeUT() {
        return haveLopendeUT;
    }

    public void setHaveLopendeUT(boolean haveLopendeUT) {
        this.haveLopendeUT = haveLopendeUT;
    }

    public boolean getHaveNotLopendeUT(){
        return !haveLopendeUT;
    }

    public List<ByggeklossFormData> getByggeklossList() {
        return byggeklossList;
    }

    public void setByggeklossList(List<ByggeklossFormData> byggeklossList) {
        this.byggeklossList = byggeklossList;
    }

    @Override
    public boolean isEnableRightColumn() {
        return true;
    }

    @Override
    public String getHelpKey() {
        return helpKey;
    }

    public void setHelpKey(String helpKey) {
        this.helpKey = helpKey;
    }

    @Override
    public String getPageTitle() {
        return PAGE_TITLE;
    }

    @Override
    public String getPageIcon() {
        return null;
    }

    /**
     * Get the RightColumnPensjonFormData
     *
     * @return rightColumnUforetrygdFormData the RightColumnUforetrygdFormData
     */
    public RightColumnCommonFormData getRightColumnUforetrygdFormData() {
        return rightColumnUforetrygdFormData;
    }

    /**
     * Sets the RightColumnPensjonFormData
     *
     * @param rightColumnUforetrygdFormData the RightColumnPensjonFormData to set
     */
    public void setRightColumnUforetrygdFormData(RightColumnCommonFormData rightColumnUforetrygdFormData) {
        this.rightColumnUforetrygdFormData = rightColumnUforetrygdFormData;
    }

    /**
     * Method that sets the variable deleteSkjema to true when the user selects "delete skjema" in the popup window.
     *
     * @param event The event triggered when selecting "delete skjema"
     */
    public void setDeleteSkjema(ActionEvent event) {
        deleteSkjema = true;
    }

    public Sak getLopendeSak() {
        return lopendeSak;
    }

    public void setLopendeSak(Sak lopendeSak) {
        this.lopendeSak = lopendeSak;
    }

    public SakerAndSkjemaTablePanel getSakerAndSkjemaTablePanel() {
        return sakerAndSkjemaTablePanel;
    }

    /**
     * Getter for the selectedSkjemaOrKrav
     *
     * @return the selectedSkjemaOrKrav
     */
    public SkjemaKravFormData getSelectedSkjemaOrKrav() {
        return sakerAndSkjemaTablePanel.getSelectedSkjemaOrKrav();
    }

    /**
     * Setter for the selectedSkjemaOrKrav
     *
     * @param selectedSkjemaOrKrav the selectedSkjemaOrKrav to set
     */
    public void setSelectedSkjemaOrKrav(SkjemaKravFormData selectedSkjemaOrKrav) {
        sakerAndSkjemaTablePanel.setSelectedSkjemaOrKrav(selectedSkjemaOrKrav);
    }

    public Long getAlderspensjonSkjemaId() {
        return alderspensjonSkjemaId;
    }

    public void setAlderspensjonSkjemaId(Long alderspensjonSkjemaId) {
        this.alderspensjonSkjemaId = alderspensjonSkjemaId;
    }

    public boolean isAlderspensjonSkjemaStarted() {
        return alderspensjonSkjemaStarted;
    }

    public void setAlderspensjonSkjemaStarted(boolean alderspensjonSkjemaStarted) {
        this.alderspensjonSkjemaStarted = alderspensjonSkjemaStarted;
    }

    public Long getForsorgertilleggSkjemaId() {
        return forsorgertilleggSkjemaId;
    }

    public void setForsorgertilleggSkjemaId(Long forsorgertilleggSkjemaId) {
        this.forsorgertilleggSkjemaId = forsorgertilleggSkjemaId;
    }

    public boolean isForsorgertilleggSkjemaStarted() {
        return forsorgertilleggSkjemaStarted;
    }

    public void setForsorgertilleggSkjemaStarted(boolean forsorgertilleggSkjemaStarted) {
        this.forsorgertilleggSkjemaStarted = forsorgertilleggSkjemaStarted;
    }

    public boolean isDeleteSkjema() {
        return deleteSkjema;
    }

    /**
     * Check if the block containing the sak and skjema list should be visible in view. It should only be visible if the list
     * contains at least one entry
     *
     * @return true if the list of sak and skjema contains at least one entry, false otherwise
     */
    public boolean isShowSakAndSkjemaBlock() {
        return sakerAndSkjemaTablePanel.isShowSakAndSkjemaBlock();
    }

    /**
     * User is born before 1943 if he/she is defined as user group 1.
     *
     * @return the userBornBefore1943
     */
    public boolean isUserBornBefore1943() {
        return isUserGroup1();
    }

    public void setDeleteSkjema(boolean deleteSkjema) {
        this.deleteSkjema = deleteSkjema;
    }

    /**
     * Setter for the pagedSortableSkjemaList
     *
     * @param pagedSortableSkjemaList the pagedSortableSkjemaList to set
     */
    public void setPagedSortableSkjemaList(PagedSortableList<SkjemaKravFormData> pagedSortableSkjemaList) {
        sakerAndSkjemaTablePanel.setPagedSortableSkjemaList(pagedSortableSkjemaList);
    }
}