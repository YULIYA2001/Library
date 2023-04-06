package com.golubovich.library.dao.impl;

import com.golubovich.library.bean.Author;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.api.AuthorDAO;
import com.golubovich.library.dao.connectionpool.ConnectionPool;
import com.golubovich.library.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AuthorDAOImpl implements AuthorDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO author(name, info) VALUES(?,?)";
    private static final String READ_ALL_QUERY = "SELECT id, name, info FROM author";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, info FROM author WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM author WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE author SET name=?, info=? WHERE id=?";

    @Override
    public long create(Author author) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, author.getName());
            preparedStatement.setString(2, author.getInfo());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return 0;

        } catch (SQLException e) {
            throw new DAOException("Error when create an author", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Author> read() throws DAOException {
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

            List<Author> authors = new ArrayList<>();
            while (resultSet.next()) {
                Author author = new Author(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("info"));
                authors.add(author);
            }

            return authors;

        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when read authors", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public void update(Author updatedAuthor) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, updatedAuthor.getName());
            preparedStatement.setString(2, updatedAuthor.getInfo());
            preparedStatement.setLong(3, updatedAuthor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException("Error when update author", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean delete(Author deletedAuthor) throws DAOException {
        return false;
    }

    @Override
    public Author findById(long id) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_ID_QUERY);
            preparedStatement.setLong(1, id);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            resultSet.next();
            return new Author(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("info"));

        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when find author by id", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
