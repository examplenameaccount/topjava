package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;

@ActiveProfiles(value = {"postgres", "datajpa"})
public class DataJpaTestPostgres extends BaseTestClass {
    @Test
    public void getUserWithMeals() {
        User userWithMeal = getServiceUser().getUserWithMeal(UserTestData.ADMIN_ID);
        assertMatch(userWithMeal.getMeals(), ADMIN_MEAL2, ADMIN_MEAL1);
    }

    @Test
    public void getMealWithUser() {
        User user = getServiceMeal().getMealWithUser(ADMIN_MEAL_ID, UserTestData.ADMIN_ID).getUser();
        UserTestData.assertMatch(user, UserTestData.ADMIN);
    }
}
