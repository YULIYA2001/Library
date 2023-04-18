package com.golubovich.library.spring.repository;

import com.golubovich.library.spring.model.Genre;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GenreRepository extends CrudRepository<Genre, Long> {
    List<Genre> findAllWithCustomOrderBy(Sort sort);
}



