package ru.javawebinar.topjava.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@Component
public class AdminRestUserValidator extends UserValidator {
    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }
}
