package com.golubovich.library.bean;

import java.util.List;

public class Author {
    private long id;
    private String name;
    private String info;

    public Author() {
    }

    public Author(String name, String info) {
        this.name = name;
        this.info = info;
    }

    public Author(long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", info='" + info + '\'' +
                '}';
    }
}
