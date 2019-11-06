package ru.javawebinar.topjava.service.mealservicetest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.BaseTestClass;
import ru.javawebinar.topjava.service.MealServiceTest;

@RunWith(SpringRunner.class)
@ActiveProfiles(value = {Profiles.JPA})
public class JpaTest extends MealServiceTest {
}
