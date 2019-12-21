package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.UserTo;
import ru.javawebinar.topjava.util.ValidationUtil;

import java.io.BufferedReader;

@Component
public class UserValidator implements Validator {
    protected static String USER_WITH_EMAIL_EXIST = "User with this email already exists";

    @Autowired
    protected UserRepository repository;

    @Autowired
    UserService userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return UserTo.class.equals(clazz);
    }

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
        if (errors.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            errors.getFieldErrors().forEach(fieldError -> {
                builder.append(fieldError.getCode() + "\n");
            });
            throw new DataIntegrityViolationException(builder.toString());
        }
    }
}
