package com.ajs.domain;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name="ITEM")
public class Item extends PersistentObject {

    private String code;
    private String name;
    private String description;
    private Category category;
    private Set<InvoiceItemRlship> invoiceItemRlships;
    private BigDecimal amount;

    public Item() {}

    @Column(name="CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    @JoinColumn(name="CATEGORY_ID")
    @Cascade(CascadeType.ALL)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @OneToMany(mappedBy="item")
    @Cascade(CascadeType.ALL)
    public Set<InvoiceItemRlship> getInvoiceItemRlships() {
        return invoiceItemRlships;
    }

    public void setInvoiceItemRlships(Set<InvoiceItemRlship> invoiceItemRlships) {
        this.invoiceItemRlships = invoiceItemRlships;
    }
}