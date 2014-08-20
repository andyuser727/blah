package com.ajs.client.ui.category;

import com.ajs.client.activity.category.CategoryListActivity;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.client.ui.BaseAbstractView;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.ui.Button;

public class CategoryListViewImpl extends BaseAbstractView {
    private static CategoryListViewImplUiBinder uiBinder = GWT.create(CategoryListViewImplUiBinder.class);

    interface CategoryListViewImplUiBinder extends UiBinder<Widget, CategoryListViewImpl> {
    }

    private Activity activity;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected SimplePager pager;

    @UiField
    protected FlowPanel tablePanel, searchPanel, datePanel;

    @UiField
    protected Button btnAdd, btnCancel, btnSave, btnProcessDeletes;

    @UiField
    CheckBox selectAll;

    @UiField
    protected
    DialogBox addDialog;

    @UiField
    protected
    TextBox code, description, test1, test2;

    public CellTable<CategoryDetailDto> table;

    public CategoryListViewImpl() {
        initWidget(uiBinder.createAndBindUi(this));
        tablePanel.setVisible(false);
    }

    public CellTable<CategoryDetailDto> getTable() {
        return table;
    }

    public void setTable(CellTable<CategoryDetailDto> table) {
        this.table = table;
    }

    public FlowPanel getTablePanel() {
        return tablePanel;
    }

    public DialogBox getAddDialog() {
        return addDialog;
    }

    public TextBox getCode() {
        return code;
    }

    public TextBox getDescription() {
        return description;
    }

    public SimplePager getPager() {
        return pager;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public CheckBox getSelectAll() {
        return selectAll;
    }

    public FlowPanel getSearchPanel() {
        return searchPanel;
    }

    public FlowPanel getDatePanel() {
        return datePanel;
    }

    // TODO maybe don't use bind in the activity for buttons etc, use UIHandler
    @UiHandler("selectAll")
    protected void goSelectAllChange(ValueChangeEvent<Boolean> event) {
        ((CategoryListActivity)getActivity()).doSelectAll(event.getValue());
        selectAll.setFocus(true);
        ((CategoryListActivity)getActivity()).doSelectAll(event.getValue());
    }

    @UiHandler("btnProcessDeletes")
    protected void processDeletes(ClickEvent e) {
        ((CategoryListActivity)getActivity()).processDeletes();
    }

    @UiHandler("btnAdd")
    protected void btnAdd(ClickEvent e) {
        ((CategoryListActivity)getActivity()).showAddDialog();
    }

    @UiHandler("btnSave")
    protected void btnSave(ClickEvent e) {
        ((CategoryListActivity)getActivity()).doSave();
    }

    @UiHandler("btnCancel")
    protected void btnCancel(ClickEvent e) {
        ((CategoryListActivity)getActivity()).doCancel();
    }
}
