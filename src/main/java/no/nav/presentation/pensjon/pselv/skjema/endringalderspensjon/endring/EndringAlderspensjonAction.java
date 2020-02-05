package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring;

import static no.stelvio.common.util.DateUtil.isAfterByDay;
import static no.stelvio.common.util.DateUtil.isBeforeByDay;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;

import no.nav.domain.pensjon.kjerne.kodetabeller.SakTypeCode;
import no.nav.domain.pensjon.kjerne.sak.Sak;
import no.nav.domain.pensjon.kjerne.vedtak.Vedtak;
import no.nav.presentation.pensjon.pselv.common.PselvUtil;
import no.nav.presentation.pensjon.pselv.common.enums.EndringAlderspensjonState;
import no.nav.presentation.pensjon.pselv.common.session.PselvTransferObject;
import no.nav.presentation.pensjon.pselv.skjema.alderspensjon.SkjemaDataForPreComplementing;
import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.SkjemaEndringAlderspensjonCommonAction;
import no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.SkjemaEndringAlderspensjonCommonInputData;

/**
 * The action for endringalderspensjon
 *
 */
public class EndringAlderspensjonAction extends SkjemaEndringAlderspensjonCommonAction {
    private static final String INKLUDEREOPPTJNOKKELINFO = "pselv.sks016.statisk_tekst.inkludereopptjeningnokkelopplysninger";

    private static final String ENDREAPNOKKELINFO = "pselv.sks016.statisk_tekst.endrealderspensjonnokkelopplysninger";

    private PselvTransferObject pselvTransferObject;

    /**
     * The action delegate
     */
    private EndringAlderspensjonActionDelegate actionDelegate;

    /**
     * The form populator
     */
    private EndringAlderspensjonFormPopulator formPopulator;

    /**
     * Creates form object
     *
     * @param context the RequestContext instance
     * @return the form object
     * @throws Exception exception from spring exception if creating form object fails
     */
    @Override
    protected Object createFormObject(RequestContext context) throws Exception {
        if (context.getFlowScope().get(SKJEMA_INPUT_DATA) == null) {
            SkjemaEndringAlderspensjonCommonInputData inputData = new SkjemaEndringAlderspensjonCommonInputData();
            context.getFlowScope().put(SKJEMA_INPUT_DATA, inputData);
        }

        EndringAlderspensjonForm form = (EndringAlderspensjonForm) super.createFormObject(context);
        populateForm(form);

        return form;
    }

    /**
     * Updates inputData with state based the chosen option.
     *
     * @param context the RequestContext instance
     * @return the success() Event
     */
    public Event updateInputData(RequestContext context) {
        EndringAlderspensjonForm form = (EndringAlderspensjonForm) super.getFormObject(context);
        SkjemaEndringAlderspensjonCommonInputData inputData = form.getInputData();
        if (form.hasUserChosenEndreUttaksgradAndInkluderNyOpptjening()) {
            inputData.setEndringAlderspensjonState(EndringAlderspensjonState.KAN_SOKE_ENDRE_UTTAKSGRAD);
        } else if (form.hasUserChosenInkluderNyOpptjening()) {
            inputData.setEndringAlderspensjonState(EndringAlderspensjonState.KAN_SOKE_INKLUDER_NY_OPPTJENING);
            inputData.setForsteVirkningstidspunkt(form.getInkluderOpptjeningstidspunkt());
        } else {
            inputData.setEndringAlderspensjonState(EndringAlderspensjonState.KAN_SOKE_INKLUDER_NY_OPPTJENING);
            inputData.setUttakstidspunkt(form.getGjeldendeUttakstidspunkt());
            inputData.setUttaksgrad(form.getGjeldendeUttaksgrad());
            inputData.setForsteVirkningstidspunkt(form.getInkluderOpptjeningstidspunkt());
        }
        form.setShowLiteBox(false);
        return success();
    }

