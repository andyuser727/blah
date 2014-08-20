package com.ajs.domain;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 11/12/2013
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="CUSTOMER_ORDER")
public class CustomerOrder extends PersistentObject {

    private String customerOrderNumber;
    private String description;
    private String customerReference;
    private Set<CustomerOrderItemRlship> customerOrderItemRlships;
    private Customer customer;
    private BigDecimal amount;
    private Date customerOrderDate;

    public CustomerOrder(){
    }

    public String getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public void setCustomerOrderNumber(String customerOrderNumber) {
        this.customerOrderNumber = customerOrderNumber;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCustomerOrderDate() {
        return customerOrderDate;
    }

    public void setCustomerOrderDate(Date customerOrderDate) {
        this.customerOrderDate = customerOrderDate;
    }

    @ManyToOne
    @JoinColumn(name="PARTY_ID")
    @Cascade(CascadeType.ALL)
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy="customerOrder")
    @Cascade(CascadeType.ALL)
    public Set<CustomerOrderItemRlship> getCustomerOrderItemRlships() {
        return customerOrderItemRlships;
    }

    public void setCustomerOrderItemRlships(Set<CustomerOrderItemRlship> customerOrderItemRlships) {
        this.customerOrderItemRlships = customerOrderItemRlships;
    }
}
