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
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="INVOICE_CACHE_TEST")
@Cache(usage= CacheConcurrencyStrategy.READ_ONLY, region="INVOICE_CACHE_TEST")
public class InvoiceCacheTest extends PersistentObject {

    private String invoiceNumber;
    private String description;
    private String customerReference;
    private BigDecimal amount;
    private Date invoiceDate;


    public InvoiceCacheTest(){
    }

    public String getInvoiceCacheTestNumber() {
        return invoiceNumber;
    }

    public void setInvoiceCacheTestNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
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

    public Date getInvoiceCacheTestDate() {
        return invoiceDate;
    }

    public void setInvoiceCacheTestDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }
}
