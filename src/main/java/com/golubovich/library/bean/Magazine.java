package com.golubovich.library.bean;

import java.util.Date;

public class Magazine extends Item {
    private int number;
    private Date date;

    public Magazine() {
    }

    public Magazine(String title, String language, int number, Date date) {
        super(title, language);
        this.number = number;
        this.date = date;
    }

    public Magazine(long id, String title, String language, int number, Date date) {
        super(id, title, language);
        this.number = number;
        this.date = date;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "number=" + number +
                ", date=" + date +
                "} " + super.toString();
    }
}
