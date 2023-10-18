package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> users = Arrays.asList(
            new User(null, "Иванов","ivanov@mail.ru", "123", Role.USER),
            new User(null, "Сидоров", "sidorov@yandex.ru", "090909", Role.ADMIN),
            new User (null, "Петрова", "petrova@mai.ru", "1234", Role.USER)


    );
}
