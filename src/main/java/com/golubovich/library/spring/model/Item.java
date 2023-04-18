package com.golubovich.library.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "item")
public abstract class Item {
    @Id
    @GeneratedValue
    private long id;
    private String title;
    private String language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
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
