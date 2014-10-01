package com.ajs.client.form.invoice;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyPressEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyPressHandler;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceDetailForm extends DynamicForm {

    private TextItem customerReference;
    private TextItem invoiceNumber;
    private FloatItem amount;
    private TextAreaItem invoiceDescription;
    private SelectItem customer;
    private DateItem invoiceDate;

    public InvoiceDetailForm(){

        setWidth("1000px");
        setPadding(30);
        setLayoutAlign(VerticalAlignment.BOTTOM);
        setAlign(Alignment.LEFT);
        setNumCols(6);
        setBackgroundColor("#E9F5F2");

        customerReference = new TextItem();
        customerReference.setTitle("Customer Reference");

        amount = new FloatItem();
        amount.setTitle("Amount");
        amount.setKeyPressFilter("[0-9.]");

        amount.addKeyPressHandler(new KeyPressHandler() {
            @Override
            public void onKeyPress(KeyPressEvent keyPressEvent) {
                if (keyPressEvent.getCharacterValue() != null && keyPressEvent.getCharacterValue().equals(46) && amount.getEnteredValue() != null && amount.getEnteredValue().length() > 0 && amount.getEnteredValue().contains("."))
                keyPressEvent.cancel();
            }
        });

        invoiceNumber = new TextItem();
        invoiceNumber.setTitle("Invoice Number");

        invoiceDate = new DateItem();
        invoiceDate.setTitle("InvoiceDate");

        invoiceDescription = new TextAreaItem();
        invoiceDescription.setTitle("Description");

        customer = new SelectItem();
        customer.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
//                invoiceDetailDto.setCustomerId(Long.valueOf(event.getValue().toString()));
            }
        });

        customer.setName("customer");
        customer.setTitle("Customer");
        customer.setAddUnknownValues(false);

        setFields(customer, customerReference, amount, invoiceDate, invoiceNumber, invoiceDescription);
    }

    public TextItem getCustomerReference() {
        return customerReference;
    }

    public TextItem getInvoiceNumber() {
        return invoiceNumber;
    }

    public TextItem getAmount() {
        return amount;
    }

    public TextAreaItem getInvoiceDescription() {
        return invoiceDescription;
    }

    public SelectItem getCustomer() {
        return customer;
    }

    public DateItem getInvoiceDate() {
        return invoiceDate;
    }
}

