package com.ajs.client.datasource.item;

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.fields.DataSourceBooleanField;
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
public class ItemListDataSource extends DataSource {

    private static ItemListDataSource instance = null;
    private static DataSourceBooleanField remove = new DataSourceBooleanField("remove", "Delete");

    private static DataSourceIntegerField id = new  DataSourceIntegerField("id");
    private static DataSourceTextField itemName = new DataSourceTextField("itemName", "Item Name");
    private static DataSourceTextField itemCode = new DataSourceTextField("itemCode", "Item Code");
    private static DataSourceFloatField amount = new DataSourceFloatField("amount", "Amount");
    private static DataSourceIntegerField quantity = new  DataSourceIntegerField("quantity", "Quantity");
    private static DataSourceTextField itemDescription = new DataSourceTextField("itemDescription", "Item Description");

    protected ItemListDataSource() {
        // Exists only to defeat instantiation.
    }
    public static ItemListDataSource getInstance() {
        if(instance == null) {
            instance = new ItemListDataSource();
            instance.setID("itemListDataSource");

            instance.setClientOnly(true);
            id.setPrimaryKey(true);
            id.setHidden(true);
            itemName.setAttribute("width", "10%");
            itemCode.setAttribute("width", "10%");
            quantity.setAttribute("width", "10%");
            quantity.setAttribute("width", "10%");
            amount.setAttribute("width", "10%");
            itemDescription.setAttribute("width", "50%");

            instance.setFields(remove, id, itemName, itemCode, itemDescription, amount, quantity);
        }
        return instance;
    }

}

