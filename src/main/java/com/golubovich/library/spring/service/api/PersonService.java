package com.golubovich.library.servisespring.api;

import com.golubovich.library.model.Person;
import com.golubovich.library.model.Role;
import com.golubovich.library.servisespring.ServiceException;

import java.util.List;

public interface PersonService {
    long add(Person person) throws ServiceException;

    void changeRole(long id, Role role) throws ServiceException;

    List<Person> showAll() throws ServiceException;

    Person takeById(Long id) throws ServiceException;

    Person signIn(String email, String password) throws ServiceException;
}
