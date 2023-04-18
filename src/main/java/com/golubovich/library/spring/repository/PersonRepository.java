package com.golubovich.library.spring.repository;

import com.golubovich.library.spring.model.Person;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {
    Optional<Person> findByEmail(String email);

    List<Person> findAllWithCustomOrderBy(Sort sort);
}
