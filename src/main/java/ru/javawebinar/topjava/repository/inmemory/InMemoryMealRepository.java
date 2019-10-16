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

    @Override
    public Meal save(Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            return repository.computeIfAbsent(meal.getUserId(), key -> new HashMap<>()).put(meal.getId(), meal);
        }
        if (repository.get(meal.getUserId()) == null)
            return null;
        return repository
                .get(meal.getUserId())
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
    public List<Meal> getAll(int userId) {
        if (repository.get(userId) == null) {
            return new ArrayList<>();
        }
        return repository.get(userId)
                .values()
                .stream()
                .sorted(Comparator.comparing(Meal::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Meal> getAll(int userId,
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

