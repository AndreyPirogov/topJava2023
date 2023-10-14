package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDaoInterface {
    void deleteMeal(int mealsId);
    List<Meal> getMeals();
    void add(LocalDateTime dateTime, String description, int calories);
    Meal getMeal(int id);
    void update(int id, LocalDateTime dateTime, String description, int calories);

}
