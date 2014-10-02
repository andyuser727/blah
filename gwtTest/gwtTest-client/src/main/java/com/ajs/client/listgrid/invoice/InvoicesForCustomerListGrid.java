package com.ajs.client.listgrid.invoice;

import com.ajs.client.datasource.invoice.InvoicesForCustomerDataSource;
import com.smartgwt.client.types.FetchMode;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickHandler;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class InvoicesForCustomerListGrid extends ListGrid {

    public InvoicesForCustomerListGrid() {

        ListGridField invoiceNumber = new ListGridField("invoiceNumber", "Invoice Number");
        ListGridField invoiceDescription = new ListGridField("invoiceDescription", "Invoice Description");
        ListGridField invoiceDate = new ListGridField("invoiceDate", "Invoice Date");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);

        setAutoFetchData(true);
        setModalEditing(false);
        setPreventDuplicates(false);
        setDataFetchMode(FetchMode.LOCAL);
        setCanResizeFields(true);
        setAlternateRecordStyles(true);

        addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        setHeight(250);
        setTitle("Invoices");

        setDataSource(InvoicesForCustomerDataSource.getInstance());
        setCanSelectAll(false);
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        setSelectionType(SelectionStyle.SINGLE);
        setWidth(853);

        setFields(invoiceNumber, invoiceDescription, invoiceDate, amount);
    }
}
