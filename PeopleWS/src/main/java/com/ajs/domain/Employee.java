package com.ajs.domain;

import javax.persistence.*;

@Entity
@DiscriminatorValue("EMPLOYEE")
public class Employee extends Person {

    private Long salary;

    public Employee() {}


    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}