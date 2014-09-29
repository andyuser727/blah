package com.ajs.client.ui.rightpanel;

import com.ajs.client.activity.rightpanel.RightPanelActivity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class RightPanelViewImpl extends Composite implements RightPanelActivity.IRightPanelViewDisplay {
    private static RightPaneViewImplUiBinder uiBinder = GWT.create(RightPaneViewImplUiBinder.class);

    interface RightPaneViewImplUiBinder extends UiBinder<Widget, RightPanelViewImpl> {
    }

    @UiField
    protected Label rightPanelLabel;

//    @UiField
//    protected SimplePanel contentWrapperPanel;

    public RightPanelViewImpl() {

        initWidget(uiBinder.createAndBindUi(this));
    }

    @Override
    public void setRightPanelLabel(final String text) {
        rightPanelLabel.setText(text);
    }
}
