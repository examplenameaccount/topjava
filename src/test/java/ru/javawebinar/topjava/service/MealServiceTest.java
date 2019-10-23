package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.USER_ID;
import static ru.javawebinar.topjava.MealTestData.assertMatch;

@ContextConfiguration({
        "classpath:spring/spring-jdbc.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {
    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void createForUser() {
        Meal newMeal = new Meal(LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal created = service.create(newMeal, USER_ID);
        newMeal.setId(created.getId());

        assertMatch(service.getAll(USER_ID), newMeal, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void createForAdmin() {
        Meal newMeal = new Meal(LocalDateTime.of(2017, Month.MAY, 30, 10, 0), "Завтрак", 500);
        Meal created = service.create(newMeal, ADMIN_ID);
        newMeal.setId(created.getId());

        assertMatch(service.getAll(ADMIN_ID), newMeal, MEAL6, MEAL5, MEAL4);
    }

    @Test(expected = DataAccessException.class)
    public void duplicateCreate() {
        Meal meal = new Meal(MEAL1);
        meal.setId(null);
        service.create(meal, USER_ID);
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, USER_ID);
        assertMatch(meal, MEAL1);
    }

    @Test(expected = NotFoundException.class)
    public void getAnotherMan() {
        service.get(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void delete() {
        service.delete(MEAL1_ID, USER_ID);
        assertMatch(service.getAll(USER_ID),
                MEAL3,
                MEAL2);
    }


    @Test(expected = NotFoundException.class)
    public void deletedNotFound() {
        service.delete(1, USER_ID);
    }

    @Test(expected = NotFoundException.class)
    public void deletedAnotherMan() {
        service.delete(MEAL1_ID, ADMIN_ID);
    }

    @Test
    public void getBetweenDates() {
        List<Meal> all = service.getBetweenDates(LocalDate.of(2015, Month.MAY, 31), LocalDate.of(2015, Month.MAY, 31), ADMIN_ID);
        assertMatch(all, MEAL6, MEAL5, MEAL4);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, MEAL3, MEAL2, MEAL1);
    }

    @Test
    public void update() {
        Meal updated = new Meal(MEAL1);
        updated.setCalories(1000);
        updated.setDescription("NewDescription");
        updated.setDateTime(LocalDateTime.now());
        service.update(updated, USER_ID);
        assertMatch(service.get(MEAL1_ID, USER_ID), updated);
    }

    @Test(expected = NotFoundException.class)
    public void updatedAnotherMan() {
        service.update(MEAL1, ADMIN_ID);
    }
}