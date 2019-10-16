package ru.javawebinar.topjava;

import org.slf4j.Logger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.SecurityUtil;
import ru.javawebinar.topjava.web.UserServlet;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.slf4j.LoggerFactory.getLogger;

public class SpringMain {
    private static final Logger log = getLogger(SpringMain.class);

    public static void main(String[] args) {
        // java 7 automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ROLE_ADMIN));
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            mealRestController.create(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
            SecurityUtil.setAuthUserId(3);
            mealRestController.delete(1);
//            mealRestController.getAll().forEach(a -> log.info(mealRestController.get(1).toString()));
//            mealRestController.getAll().forEach(a -> log.info(mealRestController.getAll().toString()));
        }
    }
}

class Test {
    public static void main(String[] args) {
        Map<Integer, Map<Integer, Meal>> repository = new HashMap<>();
//        repository.put("awesome key", "cool value");
        repository.computeIfAbsent(1, a->repository.get(1));
//        repository.forEach((a, b) -> System.out.println(a + " - " + b));
    }
}
