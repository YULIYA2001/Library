package com.golubovich.library.dao.impl;

import com.golubovich.library.bean.Person;
import com.golubovich.library.bean.Role;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.dao.connectionpool.ConnectionPool;
import com.golubovich.library.dao.connectionpool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PersonDAOImpl implements PersonDAO {
    private static final ConnectionPool connectionPool = ConnectionPool.getInstance();

    private static final String CREATE_QUERY = "INSERT INTO person(email, password, role) VALUES(?,?,?)";
    private static final String READ_ALL_QUERY = "SELECT id, email, password, role FROM person";
    private static final String FIND_BY_ID_QUERY = "SELECT id, email, password, role FROM person WHERE id=?";
    private static final String FIND_BY_EMAIL_QUERY = "SELECT id, email, password, role FROM person WHERE email=?";
    private static final String DELETE_QUERY = "DELETE FROM person WHERE id=?";
    private static final String UPDATE_QUERY = "UPDATE person SET password=?, role=? WHERE id=?";

    private static final String DB_CONNECTION_FAIL = "DB connection fail";
    private static final String EMAIL_STR = "email";
    private static final String PASSWORD_STR = "password";

    @Override
    public long create(Person person) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(CREATE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, person.getEmail());
            preparedStatement.setString(2, person.getPassword());
            preparedStatement.setString(3, person.getRole().name());
            preparedStatement.executeUpdate();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            return 0;

        } catch (SQLException e) {
            throw new DAOException("Error when create a person", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public List<Person> read() throws DAOException {
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

            List<Person> people = new ArrayList<>();
            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getLong("id"),
                        resultSet.getString(EMAIL_STR),
                        resultSet.getString(PASSWORD_STR),
                        Role.valueOf(resultSet.getString("role")));
                people.add(person);
            }

            return people;

        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } catch (SQLException e) {
            throw new DAOException("Error when read all person", e);
        } finally {
            connectionPool.closeConnection(connection, statement, resultSet);
        }
    }

    @Override
    public boolean update(Person updatedPerson) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, updatedPerson.getPassword());
            preparedStatement.setString(2, updatedPerson.getRole().name());
            preparedStatement.setLong(3, updatedPerson.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when update person", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public boolean delete(Person deletedPerson) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(DELETE_QUERY);
            preparedStatement.setLong(1, deletedPerson.getId());
            int res = preparedStatement.executeUpdate();
            return res == 1;
        } catch (SQLException e) {
            throw new DAOException("Error when delete person", e);
        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement);
        }
    }

    @Override
    public Person findById(long id) throws DAOException {
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
            return new Person(
                    resultSet.getLong("id"),
                    resultSet.getString(EMAIL_STR),
                    resultSet.getString(PASSWORD_STR),
                    Role.valueOf(resultSet.getString("role")));

        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } catch (SQLException e) {
            throw new DAOException("Error when find person by id", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }

    @Override
    public Person findByEmail(String email) throws DAOException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = connectionPool.takeConnection();
            preparedStatement = connection.prepareStatement(FIND_BY_EMAIL_QUERY);
            preparedStatement.setString(1, email);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.isBeforeFirst()) {
                return null;
            }

            resultSet.next();
            return new Person(
                    resultSet.getLong("id"),
                    resultSet.getString(EMAIL_STR),
                    resultSet.getString(PASSWORD_STR),
                    Role.valueOf(resultSet.getString("role")));

        } catch (ConnectionPoolException e) {
            throw new DAOException(DB_CONNECTION_FAIL, e);
        } catch (SQLException e) {
            throw new DAOException("Error when find person by email", e);
        } finally {
            connectionPool.closeConnection(connection, preparedStatement, resultSet);
        }
    }
}
