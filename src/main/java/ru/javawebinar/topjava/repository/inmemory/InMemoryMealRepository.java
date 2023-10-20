package ru.javawebinar.topjava.repository.inmemory;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);
    private final Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal m: MealsUtil.meals) {
            save(1, m);
        }
    }

    @Override
    public Meal save(int userId, Meal meal) {
        log.debug("save meal for user id {}", userId);
        meal.setUserId(userId);
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }
        // handle case: update, but not present in storage
        return repository.computeIfPresent(userId, (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        log.debug("delete {} for user id {}", id, userId);
        Meal m = repository.remove(id);
        if ( m == null) return false;
        else if (m.getUserId() == userId) return true;
        else save(userId, m);
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        log.debug("get {} for user id {}",id, userId);
        Meal m = repository.get(id);
        if(m.getUserId() != userId) return null;
        return m;
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().parallelStream()
                .filter(o -> o.getUserId() == userId)
                .sorted(Comparator.comparing(Meal::getDate))
                .collect(Collectors.toList());
    }
}

