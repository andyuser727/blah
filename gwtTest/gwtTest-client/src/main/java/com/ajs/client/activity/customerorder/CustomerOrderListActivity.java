package com.ajs.client.activity.customerorder;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.client.datasource.customerorder.CustomerOrderDataSource;
import com.ajs.client.datasource.customerorder.ItemsForCustomerOrderDataSource;
import com.ajs.client.datasource.item.ItemListDataSource;
import com.ajs.client.form.customerorder.CustomerOrderDetailForm;
import com.ajs.client.layout.customerorder.CustomerOrderDetailLayout;
import com.ajs.client.layout.item.ItemListLayout;
import com.ajs.client.listgrid.item.ItemListGrid;
import com.ajs.client.place.CustomerOrderListPlace;
import com.ajs.client.ui.customerorder.CustomerOrderListView;
import com.ajs.client.window.customerorder.CustomerOrderWindow;
import com.ajs.client.window.item.ItemListWindow;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.commands.LoadCustomerList;
import com.ajs.shared.commands.customerorder.LoadCustomerOrderDetail;
import com.ajs.shared.commands.customerorder.LoadCustomerOrderList;
import com.ajs.shared.commands.customerorder.SaveCustomerOrderDetail;
import com.ajs.shared.commands.item.LoadItemList;
import com.ajs.shared.commands.item.SaveItemDetail;
import com.ajs.shared.dto.customerorder.CustomerOrderDetailDto;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.types.*;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.TextAreaItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.math.BigDecimal;
import java.util.*;

//import com.ajs.commands.customerOrder.ProcessCustomerOrderDeletes;

public class CustomerOrderListActivity extends BaseAbstractActivity {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private List<com.ajs.shared.dto.customerorder.CustomerOrderDetailDto> customerOrderDetailDtos;
    private CustomerOrderDetailDto customerOrderDetailDto;
    private List<CustomerDetailDto> customerDetailDtos;
    private List<ItemDetailDto> itemDetailDtos;
    private Map<Long, ItemDetailDto> newItemDtos;
    private Provider<CustomerOrderListPlace> customerOrderListPlaceProvider;

    private List<Long> itemIdsToExclude;
    private List<Long> itemIdsToRemove;

    TextItem itemCode;
    TextItem itemName;
    TextItem itemAmount;
    TextAreaItem itemDescription;

    private Window customerOrderWindow;
    private Window itemListWindow;
    private Window addItemWindow;

    private ListGrid customerOrderGrid;
    private ItemListGrid itemsForCustomerOrderGrid;
    private ItemListGrid itemListGrid;

    private CustomerOrderDataSource customerOrderDataSource;
    private ItemsForCustomerOrderDataSource itemsForCustomerOrderDataSource;
    private ItemListDataSource itemListDataSource;

    private Button saveItemButton;
    private Button cancelButton;
    private Button saveCustomerOrderButton;
    private Button addItemButton;
    private Button newItemButton;
    private Button addItemsButton;
    private Button deleteSelectedButton;

    private CustomerOrderDetailForm customerOrderDetailForm;

    private VLayout addCustomerOrderLayout;
    private VLayout itemListLayout;

    private Long customerOrderId;


    Window dlg;

    @Inject
    public CustomerOrderListActivity(CustomerOrderDataSource customerOrderDataSource,
                               EventBus eventBus,
                               CustomerOrderListView display,
                               PlaceController placeController,
                               AppServiceAsync appServiceAsync,
                               Provider<CustomerOrderListPlace> customerOrderListPlaceProvider) {
        super(display);
        this.customerOrderDataSource = customerOrderDataSource;
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.customerOrderListPlaceProvider = customerOrderListPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        // TODO for some reason, customerOrderDataSource won't work with singleton pattern, must be injected
        resetCustomerOrderGrid();
        resetCustomerOrderDataSource();
        itemsForCustomerOrderDataSource = ItemsForCustomerOrderDataSource.getInstance();
        itemListDataSource = ItemListDataSource.getInstance();

        createCustomerOrderListGrid();

        container.setWidget(display.asWidget());
        loadCustomerOrders();
    }

