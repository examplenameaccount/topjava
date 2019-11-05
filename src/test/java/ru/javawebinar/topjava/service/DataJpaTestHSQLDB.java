package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

import static ru.javawebinar.topjava.MealTestData.*;

@ActiveProfiles(value = {"hsqldb", "datajpa"})
public class DataJpaTestHSQLDB extends BaseTestClass {
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
