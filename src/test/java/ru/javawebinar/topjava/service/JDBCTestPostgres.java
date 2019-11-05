package ru.javawebinar.topjava.service;

import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = {"postgres", "postgresRepository"})
public class JDBCTestPostgres extends BaseTestClass {
}
