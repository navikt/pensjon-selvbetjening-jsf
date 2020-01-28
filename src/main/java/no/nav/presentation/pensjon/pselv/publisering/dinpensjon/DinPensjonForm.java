package no.nav.presentation.pensjon.pselv.publisering.dinpensjon;

import java.util.Date;
import java.util.List;

import javax.faces.event.ActionEvent;

import org.jfree.data.xy.XYDataset;

import no.stelvio.domain.person.Pid;
import no.stelvio.presentation.util.PagedSortableList;

import no.nav.domain.pensjon.kjerne.kodetabeller.RegelverkTypeCode;
import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.simulering.Pensjonsperiode;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.CommonForm;
import no.nav.presentation.pensjon.pselv.publisering.dinpensjon.support.GraphTableData;
import no.nav.presentation.pensjon.pselv.publisering.support.ByggeklossFormData;
import no.nav.presentation.pensjon.pselv.publisering.support.SakerAndSkjemaTablePanel;
import no.nav.presentation.pensjon.pselv.publisering.support.SakerAndSkjemaTablePanelViewHelper;
import no.nav.presentation.pensjon.pselv.rightcolumn.RightColumnPensjonFormData;

/**
 * Form class for module PUS002 Din Pensjon.
 *
 */
public class DinPensjonForm extends CommonForm {

    private static final long serialVersionUID = -4691391141388428499L;

    /**
     * A simulation is done if user is older than a certain age
     */
    private static final int MINIMUM_AGE_SIMULATION = 16;

    /**
     * Value used by xhtml-frame to open right help text
     */
    private String helpKey;

    /**
     * Value used by xhtml-frame to set right page title
     */
    private static final String PAGE_TITLE = "pselv.pus002.skjermbildetittel.tittel";

    /**
     * Page icon
     */
    private static final String PAGE_ICON = null;

    /**
     * Determines if the person data part of the form should be visible
     */
    private boolean visPersonData;

    /**
     * User's name
     */
    private String navn;

    /**
     * User's pid
     */
    private String fnr;

    private XYDataset inputGraph;

    /**
     * List of object for representing simulation result in a table
     */
    private List<GraphTableData> graphTableDataList;

    private boolean showErrorMessageServiceGraf;

    private boolean userHasMadeHurtigberegning;

    /**
     * Variable set when user clicks one of the links "søknad om alderspensjon" or "søknad om forsørgertillegg". Determines if
     * the user's previous skjema of the same type, should be deleted.
     */
    private boolean deleteSkjema;

    /**
     * The id of the user's alderspensjon skjema. Null if the user does not have a started skjema of this type
     */
    private Long alderspensjonSkjemaId;

    /**
     * The id of the user's forsørgertillegg skjema. Null if the user does not have a started skjema of this type
     */
    private Long forsorgertilleggSkjemaId;

    /**
     * Determines if the user already has a started alderspensjon skjema
     */
    private boolean alderspensjonSkjemaStarted;

    /**
     * Determines if the user already has a started forsørgertillegg skjema
     */
    private boolean forsorgertilleggSkjemaStarted;

    private boolean showGraph;

    private boolean showFremdriftsindikator;

    private boolean lopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP;

    private boolean lessThanLimitMonthsUntilUserIs62;

    private Sak lopendeSak;

    private Vedtak lopendeVedtakAlderspensjon;

    private List<ByggeklossFormData> byggeklossList;

    private boolean showMessageForsteMuligUtttak;

    private RightColumnPensjonFormData rightColumn;

    private List<List<Pensjonsperiode>> simulationResults;

    private final SakerAndSkjemaTablePanel sakerAndSkjemaTablePanel;

    public DinPensjonForm() {
        this.sakerAndSkjemaTablePanel = new SakerAndSkjemaTablePanelViewHelper(isUserGroup1(), this.lopendeSak);
    }


    /**
     * Checks if the list of sak and skjema in view contains only sak-entries
     *
     * @return true if the list only contains sak-entries, false otherwise
     */
    public boolean isSakAndSkjemaListContainsOnlySak() {
        return sakerAndSkjemaTablePanel.isSakAndSkjemaListContainsOnlySak();
    }

