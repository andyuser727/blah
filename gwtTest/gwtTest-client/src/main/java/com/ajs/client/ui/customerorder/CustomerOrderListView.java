package com.ajs.client.ui.customerorder;

import com.ajs.client.activity.customerorder.CustomerOrderListActivity;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;

public class CustomerOrderListView extends BaseAbstractView {
    private static CustomerOrderListViewUiBinder uiBinder = GWT.create(CustomerOrderListViewUiBinder.class);

    interface CustomerOrderListViewUiBinder extends UiBinder<Widget, CustomerOrderListView> {
    }

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @UiField
    protected Button btnAddCustomerOrder, btnProcessDeletes;

    @Inject
    public CustomerOrderListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }

    @UiHandler("btnProcessDeletes")
    protected void processDeletes(ClickEvent e) {
//        ((CustomerOrderListActivity)getActivity()).processDeletes();
    }

    @UiHandler("btnAddCustomerOrder")
    protected void btnAddCustomerOrder(ClickEvent e) {
        ((CustomerOrderListActivity)getActivity()).doAddNewCustomerOrder();
    }
}
