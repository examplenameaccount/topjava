package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealService {
    List<Meal> allMeals();

    void add(Meal meal);

    void delete(Meal meal);

    void deleteMealById(int mealId);

    void edit(Meal meal);

    Meal getById(int id);
}
