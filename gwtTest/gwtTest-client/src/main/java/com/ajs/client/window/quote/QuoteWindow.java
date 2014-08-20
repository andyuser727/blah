package com.ajs.client.window.quote;

import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Window;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 20:32
 * To change this template use File | Settings | File Templates.
 */


public class QuoteWindow extends Window {

    public QuoteWindow(boolean newQuote) {
        setWidth(950);
        setHeight(850);
        if (newQuote) {
            setTitle("Add Quote");
        } else {
            setTitle("Quote Details");
        }
        setShowMinimizeButton(false);
        setIsModal(true);
        setShowModalMask(true);
        centerInPage();
        setScrollbarSize(0);
        setAlign(Alignment.LEFT);
    }

}
