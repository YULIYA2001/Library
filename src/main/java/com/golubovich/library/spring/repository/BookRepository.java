package com.golubovich.library.spring.repository;

import com.golubovich.library.spring.model.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Query(value = "SELECT item.*, book.* FROM item JOIN book ON book.id = item.id ORDER BY item.id", nativeQuery = true)
    List<Book> findAll();
}