    private void resetCustomerOrderGrid() {

        if (customerOrderGrid == null) {
            customerOrderGrid = new ListGrid();
        } else {
            customerOrderGrid.destroy();
            customerOrderGrid = new ListGrid();
        }
    }

    private void resetCustomerOrderDataSource() {

        if (customerOrderDataSource == null) {
            customerOrderDataSource = new CustomerOrderDataSource();
        } else {
            customerOrderDataSource.destroy();
            customerOrderDataSource = new CustomerOrderDataSource();
        }
    }

    private void resetItemsForCustomerOrderGrid() {

        if (itemsForCustomerOrderGrid == null) {
            itemsForCustomerOrderGrid = new ItemListGrid(false, false, true, null);
        } else {
            itemsForCustomerOrderGrid.destroy();
        }
    }

    private void resetItemsForCustomerOrderDataSource() {
        itemsForCustomerOrderDataSource.destroy();
        itemsForCustomerOrderDataSource = ItemsForCustomerOrderDataSource.getInstance();
    }

    private void resetItemListGrid() {

        if (itemListGrid == null) {
            itemListGrid = new ItemListGrid(false, false, false, null);
        } else {
            itemListGrid.destroy();
        }
    }

    private void resetItemListDataSource() {
        itemListDataSource.destroy();
        itemListDataSource = ItemListDataSource.getInstance();
    }

