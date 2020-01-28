package no.nav.presentation.pensjon.pselv.skjema.alderspensjon.personopplysninger;

import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import no.stelvio.presentation.binding.context.MessageContextUtil;

import no.nav.domain.pensjon.common.person.AnnenAdresse;

/**
 * Validator for skjemaside SkjemaPersonOpplysninger. This skjemaside contains data that we want to cross validate or check for
 * null values.
 */
public class SkjemaPersonOpplysningerValidator implements Validator {

    private static final String NORSK_KONTONUMMER_FIELD = "kontonummerNorge";
    private static final String MISSING_DATA_DEFAULT = "Feltet må fylles ut";
    private static final String ERRORMSG_MISSING_VALUES = "javax.faces.component.UIInput.REQUIRED";
    private static final String LANDLISTE_FIELD = "statsborgerskap";
    private static final String UGYLDIG_TELEFONNUMMER = "pselv.standardvalidering.ugyldigtelefonnummer";
    private static final String MOBILE_FIELD = "telefonNummerMob";
    private static final String PHONE_HOME_FIELD = "telefonNummerHjem";
    private static final String PHONE_WORK_FIELD = "telefonNummerArbeid";
    private static Log logger = LogFactory.getLog(SkjemaPersonOpplysningerValidator.class);
    private static final Pattern PATTERN_PNONE_NUMBER = Pattern.compile("([0-9\\+\\s]+)");

    /**
     * Only objects of type SkjemaPersonOpplysningerForm can be validated by this class.
     */
    @Override
    public boolean supports(Class clazz) {
        return SkjemaPersonOpplysningerForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object formObject, Errors errors) {
        SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) formObject;

        if (logger.isDebugEnabled()) {
            logger.debug("validating()");
            StringBuilder sb = new StringBuilder();
            sb.append("norskStatsborger : " + form.isNorskStatsborger());
            sb.append(". Land code : " + form.getStatsborgerskap2());
            logger.debug(sb.toString());
        }

        if (StringUtils.isEmpty(form.getKontonummerNorge())) {
            errors.rejectValue(NORSK_KONTONUMMER_FIELD, ERRORMSG_MISSING_VALUES, null, MISSING_DATA_DEFAULT);
        }

        if (!form.isNorskStatsborger() && StringUtils.isBlank(form.getStatsborgerskap2())) {
            errors.rejectValue(LANDLISTE_FIELD, ERRORMSG_MISSING_VALUES, MISSING_DATA_DEFAULT);
        }

        if (StringUtils.isNotEmpty(form.getTelefonNummerHjem())) {
            if (!validatePhoneNumber(form.getTelefonNummerHjem())) {
                Object[] param = {form.getTelefonNummerHjem()};
                errors.rejectValue(PHONE_HOME_FIELD, UGYLDIG_TELEFONNUMMER, param, "");
            }
        }

        if (StringUtils.isNotEmpty(form.getTelefonNummerArbeid())) {
            if (!validatePhoneNumber(form.getTelefonNummerArbeid())) {
                Object[] param = {form.getTelefonNummerArbeid()};
                errors.rejectValue(PHONE_WORK_FIELD, UGYLDIG_TELEFONNUMMER, param, "");
            }
        }

        if (StringUtils.isNotEmpty(form.getTelefonNummerMob())) {
            if (!validatePhoneNumber(form.getTelefonNummerMob())) {
                Object[] param = {form.getTelefonNummerMob()};
                errors.rejectValue(MOBILE_FIELD, UGYLDIG_TELEFONNUMMER, param, "");
            }
        }

        if (form.isPersonUtVandret() && !form.isDiskresjon()) {
            ValidationUtils.invokeValidator(new AnnenAdresseValidator(), form, errors);
        }

        MessageContextUtil.addMessagesOnMessageContext(errors);
    }

    private Boolean validatePhoneNumber(String toValidate) {
        return PATTERN_PNONE_NUMBER.matcher(toValidate).matches() ? Boolean.TRUE : Boolean.FALSE;
    }

    private static class AnnenAdresseValidator implements Validator {
        private static final String LANDKODE_FIELD = "landkode";
        private static final String ADRESSELINJE1 = "adresselinje1";
        private static final String NAME_UTVANDRET_ELEMENT = "utvandretAdresse";
        private static final String OBLIGATORISK_FELT = "pselv.standardvalidering.obligatoriskfelt";

        @Override
        public void validate(Object formObject, Errors errors) {
            SkjemaPersonOpplysningerForm form = (SkjemaPersonOpplysningerForm) formObject;
            AnnenAdresse addresse = form.getUtvandretAdresse();
            BindingResult bindingResult = (BindingResult) errors;

            if (isEmptyString(addresse.getLandkode())) {
                bindingResult.addError(rejectMissingMandatoryField(LANDKODE_FIELD));
            }

            if (isEmptyString(addresse.getAdresselinje1())) {
                bindingResult.addError(rejectMissingMandatoryField(ADRESSELINJE1));
            }
        }

        private FieldError rejectMissingMandatoryField(String field) {
            return new FieldError(NAME_UTVANDRET_ELEMENT, field, null, false, new String[]{OBLIGATORISK_FELT}, null, null);
        }

        /**
         * Only objects of type SkjemaPersonOpplysningerForm can be validated by this class.
         */
        @Override
        public boolean supports(Class clazz) {
            return SkjemaPersonOpplysningerForm.class.isAssignableFrom(clazz);
        }

        private boolean isEmptyString(String string) {
            return string == null || "".equalsIgnoreCase(string.trim());
        }
    }
}
