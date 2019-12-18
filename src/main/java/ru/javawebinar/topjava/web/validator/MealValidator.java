package ru.javawebinar.topjava.web.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;

@Component
public class MealValidator implements Validator {
    private static String MEAL_WITH_DATE_EXIST = "Meal with this dateTime already exists";

    @Autowired
    private MealService mealService;

    @Override
    public boolean supports(Class<?> clazz) {
        return Meal.class.equals(clazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Meal meal = (Meal) o;
        if (mealService.getBetweenDates(meal.getDate(), meal.getDate(), SecurityUtil.authUserId())
                .stream()
                .anyMatch(_meal -> _meal.getDateTime().equals(meal.getDateTime()))) {
            throw new DataIntegrityViolationException(MEAL_WITH_DATE_EXIST);
        }
    }
}
