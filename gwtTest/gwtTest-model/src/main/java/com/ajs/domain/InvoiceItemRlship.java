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

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name="INVOICE_ITEM_RLSHIP")
public class InvoiceItemRlship extends PersistentObject {

    private Item item;
    private Invoice invoice;

    public InvoiceItemRlship(){
    }

    @ManyToOne
    @JoinColumn(name="ITEM_ID")
    @Cascade(CascadeType.SAVE_UPDATE)
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @ManyToOne
    @JoinColumn(name="INVOICE_ID")
    @Cascade(CascadeType.SAVE_UPDATE)
    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
