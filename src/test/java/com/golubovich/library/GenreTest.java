package com.golubovich.library;

import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import org.junit.jupiter.api.Test;

class GenreTest {

    private static final Controller controller = ControllerImpl.getInstance();
    private static final String DIVIDER_REGEX = "&";
    private static final String ERROR_REGEX = "1";

    @Test
    void testAdd() {
        String response = controller.doAction(
                "add_genre&name=������&description=������������ ����, ������������� �������");

        if (response.equals(ERROR_REGEX)) {
            System.out.println("������ ��� ���������� �����. ������� �� ���������");
        } else {
            System.out.println("���� ��������");
        }
    }

    @Test
    void testShow() {
        String response = controller.doAction("show_genres");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("������ ��� ��������� ������. ���-�� ����� �� ���");
        } else {
            System.out.println(response.substring(2));
        }
    }

    @Test
    void testChangeDescription() {
        String response = controller.doAction(
                "change_genre&id=49&description=������������ ����," +
                        " ������������� (���������������) �������");

        if (response.split(DIVIDER_REGEX)[0].equals(ERROR_REGEX)) {
            System.out.println("������ ��� ��������� �����. ��������� id");
        } else {
            System.out.println("�������� ����� ��������");
        }
    }
}
