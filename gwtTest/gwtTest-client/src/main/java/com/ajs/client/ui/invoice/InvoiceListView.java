package com.ajs.client.ui.invoice;

import com.ajs.client.activity.invoice.InvoiceListActivity;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;

public class InvoiceListView extends BaseAbstractView {
    private static InvoiceListViewUiBinder uiBinder = GWT.create(InvoiceListViewUiBinder.class);

    interface InvoiceListViewUiBinder extends UiBinder<Widget, InvoiceListView> {
    }

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @UiField
    protected Button btnAddInvoice, btnProcessDeletes;

    @Inject
    public InvoiceListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }

    @UiHandler("btnProcessDeletes")
    protected void processDeletes(ClickEvent e) {
        ((InvoiceListActivity)getActivity()).processDeletes();
    }

    @UiHandler("btnAddInvoice")
    protected void btnAddInvoice(ClickEvent e) {
        ((InvoiceListActivity)getActivity()).doAddNewInvoice();
    }
}
