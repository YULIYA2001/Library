package com.golubovich.library.spring.controller;


import com.golubovich.library.spring.model.Person;
import com.golubovich.library.spring.model.Role;
import com.golubovich.library.spring.service.PersonServiceImpl;
import com.golubovich.library.spring.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.log4j.Logger;

import java.util.List;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    private static final Logger log = Logger.getLogger(PersonController.class);
    private final PersonServiceImpl personService;

    @Autowired
    public PersonController(PersonServiceImpl personService) {
        this.personService = personService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Person>> getAllUsers() {
        try {
            List<Person> users = personService.showAll();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Person> getUserById(@PathVariable("id") Long id) {
        try {
            Person user = personService.takeById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


//    @GetMapping("/login")
//    public Person loginPerson(@RequestBody String email, @RequestBody String password) {
//        try {
//            return personService.signIn(email, password);
//        } catch (ServiceException e) {
//            log.error(e);
//            throw new RuntimeException(e);
//        }
//    }
//    @PostMapping("/login")
//    public Person loginPerson(@RequestBody Person person) {
//        try {
//            return personService.signIn(person.getEmail(), person.getPassword());
//        } catch (ServiceException e) {
//            log.error(e);
//            throw new RuntimeException(e);
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<Person> registerUser(@RequestBody Person user) {
        try {
            Person newUser = personService.add(user);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Person> changeUserRole(@PathVariable("id") Long id, @RequestBody String role) {
        try {
            Person updateUser = personService.changeRole(id, Role.valueOf(role.toUpperCase()));
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