    private void createCustomerOrderButtons() {

        cancelButton = new Button();
        saveCustomerOrderButton = new Button();
        addItemButton = new Button();
        deleteSelectedButton = new Button();
        cancelButton.setTitle("Cancel");
        saveCustomerOrderButton.setTitle("Save");
        addItemButton.setTitle("Add Item");
        deleteSelectedButton.setTitle("Delete Selected");

        deleteSelectedButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // get ids of
            }
        });

        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                customerOrderDetailDto = new CustomerOrderDetailDto();
                customerOrderWindow.hide();
            }
        });

        saveCustomerOrderButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSaveCustomerOrder();
            }
        });

        addItemButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                resetItemListGrid();
                resetItemListDataSource();

                createItemsListGrid();
                createItemListButtons();
                itemListLayout = new ItemListLayout(itemListGrid, newItemButton, addItemsButton, null);
                showItemListDialog();
                loadItemsList();

                memorizesCustomerOrderDetails();
            }
        });
    }

    private void createItemListButtons() {
        newItemButton = new Button();
        newItemButton.setTitle("New Item");

        newItemButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                createNewItemButtons();
                showAddItemDialog();
            }
        });

        addItemsButton = new Button();
        addItemsButton.setTitle("Add Selected Items");

        addItemsButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                if (newItemDtos == null) {
                    newItemDtos = new HashMap<Long, ItemDetailDto>();
                }

                for (ListGridRecord listGridRecord : itemListGrid.getSelectedRecords()) {
                    ItemDetailDto itemDetailDto = new ItemDetailDto();
                    itemDetailDto.setAmount(listGridRecord.getAttribute("amount") == null ? null : new BigDecimal(listGridRecord.getAttribute("amount")));
                    itemDetailDto.setCode(listGridRecord.getAttribute("itemCode"));
                    itemDetailDto.setName(listGridRecord.getAttribute("itemName"));
                    itemDetailDto.setDescription(listGridRecord.getAttribute("itemDescription"));
                    itemDetailDto.setId(Long.valueOf(Long.valueOf(listGridRecord.getAttribute("id"))));

                    itemIdsToExclude.add(Long.valueOf(Long.valueOf(listGridRecord.getAttribute("id"))));
                    newItemDtos.put(Long.valueOf(Long.valueOf(listGridRecord.getAttribute("id"))), itemDetailDto);
                }

                itemListWindow.hide();
                customerOrderWindow.hide();
                displayCustomerOrderWindow(false);
            }
        });
    }

    private void createNewItemButtons() {

        saveItemButton = new Button();
        saveItemButton.setTitle("Save");

        saveItemButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                ItemDetailDto itemDetailDto = new ItemDetailDto();
                itemDetailDto.setCode(itemCode.getEnteredValue());
                itemDetailDto.setDescription(itemDescription.getEnteredValue());
                itemDetailDto.setName(itemName.getEnteredValue());
                itemDetailDto.setAmount(itemAmount.getValueAsString() != null && itemAmount.getValueAsString().length() != 0 ? new BigDecimal(itemAmount.getValueAsString()) : null);

                saveItem();
            }
        });
    }

    private VLayout createNewItemLayout() {

        final DynamicForm form = new DynamicForm();
        form.setHeight100();
        form.setWidth("300px");
        form.setPadding(30);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAlign(Alignment.LEFT);

        form.setFields(itemCode, itemName, itemDescription, itemAmount);

        VLayout mainLayout = new VLayout();
        mainLayout.addMember(form);
        mainLayout.setAlign(Alignment.LEFT);

        HLayout formLayout = new HLayout();
        formLayout.addMember(form);
        formLayout.setAlign(Alignment.LEFT);

        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(saveItemButton);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setPadding(20);

        mainLayout.addMember(formLayout);
        mainLayout.addMember(buttonLayout);

        return mainLayout;
    }

    private void createItemsListGrid() {

        itemListGrid = new ItemListGrid(false, false, false, null);

        itemListGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {

                if (!(event.getFieldNum() == 0)) {

                    ItemDetailDto itemDetailDto = new ItemDetailDto();
                    itemDetailDto.setAmount(event.getRecord().getAttribute("amount") == null ? null : new BigDecimal(event.getRecord().getAttribute("amount")));
                    itemDetailDto.setCode(event.getRecord().getAttribute("itemCode"));
                    itemDetailDto.setName(event.getRecord().getAttribute("itemName"));
                    itemDetailDto.setDescription(event.getRecord().getAttribute("itemDescription"));
                    itemDetailDto.setId(Long.valueOf(event.getRecord().getAttribute("id")));

                    itemIdsToExclude.add(itemDetailDto.getId());

                    if (newItemDtos == null) {
                        newItemDtos = new HashMap<Long, ItemDetailDto>();
                    }

                    newItemDtos.put(Long.valueOf(event.getRecord().getAttribute("id")), itemDetailDto);

                    itemListWindow.hide();
                    customerOrderWindow.hide();
                    displayCustomerOrderWindow(false);
                }
            }
        });

        itemListGrid.addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });
    }

    public void createCustomerOrderListGrid() {

        ListGridField customerName = new ListGridField("customerName", "Customer Name");
        ListGridField customerOrderNumber = new ListGridField("customerOrderNumber", "CustomerOrder Number");
        ListGridField customerOrderDate = new ListGridField("customerOrderDate", "CustomerOrder Date");
        ListGridField customerReference = new ListGridField("customerReference", "Customer Reference");
        ListGridField description = new ListGridField("description", "Description");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);
        ListGridField id = new ListGridField("id", "ID");
        id.setType(ListGridFieldType.INTEGER);

        customerOrderGrid.setFields(id, customerName, customerReference, customerOrderNumber, description, amount, customerOrderDate);

        customerOrderGrid.setHeight(670);
        customerOrderGrid.setWidth(1350);
        customerOrderGrid.setTitle("CustomerOrders");
        customerOrderGrid.setDataSource(customerOrderDataSource);
        customerOrderGrid.setAutoFetchData(true);
        customerOrderGrid.setShowFilterEditor(true);
        customerOrderGrid.setCanSelectAll(true);
        customerOrderGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        customerOrderGrid.setSelectionType(SelectionStyle.SIMPLE);
        customerOrderGrid.setModalEditing(false);
        customerOrderGrid.setAlternateRecordStyles(true);

        customerOrderGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
                if (!(event.getFieldNum() == 0)) {
                    customerOrderId = Long.valueOf(event.getRecord().getAttribute("id"));
                    loadCustomerOrderDetails();
                }
            }
        });

        customerOrderGrid.addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        ((CustomerOrderListView) display).getGridPanel().add(customerOrderGrid);
    }

    private void loadCustomerOrderDetails() {

        showWaitingForServer();
        LoadCustomerOrderDetail loadCustomerOrderDetail = new LoadCustomerOrderDetail(customerOrderId);

        appServiceAsync.callServer(loadCustomerOrderDetail,
                new AsyncCallback<AppResponse<CustomerOrderDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<CustomerOrderDetailDto> result) {

                        hideWaitingForServer();
                        // TODO SHIT! CHANGE IT
                        itemIdsToExclude = new ArrayList<Long>();
                        customerOrderDetailDto = result.getDtos().get(0);
                        for (ItemDetailDto itemDetailDto : customerOrderDetailDto.getItemDtoList().values()) {
                            itemIdsToExclude.add(itemDetailDto.getId());
                        }
                        editCustomerOrder();
                    }
                });
    }

