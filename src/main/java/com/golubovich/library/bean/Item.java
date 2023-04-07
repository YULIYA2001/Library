package com.golubovich.library.bean;

public abstract class Item {
    private long id;
    private String title;
    private String language;
    private Person person;

    public Item() {
    }

    public Item(String title, String language, Person person) {
        this.title = title;
        this.language = language;
        this.person = person;
    }

    public Item(long id, String title, String language, Person person) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.person = person;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                ", person=" + person +
                '}';
    }
}
