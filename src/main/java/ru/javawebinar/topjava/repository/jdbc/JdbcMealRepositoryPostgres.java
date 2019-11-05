package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;

@Repository
@Profile("postgresJDBC")
public class JdbcMealRepositoryPostgres extends JdbcMealRepository {
    public JdbcMealRepositoryPostgres(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    List<Meal> abstractGetBetweenInclusive(JdbcTemplate jdbcTemplate, RowMapper<Meal> rowMapper, LocalDate startDate, LocalDate endDate, int userId) {
        System.out.println("here");
        return jdbcTemplate.query(
                "SELECT * FROM meals WHERE user_id=? AND date_time >=? AND date_time < ? ORDER BY date_time DESC",
                rowMapper, userId, getStartInclusive(startDate), getEndExclusive(endDate));
    }

    @Override
    <T> T abstractDate(Meal meal) {
        return (T) meal.getDateTime();
    }

}
