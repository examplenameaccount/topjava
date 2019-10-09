package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MealDAOImpl implements MealDAO {
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    public MealDAOImpl() {
        MealsUtil.getMeals().forEach(meal -> {
            meal.setId(AUTO_ID.getAndIncrement());
            add(meal);
        });
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public Meal add(Meal meal) {
        meal.setId(AUTO_ID.getAndIncrement());
        return meals.put(meal.getId(), meal);
    }

    @Override
    public Meal edit(Meal meal) {
        return meals.computeIfPresent(meal.getId(), (key, value) -> meal);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public void deleteMealById(int mealId) {
        meals.remove(mealId);
    }
}
