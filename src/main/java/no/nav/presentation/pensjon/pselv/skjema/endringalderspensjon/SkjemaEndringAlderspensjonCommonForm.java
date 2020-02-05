package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon;

import no.stelvio.common.util.DateUtil;

import no.nav.presentation.pensjon.pselv.common.CommonForm;

/**
 * Common form baseclass for SKS016 and SKS017.
 */
public abstract class SkjemaEndringAlderspensjonCommonForm extends CommonForm {

    private static final long serialVersionUID = 1L;
    private static final String PAGE_ICON = "/images/pageicon/soknadendringalderspensjon.gif";
    private static final String PAGE_TITLE = "pselv.sks016.statisk_tekst.tittel";
    private SkjemaEndringAlderspensjonCommonInputData inputData;

    @Override
    public String getHelpKey() {
        return null;
    }

    @Override
    public boolean isEnableRightColumn() {
        return false;
    }

    @Override
    public String getPageIcon() {
        return PAGE_ICON;
    }

    @Override
    public String getPageTitle() {
        return PAGE_TITLE;
    }

    public SkjemaEndringAlderspensjonCommonInputData getInputData() {
        return inputData;
    }

    public void setInputData(SkjemaEndringAlderspensjonCommonInputData inputData) {
        this.inputData = inputData;
    }

    public String getLopendeVirkFomAsString() {
        return DateUtil.format(getInputData().getLopendeVirkFom());
    }
}
