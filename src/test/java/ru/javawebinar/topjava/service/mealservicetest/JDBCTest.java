package ru.javawebinar.topjava.service.mealservicetest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveJDBCRepositoryProfileResolver;
import ru.javawebinar.topjava.service.MealServiceTest;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;

@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = ActiveJDBCRepositoryProfileResolver.class)
public class JDBCTest extends MealServiceTest {
}
