package com.golubovich.library.service.impl;

import com.golubovich.library.bean.Person;
import com.golubovich.library.bean.Role;
import com.golubovich.library.dao.DAOException;
import com.golubovich.library.dao.DAOProvider;
import com.golubovich.library.dao.api.PersonDAO;
import com.golubovich.library.spring.service.ServiceException;
import com.golubovich.library.service.api.PersonService;

import java.util.List;

public class PersonServiceImpl implements PersonService {
    private final DAOProvider daoProvider = DAOProvider.getInstance();
    private final PersonDAO personDAO = daoProvider.getPersonDAO();

    @Override
    public long add(Person person) throws ServiceException {
        try {
            validatePerson(person);

            if (personDAO.findByEmail(person.getEmail()) != null) {
                throw new ServiceException("Existing email");
            }

            long createdId = personDAO.create(person);
            return createdId;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean changeRole(long id, Role role) throws ServiceException {
        try {
            Person person = personDAO.findById(id);
            if (person != null) {
                person.setRole(role);
            } else {
                throw new ServiceException("Id is not found");
            }
            return personDAO.update(person);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Person> showAll() throws ServiceException {
        try {
            return personDAO.read();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Person takeById(Long id) throws ServiceException {
        try {
            Person person = personDAO.findById(id);
            if (person != null) {
                return person;
            } else {
                throw new ServiceException("No person with id");
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public Person signIn(String email, String password) throws ServiceException {
        try {
            Person person = personDAO.findByEmail(email);
            if (person != null) {
                if (person.getPassword().equals(password)) {
                    return person;
                }
            }

            return null;

        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    private void validatePerson(Person person) throws ServiceException {
        if (person.getEmail() == null || person.getPassword() == null || person.getRole() == null) {
            throw new ServiceException("Not valid data");
        }
    }

}
