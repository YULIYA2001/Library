package com.golubovich.library.dao.impl;

import com.golubovich.library.bean.Genre;
import com.golubovich.library.dao.connectionpool.ConnectionPool;
import com.golubovich.library.dao.connectionpool.ConnectionPoolException;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.api.GenreDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GenreDAOImpl implements GenreDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO genre(name, description) VALUES(?,?)";
    private static final String READ_ALL_QUERY = "SELECT id, name, description FROM genre";
    private static final String FIND_BY_ID_QUERY = "SELECT id, name, description FROM genre WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM genre WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE genre SET name=?, description=? WHERE id=?";


    @Override
    public long create(Genre genre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, genre.getName());
            preparedStatement.setString(2, genre.getDescription());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return 0;

        } catch (SQLException e) {
            throw new DAOException("Error when create a genre", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Genre> read() throws DAOException {
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

            List<Genre> genres = new ArrayList<>();
            while (resultSet.next()) {
                Genre genre = new Genre(
                        resultSet.getInt("id"),
                        resultSet.getString("name"),
                        resultSet.getString("description"));
                genres.add(genre);
            }

            return genres;

        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when read genres", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean update(Genre updatedGenre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, updatedGenre.getName());
            preparedStatement.setString(2, updatedGenre.getDescription());
            preparedStatement.setLong(3, updatedGenre.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when update genre", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean delete(Genre deletedGenre) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, deletedGenre.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when delete genre", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Genre findById(long id) throws DAOException {
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
            return new Genre(
                    resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getString("description"));

        } catch (ConnectionPoolException e) {
            throw new DAOException("DB connection fail", e);
        } catch (SQLException e) {
            throw new DAOException("Error when find genre by id", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
