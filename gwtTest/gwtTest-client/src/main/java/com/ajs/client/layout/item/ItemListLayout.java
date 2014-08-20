package com.ajs.client.layout.item;

import com.ajs.client.listgrid.item.ItemListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 04/01/2014
 * Time: 16:09
 * To change this template use File | Settings | File Templates.
 */
public class ItemListLayout extends VLayout {

    public ItemListLayout(ItemListGrid itemListGrid,
                          Button newItemButton,
                          Button addItemsButton) {

        HLayout itemLayout = new HLayout();
        itemLayout.addMember(itemListGrid);
        itemLayout.setPadding(20);
        itemLayout.setAlign(Alignment.LEFT);
        itemLayout.setWidth(300);

        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(newItemButton);
        buttonLayout.addMember(addItemsButton);
        buttonLayout.setPadding(20);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setWidth(300);

        addMember(itemLayout);
        addMember(buttonLayout);
    }
}
