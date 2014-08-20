package com.ajs.client.datasource.invoice;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 23/12/2013
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceDataSource extends DataSource {
    public InvoiceDataSource() {
        setID("invoiceDataSource");

        setClientOnly(true);

        DataSourceIntegerField id = new  DataSourceIntegerField("id");
        id.setPrimaryKey(true);
        id.setHidden(true);
//        DataSourceLinkField test = new DataSourceLinkField("customerName", "Customer Name");
//        test.set

        DataSourceTextField customerName = new DataSourceTextField("customerName", "Customer Name");
        customerName.setAttribute("width", "10%");

        DataSourceTextField customerReference = new DataSourceTextField("customerReference", "Customer Ref");
        customerReference.setAttribute("width", "10%");

        DataSourceTextField invoiceNumber = new DataSourceTextField("invoiceNumber", "Invoice Number");
        invoiceNumber.setAttribute("width", "10%");

        DataSourceFloatField amount = new DataSourceFloatField("amount", "Amount");
        amount.setAttribute("width", "10%");

        DataSourceTextField description = new DataSourceTextField("description", "Description");
        description.setAttribute("width", "50%");

        DataSourceDateField invoiceDate = new DataSourceDateField("invoiceDate", "Invoice Date");
        invoiceDate.setAttribute("width", "10%");

        setFields(id, customerName, customerReference, invoiceNumber, description, amount, invoiceDate);
    }
}

