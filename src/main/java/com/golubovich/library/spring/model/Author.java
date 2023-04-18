package com.golubovich.library.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"books"})
@EqualsAndHashCode(exclude = {"books"})
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    private String info;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;

    public Author(String name, String info) {
        this.name = name;
        this.info = info;
    }


    // чтобы @Data сгенерировала все, кроме сеттера id
    private void setId(long id) {
        this.id = id;
    }
}
