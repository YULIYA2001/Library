package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

public class AuthorTest {
    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    public void testAdd() {
        String response = controller.doAction(
                "add_author&name=Я. Колос&info=1882-1956 белорусский советский писатель," +
                        " драматург, поэт и переводчик");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("Ошибка при добавлении автора. Вставка не выполнена");
        } else {
            System.out.println("Автор добавлен");
        }
    }

    @Test
    public void testShow() {
        String response = controller.doAction("show_authors");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при просмотре авторов. Что-то пошло не так");
        } else {
            System.out.println(response.substring(2));
        }
    }

    @Test
    public void testChangeInfo() {
        String response = controller.doAction("change_author&id=6&info=1882-1956 " +
                "Белорусский советский писатель, драматург, поэт и переводчик");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("Ошибка при изменении автора. Проверить id");
        } else {
            System.out.println("Информация об авторе изменена");
        }
    }
}
