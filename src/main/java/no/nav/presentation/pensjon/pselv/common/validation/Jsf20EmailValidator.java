package no.nav.presentation.pensjon.pselv.common.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import static no.stelvio.presentation.binding.context.MessageContextUtil.getMessage;
import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.validator.GenericValidator.isEmail;

public class Jsf20EmailValidator implements Validator {

    public static final String EMAIL_MESSAGE_ID = "pselv.standardvalidering.epostadresse";

    @Override
    public void validate(FacesContext facesContext, UIComponent uiComponent, Object value) throws ValidatorException {
        if (isBlank(value.toString()) || isEmail(value.toString().trim())) {
            return;
        }

        throw new ValidatorException(new FacesMessage(getMessage(EMAIL_MESSAGE_ID, new Object[]{value.toString()})));
    }
}
