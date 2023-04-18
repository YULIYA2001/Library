package com.golubovich.library.spring.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "author")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String info;

    public Author(String name, String info) {
        this.name = name;
        this.info = info;
    }

    // чтобы @Data сгенерировала все, кроме сеттера id
    private void setId(long id) {
        this.id = id;
    }
}
