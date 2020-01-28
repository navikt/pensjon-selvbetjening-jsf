package no.nav.presentation.pensjon.pselv.rightcolumn;

import java.util.Date;

import no.stelvio.common.util.DateUtil;

/**
 * FormData class used by RightColumnHelper and which holds form data for "FES001 Høyrekolonna".
 *
 */
public class RightColumnPensjonFormData extends RightColumnCommonFormData {

    private static final long serialVersionUID = -621277051247138988L;

    private Double pensjonsbeholdningValue;

    private String pensjonsbeholdningDate;

    private String sivilStatus;

    private Long latestRegisteredInntekt;

    private String latestRegisteredInntektYear;

    private boolean errorOpptjeningOrSivilstand;

    private boolean isBorn1954OrLater;

    /**
     * Checks if block 'informasjon NAV har om deg' is to be visible or not. Block is visible if no aberrance/avvik did occurre
     * when calling HentBeholdningListe and/or HentOpptjeningsgrunnlag.
     *
     * @return true if 'informasjon NAV har om deg' is to be shown, false otherwise.
     */
    @Override
    public boolean isShowBlokkInfoNavHarOmDeg() {
        return !errorOpptjeningOrSivilstand;
    }

    /**
     * Checks if elements inntekt is to be visible or not.
     *
     * @return true if 'inntekt tekst' is to be shown, false otherwise.
     */
    public boolean isShowElementInntekt() {
        return latestRegisteredInntekt != null && latestRegisteredInntektYear != null;
    }

    /**
     * Checks if element 'pensjonsbeholdning tekst' is to be visible or not. The element is to be shown if user is born in 1954
     * or later AND does not have a løpende alderspensjon.
     *
     * @return true if 'pensjonsbeholdning tekst' is to be shown, false otherwise.
     */
    public boolean isShowElementPensjonsbeholdning() {
        return !isLopendeAlder() && isBorn1954OrLater;
    }

    /**
     * Check if the field pensjonsbeholdningDate and pensjonsbeholdningValue has a value, if not we dont have data to show and
     * block is not shown.
     *
     * @return true if pensjonsbeholdningDate and pensjonsbeholdningValue is set and the values should be shown in the page.
     */
    public boolean isShowPensjonsbeholdningInfo() {
        return pensjonsbeholdningDate != null && pensjonsbeholdningValue != null;
    }

    /**
     * Gets the sivilstatus
     *
     * @return the sivilstatus
     */
    public String getSivilStatus() {
        return sivilStatus;
    }

    /**
     * Gets the pensjonsbeholdning value
     *
     * @return the pensjonsbeholdning value
     */
    public Double getPensjonsbeholdningValue() {
        return pensjonsbeholdningValue;
    }

    /**
     * Gets the pensjonsbeholdningsdate
     *
     * @return the pensjonsbeholdningsdate
     */
    public String getPensjonsbeholdningDate() {
        return pensjonsbeholdningDate;
    }

    /**
     * Gets the latest registered inntekt
     *
     * @return the latest registered inntekt
     */
    public Long getLatestRegisteredInntekt() {
        return latestRegisteredInntekt;
    }

    /**
     * Gets the latest registered inntekt year
     *
     * @return the latest registered inntekt year
     */
    public String getLatestRegisteredInntektYear() {
        return latestRegisteredInntektYear;
    }

    /**
     * Sets the sivil status
     *
     * @param sivilStatus the sivil status
     */
    public void setSivilStatus(String sivilStatus) {
        this.sivilStatus = sivilStatus;
    }

    /**
     * Sets the pensjonsutbeholdningsvalue
     *
     * @param pensjonsbeholdningValue the pensjonsutbeholdningsvalue
     */
    public void setPensjonsbeholdningValue(Double pensjonsbeholdningValue) {
        this.pensjonsbeholdningValue = pensjonsbeholdningValue;
    }

    /**
     * Sets the pensjonsbeholdningsdate
     *
     * @param pensjonsbeholdningDate the pensjonsbeholdningsdate
     */
    public void setPensjonsbeholdningDate(Date pensjonsbeholdningDate) {
        this.pensjonsbeholdningDate = DateUtil.format(pensjonsbeholdningDate);
    }

    /**
     * Sets the latest registered inntekt
     *
     * @param latestRegisteredInntekt the latest registered inntekt
     */
    public void setLatestRegisteredInntekt(Long latestRegisteredInntekt) {
        this.latestRegisteredInntekt = latestRegisteredInntekt;
    }

    /**
     * Sets the latest registered inntekt year
     *
     * @param latestRegisteredInntektYear the latest registered inntekt year
     */
    public void setLatestRegisteredInntektYear(String latestRegisteredInntektYear) {
        this.latestRegisteredInntektYear = latestRegisteredInntektYear;
    }

    /**
     * Sets the error opptjening or sivilstand
     *
     * @param error the error opptjening or sivilstand
     */
    public void setErrorOpptjeningOrSivilStand(boolean error) {
        errorOpptjeningOrSivilstand = error;
    }

    /**
     * Sets the born 1954 or later flag
     *
     * @param isBorn1954OrLater the born 1954 or later flag
     */
    public void setBorn1954OrLater(boolean isBorn1954OrLater) {
        this.isBorn1954OrLater = isBorn1954OrLater;
    }
}
