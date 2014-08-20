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
public class InvoicesForCustomerDataSource extends DataSource {

    private static InvoicesForCustomerDataSource instance = null;
    private static DataSourceBooleanField select = new DataSourceBooleanField("select", "Select");

    private static DataSourceIntegerField id = new  DataSourceIntegerField("id");
    private static DataSourceTextField invoiceNumber = new DataSourceTextField("invoiceNumber", "Invoice Number");
    private static DataSourceTextField invoiceDescription = new DataSourceTextField("invoiceDescription", "Invoice Description");
    private static DataSourceFloatField amount = new DataSourceFloatField("amount", "Amount");
    private static DataSourceTextField invoiceDate = new DataSourceTextField("invoiceDate", "Invoice Date");

    protected InvoicesForCustomerDataSource() {
        // Exists only to defeat instantiation.
    }
    public static InvoicesForCustomerDataSource getInstance() {
        if(instance == null) {
            instance = new InvoicesForCustomerDataSource();
            instance.setID("invoicesForCustomerDataSource");

            instance.setClientOnly(true);
            id.setPrimaryKey(true);
            id.setHidden(true);
            select.setAttribute("width", "5%");
            invoiceNumber.setAttribute("width", "10%");
            invoiceDescription.setAttribute("width", "10%");
            amount.setAttribute("width", "10%");
            invoiceDate.setAttribute("width", "50%");

            instance.setFields(select, id, invoiceNumber, invoiceDescription, invoiceDate, amount);
        }
        return instance;
    }

}

