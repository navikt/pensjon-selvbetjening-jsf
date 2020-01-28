package no.nav.presentation.pensjon.pselv.rightcolumn;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import no.nav.presentation.pensjon.pselv.tag.PSELVSecurityFunctions;

/**
 * Common functionality for the different variations of the right column
 *
 */
public abstract class RightColumnCommonFormData implements Serializable {

    private static final long serialVersionUID = -470508843395848846L;

    private List<String> lopendeYtelser;

    private Float latestPensjonsutbetalingValue;

    private Date latestPensjonsutbetalingDate;

    private Date nextPensjonsutbetalingDate;

    private boolean errorNestePensjonsutbetaling;

    private boolean errorLatestPensjonsutbetaling;

    private boolean lopendeAlder;

    private boolean lopendeAlderWithUttaksgradZero;

    private boolean lopendeUforetrygd;

    /**
     * Checks if block 'løpende ytelser' is to be visible or not. Løpende ytelser is to be shown if user has at least one
     * løpende ytelse, BUT is not to be shown if user is logged on with security LAV and if logged on user only has begrenset
     * fullmakt.
     *
     * @return true if 'løpende ytelser' is to be shown, false otherwise.
     */
    public boolean isShowBlokkLopendeYtelser() {
        return lopendeYtelser != null
                && !lopendeYtelser.isEmpty()
                && isNotLopendeAlderWithUttaksgradZeroAndNoLatestUtbetaling()
                && isNotBegrensetFullmakt()
                && isNotLavSikkerhet();
    }

    /**
     * Determine if block 'pensjonsutbetalinger' is to be shown. The block will be shown if one or both of the elements 'neste utbetalingsdato'
     * and 'siste utebetaling' is shown, and if bruker doesn't have begrenset fullmakt and 'LAV' security.
     *
     * @return true if 'pensjonsutbetalinger' is to be shown, false otherwise.
     */
    public boolean isShowBlokkPensjonsutbetalinger() {
        return (isShowElementNextUtbetalingDato() || isShowElementLatestUtbetaling())
                && isNotBegrensetFullmakt()
                && isNotLavSikkerhet();
    }

    /**
     * Determine if block 'info nav har om deg' is to be shown.
     *
     * @return true if 'info nav har om deg' is to be shown, false otherwise.
     */
    public abstract boolean isShowBlokkInfoNavHarOmDeg();

    /**
     * Checks if user has at least one lopende ytelse (with latest payment no longer than 2 months ago), though at the moment
     * payments are 0,-.
     *
     * @return true if lopende ytelse
     */
    public boolean isShowElementNoFuturePayments() {
        return lopendeYtelser != null
                && !lopendeYtelser.isEmpty()
                && nextPensjonsutbetalingDate == null
                && latestPensjonsutbetalingDate != null;
    }

    /**
     * Checks if aberrance message/avviksmelding 'utbetalingsdatoerKanIkkeVises' is to be visible or not; message is to be shown
     * if no aberrance/avvik did occurre when calling HentPeriodisertUtbetalingListe and/or BestemGjeldendeVedtak and/or
     * HentFremtidigeUtbetalingsDatoer.
     *
     * @return true if aberrance message/avviksmelding is to be shown, false otherwise.
     */
    public boolean isShowAvvikPensjonsutbetaling() {
        return errorLatestPensjonsutbetaling || errorNestePensjonsutbetaling;
    }

    /**
     * Checks if element 'neste utbetaling' is to be visible or not.
     *
     * @return true if 'neste utbetaling' is to be shown, false otherwise.
     */
    public boolean isShowElementNextUtbetaling() {
        return (isShowElementNextUtbetalingDato() || isShowElementLatestUtbetaling()) && !errorNestePensjonsutbetaling;
    }

    /**
     * Checks if element 'neste utbetaling dato' is to be visible or not. The element is to be shown if there exists a løpende
     * ytelse with netto payment greater than zero.
     *
     * @return true if 'neste utbetaling dato' is to be shown, false otherwise.
     */
    public boolean isShowElementNextUtbetalingDato() {
        /*
         * Meaning: if nextPensjonsutbetalingDate == null then there exists no løpende vedtak with netto payment greater than
         * zero.
         */
        return nextPensjonsutbetalingDate != null;
    }

