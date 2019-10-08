package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDAOImpl implements MealDAO {

    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();
    private static final AtomicInteger AUTO_ID = new AtomicInteger(1);

    public MealDAOImpl() {
        meals.putAll(MealsUtil.getMeals().stream().collect(Collectors.toMap(a -> a.getId(), b -> b)));
    }

    @Override
    public List<Meal> allMeals() {
        return new ArrayList<>(meals.values());
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
