package ru.javawebinar.topjava.service.datajpa;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.JpaUtil;
import ru.javawebinar.topjava.service.AbstractJpaUserServiceTest;
import ru.javawebinar.topjava.service.AbstractUserServiceTest;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Optional;

import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL1;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL2;
import static ru.javawebinar.topjava.Profiles.DATAJPA;
import static ru.javawebinar.topjava.UserTestData.*;

@ActiveProfiles(DATAJPA)
public class DataJpaUserServiceTest extends AbstractJpaUserServiceTest {
    @Test
    public void getWithMeals() throws Exception {
        User user = service.getWithMeals(ADMIN_ID);
        assertMatch(user, ADMIN);
        MealTestData.assertMatch(user.getMeals(), ADMIN_MEAL2, ADMIN_MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getWithMealsNotFound() throws Exception {
        service.getWithMeals(1);
    }
}