    public Event brukerHasChosenDate67MAndHasLopendeUfore(RequestContext context){
        EndringAlderspensjonForm form = (EndringAlderspensjonForm) super.getFormObject(context);
        SkjemaEndringAlderspensjonCommonInputData inputData = form.getInputData();

        Date brukers67M = calculateFirstDayInMonthAfterPersonTurns67(form.getBruker().getPid().getFodselsdato());
        if (form.hasUserChosenEndreUttaksgradAndInkluderNyOpptjening() && inputData.getUttaksgrad() < 100 && form.getHasLopendeUforeAt67M()
                && inputData.getUttakstidspunkt().equals(brukers67M)) {
            form.setShowLiteBox(true);
            return yes();
        }
        form.setShowLiteBox(false);
        return no();
    }

    /**
     * Fills the summary information from the current step into collapsed element on gui
     *
     * @param context the RequestContext instance
     * @return the success() Events
     */
    @Override
    public Event setTypedKeyInformation(RequestContext context) {
        EndringAlderspensjonForm form = (EndringAlderspensjonForm) super.getFormObject(context);
        String typedKeyInformation;
        if (!form.kreverHandlingsvalg() || form.hasUserChosenEndreUttaksgradAndInkluderNyOpptjening()) {
            typedKeyInformation = createTypedKeyInformationEndringUttaksgrad(form);
        } else {
            typedKeyInformation = createTypedKeyInformationInkluderNyOpptjening(form);
        }
        super.setTypedKeyInformationCurrentStep(context, typedKeyInformation);
        return success();
    }

    /**
     * Populates the form based on if user can apply or not.
     *
     * @param form the form object
     */
    private void populateForm(EndringAlderspensjonForm form) {
        SkjemaDataForPreComplementing skjemaData = getMappingObjectKalkulatorToSkjema();
        form.setSkjema(skjemaData);
        if (brukerKanSoke(form)) {
            Pid pid = form.getBruker().getPid();
            List<Vedtak> allevedtak = actionDelegate.getAlleVedtak(pid);

            if (userWillBeAbleToChooseUttakstidspunktEqualTo67M(form)) {
                List<Vedtak> lopendeVedtakAt67MListe = actionDelegate.getLopendeVedtakAt67M(pid, calculateFirstDayInMonthAfterPersonTurns67(pid.getFodselsdato()));

                setHasLopendeUforeGjenlevOrFamplAt67M(form, lopendeVedtakAt67MListe);
                setHasLopendeUforeAt67M(form, lopendeVedtakAt67MListe);
            }

            Sak alderspensjonSak = actionDelegate.getAlderspensjonSakListeLopendeAndTilBehandling(pid);
            formPopulator.populateFormUserCanApply(allevedtak, alderspensjonSak, form);
        } else {
            formPopulator.fillError(form);
        }
    }

    protected boolean userWillBeAbleToChooseUttakstidspunktEqualTo67M(EndringAlderspensjonForm form) {

        Date bruker67M = calculateFirstDayInMonthAfterPersonTurns67(form.getBruker().getPid().getFodselsdato());
        int sperrefrist = PselvUtil.fetchSperreVedtakFremITid();
        Date latestChooseableDateDueToSPERREFRIST = DateUtil.getFirstDayOfMonth(DateUtil.getRelativeDateByMonth(Calendar.getInstance().getTime(), sperrefrist+1));
        Date firstDayInNextMonth = DateUtil.getFirstDayOfMonth(DateUtil.getRelativeDateByMonth(Calendar.getInstance().getTime(), 1));

        return (isAfterByDay(bruker67M, firstDayInNextMonth, true)) && (isBeforeByDay(bruker67M, latestChooseableDateDueToSPERREFRIST, true));
    }

    private void setHasLopendeUforeGjenlevOrFamplAt67M(EndringAlderspensjonForm form, List<Vedtak> lopendeVedtakAt67M){

        boolean hasLopendeUforeGjenlevOrFamplAt67M = lopendeVedtakAt67M
                .stream()
                .anyMatch(v -> v.getSakstype().isCodeEqualTo(SakTypeCode.UFOREP) || v.getSakstype().isCodeEqualTo(SakTypeCode.GJENLEV) || v.getSakstype()
                        .isCodeEqualTo(SakTypeCode.FAM_PL));

        form.setHasLopendeUforeGjenlevOrFamplAt67M(hasLopendeUforeGjenlevOrFamplAt67M);
    }

