package com.golubovich.library.dao.api;

import com.golubovich.library.bean.Person;
import com.golubovich.library.dao.DAOException;

import java.util.List;

public interface PersonDAO {
    long create(Person person) throws DAOException;

    List<Person> read() throws DAOException;

    boolean update(Person updatedPerson) throws DAOException;

    boolean delete(Person deletedPerson) throws DAOException;

    Person findById(long id) throws DAOException;

    Person findByEmail(String email) throws DAOException;
}
