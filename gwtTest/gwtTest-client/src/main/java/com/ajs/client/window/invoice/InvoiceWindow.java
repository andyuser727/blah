package com.ajs.client.window.invoice;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */


public class InvoiceWindow extends Window {

    public InvoiceWindow(boolean newInvoice) {

        if (newInvoice) {
            setTitle("Add Invoice");
        } else {
            setTitle("Invoice Details");
        }
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        setScrollbarSize(0);
        setAlign(Alignment.LEFT);
        setWidth(950);
        setHeight(620);
        moveTo(270, 20);
//        setAutoSize(true);

    }

}
