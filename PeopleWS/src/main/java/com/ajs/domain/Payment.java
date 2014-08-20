package com.ajs.domain;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 11/12/2013
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="PAYMENT")
public class Payment extends PersistentObject {

    private String paymentNumber;
    private String description;
    private String customerReference;
    private Customer customer;
    private BigDecimal totalAmount;
    private Date paymentDate;
    private Set<PaymentInvoiceAllocation> paymentInvoiceAllocations;


    public Payment(){
    }

    public String getPaymentNumber() {
        return paymentNumber;
    }

    public void setPaymentNumber(String paymentNumber) {
        this.paymentNumber = paymentNumber;
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

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    @ManyToOne
    @JoinColumn(name="PARTY_ID")
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy="payment", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    public Set<PaymentInvoiceAllocation> getPaymentInvoiceAllocations() {
        return paymentInvoiceAllocations;
    }

    public void setPaymentInvoiceAllocations(Set<PaymentInvoiceAllocation> paymentInvoiceAllocations) {
        this.paymentInvoiceAllocations = paymentInvoiceAllocations;
    }
}
