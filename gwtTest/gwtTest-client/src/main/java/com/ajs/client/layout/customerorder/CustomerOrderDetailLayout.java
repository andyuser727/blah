package com.ajs.client.layout.customerorder;

import com.ajs.client.form.customerorder.CustomerOrderDetailForm;
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
public class CustomerOrderDetailLayout extends VLayout {

    public CustomerOrderDetailLayout(ItemListGrid itemsForCustomerOrderGrid,
                             CustomerOrderDetailForm customerOrderDetailForm,
                             Button cancelButton,
                             Button saveCustomerOrderButton,
                             Button addItemButton,
                             DynamicForm form) {

        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(cancelButton);
        buttonLayout.addMember(saveCustomerOrderButton);
        buttonLayout.setPadding(20);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setWidth(300);

        HLayout hlayout = new HLayout();
        hlayout.addMember(customerOrderDetailForm);
        hlayout.setAlign(Alignment.LEFT);

        HLayout itemLayout = new HLayout();
        itemLayout.addMember(itemsForCustomerOrderGrid);
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
