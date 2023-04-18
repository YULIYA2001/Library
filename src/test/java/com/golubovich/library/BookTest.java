package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

public class BookTest {
    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    public void testAdd() {
        String response = controller.doAction(
                "add_book&title=На ростанях&language=бел&genre_id=1&authors=6");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("Ошибка при добавлении книги. Вставка не выполнена");
        } else {
            System.out.println("Книга добавлена");
        }
    }

    @Test
    public void testDelete() {
        String response = controller.doAction("delete_book&id=25");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при удалении книги. Проверить id");
        } else {
            System.out.println("Книга удалена");
        }
    }

    @Test
    public void testShow() {
        String response = controller.doAction("show_books");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при просмотре книг. Что-то пошло не так");
        } else {
            System.out.println(response.substring(2));
        }
    }

    @Test
    public void testChangeReader() {
        String response = controller.doAction("change_book&id=25&person_id=5");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при изменении книги. Проверить id");
        } else {
            System.out.println("Читатель книги изменен");
        }
    }
}
