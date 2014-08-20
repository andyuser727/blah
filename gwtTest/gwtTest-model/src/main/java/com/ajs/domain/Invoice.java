package com.ajs.domain;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 11/12/2013
 * Time: 22:25
 * To change this template use File | Settings | File Templates.
 */

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="INVOICE")
@XmlRootElement
public class Invoice extends PersistentObject {

    private String invoiceNumber;
    private String description;
    private String customerReference;
    private Set<InvoiceItemRlship> invoiceItemRlships;
    private Customer customer;
    private BigDecimal amount;
    private Date invoiceDate;
    private Set<PaymentInvoiceAllocation> paymentInvoiceAllocations;


    public Invoice(){
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    @XmlElement
    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getCustomerReference() {
        return customerReference;
    }

    @XmlElement
    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    @Column(name="DESCRIPTION")
    public String getDescription() {
        return description;
    }

    @XmlElement
    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @XmlElement
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    @XmlElement
    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    @ManyToOne
    @JoinColumn(name="PARTY_ID")
    @Cascade(CascadeType.ALL)
    @XmlElement
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @OneToMany(mappedBy="invoice")
    @Cascade(CascadeType.SAVE_UPDATE)
    @XmlTransient
    public Set<InvoiceItemRlship> getInvoiceItemRlships() {
        return invoiceItemRlships;
    }

    public void setInvoiceItemRlships(Set<InvoiceItemRlship> invoiceItemRlships) {
        this.invoiceItemRlships = invoiceItemRlships;
    }

    @OneToMany(mappedBy="invoice")
    @Cascade(CascadeType.ALL)
    @XmlTransient
    public Set<PaymentInvoiceAllocation> getPaymentInvoiceAllocations() {
        return paymentInvoiceAllocations;
    }

    public void setPaymentInvoiceAllocations(Set<PaymentInvoiceAllocation> paymentInvoiceAllocations) {
        this.paymentInvoiceAllocations = paymentInvoiceAllocations;
    }
}
