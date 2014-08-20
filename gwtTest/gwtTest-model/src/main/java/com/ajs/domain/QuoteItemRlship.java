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
@Table(name="QUOTE_ITEM_RLSHIP")
public class QuoteItemRlship extends PersistentObject {

    private Item item;
    private Quote quote;

    public QuoteItemRlship(){
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
    @JoinColumn(name="QUOTE_ID")
    @Cascade(CascadeType.SAVE_UPDATE)
    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        this.quote = quote;
    }
}
