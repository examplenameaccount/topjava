package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDAOImpl implements MealDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    public MealDAOImpl() {
        MealsUtil.getMeals().forEach(meal -> {
            meal.setId(AUTO_ID.getAndIncrement());
            meals.put(meal.getId(), meal);
        });
    }

    @Override
    public List<Meal> allMeals() {
        return meals.values().stream().collect(Collectors.toList());
    }

    @Override
    public void add(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        meals.put(meal.getId(), meal);
    }

    @Override
    public void delete(Meal meal) {
        meals.remove(meal.getId());
    }

    @Override
    public void edit(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteMealToById(int mealId) {
        meals.remove(mealId);
    }
}
