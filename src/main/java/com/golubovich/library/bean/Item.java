package com.golubovich.library.bean;

public abstract class Item {
    private long id;
    private String title;
    private String language;

    private boolean isAvailable;

//    Person person;

    public Item() {
    }

    public Item(String title, String language) {
        this.title = title;
        this.language = language;
    }

    public Item(long id, String title, String language) {
        this.id = id;
        this.title = title;
        this.language = language;
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

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", language='" + language + '\'' +
                '}';
    }
}
