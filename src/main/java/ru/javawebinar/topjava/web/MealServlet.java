package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static String LIST_MEAL = "/meals.jsp";
    private static final Logger log = getLogger(MealServlet.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    private static String INSERT_OR_EDIT = "/editPage.jsp";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
        String forward = "";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("mealList")) {
            forward = LIST_MEAL;
            request.setAttribute("meals", MealsUtil.getFiltered(MealsUtil.getMeals(), LocalTime.MIN, LocalTime.MAX, MealsUtil.getDefaultCaloriesPerDay()));
        }

        request.getRequestDispatcher(forward).forward(request, response);
        response.sendRedirect("meals.jsp");
    }
}