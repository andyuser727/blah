package com.ajs.client.window.item;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 04/01/2014
 * Time: 17:03
 * To change this template use File | Settings | File Templates.
 */
public class ItemListWindow extends Window {
    
    public ItemListWindow(VLayout itemListLayout){
        
        setWidth(850);
        setHeight(430);
        setTitle("Item List");
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        setScrollbarSize(0);
        setAlign(Alignment.LEFT);
        addItem(itemListLayout);
    }
}
