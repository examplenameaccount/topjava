package ru.javawebinar.topjava.web.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.UserTo;

//http://docs.spring.io/spring/docs/current/spring-framework-reference/html/validation.html#validation-mvc-configuring
@Component
public class ProfileUserUIValidator extends UserValidator {

    @Override
    public void validate(Object target, Errors errors) {
        UserTo userTo = (UserTo) target;

        User user = repository.getByEmail(userTo.getEmail());

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Name.form");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty.userForm.email");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty.userForm.password");

        if (!userTo.isNew()) {
            if (user != null && !user.getId().equals(userTo.getId())) {
                errors.rejectValue("email", "Exist.userForm.email");
            }
        } else {
            if (user != null) {
                errors.rejectValue("email", "Exist.userForm.email");
            }
        }
    }
}
