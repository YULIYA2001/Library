package com.golubovich.library.bean;

import java.util.List;

public class Book extends Item {
    private Genre genre;
    private List<Author> author;

    public Book() {
    }

    public Book(String title, String language, Genre genre, List<Author> author) {
        super(title, language);
        this.genre = genre;
        this.author = author;
    }

    public Book(long id, String title, String language, Genre genre, List<Author> author) {
        super(id, title, language);
        this.genre = genre;
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public List<Author> getAuthor() {
        return author;
    }

    public void setAuthor(List<Author> author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "genre=" + genre +
                ", author=" + author +
                "} " + super.toString();
    }
}
