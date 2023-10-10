package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInterface;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final Logger log = getLogger(MealServlet.class);
    private static String LIST_MEALS = "/meals.jsp";
    private MealDaoInterface dao;

    public MealServlet() {
        this.dao = MealDao.getINSTANCE();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        log.debug("redirect to meals");
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "delete":
                log.debug("action: delete");
                dao.deleteMeal(parsActionMealId(req.getParameter("mealId")));
                req.setAttribute("mealTO", MealsUtil.filteredByStreams(dao.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                req.getRequestDispatcher(LIST_MEALS).forward(req, resp);
                break;
            default:
                log.debug("action: default");
                req.setAttribute("mealTO", MealsUtil.filteredByStreams(dao.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                req.getRequestDispatcher(LIST_MEALS).forward(req, resp);
        }
    }

    private int parsActionMealId(String action){
        log.debug("pars action id:" + action);
        return Integer.parseInt(action);
    }
}
