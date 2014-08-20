package com.ajs.domain;


import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
//@DiscriminatorValue("CUSTOMER")
@Table(name="CUSTOMER")
public class Customer extends PersistentObject {

    private String firstName;
    private String surName;
    private String initial;
    private Set<Invoice> invoices;
    private Set<Quote> quotes;
    private Set<CustomerOrder> customerOrders;

    public Customer() {}

    public Customer(String firstName, String surName) {
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

    @OneToMany(mappedBy="customer")
    @Cascade(CascadeType.ALL)
    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    @OneToMany(mappedBy="customer")
    @Cascade(CascadeType.ALL)
    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    @OneToMany(mappedBy="customer")
    @Cascade(CascadeType.ALL)
    public Set<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}