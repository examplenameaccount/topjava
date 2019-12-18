package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

@Component
public class UserValidator implements Validator {
    protected static String USER_WITH_EMAIL_EXIST = "User with this email already exists";

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;

        if (userTo.getId() != null) {
            if (!userTo.getEmail().equalsIgnoreCase(userService.get(userTo.getId()).getEmail()) && userService.getByEmail(userTo.getEmail()) != null) {
                throw new DataIntegrityViolationException(USER_WITH_EMAIL_EXIST);
            }
        } else {
            try {
                if (userService.getByEmail(userTo.getEmail()) != null) {
                    throw new DataIntegrityViolationException(USER_WITH_EMAIL_EXIST);
                }
            } catch (NotFoundException ignore) {
            }
        }
    }
}
