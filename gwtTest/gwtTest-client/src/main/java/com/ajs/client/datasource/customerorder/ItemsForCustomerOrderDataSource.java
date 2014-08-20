package com.ajs.client.datasource.customerorder;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.*;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 23/12/2013
 * Time: 19:58
 * To change this template use File | Settings | File Templates.
 */
public class ItemsForCustomerOrderDataSource extends DataSource {

    private static ItemsForCustomerOrderDataSource instance = null;
    private static DataSourceBooleanField remove = new DataSourceBooleanField("remove", "Delete");

    private static DataSourceIntegerField id = new  DataSourceIntegerField("id");
    private static DataSourceTextField itemName = new DataSourceTextField("itemName", "Item Name");
    private static DataSourceTextField itemCode = new DataSourceTextField("itemCode", "Item Code");
    private static DataSourceFloatField amount = new DataSourceFloatField("amount", "Amount");
    private static DataSourceTextField itemDescription = new DataSourceTextField("itemDescription", "Item Description");

    protected ItemsForCustomerOrderDataSource() {
        // Exists only to defeat instantiation.
    }
    public static ItemsForCustomerOrderDataSource getInstance() {
        if(instance == null) {
            instance = new ItemsForCustomerOrderDataSource();
            instance.setID("itemsForCustomerOrderDataSource");

            instance.setClientOnly(true);
            id.setPrimaryKey(true);
            id.setHidden(true);
            itemName.setAttribute("width", "10%");
            itemCode.setAttribute("width", "10%");
            amount.setAttribute("width", "10%");
            itemDescription.setAttribute("width", "50%");

            instance.setFields(remove, id, itemName, itemCode, itemDescription, amount);
        }
        return instance;
    }

}

