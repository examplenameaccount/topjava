package ru.javawebinar.topjava.web.validator;

import org.springframework.dao.DataIntegrityViolationException;
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

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if (user.getId() != null) {
            if (!user.getEmail().equalsIgnoreCase(userService.get(user.getId()).getEmail()) && userService.getByEmail(user.getEmail()) != null) {
                throw new DataIntegrityViolationException(USER_WITH_EMAIL_EXIST);
            }
        } else {
            try {
                if (userService.getByEmail(user.getEmail()) != null) {
                    throw new DataIntegrityViolationException(USER_WITH_EMAIL_EXIST);
                }
            } catch (NotFoundException ignore) {
            }
        }
    }
}
