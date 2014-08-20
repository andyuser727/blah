package com.ajs.domain;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Set;

@Entity
//@DiscriminatorValue("CUSTOMER")
@Table(name="CUSTOMER")
@XmlRootElement
public class Customer extends PersistentObject {

    private String firstName;
    private String surName;
    private String initial;
    private Set<Invoice> invoices;
    private Set<Quote> quotes;
    private Set<CustomerOrder> customerOrders;

    public Customer() {}

    @XmlElement
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @XmlElement
    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    @XmlElement
    public String getInitial() {
        return initial;
    }

    public void setInitial(String initial) {
        this.initial = initial;
    }

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy="customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(Set<Invoice> invoices) {
        this.invoices = invoices;
    }

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy="customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<Quote> getQuotes() {
        return quotes;
    }

    public void setQuotes(Set<Quote> quotes) {
        this.quotes = quotes;
    }

    @XmlTransient
    @JsonIgnore
    @OneToMany(mappedBy="customer", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<CustomerOrder> getCustomerOrders() {
        return customerOrders;
    }

    public void setCustomerOrders(Set<CustomerOrder> customerOrders) {
        this.customerOrders = customerOrders;
    }
}