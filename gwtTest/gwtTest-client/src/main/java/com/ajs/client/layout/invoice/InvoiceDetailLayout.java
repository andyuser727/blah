package com.ajs.client.layout.invoice;

import com.ajs.client.form.invoice.InvoiceDetailForm;
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
public class InvoiceDetailLayout extends VLayout {



    public InvoiceDetailLayout(ItemListGrid itemsForInvoiceGrid,
                               InvoiceDetailForm invoiceDetailForm,
                               Button cancelButton,
                               Button saveInvoiceButton,
                               Button addItemButton,
                               DynamicForm form) {

        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(cancelButton);
        buttonLayout.addMember(saveInvoiceButton);
        buttonLayout.addMember(addItemButton);
        buttonLayout.setPadding(20);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setWidth(860);
        buttonLayout.setBackgroundColor("#E9F5F2");

        HLayout hlayout = new HLayout();
        hlayout.addMember(invoiceDetailForm);
        hlayout.setAlign(Alignment.LEFT);

        HLayout itemLayout = new HLayout();
        itemLayout.addMember(itemsForInvoiceGrid);
        itemLayout.setPadding(20);
        itemLayout.setAlign(Alignment.LEFT);
        itemLayout.setWidth(850);
        itemLayout.setBackgroundColor("#E9F5F2");

//        HLayout addItemLayout = new HLayout();
//        addItemLayout.addMember(addItemButton);
//        addItemLayout.setPadding(20);
//        addItemLayout.setAlign(Alignment.LEFT);
//        addItemLayout.setWidth(300);

        HLayout selectAllLayout = new HLayout();

//        addItemButton.setStyleName("marginButton",true);
//        selectAllLayout.addMember(addItemButton);
        selectAllLayout.addMember(form);
        selectAllLayout.setAlign(Alignment.LEFT);
        selectAllLayout.setHeight(20);
        selectAllLayout.setWidth(860);
        selectAllLayout.setBackgroundColor("#E9F5F2");




        addMember(hlayout);
//        addMember(addItemLayout);
        addMember(selectAllLayout);
        addMember(itemLayout);
        addMember(buttonLayout);
    }
}
