package com.golubovich.library.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "book")
public class Book extends Item {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Genre genre;
    @ManyToMany(mappedBy = "book_author")
    private List<Author> authors;

    public Book(String title, String language, Person person, Genre genre, List<Author> authors) {
        super(title, language, person);
        this.genre = genre;
        this.authors = authors;
    }

}
