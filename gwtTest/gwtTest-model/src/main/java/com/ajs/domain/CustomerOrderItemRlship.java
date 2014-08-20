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

@Entity
@Table(name="CUSTOMER_ORDER_ITEM_RLSHIP")
public class CustomerOrderItemRlship extends PersistentObject {

    private Item item;
    private CustomerOrder customerOrder;

    public CustomerOrderItemRlship(){
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
    @JoinColumn(name="CUSTOMER_ORDER_ID")
    @Cascade(CascadeType.SAVE_UPDATE)
    public CustomerOrder getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(CustomerOrder customerOrder) {
        this.customerOrder = customerOrder;
    }
}
