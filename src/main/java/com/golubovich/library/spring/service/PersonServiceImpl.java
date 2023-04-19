package com.golubovich.library.spring.service;

import com.golubovich.library.spring.model.Person;
import com.golubovich.library.spring.model.Role;
import com.golubovich.library.spring.repository.PersonRepository;
import com.golubovich.library.spring.service.api.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {
    private final PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public Person add(Person person) throws ServiceException {
        validatePerson(person);

        if (personRepository.findByEmail(person.getEmail()).isPresent()) {
            throw new ServiceException("Existing email");
        }

        return personRepository.save(person);
    }

    @Override
    public Person changeRole(long id, Role role) throws ServiceException {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            person.setRole(role);
        } else {
            throw new ServiceException("Id is not found");
        }

        return personRepository.save(person);
    }

    @Override
    public List<Person> showAll() throws ServiceException {
        return personRepository.findAllWithCustomOrderBy(Sort.by(Sort.Direction.ASC, "id"));
    }

    @Override
    public Person takeById(Long id) throws ServiceException {
        Person person = personRepository.findById(id).orElse(null);
        if (person != null) {
            return person;
        } else {
            throw new ServiceException("No person with id");
        }
    }

    @Override
    public Person signIn(String email, String password) throws ServiceException {
        Person person = personRepository.findByEmail(email).orElse(null);
        if (person != null && person.getPassword().equals(password)) {
            return person;
        }

        return null;
    }

    private void validatePerson(Person person) throws ServiceException {
        if (person.getEmail() == null || person.getPassword() == null || person.getRole() == null) {
            throw new ServiceException("Not valid data");
        }
    }

}
