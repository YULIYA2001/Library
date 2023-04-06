package com.golubovich.library.bean;

import java.util.Date;

public class PersonDetails {
    private long personId;
    private String surname;
    private String name;
    private String patronymic;
    private String phone;
    private int age;
    private Date regDate = new Date();
    private Person person;

    public PersonDetails() {
    }

    public PersonDetails(String surname, String name, String patronymic, String phone,
                         int age, Date regDate, Person person) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.age = age;
        this.regDate = regDate;
        this.person = person;
    }

    public PersonDetails(long personId, String surname, String name, String patronymic,
                         String phone, int age, Date regDate, Person person) {
        this.personId = personId;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.phone = phone;
        this.age = age;
        this.regDate = regDate;
        this.person = person;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "PersonDetails{" +
                "personId=" + personId +
                ", surname='" + surname + '\'' +
                ", name='" + name + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age +
                ", regDate=" + regDate +
                ", person=" + person +
                '}';
    }
}
