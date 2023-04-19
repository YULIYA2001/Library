package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreTest {

    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    void testAdd() {
        String response = controller.doAction(
                "add_genre&name=аполог&description=литературный жанр, дидактический рассказ");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("Ошибка при добавлении жанра. Вставка не выполнена");
        } else {
            System.out.println("Жанр добавлен");
        }

        assertEquals(response, "0");
    }

    @Test
    void testShow() {
        String response = controller.doAction("show_genres");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при просмотре жанров. Что-то пошло не так");
        } else {
            System.out.println(response.substring(2));
        }

        assertEquals(response, "0");
    }

    @Test
    void testChangeDescription() {
        String response = controller.doAction(
                "change_genre&id=49&description=литературный жанр," +
                        " дидактический (нравоучительный) рассказ");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при изменении жанра. Проверить id");
        } else {
            System.out.println("Описание жанра изменено");
        }

        assertEquals(response, "1");
    }
}
