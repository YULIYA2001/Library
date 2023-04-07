package com.golubovich.library;


import com.golubovich.library.bean.*;
import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.dao.api.GenreDAO;
import com.golubovich.library.dao.api.ItemDAO;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.dao.impl.BookDAOImpl;
import com.golubovich.library.dao.impl.PersonDAOImpl;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.GenreService;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class LibraryConsole {

    public static void main(String[] args) throws DAOException {


        // PERSON VIEW
//        Controller controller = ControllerImpl.getInstance();

//        //add
//        String response = controller.doAction("signup_person&email=1@mail.ru&password=123456");
//        System.out.println(response);
//
//        // change role
//        String response2 = controller.doAction("change_person&id=3&role=ADMIN");
//        System.out.println(response2);
//
//        //show all
//        String response1 = controller.doAction("show_people");
//        System.out.println(response1);
//
//        //sign in
//        String response3 = controller.doAction("signin_person&email=1@mail.ru&password=123456");
//        System.out.println(response3);



        // BOOK VIEW
        Controller controller = ControllerImpl.getInstance();

//        //add
//        String response = controller.doAction("add_book&title=tihiy Don&language=ru&genre_id=2&authors=1 2");
//        //analyzeResponse(response);
//        System.out.println(response);

//        //show all
//        String response1 = controller.doAction("show_books");
//        System.out.println(response1);
//
//        // delete
//        String response3 = controller.doAction("delete_book&id=23");
//        System.out.println(response3);

//        // change reader
//        String response2 = controller.doAction("change_book&id=24&person_id=0");
//        System.out.println(response2);




//        // AUTHOR VIEW
//        Controller controller = ControllerImpl.getInstance();
//
//        //add
//        String response = controller.doAction("add_author&name=Kolos&info=smth about Kolos");
//        //analyzeResponse(response);
//        System.out.println(response);
//
//        //show all
//        String response1 = controller.doAction("show_authors");
//        System.out.println(response1);
//
//        // change genre description
//        String response2 = controller.doAction("change_author&id=5&info=belarusian writer");
//        System.out.println(response2);


//        // GENRE VIEW
//        Controller controller = ControllerImpl.getInstance();
//
//        //add
//        String response = controller.doAction("add_genre&name=аполог&description=литературный жанр, дидактический (нравоучительный) рассказ");
//        //analyzeResponse(response);
//        System.out.println(response);
//
//        //show all
//        String response1 = controller.doAction("show_genres");
//        System.out.println(response1);
//
//        // change genre description
//        String response2 = controller.doAction("change_genre&id=41&description=prosaic genre 2");
//        System.out.println(response2);

    }
}