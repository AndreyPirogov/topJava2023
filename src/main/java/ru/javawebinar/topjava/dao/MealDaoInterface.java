package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDaoInterface {
    void deleteMeal(int mealsId);
    List<Meal> getMeals();
}
