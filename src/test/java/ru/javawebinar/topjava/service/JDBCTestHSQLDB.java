package ru.javawebinar.topjava.service;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@ActiveProfiles(value = {"hsqldb", "hsqldbJDBC"})
public class JDBCTestHSQLDB extends BaseTestClass {
}
