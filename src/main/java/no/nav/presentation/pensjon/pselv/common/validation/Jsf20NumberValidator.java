package no.nav.presentation.pensjon.pselv.common.validation;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import org.apache.commons.lang3.StringUtils;

import static no.stelvio.presentation.binding.context.MessageContextUtil.getMessage;

public class Jsf20NumberValidator implements Validator {

    public static final String NUMBER_MESSAGE_ID = "pselv.standardvalidering.desimaltall";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if (isValidNumber(value)) {
            return;
        }

        throw new ValidatorException(new FacesMessage(getMessage(NUMBER_MESSAGE_ID, new Object[]{value.toString()})));
    }

    private boolean isValidNumber(Object value) {
        // This is just a copy from the regex-validation in inntektogskatt.xhtml and contains an error. The regex is wrong, and it is reported as an production error.
        if (value == null || StringUtils.isBlank(value.toString())) {
            return true;
        }

        return Pattern.matches("\\+?[0-9\\s]*", value.toString().trim());
    }
}
