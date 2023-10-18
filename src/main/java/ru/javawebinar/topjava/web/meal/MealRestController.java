package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.util.Collections;
import java.util.List;

public class MealRestController {
    private static Logger log = LoggerFactory.getLogger(MealRestController.class);

    private MealService service;

    public List<Meal> getAll(int userId){
        log.debug("getAll from userId: {}", userId);
        return (List<Meal>) service.getAll(userId);
    }





}