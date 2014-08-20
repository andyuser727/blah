package com.ajs.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("PERSON")
public class Person extends Party {

    private String firstName;
    private String surName;
    private String initial;

    public Person() {}

    public Person(String firstName, String surName, Long salary) {
        this.firstName = firstName;
        this.surName = surName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }
}