package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookTest {
    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    void testAdd() {
        String response = controller.doAction(
                "add_book&title=�� ��������&language=���&genre_id=1&authors=6");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("������ ��� ���������� �����. ������� �� ���������");
        } else {
            System.out.println("����� ���������");
        }

        assertEquals(response, "0");
    }

    @Test
    void testDelete() {
        String response = controller.doAction("delete_book&id=25");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("������ ��� �������� �����. ��������� id");
        } else {
            System.out.println("����� �������");
        }

        assertEquals(response, "1");
    }

    @Test
    void testShow() {
        String response = controller.doAction("show_books");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("������ ��� ��������� ����. ���-�� ����� �� ���");
        } else {
            System.out.println(response.substring(2));
        }

        assertEquals(response, "0");
    }

    @Test
    void testChangeReader() {
        String response = controller.doAction("change_book&id=25&person_id=5");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("������ ��� ��������� �����. ��������� id");
        } else {
            System.out.println("�������� ����� �������");
        }

        assertEquals(response, "1");
    }
}
