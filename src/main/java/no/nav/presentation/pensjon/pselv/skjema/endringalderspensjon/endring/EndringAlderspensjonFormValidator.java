package no.nav.presentation.pensjon.pselv.skjema.endringalderspensjon.endring;

import static no.stelvio.common.util.DateUtil.isAfterByDay;

import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import no.stelvio.common.util.DateUtil;
import no.stelvio.domain.person.Pid;
import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.presentation.pensjon.pselv.common.PselvUtil;

/**
 * The form validator for the module SKS016 Skjemaside endring alderspensjon.
 *
 * */
public class EndringAlderspensjonFormValidator implements Validator {
    private static final String FORTIDLIGAENDRE = "pselv.sks016.kryssvalidering.fortidligaendre";

    private static final String OBLIGATORISK_MELDING = "pselv.standardvalidering.obligatoriskfelt";

    private static final String OVERSKRIFTENDREALDER = "pselv.sks016.statisk_tekst.overskriftendrealder";

    private static final String FORHOYFEILMELDING = "pselv.sks016.kryssvalidering.upogapoverstiger100prosent";

    /**
     * Mandatory fields
     */
    private static final String FIELD_NY_UTTAKSGRAD = "uttaksgrad";

    private static final String FIELD_DATO_NY_UTTAKGRAD = "uttakstidspunkt";

    private static final Integer SISTE_AAR_GAMMELT_REGELVERK = 2010;

    private static final int YEAR_1943 = 1943;

    private static final int AGE_67 = 67;

    private static final int ONE_MONTH = 1;

    private static final int FULL_PENSJONSGRAD = 100;

    /**
     * Returns wheter the class is supported
     *
     * @param clazz the class to check support for
     * @return the indicator for wheter class is supported
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean supports(Class clazz) {
        return clazz.equals(EndringAlderspensjonForm.class);
    }

    /**
     * Validates the form and optionally sets error messages
     *
     * @param target the form object
     * @param errors the errors object
     */
    @Override
    public void validate(Object target, Errors errors) {
        if (!(target instanceof EndringAlderspensjonForm)) {
            return;
        }
        EndringAlderspensjonForm form = (EndringAlderspensjonForm) target;

        boolean feltMangler = false;
        if (form.kreverHandlingsvalg() && ikkeValgtHandling(form)) {
            errors.reject(OVERSKRIFTENDREALDER);
            feltMangler = true;
        }
        if (!form.kreverHandlingsvalg() || form.hasUserChosenEndreUttaksgradAndInkluderNyOpptjening()) {
            if (ikkeValgtUttaksgrad(form)) {
                errors.rejectValue(FIELD_NY_UTTAKSGRAD, OBLIGATORISK_MELDING);
                feltMangler = true;
            }
            if (ikkeValgtUttakstidspunkt(form)) {
                errors.rejectValue(FIELD_DATO_NY_UTTAKGRAD, OBLIGATORISK_MELDING);
                feltMangler = true;
            }
            if (!feltMangler && valgtFeilUttaksgrad(form)) {
                errors.reject(FORTIDLIGAENDRE);
            }
            // Sir 235226, rub2812
            if (!feltMangler && valgtForHoy(form)) {
                errors.reject(FORHOYFEILMELDING);
            }
        }
        MessageContextUtil.addMessagesOnMessageContext(errors);
    }

    /**
     * Returns wheter handlingsvalg was not chosen
     *
     * @param form the form object
     * @return wheter handlingvalg was not chosen
     */
    private boolean ikkeValgtHandling(EndringAlderspensjonForm form) {
        return form.getHandlingsValg() == null;
    }

    /**
     * Returns wheter uttaksgrad was not chosen
     *
     * @param form the form object
     * @return wheter uttaksgrad was not chosen
     */
    private boolean ikkeValgtUttaksgrad(EndringAlderspensjonForm form) {
        return StringUtils.isBlank(form.getUttaksgrad());
    }

    /**
     * Returns wheter uttakstidspunkt was not chosen
     *
     * @param form the form object
     * @return wheter uttakstidspunkg was not chosen
     */
    private boolean ikkeValgtUttakstidspunkt(EndringAlderspensjonForm form) {
        return StringUtils.isBlank(form.getUttakstidspunkt());
    }

