package com.golubovich.library.model;

public enum Role {
    USER("Читатель"),
    ADMIN("Администратор");

    private final String name;
    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
