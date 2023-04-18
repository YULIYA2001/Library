package com.golubovich.library.spring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public abstract class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @JoinColumn(name = "person_id")
    private Person person;

    public Item(String title, String language, Person person) {
        this.title = title;
        this.language = language;
        this.person = person;
    }


    // чтобы @Data сгенерировала все, кроме сеттера id
    private void setId(long id) {
        this.id = id;
    }
}
