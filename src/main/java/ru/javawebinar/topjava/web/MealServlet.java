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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static String LIST_MEAL;
    private static String INSERT_OR_EDIT;
    private static Logger log;
    private static DateTimeFormatter formatter;
    private static MealDAO mealDAO;

    @Override
    public void init() throws ServletException {
        super.init();
        LIST_MEAL = "/meals.jsp";
        INSERT_OR_EDIT = "/editPage.jsp";
        log = getLogger(MealServlet.class);
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        mealDAO = new MealDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter("action") == null ? "" : request.getParameter("action").toLowerCase();
        log.debug("performed " + (action.isEmpty() ? "return mealList" : action));
        switch (action) {
            case ("delete"):
                mealDAO.deleteMealById(Integer.parseInt(request.getParameter("mealId")));
                request.setAttribute("meals", MealsUtil.getFiltered(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.getDefaultCaloriesPerDay()));
                response.sendRedirect(request.getContextPath() + "/meals");
                break;
            case ("edit"):
                Meal meal = mealDAO.getById(Integer.parseInt(request.getParameter("mealId")));
                request.setAttribute("meal", meal);
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            case ("insert"):
                request.getRequestDispatcher(INSERT_OR_EDIT).forward(request, response);
                break;
            default:
                request.setAttribute("meals", MealsUtil.getFiltered(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.getDefaultCaloriesPerDay()));
                request.getRequestDispatcher(LIST_MEAL).forward(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String mealId = request.getParameter("mealId");
        LocalDateTime localDateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (mealId == null || mealId.isEmpty()) {
            Meal meal = new Meal(localDateTime, description, calories);
            mealDAO.add(meal);
        } else {
            Meal meal = new Meal(Integer.parseInt(mealId), localDateTime, description, calories);
            mealDAO.edit(meal);
        }

        request.setAttribute("meals", MealsUtil.getFiltered(mealDAO.getAll(), LocalTime.MIN, LocalTime.MAX, MealsUtil.getDefaultCaloriesPerDay()));
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
        response.sendRedirect("meals.jsp");
    }
}