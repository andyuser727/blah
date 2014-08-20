package com.ajs.client.ui.category;

import com.ajs.client.activity.category.CategoryDetailActivity;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.*;

public class CategoryDetailViewImpl extends BaseAbstractView {
    private static CategoryDetailViewImplUiBinder uiBinder = GWT.create(CategoryDetailViewImplUiBinder.class);

    interface CategoryDetailViewImplUiBinder extends UiBinder<Widget, CategoryDetailViewImpl> {
    }

    private Activity activity;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected SimplePager pager;

    @UiField
    protected FlowPanel tablePanel;

    @UiField
    protected Button btnExit, btnCancel, btnSave, btnAddItem, btnSaveCat, btnTest;

    @UiField
    protected
    DialogBox addDialog;

    @UiField
    protected
    TextBox code, description, test1, test2, itemDescription, itemCode;

    public CellTable<ItemDetailDto> table;

    public CategoryDetailViewImpl() {

        // TODO table in view or activity?
        table = new CellTable<ItemDetailDto>();

        TextColumn<ItemDetailDto> codeColumn = new TextColumn<ItemDetailDto>() {
            @Override
            public String getValue(ItemDetailDto dto) {
                return dto.getCode();
            }
        };

        table.addColumn(codeColumn);

        TextColumn<ItemDetailDto> descriptionColumn = new TextColumn<ItemDetailDto>() {
            @Override
            public String getValue(ItemDetailDto dto) {
                return dto.getDescription();
            }
        };

        table.addColumn(descriptionColumn);

        initWidget(uiBinder.createAndBindUi(this));

        tablePanel.add(table);
        tablePanel.add(pager);
        pager.setDisplay(table);
        tablePanel.setVisible(false);
    }

    // abstract
    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public SimplePager getPager() {
        return pager;
    }

    public FlowPanel getTablePanel() {
        return tablePanel;
    }

    public TextBox getCode() {
        return code;
    }

    public TextBox getDescription() {
        return description;
    }

    public DialogBox getAddDialog() {
        return addDialog;
    }

    public TextBox getItemDescription() {
        return itemDescription;
    }

    public TextBox getItemCode() {
        return itemCode;
    }

    public CellTable<ItemDetailDto> getTable() {
        return table;
    }

    @UiHandler("btnAddItem")
    protected void btnAddItem(ClickEvent e) {
        ((CategoryDetailActivity)getActivity()).showAddDialog();
    }

    @UiHandler("btnSaveCat")
    protected void btnSaveCat(ClickEvent e) {
        ((CategoryDetailActivity)getActivity()).doSaveCat();
    }

    @UiHandler("btnCancel")
    protected void btnCancel(ClickEvent e) {
        ((CategoryDetailActivity)getActivity()).doCancel();
    }

    @UiHandler("btnExit")
    protected void btnExit(ClickEvent e) {
        ((CategoryDetailActivity)getActivity()).doExit();
    }

    @UiHandler("btnSave")
    protected void btnSave(ClickEvent e) {
        ((CategoryDetailActivity)getActivity()).doSave();
    }
}
