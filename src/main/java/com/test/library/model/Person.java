package com.test.library.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
public class Person {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "year")
    private int year;

    @OneToMany(mappedBy = "person", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int year) {
        this.fullName = fullName;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

}
