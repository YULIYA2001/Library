package com.golubovich.library.dao.api;

import com.golubovich.library.bean.Person;
import com.golubovich.library.dao.DAOException;

import java.util.List;

public interface PersonDAO {
    boolean create(Person person) throws DAOException;

    List<Person> read() throws DAOException;

    void update(Person currentPerson, Person updatedPerson) throws DAOException;

    boolean delete(Person deletedPerson) throws DAOException;

    Person findById(long id) throws DAOException;

    Person findByEmail(String email) throws DAOException;
}
