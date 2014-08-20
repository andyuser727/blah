package com.ajs.client.form.quote;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.*;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:01
 * To change this template use File | Settings | File Templates.
 */
public class QuoteDetailForm extends DynamicForm {

    private TextItem customerReference;
    private TextItem quoteNumber;
    private FloatItem amount;
    private TextAreaItem quoteDescription;
    private SelectItem customer;
    private DateItem quoteDate;

    public QuoteDetailForm(){

        setHeight100();
        setWidth("300px");
        setPadding(50);
        setLayoutAlign(VerticalAlignment.BOTTOM);
        setAlign(Alignment.LEFT);

        customerReference = new TextItem();
        customerReference.setTitle("Customer Reference");

        amount = new FloatItem();
        amount.setTitle("Amount");
        amount.setKeyPressFilter("[0-9.]");

        quoteNumber = new TextItem();
        quoteNumber.setTitle("Quote Number");

        quoteDate = new DateItem();
        quoteDate.setTitle("QuoteDate");

        quoteDescription = new TextAreaItem();
        quoteDescription.setTitle("Description");

        customer = new SelectItem();
        customer.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
//                quoteDetailDto.setCustomerId(Long.valueOf(event.getValue().toString()));
            }
        });

        customer.setName("customer");
        customer.setTitle("Customer");
        customer.setAddUnknownValues(false);

        setFields(customer, customerReference, amount, quoteDate, quoteNumber, quoteDescription);
    }

    public TextItem getCustomerReference() {
        return customerReference;
    }

    public TextItem getQuoteNumber() {
        return quoteNumber;
    }

    public TextItem getAmount() {
        return amount;
    }

    public TextAreaItem getQuoteDescription() {
        return quoteDescription;
    }

    public SelectItem getCustomer() {
        return customer;
    }

    public DateItem getQuoteDate() {
        return quoteDate;
    }
}

