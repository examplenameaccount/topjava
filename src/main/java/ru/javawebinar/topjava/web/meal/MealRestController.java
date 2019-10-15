package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    @Autowired
    private MealService service;

    protected final Logger log = LoggerFactory.getLogger(getClass());

    public List<MealTo> getAll() {
        return getAll(null, null, null, null);
    }


    public List<MealTo> getAll(LocalDate startDate,
                               LocalDate endDate,
                               LocalTime startTime,
                               LocalTime endTime) {
        log.info("getAll");
        final List<MealTo> meals =
                MealsUtil.getTos(service.getAll(SecurityUtil.authUserId(),
                        startDate == null ? LocalDate.MIN : startDate,
                        endDate == null ? LocalDate.MAX : endDate),
                        SecurityUtil.authUserCaloriesPerDay());
        return meals.stream()
                .filter(a ->
                        DateTimeUtil.isBetween(
                                a.getDateTime().toLocalTime(),
                                startTime == null ? LocalTime.MIN : startTime,
                                endTime == null ? LocalTime.MAX : endTime)).
                        collect(Collectors.toList());
    }


    public Meal get(int id) {
        log.info("get {}", id);
        return service.get(authUserId(), id);
    }

    public Meal create(Meal meal) {
        log.info("create {}", meal);
        checkNew(meal);
        meal.setUserId(authUserId());
        return service.create(meal);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(authUserId(), id);
    }

    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        assureIdConsistent(meal, id);
        meal.setUserId(authUserId());
        service.update(meal);
    }
}