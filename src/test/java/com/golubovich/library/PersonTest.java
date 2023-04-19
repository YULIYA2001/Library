package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonTest {
    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    void testSignUp() {
        String response = controller.doAction("signup_person&email=tom@mail.ru&password=tom000");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("Ошибка при регистрации пользователя. Регистрация не выполнена");
        } else {
            System.out.println("Пользователь добавлен");
        }
        assertEquals(response, "1");
    }

    @Test
    void testSignIn() {
        String response = controller.doAction("signin_person&email=tom@mail.ru&password=tom000");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("Ошибка при авторицации пользователя. Неверный логин или пароль");
        } else {
            System.out.println("Успешная авторизация");
        }

        assertEquals(response, "0");
    }

    @Test
    void testShow() {
        String response = controller.doAction("show_people");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при просмотре пользователей. Что-то пошло не так");
        } else {
            System.out.println(response.substring(2));
        }

        assertEquals(response, "0");
    }

    @Test
    void testChangeRole() {
        String response = controller.doAction("change_person&id=3&role=admin");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при изменении роли. Проверить id");
        } else {
            System.out.println("Роль пользователя изменена");
        }

        assertEquals(response, "1");
    }
}
