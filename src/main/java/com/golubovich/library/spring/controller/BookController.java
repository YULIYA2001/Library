package com.golubovich.library.spring.controller;


import com.golubovich.library.spring.model.Book;
import com.golubovich.library.spring.service.BookServiceImpl;
import com.golubovich.library.spring.service.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "http://localhost:4200")
public class BookController {
    private static final Logger log = Logger.getLogger(BookController.class);
    private final BookServiceImpl bookService;

    @Autowired
    public BookController(BookServiceImpl bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        try {
            List<Book> books = bookService.showAll();
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
        try {
            Book book = bookService.takeById(id);
            return new ResponseEntity<>(book, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBookById(@PathVariable("id") Long id) {
        try {
            bookService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @PostMapping("/add")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        try {
            Book newBook = bookService.add(book);
            return new ResponseEntity<>(newBook, HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }


    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAuthority('user:write')")
    public ResponseEntity<Book> changeBookReader(@PathVariable("id") Long id, @RequestBody Long personId) {
        try {
            Book updateBook = bookService.changeReader(id, personId);
            return new ResponseEntity<>(updateBook, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
