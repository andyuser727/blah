package com.ajs.client.listgrid.item;

import com.ajs.client.datasource.customerorder.ItemsForCustomerOrderDataSource;
import com.ajs.client.datasource.item.ItemListDataSource;
import com.ajs.client.datasource.item.ItemsForInvoiceDataSource;
import com.ajs.client.datasource.quote.ItemsForQuoteDataSource;
import com.smartgwt.client.types.FetchMode;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickHandler;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:19
 * To change this template use File | Settings | File Templates.
 */
public class ItemListGrid extends ListGrid {

    public ItemListGrid(boolean attachedToInvoice, boolean attachedToQuote, boolean attachedToCustomerOrder, ChangeHandler quantityChangeHandler ) {

        ListGridField itemName = new ListGridField("itemName", "Item Name");
        ListGridField itemCode = new ListGridField("itemCode", "Item Code");
        ListGridField itemDescription = new ListGridField("itemDescription", "Item Description");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);
        ListGridField quantity = new ListGridField("quantity", "Quantity");
        quantity.setType(ListGridFieldType.INTEGER);
        ListGridField rowNum = new ListGridField("rowNum", "RowNum");
        quantity.setType(ListGridFieldType.INTEGER);

//        quantity.setValidateOnChange(true);
//
//        IntegerRangeValidator integerRangeValidator = new IntegerRangeValidator();
//        integerRangeValidator.setMin(1);
//        quantity.setValidators(integerRangeValidator);
//        quantity.setFilterOnKeypress(true);

        setAutoFetchData(true);
        setModalEditing(false);
        setPreventDuplicates(false);
        setDataFetchMode(FetchMode.LOCAL);
        setCanResizeFields(true);
        setAlternateRecordStyles(true);
        setAutoSaveEdits(true);
//        startEditing();

        addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        setHeight(250);
        setTitle("Items");

        if (attachedToInvoice) {
            setDataSource(ItemsForInvoiceDataSource.getInstance());
            setWidth(820);
            ListGridField delete = new ListGridField("remove", "Delete");
            delete.setCanEdit(true);
            quantity.setCanEdit(true);
            setFilterOnKeypress(true);
            quantity.addChangeHandler(quantityChangeHandler);
            setFields(delete, itemCode, itemName, itemDescription, amount, quantity);
        } else if (attachedToQuote) {
            setDataSource(ItemsForQuoteDataSource.getInstance());
            setWidth(890);
            ListGridField delete = new ListGridField("remove", "Delete");
            delete.setCanEdit(true);
            setFields(delete, itemCode, itemName, itemDescription, amount);
        } else if (attachedToCustomerOrder) {
            setDataSource(ItemsForCustomerOrderDataSource.getInstance());
            setWidth(890);
            ListGridField delete = new ListGridField("remove", "Delete");
            delete.setCanEdit(true);
            setFields(delete, itemCode, itemName, itemDescription, amount);
        } else {
            setDataSource(ItemListDataSource.getInstance());
            setWidth(800);
            quantity.setCanEdit(true);
            setCanSelectAll(true);
            setSelectionAppearance(SelectionAppearance.CHECKBOX);
            setSelectionType(SelectionStyle.SIMPLE);
            setShowFilterEditor(true);
            setFields(itemCode, itemName, itemDescription, amount, quantity);
        }
    }
}
