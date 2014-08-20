package com.ajs.client.form.customerorder;

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
public class CustomerOrderDetailForm extends DynamicForm {

    private TextItem customerReference;
    private TextItem customerOrderNumber;
    private FloatItem amount;
    private TextAreaItem customerOrderDescription;
    private SelectItem customer;
    private DateItem customerOrderDate;

    public CustomerOrderDetailForm(){

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

        customerOrderNumber = new TextItem();
        customerOrderNumber.setTitle("CustomerOrder Number");

        customerOrderDate = new DateItem();
        customerOrderDate.setTitle("CustomerOrderDate");

        customerOrderDescription = new TextAreaItem();
        customerOrderDescription.setTitle("Description");

        customer = new SelectItem();
        customer.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
//                customerOrderDetailDto.setCustomerId(Long.valueOf(event.getValue().toString()));
            }
        });

        customer.setName("customer");
        customer.setTitle("Customer");
        customer.setAddUnknownValues(false);

        setFields(customer, customerReference, amount, customerOrderDate, customerOrderNumber, customerOrderDescription);
    }

    public TextItem getCustomerReference() {
        return customerReference;
    }

    public TextItem getCustomerOrderNumber() {
        return customerOrderNumber;
    }

    public TextItem getAmount() {
        return amount;
    }

    public TextAreaItem getCustomerOrderDescription() {
        return customerOrderDescription;
    }

    public SelectItem getCustomer() {
        return customer;
    }

    public DateItem getCustomerOrderDate() {
        return customerOrderDate;
    }
}

