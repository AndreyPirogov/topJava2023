package ru.javawebinar.topjava.dao;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.slf4j.LoggerFactory.getLogger;

public class MealDao implements MealDaoInterface {
    public static final Logger log = getLogger(MealDao.class);
    public static final AtomicInteger counter = new AtomicInteger(1) ;
    private static MealDao INSTANCE;
    private List<Meal> meals;

    private MealDao() {
        meals = new ArrayList<>(Arrays.asList(
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
            new Meal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
    ));

    }

    public static synchronized MealDao getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new MealDao();

            log.debug("create DAO");
        }
        return INSTANCE;
    }

    @Override
    public List<Meal> getMeals() {
        log.debug("get Meals");
        return meals;
    }

    @Override
    public void add(LocalDateTime dateTime, String description, int calories) {
        if(calories < 0) return;
        log.debug("add Meals");
        meals.add(new Meal(dateTime, description, calories));
    }

    @Override
    public void deleteMeal(int mealsId) {
        log.debug("delete meal: " + mealsId);
        meals.removeIf(m -> m.getId() == mealsId);
    }

    @Override
    public Meal getMeal(int id) {
        for (Meal m: meals) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    @Override
    public void update(int id, LocalDateTime dateTime, String description, int calories) {
        for (Meal m: meals) {
            if (m.getId() == id) {
                meals.remove(m);
                meals.add(new Meal(dateTime, description, calories));
                return;
            }
        }

    }
}