    /**
     * Checks that user does not have lopende alder with uttaksgrad zero and no latest utbetaling
     *
     * @return true if user does not have lopende alder with uttaksgrad zero and no latest utbetaling
     */
    private boolean isNotLopendeAlderWithUttaksgradZeroAndNoLatestUtbetaling() {
        return !(isLopendeAlderWithUttaksgradZero() && !isShowElementLatestUtbetaling());
    }

    /**
     * Checks if element 'forrige utbetaling' is to be visible or not. The element is to be shown if the most recent
     * ytelses-payment is no more than 2 months old.
     *
     * @return true if 'forrige utbetaling' is to be shown, false otherwise.
     */
    public boolean isShowElementLatestUtbetaling() {
        /*
         * Meaning: if latestPensjonsutbetalingDate == null then the date of the most recent utbetaling was more than 2 months
         * old.
         */
        return latestPensjonsutbetalingDate != null && !errorLatestPensjonsutbetaling;
    }

    /**
     * Checks if user is logged on with security higher than LAV.
     *
     * @return true user does NOT have Role LAV, false otherwise.
     */
    public boolean isNotLavSikkerhet() {
        return !isLavSikkerhet();
    }

    /**
     * Checks if user is logged on with low security.
     *
     * @return true user has Role LAV, false otherwise.
     */
    public boolean isLavSikkerhet() {
        return !PSELVSecurityFunctions.isUserInMedium() && !PSELVSecurityFunctions.isUserInHoy();
    }

    /**
     * Checks if logged on user has begrenset fullmakt.
     *
     * @return true user has Role FULLMAKT, false otherwise.
     */
    public boolean isBegrensetFullmakt() {
        return PSELVSecurityFunctions.isFullmakt();
    }

    /**
     * Checks if logged on user does not have begrenset fullmakt.
     *
     * @return true user does not have Role FULLMAKT, false otherwise.
     */
    public boolean isNotBegrensetFullmakt() {
        return !isBegrensetFullmakt();
    }

    public List<String> getLopendeYtelser() {
        return lopendeYtelser;
    }

    public void setLopendeYtelser(List<String> lopendeYtelser) {
        this.lopendeYtelser = lopendeYtelser;
    }

    public Float getLatestPensjonsutbetalingValue() {
        return latestPensjonsutbetalingValue;
    }

    public void setLatestPensjonsutbetalingValue(Float latestPensjonsutbetalingValue) {
        this.latestPensjonsutbetalingValue = latestPensjonsutbetalingValue;
    }

    public Date getLatestPensjonsutbetalingDate() {
        return latestPensjonsutbetalingDate;
    }

    public void setLatestPensjonsutbetalingDate(Date latestPensjonsutbetalingDate) {
        this.latestPensjonsutbetalingDate = latestPensjonsutbetalingDate;
    }

    public Date getNextPensjonsutbetalingDate() {
        return nextPensjonsutbetalingDate;
    }

    public void setNextPensjonsutbetalingDate(Date nextPensjonsutbetalingDate) {
        this.nextPensjonsutbetalingDate = nextPensjonsutbetalingDate;
    }

    public boolean isErrorNestePensjonsutbetaling() {
        return errorNestePensjonsutbetaling;
    }

    public void setErrorNestePensjonsutbetaling(boolean errorNestePensjonsutbetaling) {
        this.errorNestePensjonsutbetaling = errorNestePensjonsutbetaling;
    }

    public boolean isErrorLatestPensjonsutbetaling() {
        return errorLatestPensjonsutbetaling;
    }

    public void setErrorLatestPensjonsutbetaling(boolean errorLatestPensjonsutbetaling) {
        this.errorLatestPensjonsutbetaling = errorLatestPensjonsutbetaling;
    }

    public boolean isLopendeAlder() {
        return lopendeAlder;
    }

    public void setLopendeAlder(boolean lopendeAlder) {
        this.lopendeAlder = lopendeAlder;
    }

    public boolean isLopendeAlderWithUttaksgradZero() {
        return lopendeAlderWithUttaksgradZero;
    }

    public void setLopendeAlderWithUttaksgradZero(boolean lopendeAlderWithUttaksgradZero) {
        this.lopendeAlderWithUttaksgradZero = lopendeAlderWithUttaksgradZero;
    }

    public boolean isLopendeUforetrygd() {
        return lopendeUforetrygd;
    }

    public void setLopendeUforetrygd(boolean lopendeUforetrygd) {
        this.lopendeUforetrygd = lopendeUforetrygd;
    }
}
