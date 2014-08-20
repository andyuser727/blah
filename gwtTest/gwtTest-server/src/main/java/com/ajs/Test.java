package com.ajs;



public class Test {
    private Long id;
    private String firstName;
    private String lastName;
    private Long salary;

    public Test() {}

    public Test(String fname, String lname, Long salary) {
        this.firstName = fname;
        this.lastName = lname;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }
}