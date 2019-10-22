package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;

    public static final int MEAL1_ID = START_SEQ;
    public static final int MEAL2_ID = START_SEQ + 1;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500);
    public static final Meal MEAL2 = new Meal(MEAL2_ID, LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 2000);

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual);
    }
}
