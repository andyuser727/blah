package com.ajs.client.window.customerorder;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */


public class CustomerOrderWindow extends Window {

    public CustomerOrderWindow(boolean newCustomerOrder) {
        setWidth(950);
        setHeight(850);
        if (newCustomerOrder) {
            setTitle("Add CustomerOrder");
        } else {
            setTitle("CustomerOrder Details");
        }
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        setScrollbarSize(0);
        setAlign(Alignment.LEFT);
    }

}
