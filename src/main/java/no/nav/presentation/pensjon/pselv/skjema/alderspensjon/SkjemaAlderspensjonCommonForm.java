package no.nav.presentation.pensjon.pselv.skjema.alderspensjon;

import java.util.Calendar;
import java.util.Date;

import no.stelvio.common.util.DateUtil;

import no.nav.domain.pensjon.kjerne.skjema.Skjema;
import no.nav.presentation.pensjon.pselv.common.CommonForm;
import no.nav.presentation.pensjon.pselv.common.PensjonsKalkulatorConstants;
import no.nav.presentation.pensjon.pselv.common.PselvConstants;

public abstract class SkjemaAlderspensjonCommonForm extends CommonForm {

    private static final long serialVersionUID = -5531113250047417628L;
    private static final String PAGE_ICON = "/images/pageicon/soknadalderspensjon.gif";
    private Skjema skjema;
    private SkjemaAlderspensjonCommonInputData inputData;
    private boolean showGaaVidereKnapp;
    private boolean bornBefore1943;
    private boolean bornBetweenJanNov1943;
    private boolean bornFomDec1943;
    private boolean bornFom1944;
    private String dialogboksMessage;

    /**
     * Indicates if a dialog box should be shown when the user clicks the button "lagre og gaa videre". Is default set to false,
     * must be overridden in modules where this should be different.
     */
    private boolean showDialogboks;

    public SkjemaAlderspensjonCommonForm() {
        super();
        showDialogboks = false;
        showGaaVidereKnapp = true;
    }

    public boolean isForsorgningstilleggSoknad() {
        return !inputData.getAlderspensjonSoknad();
    }

    public boolean isAldersPensjonSoknad() {
        return inputData.getAlderspensjonSoknad();
    }

    @Override
    public String getHelpKey() {
        return null;
    }

    @Override
    public boolean isEnableRightColumn() {
        return false;
    }

    public abstract String getSkjemaStepHeader();

    public Skjema getSkjema() {
        return skjema;
    }

    public void setSkjema(Skjema skjema) {
        this.skjema = skjema;
    }

    public SkjemaAlderspensjonCommonInputData getInputData() {
        return inputData;
    }

    public void setInputData(SkjemaAlderspensjonCommonInputData inputData) {
        this.inputData = inputData;
    }

    public boolean isShowGaaVidereKnapp() {
        return showGaaVidereKnapp;
    }

    public void setShowGaaVidereKnapp(boolean showGaaVidereKnapp) {
        this.showGaaVidereKnapp = showGaaVidereKnapp;
    }

    public String getDialogboksMessage() {
        return dialogboksMessage;
    }

    public void setDialogboksMessage(String dialogboksMessage) {
        this.dialogboksMessage = dialogboksMessage;
    }

    public boolean isShowDialogboks() {
        return showDialogboks;
    }

    public void setShowDialogboks(boolean showDialogboks) {
        this.showDialogboks = showDialogboks;
    }

    public void dividingIntoKull() {
        Calendar dateOfBirth = Calendar.getInstance();
        dateOfBirth.setTime(getBruker().getPid().getFodselsdato());
        int birthYear = dateOfBirth.get(Calendar.YEAR);
        int birthMnth = dateOfBirth.get(Calendar.MONTH);

        if (birthYear < PensjonsKalkulatorConstants.YEAR_1943) {
            setBornBefore1943(true);
        }

        if (birthYear == PensjonsKalkulatorConstants.YEAR_1943
                && birthMnth >= Calendar.JANUARY && birthMnth <= Calendar.NOVEMBER) {
            setBornBetweenJanNov1943(true);
        }

        if (birthYear >= PensjonsKalkulatorConstants.YEAR_1944) {
            setBornFom1944(true);
        }

        if (birthYear == PensjonsKalkulatorConstants.YEAR_1943 && birthMnth == Calendar.DECEMBER
                || birthYear >= PensjonsKalkulatorConstants.YEAR_1944) {
            setBornFomDec1943(true);
        }
    }

    public boolean isBornBefore1943() {
        return bornBefore1943;
    }

    public void setBornBefore1943(boolean bornBefore1943) {
        this.bornBefore1943 = bornBefore1943;
    }

    public boolean isBornBetweenJanNov1943() {
        return bornBetweenJanNov1943;
    }

    public void setBornBetweenJanNov1943(boolean bornBetweenJanNov1943) {
        this.bornBetweenJanNov1943 = bornBetweenJanNov1943;
    }

    public boolean isBornFom1944() {
        return bornFom1944;
    }

    public void setBornFom1944(boolean bornFom1944) {
        this.bornFom1944 = bornFom1944;
    }

    public boolean isBornFomDec1943() {
        return bornFomDec1943;
    }

    public void setBornFomDec1943(boolean bornFomDec1943) {
        this.bornFomDec1943 = bornFomDec1943;
    }

    /**
     * Check if pensjoneringstidspunkt is after 01.01.2011. 01.01.2011 is the start date of nytt regelverk.
     */
    public boolean isPensjoneringstidspunktStartDateNyttRegelverk() {
        Date iverksettelsesTidspunkt = getSkjema().getSkjemaInnledning().getIverksettelsesdato();
        return DateUtil.isBeforeByDay(PselvConstants.DATO_NYTT_REGELVERK, iverksettelsesTidspunkt, true);
    }

    @Override
    public String getPageIcon() {
        return PAGE_ICON;
    }
}
