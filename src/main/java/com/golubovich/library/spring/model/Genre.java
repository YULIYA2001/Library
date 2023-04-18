package com.golubovich.library.spring.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "genre")
public class Genre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String description;

    public Genre(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // чтобы @Data сгенерировала все, кроме сеттера id
    private void setId(long id) {
        this.id = id;
    }
}
