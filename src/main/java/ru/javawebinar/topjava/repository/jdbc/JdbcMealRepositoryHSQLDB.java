package ru.javawebinar.topjava.repository.jdbc;

import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.getEndExclusive;
import static ru.javawebinar.topjava.util.DateTimeUtil.getStartInclusive;

@Repository
@Profile("hsqldbJDBC")
public class JdbcMealRepositoryHSQLDB extends JdbcMealRepository {
    public JdbcMealRepositoryHSQLDB(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        super(jdbcTemplate, namedParameterJdbcTemplate);
    }

    @Override
    List<Meal> abstractGetBetweenInclusive(JdbcTemplate jdbcTemplate, RowMapper<Meal> rowMapper, LocalDate startDate, LocalDate endDate, int userId) {
        return jdbcTemplate.query(
                "SELECT * FROM meals WHERE user_id=? AND date_time >=? AND date_time < ? ORDER BY date_time DESC",
                rowMapper, userId, Timestamp.valueOf(getStartInclusive(startDate)), Timestamp.valueOf(getEndExclusive(endDate)));
    }

    @Override
    <T> T abstractDate(Meal meal) {
        return (T) Timestamp.valueOf(meal.getDateTime());
    }

    @Override
    public Meal getMealWithUser(int id, int userId) {
        return null;
    }
}
