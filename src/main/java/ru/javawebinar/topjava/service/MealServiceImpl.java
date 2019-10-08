package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public class MealServiceImpl implements MealService {
    private MealDAO mealToDAO = new MealDAOImpl();

    @Override
    public List<Meal> allMeals() {
        return mealToDAO.allMeals();
    }

    @Override
    public void add(Meal meal) {
        mealToDAO.add(meal);
    }

    @Override
    public void delete(Meal meal) {
        mealToDAO.delete(meal);
    }

    @Override
    public void deleteMealById(int mealId) {
        mealToDAO.deleteMealToById(mealId);
    }

    @Override
    public void edit(Meal meal) {
        mealToDAO.edit(meal);
    }

    @Override
    public Meal getById(int id) {
        return mealToDAO.getById(id);
    }
}
