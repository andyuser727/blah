package com.ajs.client.ui.payment;

import com.ajs.client.activity.payment.PaymentListActivity;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;

public class PaymentListView extends BaseAbstractView {
    private static PaymentListViewUiBinder uiBinder = GWT.create(PaymentListViewUiBinder.class);

    interface PaymentListViewUiBinder extends UiBinder<Widget, PaymentListView> {
    }

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @UiField
    protected Button btnAddPayment, btnProcessDeletes;

    @Inject
    public PaymentListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }

    @UiHandler("btnProcessDeletes")
    protected void processDeletes(ClickEvent e) {
//        ((PaymentListActivity)getActivity()).processDeletes();
    }

    @UiHandler("btnAddPayment")
    protected void btnAddPayment(ClickEvent e) {
        ((PaymentListActivity)getActivity()).doAddNewPayment();
    }
}
