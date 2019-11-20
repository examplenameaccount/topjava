package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import ru.javawebinar.topjava.service.MealService;

abstract public class AbstractMealController {
    @Autowired
    protected MealService mealService;
}
