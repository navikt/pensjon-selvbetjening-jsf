package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.innledning;

import java.util.Date;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import no.stelvio.common.util.DateUtil;
import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.presentation.pensjon.pselv.common.utils.PensjonDatoUtil;

public class SkjemaInnledningFormValidator implements Validator {

    private static final String DEFAULT_FALLBACK_MESSAGE = "Validation failed.";
    private static final String UP_AP_OVER_100 = "pselv.sks001.kryssvalidering.upogapoverstiger100prosent";
    private static final String INGEN_TILLEGG = "pselv.sks001.kryssvalidering.ingenvalgtetillegg";
    private static final String OBLIFELT_FEILMELDING = "pselv.standardvalidering.obligatoriskfelt";
    private static final String OBLIFELT_FEILMELDING_DEFAULT = "Feltet må fylles ut";
    private static final String MAA_SVARE_AFP = "svarAFP";
    private static final int FULL_PENSJONSGRAD = 100;
    private static final int AGE_67 = 67;

    @Override
    public void validate(Object formObject, Errors errors) {
        SkjemaInnledningForm form = (SkjemaInnledningForm) formObject;

        if (valgtFeilUttaksgrad(form)) {
            errors.reject(UP_AP_OVER_100);
        } else if (form.isForsorgningstilleggSoknad() && !form.getForsorgningstilleggBarn() && !form.getForsorgningstilleggEPS()) {
            errors.reject(INGEN_TILLEGG, DEFAULT_FALLBACK_MESSAGE);
        } else if (form.isAldersPensjonSoknad()) {
            validateAlderspensjonSoknad(form, errors);
        }

        MessageContextUtil.addMessagesOnMessageContext(errors);
    }

    private boolean valgtFeilUttaksgrad(SkjemaInnledningForm form) {
        boolean valgtFeil = false;

        if (form.getValgtPensjoneringstidspunkt() == null) {
            return true;
        }

        Date date = DateUtil.parse(form.getValgtPensjoneringstidspunkt());
        String valgtGrad = form.getValgtPensjoneringsGrad();

        if (valgtGrad == null) {
            return true;
        }

        if (!isLegalUttaksgrad(Integer.parseInt(valgtGrad.substring(0, valgtGrad.length() - 2)), form.getUforeGrad())) {
            valgtFeil = true;

            if (chosenUttakAfter67Plus1Month(form.getBruker().getPid().getFodselsdato(), date)) {
                valgtFeil = false;
            }
        }

        return valgtFeil;
    }

    private boolean chosenUttakAfter67Plus1Month(Date fodselsdato, Date uttakstidspunkt) {
        Date dateWhenUserIs67And1Month = PensjonDatoUtil.firstDayOfMonthAfterUserTurnsByAge(fodselsdato, AGE_67);
        return DateUtil.isAfterByDay(uttakstidspunkt, dateWhenUserIs67And1Month, true);
    }

    private boolean isLegalUttaksgrad(Integer uttaksgrad, Integer uforeGrad) {
        if (uttaksgrad == null) {
            return false;
        }

        if (uforeGrad == null) {
            return true;
        }

        return uttaksgrad + uforeGrad <= FULL_PENSJONSGRAD;
    }

    /**
     * Only objects of type SkjemasideFamilieforholdForm can be validated by this class.
     */
    @Override
    public boolean supports(Class clazz) {
        return clazz.equals(SkjemaInnledningForm.class);
    }

    private void validateAlderspensjonSoknad(SkjemaInnledningForm form, Errors errors) {
        if (form.isBornFom1944() && form.getSvarAFP() == null) {
            errors.rejectValue(MAA_SVARE_AFP, OBLIFELT_FEILMELDING, OBLIFELT_FEILMELDING_DEFAULT);
        }
    }
}
