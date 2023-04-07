package com.golubovich.library.bean;

import java.util.List;

public class Book extends Item {
    private Genre genre;
    private List<Author> authors;

    public Book() {
    }

    public Book(String title, String language, Person person, Genre genre, List<Author> authors) {
        super(title, language, person);
        this.genre = genre;
        this.authors = authors;
    }

    public Book(long id, String title, String language, Person person, Genre genre, List<Author> authors) {
        super(id, title, language, person);
        this.genre = genre;
        this.authors = authors;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return "Book{" +
                "genre=" + genre +
                ", authors=" + authors +
                "} " + super.toString();
    }
}
