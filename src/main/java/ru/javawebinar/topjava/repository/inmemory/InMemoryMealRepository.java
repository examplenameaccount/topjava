package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (int i = 0, j = 3; i < 3 && j < 6; i++, j++) {
            save(1, MealsUtil.MEALS.get(i));
            save(2, MealsUtil.MEALS.get(j));
        }
    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            Map<Integer, Meal> map = repository.computeIfAbsent(userId, key -> new HashMap<>());
            map.put(meal.getId(), meal);
            return map.get(meal.getId());
        }
        Map<Integer, Meal> mapMeal = repository.get(meal.getUserId());
        if (mapMeal == null)
            return null;
        return mapMeal
                .computeIfPresent(meal.getId(), (integer, meal1) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        final Map<Integer, Meal> mapMeal = repository.get(userId);
        if (mapMeal == null || mapMeal.get(id) == null) return false;
        return mapMeal.remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        final Map<Integer, Meal> mapMeal = repository.get(userId);
        if (mapMeal == null || mapMeal.get(id) == null) return null;
        return mapMeal.get(id);
    }

    @Override
    public List<Meal> getAllWithoutFilter(int userId) {
        if (repository.get(userId) == null) {
            return new ArrayList<>();
        }
        return getAllWithFilter(userId, LocalDate.MIN, LocalDate.MAX);
    }

    @Override
    public List<Meal> getAllWithFilter(int userId,
                                       LocalDate startDate,
                                       LocalDate endDate
    ) {
        if (repository.get(userId) == null) {
            return new ArrayList<>();
        }
        return repository.get(userId)
                .values()
                .stream()
                .filter(a ->
                        DateTimeUtil.isBetweenDate(a.getDateTime(),
                                startDate, endDate))
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }
}

