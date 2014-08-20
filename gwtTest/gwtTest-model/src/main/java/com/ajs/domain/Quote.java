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
@Table(name="QUOTE")
public class Quote extends PersistentObject {

    private String quoteNumber;
    private String description;
    private String customerReference;
    private Set<QuoteItemRlship> quoteItemRlships;
    private Customer customer;
    private BigDecimal amount;
    private Date quoteDate;


    public Quote(){
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
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

    public Date getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
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

    @OneToMany(mappedBy="quote")
    @Cascade(CascadeType.ALL)
    public Set<QuoteItemRlship> getQuoteItemRlships() {
        return quoteItemRlships;
    }

    public void setQuoteItemRlships(Set<QuoteItemRlship> quoteItemRlships) {
        this.quoteItemRlships = quoteItemRlships;
    }
}