    /**
     * Returns wheter the combination of uttaksgrad and uttakstidspunkt is invalid
     *
     * @param form the form object
     * @return wheter invalid combination of uttaksgrad and uttakstidspunkt was chosen
     */
    private boolean valgtFeilUttaksgrad(EndringAlderspensjonForm form) {
        boolean valgtFeil = false;
        if (valgtUttaksgradGradert(form)) {
            Calendar forsteLovligeTidspunktForGradertUttak = Calendar.getInstance();
            /* 10.9 Chekcs if "uttak fram i tid" (if exists) is active/gjeldende the day before chosen uttakstidspunkt. */
            if (DateUtil.isBeforeDay(form.getFomFramITid(), DateUtil.parse(form.getUttakstidspunkt()))) {
                forsteLovligeTidspunktForGradertUttak.setTime(form.getFomFramITid());
            } else {
                forsteLovligeTidspunktForGradertUttak.setTime(form.getGjeldendeUttakstidspunkt());
            }
            forsteLovligeTidspunktForGradertUttak.add(Calendar.MONTH, PselvUtil.fetchSperreEndringAvUttaksgrad());

            //SIR 213716 - this cross validation message should not be triggered if the user is born in 1943 and
            //the last vedtak is from 2010
            if (DateUtil.parse(form.getUttakstidspunkt()).before(forsteLovligeTidspunktForGradertUttak.getTime())
                    && !fodt1943OgForrigeVedtakI2010(form)) {
                valgtFeil = true;
            }
        }

        return valgtFeil;
    }

    /**
     * Check if the combination of uttaksgrad and uforegrad is to high and the uttakstidspunkt is before user is 67 and one
     * month.
     *
     * @param form The {@link EndringAlderspensjonForm} to use.
     * @return true if the combination of uforegrad and uttaksgrad is to high
     */
    private boolean valgtForHoy(EndringAlderspensjonForm form) {
        boolean valgtFeil = false;
        if (!isLegalUttaksgrad(form.getInputData().getUttaksgrad(), form.getUforegrad())) {
            valgtFeil = true;
            if (chosenUttaktidspunktEqualOrAfter67M(form.getBruker().getPid(), form.getInputData().getUttakstidspunkt())) {
                valgtFeil = false;
            }
        }
        return valgtFeil;
    }

    /**
     * Checks if the chosen uttakstidspunkt is after the month after user turns 67
     *
     * @param pid the users pid
     * @param uttakstidspunkt the uttakstidspunkt
     * @return the indicator showing if the user has chosen uttakstidspunkt after the month after user turns 67
     */
    private boolean chosenUttaktidspunktEqualOrAfter67M(Pid pid, Date uttakstidspunkt) {
        Date fodselsDato = pid.getFodselsdato();
        Date dateWhenUserIs67And1Month = DateUtils.addYears(fodselsDato, AGE_67);
        dateWhenUserIs67And1Month = DateUtil.getFirstDayOfMonth(DateUtils.addMonths(dateWhenUserIs67And1Month, ONE_MONTH));

        return (isAfterByDay(uttakstidspunkt, dateWhenUserIs67And1Month, true));
    }

    /**
     * Check if uttaksgrad is less or equal to 100 - uforegrad
     *
     * @param uttaksgrad the uttaksgrad
     * @param uforeGrad the uforegrad
     * @return the indicator for wheter uttaksgrad is legal
     */
    private boolean isLegalUttaksgrad(Integer uttaksgrad, Integer uforeGrad) {
        if (uttaksgrad == null) {
            return false;
        }
        if (uforeGrad == null) {
            return true;
        }
        if (uttaksgrad + uforeGrad > FULL_PENSJONSGRAD) {
            return false;
        }
        if (uforeGrad >= 80) {
            return false;
        }
        return true;
    }

    /**
     * Determine if the user has selected a partial uttaksgrad in view (not equal to 100% or 0%)
     *
     * @param form The form to get the selected uttaksgrad from
     * @return True if the uttaksgrad is selected, and is different from 100% and 0%, false otherwise
     */
    private boolean valgtUttaksgradGradert(EndringAlderspensjonForm form) {
        return valgtUttaksgrad(form) && Integer.parseInt(form.getUttaksgrad()) != 100
                && Integer.parseInt(form.getUttaksgrad()) != 0;
    }

    /**
     * Returns true if uttaksgrad was chosen
     *
     * @param form the form object
     * @return true if uttaksgrad is selected in view, false otherwise
     */
    private boolean valgtUttaksgrad(EndringAlderspensjonForm form) {
        return !ikkeValgtUttaksgrad(form);
    }

    /**
     * Determine if the user is born in 1943 and last vedtak is from 2010 (gammelt regelverk)
     *
     * @param form The form to get the user and last vedtak from
     * @return True if the user is born in 1943 and last vedtak is from 2010, false otherwise
     */
    private boolean fodt1943OgForrigeVedtakI2010(EndringAlderspensjonForm form) {
        int birthYear = Pid.get4DigitYearOfBirth(form.getBruker().getPid().getPid());
        Date forrigeVedtakFom = form.getEndringOpptjeningAldersvedtakFom();

        return birthYear == YEAR_1943 && DateUtil.getYear(forrigeVedtakFom) == SISTE_AAR_GAMMELT_REGELVERK;
    }
}
