package com.golubovich.library.spring.controller;


import com.golubovich.library.spring.model.Genre;
import com.golubovich.library.spring.service.GenreServiceImpl;
import com.golubovich.library.spring.service.ServiceException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/genre")
@CrossOrigin(origins = "http://localhost:4200")
public class GenreController {
    private static final Logger log = Logger.getLogger(GenreController.class);
    private final GenreServiceImpl genreService;

    @Autowired
    public GenreController(GenreServiceImpl genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Genre>> getAllGenres() {
        try {
            List<Genre> genres = genreService.showAll();
            return new ResponseEntity<>(genres, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Genre> getGenreById(@PathVariable("id") Long id) {
        try {
            Genre genre = genreService.takeById(id);
            return new ResponseEntity<>(genre, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Genre> addGenre(@RequestBody Genre genre){
        try {
            Genre newGenre = genreService.add(genre);
            return new ResponseEntity<>(newGenre, HttpStatus.CREATED);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGenreById(@PathVariable("id") Long id) {
        try {
            genreService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Genre> updateGenreDescription(
            @PathVariable("id") Long id, @RequestBody String description){
        try {
            Genre updateGenre = genreService.changeDescription(id, description);
            return new ResponseEntity<>(updateGenre, HttpStatus.OK);
        } catch (ServiceException e) {
            log.error(e);
            throw new RuntimeException(e);
        }
    }
}
