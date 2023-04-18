package com.golubovich.library.spring.repository;

import com.golubovich.library.spring.model.Author;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Long> {
    @Query(value = "SELECT * FROM author ORDER BY id", nativeQuery = true)
    List<Author> showAll();
}
