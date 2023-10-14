package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoInterface;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    public static final Logger log = getLogger(MealServlet.class);
    private static String LIST_MEALS = "/meals.jsp";
    private static String EDIT_MEALS = "/mealsEdit.jsp";
    private final MealDaoInterface dao;

    public MealServlet() {
        this.dao = MealDao.getINSTANCE();
    }



    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String redirectList;
        log.debug("redirect to meals");
        String action = req.getParameter("action");
        if (action == null) action = "";
        switch (action){
            case "delete":
                log.debug("action: delete");
                dao.deleteMeal(parsInteger(req.getParameter("mealId")));
                redirectList = LIST_MEALS;
                req.setAttribute("mealTO", MealsUtil.filteredByStreams(dao.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
                break;
            case "edit":
                log.debug("action edit");
                redirectList = EDIT_MEALS;
                req.setAttribute("mealTO", dao.getMeal(parsInteger(req.getParameter("mealId"))));
                break;
            case "add":
                log.debug("action add");
                redirectList = EDIT_MEALS;
                break;
            default:
                log.debug("action: default");
                redirectList = LIST_MEALS;
                req.setAttribute("mealTO", MealsUtil.filteredByStreams(dao.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
        }
        req.getRequestDispatcher(redirectList).forward(req, resp);
    }

    @Override
    protected void doPost( HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String description = req.getParameter("description");
        int calories = parsInteger(req.getParameter("calories"));
        LocalDateTime dateTime = parsDateTime(req.getParameter("dateTime"));
        log.debug("pos method: dateTime: "+ dateTime + " description: " + description + ", calories: " + calories + " id: " + id);
        if (id.equals("")){
            dao.add(dateTime,description,calories);
        } else {
            dao.update(parsInteger(id), dateTime, description, calories);
        }
        req.setAttribute("mealTO", MealsUtil.filteredByStreams(dao.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.CALORIES_PER_DAY));
        req.getRequestDispatcher(LIST_MEALS).forward(req, resp);
    }

    private int parsInteger(String parameter){
        log.debug("pars integer id:" + parameter);
        int number= -1;
        try {
            number = Integer.parseInt(parameter);
        } catch (NumberFormatException e){
            log.error("parsInteger err");
        }
        return number;
    }
    private LocalDateTime parsDateTime(String parameter){
        log.debug("pars dataTime :" + parameter);
        if (Objects.equals(parameter, "")) return LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

        return LocalDateTime.parse(parameter,dtf );
    }
}
