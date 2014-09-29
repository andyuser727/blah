package com.ajs.client.ui.nav;

import com.ajs.client.activity.nav.NavActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;

public class NavViewImpl extends Composite implements NavActivity.INavViewDisplay {
    private static NavViewImplUiBinder uiBinder = GWT.create(NavViewImplUiBinder.class);

    interface NavViewImplUiBinder extends UiBinder<Widget, NavViewImpl> {
    }

    @UiField
    protected Label navLabel;

    //    @UiField
    //    protected SimplePanel contentWrapperPanel;

//    @UiField
//    protected Button btnAdd, btnGB;

    public NavViewImpl() {

        initWidget(uiBinder.createAndBindUi(this));
    }


    @Override
    public void setNavLabel(final String text) {
        navLabel.setText(text);
    }
}
