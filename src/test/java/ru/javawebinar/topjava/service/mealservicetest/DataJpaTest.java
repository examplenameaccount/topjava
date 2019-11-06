package ru.javawebinar.topjava.service.mealservicetest;

import org.hibernate.Hibernate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealServiceTest;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;
import static ru.javawebinar.topjava.UserTestData.ADMIN;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = {Profiles.DATAJPA})
public class DataJpaTest extends MealServiceTest {

    @Test
    public void getMealWithUser() {
        Meal meal = getService().getMealWithUser(ADMIN_MEAL_ID, UserTestData.ADMIN_ID);
        meal.getUser().equals(ADMIN);
    }
}