//    public void processDeletes() {
//
//        showWaitingForServer();
//
//        List<Long> idsToDelete = new ArrayList<Long>();
//
//        for (ListGridRecord listGridRecord : customerOrderGrid.getSelectedRecords()) {
//            idsToDelete.add(Long.valueOf(listGridRecord.getAttribute("id")));
//        }
//
//        ProcessCustomerOrderDeletes processCustomerOrderDeletes = new ProcessCustomerOrderDeletes(idsToDelete);
//        appServiceAsync.callServer(processCustomerOrderDeletes,
//                new AsyncCallback<AppResponse>() {
//                    public void onFailure(Throwable caught) {
//                        // Show the RPC error message to the user
//                        hideWaitingForServer();
//                        System.out.println(caught.getMessage());
//                    }
//
//                    public void onSuccess(AppResponse result) {
//                        hideWaitingForServer();
//                        placeController.goTo(customerOrderListPlaceProvider.get());
//                    }
//                });
//    }

    private void loadCustomerOrders() {

        showWaitingForServer();

        LoadCustomerOrderList loadCustomerOrderList = new LoadCustomerOrderList();

        appServiceAsync.callServer(loadCustomerOrderList,
                new AsyncCallback<AppResponse<CustomerOrderDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<CustomerOrderDetailDto> result) {
                        hideWaitingForServer();
                        // TODO seems wrong, read the fucking generics chapter (for the 50th fucking time... a fucking gain)
                        customerOrderDetailDtos = result.getDtos();
                        if (customerOrderDetailDtos != null && customerOrderDetailDtos.size() > 0) {
                            setCustomerOrderComponents();
                        }
                    }
                });
    }

    void loadItemsList() {

        showWaitingForServer();

        LoadItemList loadItemsList = new LoadItemList(itemIdsToExclude);

        appServiceAsync.callServer(loadItemsList,
                new AsyncCallback<AppResponse<ItemDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<ItemDetailDto> result) {
                        hideWaitingForServer();
                        // TODO seems wrong, read the fucking generics chapter (for the 50th fucking time... a fucking gain)
                        itemDetailDtos = result.getDtos();
                        if (itemDetailDtos != null && itemDetailDtos.size() > 0) {
                            setItemListComponents();
                        }
                    }
                });
    }

    void loadCustomers(final boolean newCustomerOrder) {

        showWaitingForServer();
        LoadCustomerList loadCustomerList = new LoadCustomerList();

        appServiceAsync.callServer(loadCustomerList,
                new AsyncCallback<AppResponse<CustomerDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<CustomerDetailDto> result) {
                        hideWaitingForServer();
                        // TODO seems wrong, read the fucking generics chapter (for the 50th fucking time... a fucking gain)
                        customerDetailDtos = result.getDtos();
                        if (customerDetailDtos != null && customerDetailDtos.size() > 0) {
                            setCustomersList();
                        }
                        if (newCustomerOrder) {
                            customerOrderDetailDto = new CustomerOrderDetailDto();
                        } else {
                            setCustomerOrderData();
                            setItemsForCustomerOrderComponents();
                        }
                    }
                });
    }

    private void showItemListDialog() {

        itemListWindow = new ItemListWindow(itemListLayout);
        itemListWindow.show();
    }

    private void showAddItemDialog() {

        addItemWindow = new Window();
        addItemWindow.setWidth(350);
        addItemWindow.setHeight(350);
        addItemWindow.setTitle("New Item");
        addItemWindow.setShowMinimizeButton(false);
        addItemWindow.setIsModal(true);
        addItemWindow.setShowModalMask(true);
        addItemWindow.centerInPage();
        addItemWindow.setScrollbarSize(0);
        addItemWindow.setAlign(Alignment.LEFT);
        addItemWindow.setPadding(30);

        itemCode = new TextItem();
        itemCode.setTitle("Item Code");

        itemAmount = new TextItem();
        itemAmount.setTitle("Amount");

        itemName = new TextItem();
        itemName.setTitle("Item Name");

        itemDescription = new TextAreaItem();
        itemDescription.setTitle("Description");

//        addItemWindow.addItem(newItemLayout);
        addItemWindow.addItem(createNewItemLayout());
        addItemWindow.show();
    }

    private void setCustomersList() {

        final LinkedHashMap<String, String> customerMap = new LinkedHashMap<String, String>();

        for (CustomerDetailDto customerDetailDto : customerDetailDtos) {
            customerMap.put(String.valueOf(customerDetailDto.getId()), customerDetailDto.getFirstName() + " " + customerDetailDto.getSurName());
        }
        customerOrderDetailForm.getCustomer().setValueMap(customerMap);
    }

    private void setCustomerOrderComponents() {

        for (CustomerOrderDetailDto customerOrderDetailDto : customerOrderDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("customerName", customerOrderDetailDto.getCustomerName());
            record.setAttribute("customerOrderNumber", customerOrderDetailDto.getCustomerOrderNumber());
            record.setAttribute("customerOrderDate", customerOrderDetailDto.getCustomerOrderDate());
            record.setAttribute("customerReference", customerOrderDetailDto.getCustomerReference());
            record.setAttribute("description", customerOrderDetailDto.getDescription());
            record.setAttribute("amount", customerOrderDetailDto.getAmount() == null ? null : customerOrderDetailDto.getAmount().floatValue());
            record.setAttribute("id", customerOrderDetailDto.getId().intValue());

            customerOrderDataSource.addData(record);
        }
        customerOrderDataSource.fetchData(new Criteria());
        customerOrderGrid.redraw();
    }

    private void setItemsForCustomerOrderComponents() {

        if (customerOrderDetailDto != null && customerOrderDetailDto.getItemDtoList() != null) {

            for (ItemDetailDto itemDetailDto : customerOrderDetailDto.getItemDtoList().values()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("itemCode", itemDetailDto.getCode());
                record.setAttribute("itemName", itemDetailDto.getName());
                record.setAttribute("itemDescription", itemDetailDto.getDescription());
                record.setAttribute("amount", itemDetailDto.getAmount() == null ? null : itemDetailDto.getAmount().floatValue());
                record.setAttribute("id", itemDetailDto.getId().intValue());

                itemsForCustomerOrderDataSource.addData(record);
            }
        }

        if (newItemDtos != null) {

            for (ItemDetailDto itemDetailDto : newItemDtos.values()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("itemCode", itemDetailDto.getCode());
                record.setAttribute("itemName", itemDetailDto.getName());
                record.setAttribute("itemDescription", itemDetailDto.getDescription());
                record.setAttribute("amount", itemDetailDto.getAmount() == null ? null : itemDetailDto.getAmount().floatValue());
                record.setAttribute("id", itemDetailDto.getId().intValue());

                itemsForCustomerOrderDataSource.addData(record);
            }
        }

        itemsForCustomerOrderDataSource.fetchData(new Criteria());
        itemsForCustomerOrderGrid.redraw();
    }

    private void setItemListComponents() {

        for (ItemDetailDto itemDetailDto : itemDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("itemCode", itemDetailDto.getCode());
            record.setAttribute("itemName", itemDetailDto.getName());
            record.setAttribute("itemDescription", itemDetailDto.getDescription());
            record.setAttribute("amount", itemDetailDto.getAmount() == null ? null : itemDetailDto.getAmount().floatValue());
            record.setAttribute("id", itemDetailDto.getId().intValue());

            itemListDataSource.addData(record);
        }
        itemListDataSource.fetchData(new Criteria());
        itemListGrid.redraw();
    }

    public void doSaveCustomerOrder() {

        showWaitingForServer();
        createIdsToRemoveList();

        SaveCustomerOrderDetail saveCustomerOrderDetail = new SaveCustomerOrderDetail(customerOrderDetailDto, newItemDtos, itemIdsToRemove);
        customerOrderDetailDto.setCustomerOrderDate(customerOrderDetailForm.getCustomerOrderDate().getValueAsDate());
        customerOrderDetailDto.setDescription(customerOrderDetailForm.getCustomerOrderDescription().getEnteredValue());
        customerOrderDetailDto.setCustomerReference(customerOrderDetailForm.getCustomerReference().getValueAsString());
        customerOrderDetailDto.setCustomerOrderNumber(customerOrderDetailForm.getCustomerOrderNumber().getValueAsString());
        if (customerOrderDetailForm.getCustomer().getValueAsString() != null && customerOrderDetailForm.getCustomer().getValueAsString().length() > 0) {
            customerOrderDetailDto.setCustomerId(Long.valueOf(customerOrderDetailForm.getCustomer().getValueAsString()));
        }
        customerOrderDetailDto.setAmount(customerOrderDetailForm.getAmount().getValueAsString() != null && customerOrderDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(customerOrderDetailForm.getAmount().getValueAsString()) : null);
        appServiceAsync.callServerSimple(saveCustomerOrderDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        caught.printStackTrace();
                    }

                    public void onSuccess(SimpleResponse result) {

                        hideWaitingForServer();
                        placeController.goTo(customerOrderListPlaceProvider.get());
                        customerOrderWindow.hide();
                    }
                });
    }

    public void saveItem() {

        ItemDetailDto itemDetailDto = new ItemDetailDto();
        itemDetailDto.setCode(itemCode.getEnteredValue());
        itemDetailDto.setDescription(itemDescription.getEnteredValue());
        itemDetailDto.setName(itemName.getEnteredValue());
        itemDetailDto.setAmount(itemAmount.getValueAsString() != null && itemAmount.getValueAsString().length() != 0 ? new BigDecimal(itemAmount.getValueAsString()) : null);

        SaveItemDetail saveItemDetail = new SaveItemDetail(itemDetailDto, customerOrderId);
        appServiceAsync.callServer(saveItemDetail,
                new AsyncCallback<AppResponse<ItemDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<ItemDetailDto> result) {

                        if (newItemDtos == null) {
                            newItemDtos = new HashMap<Long, ItemDetailDto>();
                        }
                        newItemDtos.put(result.getDtos().get(0).getId(), result.getDtos().get(0));
                        itemIdsToExclude.add(result.getDtos().get(0).getId());
                        addItemWindow.hide();
                        itemListWindow.hide();
                        customerOrderWindow.hide();
                        displayCustomerOrderWindow(false);

                    }
                });
    }

    private void setCustomerOrderData() {

        customerOrderDetailForm.getCustomerOrderDescription().setValue(customerOrderDetailDto.getDescription());
        customerOrderDetailForm.getCustomerReference().setValue(customerOrderDetailDto.getCustomerReference());
        customerOrderDetailForm.getCustomerOrderNumber().setValue(customerOrderDetailDto.getCustomerOrderNumber());
        customerOrderDetailForm.getAmount().setValue(customerOrderDetailDto.getAmount());
        customerOrderDetailForm.getCustomerOrderDate().setValue(customerOrderDetailDto.getCustomerOrderDate());
        customerOrderDetailForm.getCustomer().setValue(String.valueOf(customerOrderDetailDto.getCustomerId()));
    }

    private void createIdsToRemoveList() {
        if (itemIdsToRemove == null) {
            itemIdsToRemove = new ArrayList<Long>();
        }

        for (ListGridRecord listGridRecord : itemsForCustomerOrderGrid.getRecords()) {
            if (listGridRecord.getAttribute("remove") != null && listGridRecord.getAttribute("remove").equals("true")) {
                itemIdsToRemove.add(Long.valueOf(listGridRecord.getAttribute("id")));
            }
        }
    }

    private void displayCustomerOrderWindow(boolean newCustomerOrder) {

        resetItemsForCustomerOrderGrid();
        resetItemsForCustomerOrderDataSource();
        customerOrderDetailForm = new CustomerOrderDetailForm();
        itemsForCustomerOrderGrid = new ItemListGrid(false, false, true, null);
        createCustomerOrderButtons();

        //
        CheckboxItem selectAllCheckbox;
        selectAllCheckbox = new CheckboxItem();
        selectAllCheckbox.setTitle("Select All");
        selectAllCheckbox.setAlign(Alignment.LEFT);
        selectAllCheckbox.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent changedEvent) {
                for (ListGridRecord listGridRecord : itemsForCustomerOrderGrid.getRecords()) {
                    listGridRecord.setAttribute("remove", (Boolean) changedEvent.getValue());
                }
                itemsForCustomerOrderGrid.redraw();
            }
        });

        DynamicForm form = new DynamicForm();
        form.setFields(selectAllCheckbox);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAlign(Alignment.LEFT);

        //

        addCustomerOrderLayout = new CustomerOrderDetailLayout(itemsForCustomerOrderGrid, customerOrderDetailForm, cancelButton, saveCustomerOrderButton, addItemButton, form);
        customerOrderWindow = new CustomerOrderWindow(newCustomerOrder);

        customerOrderWindow.addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                customerOrderDetailDto = new CustomerOrderDetailDto();
                customerOrderWindow.hide();
            }
        });

        customerOrderWindow.addItem(addCustomerOrderLayout);
        customerOrderWindow.show();

        loadCustomers(newCustomerOrder);
    }

    public void doAddNewCustomerOrder() {
        itemIdsToExclude = new ArrayList<Long>();
        displayCustomerOrderWindow(true);
    }

    public void editCustomerOrder() {
        displayCustomerOrderWindow(false);
    }

    private void memorizesCustomerOrderDetails() {
        customerOrderDetailDto.setCustomerOrderDate(customerOrderDetailForm.getCustomerOrderDate().getValueAsDate());
        if (customerOrderDetailForm.getCustomer().getValue() != null) {
            customerOrderDetailDto.setCustomerId(Long.valueOf(customerOrderDetailForm.getCustomer().getValueAsString()));
        }
        customerOrderDetailDto.setDescription(customerOrderDetailForm.getCustomerOrderDescription().getEnteredValue());
        customerOrderDetailDto.setCustomerReference(customerOrderDetailForm.getCustomerReference().getValueAsString());
        customerOrderDetailDto.setCustomerOrderNumber(customerOrderDetailForm.getCustomerOrderNumber().getValueAsString());
        customerOrderDetailDto.setAmount(customerOrderDetailForm.getAmount().getValueAsString() != null && customerOrderDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(customerOrderDetailForm.getAmount().getValueAsString()) : null);
    }

    private void showWaitingForServer() {

        dlg = new Window();
        dlg.setWidth(950);
        dlg.setHeight(25);
        dlg.setIsModal(true);
        dlg.setShowModalMask(true);
        dlg.centerInPage();
        Img loadingImg = new Img("/gwttestl/ezgif-save.gif", 950, 20);
        dlg.addMember(loadingImg);
        dlg.show();
    }

    private void hideWaitingForServer() {
        dlg.hide();
    }
}


