package com.golubovich.library.dao.impl;

import com.golubovich.library.bean.Author;
import com.golubovich.library.bean.Book;
import com.golubovich.library.bean.Person;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.dao.api.GenreDAO;
import com.golubovich.library.dao.api.ItemDAO;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.dao.connectionpool.ConnectionPool;
import com.golubovich.library.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookDAOImpl implements ItemDAO<Book> {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_ITEM_QUERY = "INSERT INTO item(title, language) VALUES(?,?)";
    private static final String ADD_AUTHORS_QUERY = "INSERT INTO book_author(book_id, author_id) VALUES(?,?)";
    private static final String CREATE_BOOK_QUERY = "INSERT INTO book(item_id, genre_id) VALUES(?,?)";
    private static final String READ_ALL_QUERY = "SELECT item.id, title, language, person_id, genre_id" +
            " FROM item JOIN book ON item.id = book.item_id";
    private static final String DELETE_QUERY = "DELETE FROM item WHERE id=?";
    private static final String UPDATE_ITEM_QUERY = "UPDATE item SET title=?, language=?, person_id=?" +
            " WHERE id=?";
    private static final String BY_ID = " WHERE id=?";

    @Override
    public long create(Book book) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_ITEM_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, book.getTitle());
            preparedStatement.setString(2, book.getLanguage());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();

            if (resultSet.next()) {
                long createdId = resultSet.getLong("id");

                if (createdId == 0) {
                    throw new DAOException("Error when create an item");
                }

                preparedStatement = connection.prepareStatement(CREATE_BOOK_QUERY, Statement.RETURN_GENERATED_KEYS);
                preparedStatement.setLong(1, createdId);
                preparedStatement.setLong(2, book.getGenre().getId());
                preparedStatement.executeUpdate();

                resultSet = preparedStatement.getGeneratedKeys();

                // add book-author
                if (book.getAuthors() != null) {
                    for (Author author : book.getAuthors()) {
                        preparedStatement = connection.prepareStatement(ADD_AUTHORS_QUERY);
                        preparedStatement.setLong(1, createdId);
                        preparedStatement.setLong(2, author.getId());
                        preparedStatement.executeUpdate();
                    }
                }

                return createdId;

            }
            return 0;

        } catch (SQLException e) {
            throw new DAOException("Error when create a book/item", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Book> read() throws DAOException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(READ_ALL_QUERY);

            if (!resultSet.isBeforeFirst()) {
                return Collections.emptyList();
            }

            GenreDAO genreDAO = new GenreDAOImpl();
            AuthorDAO authorDAO = new AuthorDAOImpl();
            PersonDAO personDAO = new PersonDAOImpl();
            List<Book> books = new ArrayList<>();
            while (resultSet.next()) {
                Person person = null;

                long personId = resultSet.getLong("person_id");
                if (personId != 0) {
                    person = personDAO.findById(personId);
                }

                Book book = new Book(
                        resultSet.getLong("id"),
                        resultSet.getString("title"),
                        resultSet.getString("language"),
                        person,
                        genreDAO.findById(resultSet.getLong("genre_id")),
                        authorDAO.findByBookId(resultSet.getLong("id")));
                books.add(book);
            }

            return books;

        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when read books", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean update(Book updatedBook) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();

            preparedStatement = connection.prepareStatement(UPDATE_ITEM_QUERY);
            preparedStatement.setString(1, updatedBook.getTitle());
            preparedStatement.setString(2, updatedBook.getLanguage());
            preparedStatement.setLong(4, updatedBook.getId());

            if (updatedBook.getPerson() == null) {
                preparedStatement.setNull(3, Types.BIGINT);
            } else {
                preparedStatement.setLong(3, updatedBook.getPerson().getId());
            }
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when update book", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean delete(Book deletedBook) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, deletedBook.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when delete book", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }

    }

    @Override
    public Book findById(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(READ_ALL_QUERY + BY_ID);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            resultSet.next();

            GenreDAO genreDAO = new GenreDAOImpl();
            AuthorDAO authorDAO = new AuthorDAOImpl();
            PersonDAO personDAO = new PersonDAOImpl();

            Person person = null;
            long personId = resultSet.getLong("person_id");
            if (personId != 0) {
                person = personDAO.findById(personId);
            }

            return new Book(
                    resultSet.getLong("id"),
                    resultSet.getString("title"),
                    resultSet.getString("language"),
                    person,
                    genreDAO.findById(resultSet.getLong("genre_id")),
                    authorDAO.findByBookId(resultSet.getLong("id")));


        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when find book by id", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
