package ru.javawebinar.topjava.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.exception.NotFoundException;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class ProfileUserUIValidator extends UserValidator {
    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;

        if (userTo.getId() != null) {
            if (!userTo.getEmail().equalsIgnoreCase(userService.get(userTo.getId()).getEmail()) && userService.getByEmail(userTo.getEmail()) != null) {
                errors.rejectValue("email", "Exist.userForm.email");
            }
        } else {
            try {
                if (userService.getByEmail(userTo.getEmail()) != null) {
                    errors.rejectValue("email", "Exist.userForm.email");
                }
            } catch (NotFoundException ignore) {
            }
        }
    }
}
