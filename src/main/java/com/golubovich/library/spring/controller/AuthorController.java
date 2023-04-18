package com.golubovich.library.spring.controller;

import com.golubovich.library.spring.model.Author;
import com.golubovich.library.spring.service.AuthorServiceImpl;
import com.golubovich.library.spring.service.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
@CrossOrigin(origins = "http://localhost:4200")
public class AuthorController {
    private static final Logger log = Logger.getLogger(AuthorController.class);
    private final AuthorServiceImpl authorService;

    @Autowired
    public AuthorController(AuthorServiceImpl authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Author>> getAllAuthors() {
        try {
            List<Author> authors = authorService.showAll();
            return new ResponseEntity<>(authors, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @GetMapping("/find/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable("id") Long id) {
        try {
            Author author = authorService.takeById(id);
            return new ResponseEntity<>(author, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Author> addAuthor(@RequestBody Author author){
        try {
            Author newAuthor = authorService.add(author);
            return new ResponseEntity<>(newAuthor, HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Author> updateAuthorInfo(@PathVariable("id") Long id, @RequestBody String info){
        try {
            Author updateAuthor = authorService.changeInfo(id, info);
            return new ResponseEntity<>(updateAuthor, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
