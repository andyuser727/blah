package com.ajs.client.ui.quote;

import com.ajs.client.activity.quote.QuoteListActivity;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;
import com.google.inject.Inject;

public class QuoteListView extends BaseAbstractView {
    private static QuoteListViewUiBinder uiBinder = GWT.create(QuoteListViewUiBinder.class);

    interface QuoteListViewUiBinder extends UiBinder<Widget, QuoteListView> {
    }

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel gridPanel;

    @UiField
    protected Button btnAddQuote, btnProcessDeletes;

    @Inject
    public QuoteListView() {
        initWidget(uiBinder.createAndBindUi(this));
    }

    public FlowPanel getGridPanel() {
        return gridPanel;
    }

    @UiHandler("btnProcessDeletes")
    protected void processDeletes(ClickEvent e) {
        ((QuoteListActivity)getActivity()).processDeletes();
    }

    @UiHandler("btnAddQuote")
    protected void btnAddQuote(ClickEvent e) {
        ((QuoteListActivity)getActivity()).doAddNewQuote();
    }
}
