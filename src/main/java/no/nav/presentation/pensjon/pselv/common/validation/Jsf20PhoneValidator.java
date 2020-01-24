package no.nav.presentation.pensjon.pselv.common.validation;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.GenericValidator;

import static no.stelvio.presentation.binding.context.MessageContextUtil.getMessage;

public class Jsf20PhoneValidator implements Validator {

    public static final String PHONE_MESSAGE_ID = "pselv.standardvalidering.ugyldigtelefonnummer";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if (isValidPhoneNumber(value)) {
            return;
        }

        throw new ValidatorException(new FacesMessage(getMessage(PHONE_MESSAGE_ID, new Object[] {value.toString()})));
    }

    private boolean isValidPhoneNumber(Object value) {
        if (StringUtils.isBlank(value.toString())) {
            return true;
        }

        if (!Pattern.matches("\\+?[0-9\\s]*", value.toString().trim())) {
            return false;
        }

        return GenericValidator.maxLength(value.toString().trim(), 15);
    }
}
