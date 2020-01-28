package no.nav.pensjon.selv.service.fake;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FakeValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }

    @Override
    public void validate(Object o, Errors errors) {
    }
}