    /**
     * Checks if the list of sak and skjema in view contains only skjema-entries
     *
     * @return true if the list only contains skjema-entries, false otherwise
     */
    public boolean isSakAndSkjemaListContainsOnlySkjema() {
        return sakerAndSkjemaTablePanel.isSakAndSkjemaListContainsOnlySkjema();
    }

    /**
     * Checks if the list of sak and skjema in view contains both sak- and skjema-entries
     *
     * @return true if the list contains both sak- and skjema-entries, false otherwise
     */
    public boolean isSakAndSkjemaListContainsBoth() {
        return sakerAndSkjemaTablePanel.isSakAndSkjemaListContainsBoth();
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
     * Check if the user belongs to usergroup 10
     *
     * @return true if the user belongs to usergroup 10, false otherwise
     */
    public boolean isUserGroup10() {
        return isUserGroup1() && (!hasLopendeSak() || !hasAlderspensjonSak());
    }

    /**
     * Check if the user belongs to usergroup 20
     *
     * @return true if the user belongs to usergroup 20, false otherwise
     */
    public boolean isUserGroup20() {
        return isUserGroup1() && hasAlderspensjonSak();
    }

    /**
     * Check if the user belongs to usergroup 30
     *
     * @return true if the user belongs to usergroup 30, false otherwise
     */
    public boolean isUserGroup30() {
        return isUserCanApplyForFleksibelAlder() && !hasLopendeSak();
    }

    public void setLessThanLimitMonthsUntilUserIs62(boolean lessThanLimitMonthsUntilUserIs62) {
        this.lessThanLimitMonthsUntilUserIs62 = lessThanLimitMonthsUntilUserIs62;
    }

    /**
     * Check if the user belongs to usergroup 40
     *
     * @return true if the user belongs to usergroup 40, false otherwise
     */
    public boolean isUserGroup40() {
        return isUserCanApplyForFleksibelAlder() && (hasUforepensjonSak() || hasAFPSak());
    }

    /**
     * Check if the user belongs to usergroup 50
     *
     * @return true if the user belongs to usergroup 50, false otherwise
     */
    public boolean isUserGroup50() {
        return isUserCanApplyForFleksibelAlder() && (hasGjenlevendepensjonSak() || hasFamiliepleierSak());
    }

    /**
     * Check if the user belongs to usergroup 60
     *
     * @return true if the user belongs to usergroup 60, false otherwise
     */
    public boolean isUserGroup60() {
        return !isUserGroup1() && !isUserCanApplyForFleksibelAlder() && !hasLopendeSak();
    }

    /**
     * Check if the user belongs to usergroup 70
     *
     * @return true if the user belongs to usergroup 70, false otherwise
     */
    public boolean isUserGroup70() {
        return !isUserGroup1() && !isUserCanApplyForFleksibelAlder() && hasUforepensjonSak();
    }

    /**
     * Check if the user belongs to usergroup 80
     *
     * @return true if the user belongs to usergroup 80, false otherwise
     */
    public boolean isUserGroup80() {
        if (!isUserGroup1() && !isUserCanApplyForFleksibelAlder()) {
            if (hasBarnepensjonSak() || hasGjenlevendepensjonSak() || hasFamiliepleierSak()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if the user belongs to usergroup 90
     *
     * @return true if the user belongs to usergroup 90, false otherwise
     */
    public boolean isUserGroup90() {
        return isUserCanApplyForFleksibelAlder() && hasAlderspensjonSak() && !isLopendeApOnGammeltRegelverk();
    }

    /**
     * Check if the user belongs to usergroup 91
     *
     * @return true if the user belongs to usergroup 91, false otherwise
     */
    public boolean isUserGroup91() {
        return isUserCanApplyForFleksibelAlder() && hasAlderspensjonSak() && isLopendeApOnGammeltRegelverk();
    }

    /**
     * Check if there is an active, that it is on gammelt regelverk.
     *
     * @return true if active lopende alderspensjon on gammelt regelverk, false otherwise.
     */
    private boolean isLopendeApOnGammeltRegelverk() {
        return lopendeVedtakAlderspensjon != null && (lopendeVedtakAlderspensjon.getKravhode().getRegelverkType() == null
                || RegelverkTypeCode.G_REG.equals(lopendeVedtakAlderspensjon.getKravhode().getRegelverkType()));
    }

    /**
     * Finds the first age of uttak from the list of data used for representing sumulation result in a table.
     *
     * @return String
     */
    public String getForsteUttaksalder() {
        return graphTableDataList.get(0).getAlder();
    }

    /**
     * Returns true if simulation of alderspensjon should be done (block Y), and false otherwise. Simulation is done for
     * usergroup 30, 50, 60 and 80, and only if user is older than 16 years old.
     *
     * @return boolean
     */
    public boolean isShowSimulation() {
        int usersAge = Pid.calculateAge(getBruker().getPid(), new Date());
        if (usersAge >= MINIMUM_AGE_SIMULATION) {
            if (isUserGroup30() || isUserGroup50()) {
                return true;
            } else if (isUserGroup60() || isUserGroup80()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Method that sets the variable deleteSkjema to true when the user selects "delete skjema" in the popup window.
     *
     * @param event The event triggered when selecting "delete skjema"
     */
    public void setDeleteSkjema(ActionEvent event) {
        deleteSkjema = true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getHelpKey() {
        return helpKey;
    }

    public void setHelpKey(String helpKey) {
        this.helpKey = helpKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageTitle() {
        return PAGE_TITLE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEnableRightColumn() {
        return true;
    }

    /**
     * Getter for the visPersonData
     *
     * @return the visPersonData
     */
    public boolean isVisPersonData() {
        return visPersonData;
    }

    /**
     * Setter for the visPersonData
     *
     * @param visPersonData the visPersonData to set
     */
    public void setVisPersonData(boolean visPersonData) {
        this.visPersonData = visPersonData;
    }

    /**
     * Getter for the alderspensjonSkjemaId
     *
     * @return the alderspensjonSkjemaId
     */
    public Long getAlderspensjonSkjemaId() {
        return alderspensjonSkjemaId;
    }

    /**
     * Setter for the alderspensjonSkjemaId
     *
     * @param alderspensjonSkjemaId the alderspensjonSkjemaId to set
     */
    public void setAlderspensjonSkjemaId(Long alderspensjonSkjemaId) {
        this.alderspensjonSkjemaId = alderspensjonSkjemaId;
    }

    /**
     * Getter for the alderspensjonSkjemaStarted
     *
     * @return the alderspensjonSkjemaStarted
     */
    public boolean isAlderspensjonSkjemaStarted() {
        return alderspensjonSkjemaStarted;
    }

    /**
     * Setter for the alderspensjonSkjemaStarted
     *
     * @param alderspensjonSkjemaStarted the alderspensjonSkjemaStarted to set
     */
    public void setAlderspensjonSkjemaStarted(boolean alderspensjonSkjemaStarted) {
        this.alderspensjonSkjemaStarted = alderspensjonSkjemaStarted;
    }

    /**
     * Getter for the deleteSkjema
     *
     * @return the deleteSkjema
     */
    public boolean isDeleteSkjema() {
        return deleteSkjema;
    }

    /**
     * Setter for the deleteSkjema
     *
     * @param deleteSkjema the deleteSkjema to set
     */
    public void setDeleteSkjema(boolean deleteSkjema) {
        this.deleteSkjema = deleteSkjema;
    }

    /**
     * Getter for the fnr
     *
     * @return the fnr
     */
    public String getFnr() {
        return fnr;
    }

    /**
     * Setter for the fnr
     *
     * @param fnr the fnr to set
     */
    public void setFnr(String fnr) {
        this.fnr = fnr;
    }

    /**
     * Getter for the forsorgertilleggSkjemaId
     *
     * @return the forsorgertilleggSkjemaId
     */
    public Long getForsorgertilleggSkjemaId() {
        return forsorgertilleggSkjemaId;
    }

    /**
     * Setter for the forsorgertilleggSkjemaId
     *
     * @param forsorgertilleggSkjemaId the forsorgertilleggSkjemaId to set
     */
    public void setForsorgertilleggSkjemaId(Long forsorgertilleggSkjemaId) {
        this.forsorgertilleggSkjemaId = forsorgertilleggSkjemaId;
    }

    /**
     * Getter for the forsorgertilleggSkjemaStarted
     *
     * @return the forsorgertilleggSkjemaStarted
     */
    public boolean isForsorgertilleggSkjemaStarted() {
        return forsorgertilleggSkjemaStarted;
    }

    /**
     * Setter for the forsorgertilleggSkjemaStarted
     *
     * @param forsorgertilleggSkjemaStarted the forsorgertilleggSkjemaStarted to set
     */
    public void setForsorgertilleggSkjemaStarted(boolean forsorgertilleggSkjemaStarted) {
        this.forsorgertilleggSkjemaStarted = forsorgertilleggSkjemaStarted;
    }

    /**
     * Getter for the navn
     *
     * @return the navn
     */
    public String getNavn() {
        return navn;
    }

    /**
     * Setter for the navn
     *
     * @param navn the navn to set
     */
    public void setNavn(String navn) {
        this.navn = navn;
    }

    /**
     * Setter for the pagedSortableSkjemaList
     *
     * @param pagedSortableSkjemaList the pagedSortableSkjemaList to set
     */
    public void setPagedSortableSkjemaList(PagedSortableList<SkjemaKravFormData> pagedSortableSkjemaList) {
        sakerAndSkjemaTablePanel.setPagedSortableSkjemaList(pagedSortableSkjemaList);
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

    /**
     * Gets a dataset used for generating a barchart
     *
     * @return the barData
     */
    public XYDataset getInputGraph() {
        return inputGraph;
    }

    /**
     * @param graphInput the BarData to set
     */
    public void setInputGraph(XYDataset graphInput) {
        inputGraph = graphInput;
    }

    public boolean isShowResult() {
        return isShowSimulation() && !showFremdriftsindikator && userHasMadeHurtigberegning;
    }

    public List<GraphTableData> getGraphTableDataList() {
        return graphTableDataList;
    }

    public void setGraphTableDataList(List<GraphTableData> graphTableDataList) {
        this.graphTableDataList = graphTableDataList;
    }

    /**
     * @return the showGraph
     */
    public boolean isShowGraph() {
        return showGraph;
    }

    /**
     * @param showGraph the showGraph to set
     */
    public void setShowGraph(boolean showGraph) {
        this.showGraph = showGraph;
    }

    /**
     * Hides the graph from min pensjon
     */
    public void hideGraph() {
        showGraph = false;
    }

    /**
     * Displays the graph from min pensjon
     */
    public void showGraph() {
        showGraph = true;
    }

    public boolean isShowFremdriftsindikator() {
        return showFremdriftsindikator;
    }

    public void setShowFremdriftsindikator(boolean showFremdriftsindikator) {
        this.showFremdriftsindikator = showFremdriftsindikator;
    }

    public void visFremdriftsindikator() {
        showFremdriftsindikator = true;
    }

    public boolean getShowErrorMessageServiceGraf() {
        return showErrorMessageServiceGraf;
    }

    public void setShowErrorMessageServiceGraf(boolean showErrorMessageService) {
        showErrorMessageServiceGraf = showErrorMessageService;
    }

    public boolean isLopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP() {
        return lopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP;
    }

    public void setLopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP(boolean lopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP) {
        this.lopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP = lopendeSakOfTypeAlderspensjonOrUforepensjonsOrAFP;
    }

    /**
     * Getter for the lopendeSak
     *
     * @return the lopendeSak
     */
    public Sak getLopendeSak() {
        return lopendeSak;
    }

    /**
     * Setter for the lopendeSak
     *
     * @param lopendeSak the lopendeSak to set
     */
    public void setLopendeSak(Sak lopendeSak) {
        this.lopendeSak = lopendeSak;
    }

    /**
     * Getter for the lopendeAlderspenjon
     *
     * @return the lopendeAlderspenjon
     */
    public Vedtak getLopendeAlderspensjon() {
        return lopendeVedtakAlderspensjon;
    }

    /**
     * Setter for the lopendeAlderspenjon
     *
     * @param lopendeAlderspensjon the lopendeAlderspenjon to set
     */
    public void setLopendeAlderspensjon(Vedtak lopendeAlderspensjon) {
        lopendeVedtakAlderspensjon = lopendeAlderspensjon;
    }

    /**
     * User is born before 1943 if he/she is defined as user group 1.
     *
     * @return the userBornBefore1943
     */
    public boolean isUserBornBefore1943() {
        return isUserGroup1();
    }

    /**
     * Getter for the userCanApplyForFleksibelAlder
     *
     * @return the userCanApplyForFleksibelAlder
     */
    public boolean isUserCanApplyForFleksibelAlder() {
        return !isUserGroup1() && lessThanLimitMonthsUntilUserIs62;
    }

    /**
     * Getter for the byggeklossList
     *
     * @return the byggeklossList
     */
    public List<ByggeklossFormData> getByggeklossList() {
        return byggeklossList;
    }

    /**
     * Setter for the byggeklossList
     *
     * @param byggeklossList the byggeklossList to set
     */
    public void setByggeklossList(List<ByggeklossFormData> byggeklossList) {
        this.byggeklossList = byggeklossList;
    }

    /**
     * Returs true if user has lopende sak and the sak is of type alderspensjon, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasAlderspensjonSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.ALDER);
    }

    /**
     * Returns true is user has a lopende sak, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasLopendeSak() {
        return lopendeSak != null;
    }

    /**
     * Returs true if user has lopende sak and the sak is of type AFP, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasAFPSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.AFP);
    }

    /**
     * Returs true if user has lopende sak and the sak is of type uforepensjon, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasUforepensjonSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.UFOREP);
    }

    /**
     * Returs true if user has lopende sak and the sak is of type familiepleier, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasFamiliepleierSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.FAM_PL);
    }

    /**
     * Returs true if user has lopende sak and the sak is of type gjenlevende, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasGjenlevendepensjonSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.GJENLEV);
    }

    /**
     * Returs true if user has lopende sak and the sak is of type barnepensjon, and false otherwise.
     *
     * @return boolean
     */
    private boolean hasBarnepensjonSak() {
        return hasLopendeSak() && lopendeSak.getSakType().isCodeEqualTo(SakTypeCode.BARNEP);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getPageIcon() {
        return PAGE_ICON;
    }

    /**
     * Get for showMessageForsteMuligUttak: message for informing user about her/his first possible uttak of alderspensjon.
     *
     * @return boolean
     */
    public boolean isShowMessageForsteMuligUtttak() {
        return showMessageForsteMuligUtttak;
    }

    /**
     * Setter showMessageForsteMuligUttak.
     *
     * @param showMessageForsteMuligUtttak boolean
     */
    public void setShowMessageForsteMuligUtttak(boolean showMessageForsteMuligUtttak) {
        this.showMessageForsteMuligUtttak = showMessageForsteMuligUtttak;
    }

    /**
     * Getter for RightColumnPensjonFormData.
     *
     * @return rightColumn the RightColumnPensjonFormData.
     */
    public RightColumnPensjonFormData getRightColumnFormData() {
        return rightColumn;
    }

    /**
     * Setter for RightColumnPensjonFormData.
     *
     * @param rightColumn the RightColumnPensjonFormData.
     */
    public void setRightColumnFormData(RightColumnPensjonFormData rightColumn) {
        this.rightColumn = rightColumn;
    }

    public boolean isHurtigberegning() {
        return userHasMadeHurtigberegning;
    }

    public void setHurtigberegning(boolean hurtigberegning) {
        userHasMadeHurtigberegning = hurtigberegning;
    }

    public boolean isShowHurtigberegningResults() {
        return simulationResults != null;
    }

    public void setSimulationResults(List<List<Pensjonsperiode>> simulationResults) {
        this.simulationResults = simulationResults;
    }

    public List<List<Pensjonsperiode>> getSimulationResults() {
        return simulationResults;
    }

    public SakerAndSkjemaTablePanel getSakerAndSkjemaTablePanel() {
        return sakerAndSkjemaTablePanel;
    }
}
