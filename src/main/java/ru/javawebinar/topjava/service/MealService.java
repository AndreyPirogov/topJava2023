package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.util.Collection;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

public class MealService {

    private MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public Meal create(int userId, Meal meal){
        return repository.save(userId, meal);
    }

    public void delete(int userId, int id){
        checkNotFoundWithId(repository.delete(userId,id), id);
    }

    public Meal get(int userId, int id){
       return checkNotFoundWithId(repository.get(userId, id), id);
    }
    public Collection<Meal> getAll(int userId){
        return checkNotFoundWithId(repository.getAll(userId), userId);
    }
    public void update(int userId, Meal meal){
        checkNotFoundWithId(repository.save(userId, meal), userId);
    }
}