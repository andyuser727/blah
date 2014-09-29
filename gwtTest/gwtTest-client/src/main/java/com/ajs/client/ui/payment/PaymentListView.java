package com.ajs.client.ui.payment;

import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class PaymentListView extends BaseAbstractView {
    private static PaymentListViewUiBinder uiBinder = GWT.create(PaymentListViewUiBinder.class);

    interface PaymentListViewUiBinder extends UiBinder<Widget, PaymentListView> {
    }

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @Inject
    public PaymentListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }
}