    private void setHasLopendeUforeAt67M(EndringAlderspensjonForm form, List<Vedtak> lopendeVedtakAt67M){

        boolean hasLopendeUforeAt67M = lopendeVedtakAt67M
                .stream()
                .anyMatch(v -> v.getSakstype().isCodeEqualTo(SakTypeCode.UFOREP));

        form.setHasLopendeUforeAt67M(hasLopendeUforeAt67M);
    }

    /**
     * Gets the object used for mapping values from pensjonskalkulator into skjema. The object is set on PselvTransferObject by
     * the last step in pensjonkalkulatoren - SIS005 Resultat hva hvis.
     *
     * @return SkjemaDataForPreComplementing
     */
    private SkjemaDataForPreComplementing getMappingObjectKalkulatorToSkjema() {
        SkjemaDataForPreComplementing skjemaData = null;
        if (pselvTransferObject.containsKey(PselvTransferObject.CALC_TO_SKJEMA_ENDRING_MAPPING)) {
            skjemaData = (SkjemaDataForPreComplementing) pselvTransferObject
                    .get(PselvTransferObject.CALC_TO_SKJEMA_ENDRING_MAPPING);
        }
        return skjemaData;
    }

    /**
     * Creates key information when user has chosen the option 'Endring uttaksgrad'.
     *
     * @param form The form
     * @return String
     */
    private String createTypedKeyInformationEndringUttaksgrad(EndringAlderspensjonForm form) {
        SkjemaEndringAlderspensjonCommonInputData inputData = form.getInputData();
        return getMsg(ENDREAPNOKKELINFO, new String[] {inputData.getUttaksgrad().toString(),
                DateUtil.format(inputData.getUttakstidspunkt())});
    }

    /**
     * Creates key information when user has chosen the option 'Inkluder ny opptjening'.
     *
     * @param form The form
     * @return String
     */
    private String createTypedKeyInformationInkluderNyOpptjening(EndringAlderspensjonForm form) {
        SkjemaEndringAlderspensjonCommonInputData inputData = form.getInputData();
        return getMsg(INKLUDEREOPPTJNOKKELINFO, new String[] {DateUtil.format(inputData.getForsteVirkningstidspunkt())});
    }

    /**
     * Returns wheter user can apply
     *
     * @param endringAlderspensjonForm the form object
     * @return the indicator wheter user can apply
     */
    private boolean brukerKanSoke(EndringAlderspensjonForm endringAlderspensjonForm) {
        return endringAlderspensjonForm.getInputData() != null
                && endringAlderspensjonForm.getInputData().getEndringAlderspensjonState().getUserCanApplyEndringAlderspensjon();
    }

    private Date calculateFirstDayInMonthAfterPersonTurns67(Date dateOfBirth) {
        Date dateOf67m = DateUtil.getRelativeDateByYear(dateOfBirth, 67);
        dateOf67m = DateUtil.getRelativeDateByMonth(dateOf67m, 1);
        dateOf67m = DateUtil.getFirstDayOfMonth(dateOf67m);

        return dateOf67m;
    }

    /**
     * Sets the form populator
     *
     * @param formPopulator the instance of the form populator
     */
    public void setFormPopulator(EndringAlderspensjonFormPopulator formPopulator) {
        this.formPopulator = formPopulator;
    }

    /**
     * Sets the action delegate
     *
     * @param actionDelegate the instance of the action delegate
     */
    public void setActionDelegate(EndringAlderspensjonActionDelegate actionDelegate) {
        this.actionDelegate = actionDelegate;
    }

    /**
     * Gets the pselv transfer object
     *
     * @param pselvTransferObject the pselvTransferObject to set
     */
    public void setPselvTransferObject(PselvTransferObject pselvTransferObject) {
        this.pselvTransferObject = pselvTransferObject;
    }
}
