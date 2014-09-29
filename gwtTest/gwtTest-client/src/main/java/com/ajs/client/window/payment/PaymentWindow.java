package com.ajs.client.window.payment;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */


public class PaymentWindow extends Window {

    public PaymentWindow(boolean newPayment) {


        if (newPayment) {
            setTitle("Add Payment");
        } else {
            setTitle("Payment Details");
        }
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);

        setScrollbarSize(0);
        setAlign(Alignment.LEFT);
        setWidth(950);
        setHeight(660);
        moveTo(270, 20);
//        setAutoSize(true);
    }

}
