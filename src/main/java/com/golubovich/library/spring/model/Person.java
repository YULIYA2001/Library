package com.golubovich.library.spring.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;

    public Person(String email, String password) {
        this.email = email;
        this.password = password;
        this.role = Role.USER;
    }


    // чтобы @Data сгенерировала все, кроме сеттера id
    private void setId(long id) {
        this.id = id;
    }
}
