package com.golubovich.library.spring.service.api;

import com.golubovich.library.spring.model.Person;
import com.golubovich.library.spring.model.Role;
import com.golubovich.library.spring.service.ServiceException;

import java.util.List;

public interface PersonService {
    Person add(Person person) throws ServiceException;

    Person changeRole(long id, Role role) throws ServiceException;

    List<Person> showAll() throws ServiceException;

    Person takeById(Long id) throws ServiceException;

    Person signIn(String email, String password) throws ServiceException;
}
