package com.ajs.client.widgets;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 11/12/2013
 * Time: 23:03
 * To change this template use File | Settings | File Templates.
 */
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Widget;

public class DialogBox extends com.google.gwt.user.client.ui.DialogBox {
    private static final Binder binder = GWT.create(Binder.class);
    interface Binder extends UiBinder<Widget, DialogBox> {
    }

    public DialogBox() {
        super();
//        removeStyleName("gwt-DialogBox");
    }


}
