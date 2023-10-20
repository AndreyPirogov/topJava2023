package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.web.SecurityUtil;


import java.util.List;

@Controller
public class MealRestController {
    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public List<Meal> getAll(int userId){
        log.debug("getAll from userId: {}", userId);
        return (List<Meal>) service.getAll(userId);
    }

    public Meal get(int id){
        log.debug("get Id: {}", id );
        return service.get(SecurityUtil.authUserId(), id);
    }

    public Meal create(Meal meal){
        log.debug("create Meal {}", meal);
        return service.create(SecurityUtil.authUserId(), meal);
    }

    public void delete(int id){
        log.debug("delete meal id: {}", id);
        service.delete(SecurityUtil.authUserId(), id);
    }
    public void update(Meal meal){
        service.update(SecurityUtil.authUserId(), meal);
    }





}