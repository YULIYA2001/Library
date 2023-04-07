package com.golubovich.library.service.api;

import com.golubovich.library.bean.Person;
import com.golubovich.library.bean.Role;
import com.golubovich.library.service.ServiceException;

import java.util.List;

public interface PersonService {
    long add(Person person) throws ServiceException;

    boolean changeRole(long id, Role role) throws ServiceException;

    List<Person> showAll() throws ServiceException;

    Person takeById(Long id) throws ServiceException;

    Person signIn(String email, String password) throws ServiceException;
}
