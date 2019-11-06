package ru.javawebinar.topjava.service.userservicetest;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.ActiveJDBCRepositoryProfileResolver;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.service.UserServiceTest;

import static ru.javawebinar.topjava.Profiles.REPOSITORY_IMPLEMENTATION;

@RunWith(SpringRunner.class)
@ActiveProfiles(resolver = ActiveJDBCRepositoryProfileResolver.class)
public class JDBCTest extends UserServiceTest {
}
