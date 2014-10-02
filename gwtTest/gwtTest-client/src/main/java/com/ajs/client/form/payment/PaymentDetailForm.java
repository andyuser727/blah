package com.ajs.client.form.payment;

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
public class PaymentDetailForm extends DynamicForm {

    private TextItem customerReference;
    private TextItem paymentNumber;
    private FloatItem amount;
    private StaticTextItem allocatedAmount;
    private FloatItem amountToAllocate;
    private StaticTextItem remainingAmount;


    private TextAreaItem paymentDescription;
    private SelectItem customer;
    private DateItem paymentDate;

    public PaymentDetailForm(){

//        setHeight100();
        setWidth("900px");
        setPadding(20);
        setLayoutAlign(VerticalAlignment.BOTTOM);
        setAlign(Alignment.LEFT);
        setNumCols(6);
        setBackgroundColor("#E9F5F2");

        customerReference = new TextItem();
        customerReference.setTitle("Customer Reference");

        amount = new FloatItem();
        amount.setTitle("Amount");
        amount.setKeyPressFilter("[0-9.]");

        amountToAllocate = new FloatItem();
        amountToAllocate.setTitle("Amount To Allocate");
        amountToAllocate.setKeyPressFilter("[0-9.]");

        allocatedAmount = new StaticTextItem();
        allocatedAmount.setTitle("Amount Allocated");

        remainingAmount = new StaticTextItem();
        remainingAmount.setTitle("Amount Remaining");

        paymentNumber = new TextItem();
        paymentNumber.setTitle("Payment Number");
        paymentNumber.setStartRow(false);

        paymentDate = new DateItem();
        paymentDate.setTitle("Payment Date");

        paymentDescription = new TextAreaItem();
        paymentDescription.setTitle("Description");
        paymentDescription.setStartRow(false);

        customer = new SelectItem();
        customer.addChangeHandler(new ChangeHandler() {
            public void onChange(ChangeEvent event) {
//                paymentDetailDto.setCustomerId(Long.valueOf(event.getValue().toString()));
            }
        });

        customer.setName("customer");
        customer.setTitle("Customer");
        customer.setAddUnknownValues(false);

        setFields(customer, customerReference, amount, paymentDate, paymentNumber, paymentDescription, allocatedAmount, remainingAmount, amountToAllocate);
    }

    public TextItem getCustomerReference() {
        return customerReference;
    }

    public TextItem getPaymentNumber() {
        return paymentNumber;
    }

    public TextItem getAmount() {
        return amount;
    }

    public TextAreaItem getPaymentDescription() {
        return paymentDescription;
    }

    public SelectItem getCustomer() {
        return customer;
    }

    public DateItem getPaymentDate() {
        return paymentDate;
    }

    public StaticTextItem getAllocatedAmount() {
        return allocatedAmount;
    }

    public TextItem getAmountToAllocate() {
        return amountToAllocate;
    }

    public StaticTextItem getRemainingAmount() {
        return remainingAmount;
    }

    public void setCustomerChangedHandler(ChangeHandler changeHandler){
        customer.addChangeHandler(changeHandler);
    }
}

