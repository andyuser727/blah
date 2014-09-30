package com.ajs.client.datasource.payment;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceFloatField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 23/12/2013
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDataSource extends DataSource {
    public PaymentDataSource() {
        setID("paymentDataSource");

        setClientOnly(true);

        DataSourceIntegerField id = new  DataSourceIntegerField("id");
        id.setPrimaryKey(true);
        id.setHidden(true);
//        DataSourceLinkField test = new DataSourceLinkField("customerName", "Customer Name");
//        test.set

        DataSourceTextField customerName = new DataSourceTextField("customerName", "Customer Name");
        customerName.setAttribute("width", "100px");

        DataSourceTextField customerReference = new DataSourceTextField("customerReference", "Customer Ref");
        customerReference.setAttribute("widt    h", "120px");

        DataSourceTextField paymentNumber = new DataSourceTextField("paymentNumber", "Payment Number");
        paymentNumber.setAttribute("width", "100px");

        DataSourceFloatField amount = new DataSourceFloatField("amount", "Amount");
        amount.setAttribute("width", "80px%");

        DataSourceTextField description = new DataSourceTextField("description", "Description");
        description.setAttribute("width", "250px");

        DataSourceDateField paymentDate = new DataSourceDateField("paymentDate", "Payment Date");
        paymentDate.setAttribute("width", "100px");

        setFields(id, customerName, customerReference, paymentNumber, description, amount, paymentDate);
    }
}

