package com.golubovich.library;


import com.golubovich.library.bean.Author;
import com.golubovich.library.bean.Genre;
import com.golubovich.library.controller.Controller;
import com.golubovich.library.controller.ControllerImpl;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.dao.api.GenreDAO;
import com.golubovich.library.service.ServiceException;
import com.golubovich.library.service.ServiceProvider;
import com.golubovich.library.service.api.GenreService;


import java.util.List;

public class LibraryConsole {

    public static void main(String[] args) throws DAOException {

//        // TEST AUTHOR DAO
        DAOProvider daoProvider = DAOProvider.getInstance();
        AuthorDAO authorDAO = daoProvider.getAuthorDAO();

//        long createdId = authorDAO.create(new Author("Kolos", "New Land"));
//        System.out.println(createdId);

        List<Author> authors = authorDAO.read();
        for (Author a: authors) {
            System.out.println(a);
        }

//        Author author = authorDAO.findById(4);
//        if (author != null) {
//            author.setInfo("info");
//        }
//        authorDAO.update(author);




//        // GENRE VIEW
//        Controller controller = ControllerImpl.getInstance();
//
//        //add
//        String response = controller.doAction("add_genre&name=аполог&descr=литературный жанр, дидактический (нравоучительный) рассказ");
//        //analyzeResponse(response);
//        System.out.println(response);
//
//        //show all
//        String response = controller.doAction("show_genres");
//        System.out.println(response);
//
//        // change genre description
//        String response = controller.doAction("change_genre&id=41&description=prosaic genre 2");
//        System.out.println(response);

    }
}