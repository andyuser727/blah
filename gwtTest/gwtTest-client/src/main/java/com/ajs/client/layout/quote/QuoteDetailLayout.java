package com.ajs.client.layout.quote;

import com.ajs.client.form.quote.QuoteDetailForm;
import com.ajs.client.listgrid.item.ItemListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class QuoteDetailLayout extends VLayout {

    public QuoteDetailLayout(ItemListGrid itemsForQuoteGrid,
                               QuoteDetailForm quoteDetailForm,
                               Button cancelButton,
                               Button saveQuoteButton,
                               Button addItemButton,
                               DynamicForm form) {

        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(cancelButton);
        buttonLayout.addMember(saveQuoteButton);
        buttonLayout.setPadding(20);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setWidth(300);

        HLayout hlayout = new HLayout();
        hlayout.addMember(quoteDetailForm);
        hlayout.setAlign(Alignment.LEFT);

        HLayout itemLayout = new HLayout();
        itemLayout.addMember(itemsForQuoteGrid);
        itemLayout.setPadding(20);
        itemLayout.setAlign(Alignment.LEFT);
        itemLayout.setWidth(300);

        HLayout addItemLayout = new HLayout();
        addItemLayout.addMember(addItemButton);
        addItemLayout.setPadding(20);
        addItemLayout.setAlign(Alignment.LEFT);
        addItemLayout.setWidth(300);

        HLayout selectAllLayout = new HLayout();
        selectAllLayout.addMember(form);
        selectAllLayout.setAlign(Alignment.LEFT);
        selectAllLayout.setHeight(20);
        selectAllLayout.setWidth(300);

        addMember(hlayout);
        addMember(addItemLayout);
        addMember(selectAllLayout);
        addMember(itemLayout);
        addMember(buttonLayout);
    }
}
