package com.ajs.client.ui.invoice;

import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class InvoiceListView extends BaseAbstractView {
    private static InvoiceListViewUiBinder uiBinder = GWT.create(InvoiceListViewUiBinder.class);

    interface InvoiceListViewUiBinder extends UiBinder<Widget, InvoiceListView> {
    }

//    @UiField
//    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @Inject
    public InvoiceListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }
}
