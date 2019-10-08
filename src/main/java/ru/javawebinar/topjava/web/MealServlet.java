package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDAO;
import ru.javawebinar.topjava.dao.MealDAOImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final String LIST_MEAL = "/meals.jsp";
    private static final String INSERT_OR_EDIT = "/editPage.jsp";
    private static final Logger log = getLogger(MealServlet.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static final MealDAO mealDAO = new MealDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.getById(mealId);
            mealDAO.delete(meal);
            forward = LIST_MEAL;
            request.setAttribute("meals", MealsUtil.getFiltered(mealDAO.allMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        } else if (action.equalsIgnoreCase("edit")) {
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = mealDAO.getById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("mealList")) {
            forward = LIST_MEAL;
            request.setAttribute("meals", MealsUtil.getFiltered(mealDAO.allMeals(), LocalTime.MIN, LocalTime.MAX, 2000));
        } else {
            forward = INSERT_OR_EDIT;
        }

        request.getRequestDispatcher(forward).forward(request, response);
        response.sendRedirect("meals.jsp");
    }
}