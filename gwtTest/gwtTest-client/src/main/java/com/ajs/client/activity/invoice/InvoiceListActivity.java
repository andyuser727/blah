package com.ajs.client.activity.invoice;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.client.datasource.invoice.InvoiceDataSource;
import com.ajs.client.datasource.item.ItemListDataSource;
import com.ajs.client.datasource.item.ItemsForInvoiceDataSource;
import com.ajs.client.form.invoice.InvoiceDetailForm;
import com.ajs.client.layout.invoice.InvoiceDetailLayout;
import com.ajs.client.layout.item.ItemListLayout;
import com.ajs.client.listgrid.item.ItemListGrid;
import com.ajs.client.place.InvoiceListPlace;
import com.ajs.client.ui.invoice.InvoiceListView;
import com.ajs.client.window.invoice.InvoiceWindow;
import com.ajs.client.window.item.ItemListWindow;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.Test;
import com.ajs.shared.commands.LoadCustomerList;
import com.ajs.shared.commands.invoice.LoadInvoiceDetail;
import com.ajs.shared.commands.invoice.LoadInvoiceList;
import com.ajs.shared.commands.invoice.ProcessInvoiceDeletes;
import com.ajs.shared.commands.invoice.SaveInvoiceDetail;
import com.ajs.shared.commands.item.LoadItemList;
import com.ajs.shared.commands.item.SaveItemDetail;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
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
import com.smartgwt.client.widgets.grid.events.*;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.math.BigDecimal;
import java.util.*;

public class InvoiceListActivity extends BaseAbstractActivity {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private List<InvoiceDetailDto> invoiceDetailDtos;
    private InvoiceDetailDto invoiceDetailDto;
    private List<CustomerDetailDto> customerDetailDtos;
    private List<ItemDetailDto> itemDetailDtos;
    private Map<Long, ItemDetailDto> newItemDtos;
    private Provider<InvoiceListPlace> invoiceListPlaceProvider;

    private List<Long> itemIdsToExclude;
    private List<Long> itemIdsToRemove;

    TextItem itemCode;
    TextItem itemName;
    TextItem itemAmount;
    TextAreaItem itemDescription;

    private Window invoiceWindow;
    private Window itemListWindow;
    private Window addItemWindow;

    private ListGrid invoiceGrid;
    private ItemListGrid itemsForInvoiceGrid;
    private ItemListGrid itemListGrid;

    private InvoiceDataSource invoiceDataSource;
    private ItemsForInvoiceDataSource itemsForInvoiceDataSource;
    private ItemListDataSource itemListDataSource;

    private Button saveItemButton;
    private Button cancelButton;
    private Button saveInvoiceButton;
    private Button addItemButton;
    private Button newItemButton;
    private Button addItemsButton;
    private Button deleteSelectedButton;

    private InvoiceDetailForm invoiceDetailForm;

    private VLayout addInvoiceLayout;
    private VLayout itemListLayout;

    private Long invoiceId;

    private Test test;


    Window dlg;

    @Inject
    public InvoiceListActivity(InvoiceDataSource invoiceDataSource,
                               EventBus eventBus,
                               InvoiceListView display,
                               PlaceController placeController,
                               AppServiceAsync appServiceAsync,
                               Provider<InvoiceListPlace> invoiceListPlaceProvider,
                               Test test) {
        super(display);
        this.invoiceDataSource = invoiceDataSource;
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.invoiceListPlaceProvider = invoiceListPlaceProvider;
        this.test = test;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        // TODO for some reason, invoiceDataSource won't work with singleton pattern, must be injected
        resetInvoiceGrid();
        resetInvoiceDataSource();
        itemsForInvoiceDataSource = ItemsForInvoiceDataSource.getInstance();
        itemListDataSource = ItemListDataSource.getInstance();

        createInvoiceListGrid();

        container.setWidget(display.asWidget());
        loadInvoices();

//        com.google.gwt.user.client.Window.alert(test.getMessage());
    }

    private void resetInvoiceGrid() {

        if (invoiceGrid == null) {
            invoiceGrid = new ListGrid();
        } else {
            invoiceGrid.destroy();
            invoiceGrid = new ListGrid();
        }
    }

    private void resetInvoiceDataSource() {

        if (invoiceDataSource == null) {
            invoiceDataSource = new InvoiceDataSource();
        } else {
            invoiceDataSource.destroy();
            invoiceDataSource = new InvoiceDataSource();
        }
    }

    private void resetItemsForInvoiceGrid() {

        if (itemsForInvoiceGrid == null) {
            itemsForInvoiceGrid = new ItemListGrid(true, false, false, new ChangeHandler() {
                @Override
                public void onChange(final ChangeEvent changeEvent) {
                    invoiceDetailDto.getItemDtoList().get(changeEvent.getValue());
                }
            });
        } else {
            itemsForInvoiceGrid.destroy();
        }
    }

    private void resetItemsForInvoiceDataSource() {
        itemsForInvoiceDataSource.destroy();
        itemsForInvoiceDataSource = ItemsForInvoiceDataSource.getInstance();
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

    private void createInvoiceButtons() {

        cancelButton = new Button();
        saveInvoiceButton = new Button();
        addItemButton = new Button();
        deleteSelectedButton = new Button();
        cancelButton.setTitle("Cancel");
        saveInvoiceButton.setTitle("Save");
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
                invoiceDetailDto = new InvoiceDetailDto();
                invoiceWindow.hide();
            }
        });

        saveInvoiceButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                //TODO this doesn't work
                boolean isValid = true;
                try {
                    for (ListGridRecord rec : itemsForInvoiceGrid.getRecords()) {
                        if (rec.getAttribute("quantity") != null && rec.getAttribute("quantity").length() > 0) {
                            System.out.println(rec.getAttribute("quantity"));
                            Integer.valueOf(rec.getAttribute("quantity"));
                        }
                    }
                } catch (Exception e) {
                    com.google.gwt.user.client.Window.alert("Invalid Entries");
                    return;
                }
                doSaveInvoice();

            }
        });

        addItemButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {

                resetItemListGrid();
                resetItemListDataSource();

                createItemsListGrid();
                createItemListButtons();
                itemListLayout = new ItemListLayout(itemListGrid, newItemButton, addItemsButton);
                showItemListDialog();
                loadItemsList();

                memorizesInvoiceDetails();
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

                int rowNum = 0;
                for (ListGridRecord listGridRecord : itemListGrid.getSelectedRecords()) {
                    ItemDetailDto itemDetailDto = new ItemDetailDto();
                    itemDetailDto.setAmount(listGridRecord.getAttribute("amount") == null ? null : new BigDecimal(listGridRecord.getAttribute("amount")));
                    itemDetailDto.setCode(listGridRecord.getAttribute("itemCode"));
                    itemDetailDto.setName(listGridRecord.getAttribute("itemName"));
                    itemDetailDto.setDescription(listGridRecord.getAttribute("itemDescription"));

                    itemDetailDto.setQuantity(Integer.valueOf(itemListGrid.getEditedRecord(rowNum).getAttribute("quantity") == null ? null : itemListGrid.getEditedRecord(rowNum).getAttribute("quantity")));
                    itemDetailDto.setId(Long.valueOf(listGridRecord.getAttribute("id")));
//                    com.google.gwt.user.client.Window.alert(itemListGrid.getEditedRecord(rowNum).getAttribute("quantity"));

                    itemIdsToExclude.add(Long.valueOf(Long.valueOf(listGridRecord.getAttribute("id"))));
                    newItemDtos.put(Long.valueOf(Long.valueOf(listGridRecord.getAttribute("id"))), itemDetailDto);

                    rowNum++;
                }

                itemListWindow.hide();
                invoiceWindow.hide();
                displayInvoiceWindow(false);
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

                itemListGrid.saveAllEdits();

                if (!(event.getFieldNum() == 0) && !(event.getFieldNum() == 5) && !(event.getFieldNum() == 6)) {

                    ItemDetailDto itemDetailDto = new ItemDetailDto();
                    itemDetailDto.setAmount(event.getRecord().getAttribute("amount") == null ? null : new BigDecimal(event.getRecord().getAttribute("amount")));
                    itemDetailDto.setCode(event.getRecord().getAttribute("itemCode"));
                    itemDetailDto.setName(event.getRecord().getAttribute("itemName"));
                    itemDetailDto.setDescription(event.getRecord().getAttribute("itemDescription"));
                    itemDetailDto.setId(Long.valueOf(event.getRecord().getAttribute("id")));
                    itemDetailDto.setQuantity(Integer.valueOf(event.getRecord().getAttribute("quantity")));

                    itemIdsToExclude.add(itemDetailDto.getId());

                    if (newItemDtos == null) {
                        newItemDtos = new HashMap<Long, ItemDetailDto>();
                    }

                    newItemDtos.put(Long.valueOf(event.getRecord().getAttribute("id")), itemDetailDto);

                    itemListWindow.hide();
                    invoiceWindow.hide();
                    displayInvoiceWindow(false);
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

    public void createInvoiceListGrid() {

        ListGridField customerName = new ListGridField("customerName", "Customer Name");
        ListGridField invoiceNumber = new ListGridField("invoiceNumber", "Invoice Number");
        ListGridField invoiceDate = new ListGridField("invoiceDate", "Invoice Date");
        ListGridField customerReference = new ListGridField("customerReference", "Customer Reference");
        ListGridField description = new ListGridField("description", "Description");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);
        ListGridField id = new ListGridField("id", "ID");
        id.setType(ListGridFieldType.INTEGER);

        invoiceGrid.setFields(customerName, customerReference, invoiceNumber, description, amount, invoiceDate);

        invoiceGrid.setMargin(10);
        invoiceGrid.setHeight(300);
        invoiceGrid.setWidth(900);
        invoiceGrid.setTitle("Invoices");
        invoiceGrid.setDataSource(invoiceDataSource);
        invoiceGrid.setAutoFetchData(true);
        invoiceGrid.setShowFilterEditor(true);
        invoiceGrid.setCanSelectAll(true);
        invoiceGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        invoiceGrid.setSelectionType(SelectionStyle.SIMPLE);
        invoiceGrid.setModalEditing(false);
        invoiceGrid.setAlternateRecordStyles(true);

        invoiceGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
                if (!(event.getFieldNum() == 0)) {
                    invoiceId = Long.valueOf(event.getRecord().getAttribute("id"));
                    loadInvoiceDetails();
                }
            }
        });

        invoiceGrid.addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        Button addButton = new Button("Add Invoice");
        addButton.setStyleName("marginButton", true);
        addButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(final ClickEvent clickEvent) {
                doAddNewInvoice();
            }
        });

        Button deleteButton = new Button("Delete Items");
        deleteButton.setStyleName("marginButton", true);

        ((InvoiceListView) display).getGridPanel().add(addButton);
        ((InvoiceListView) display).getGridPanel().add(deleteButton);

        ((InvoiceListView) display).getGridPanel().add(invoiceGrid);
    }

    private void loadInvoiceDetails() {

        showWaitingForServer();
        LoadInvoiceDetail loadInvoiceDetail = new LoadInvoiceDetail(invoiceId);

        appServiceAsync.callServer(loadInvoiceDetail,
                new AsyncCallback<AppResponse<InvoiceDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<InvoiceDetailDto> result) {

                        hideWaitingForServer();
                        // TODO SHIT! CHANGE IT
                        itemIdsToExclude = new ArrayList<Long>();
                        invoiceDetailDto = result.getDtos().get(0);
                        for (ItemDetailDto itemDetailDto : invoiceDetailDto.getItemDtoList().values()) {
                            itemIdsToExclude.add(itemDetailDto.getId());
                        }
                        editInvoice();
                    }
                });
    }

    public void processDeletes() {

        showWaitingForServer();

        List<Long> idsToDelete = new ArrayList<Long>();

        for (ListGridRecord listGridRecord : invoiceGrid.getSelectedRecords()) {
            idsToDelete.add(Long.valueOf(listGridRecord.getAttribute("id")));
        }

        ProcessInvoiceDeletes processInvoiceDeletes = new ProcessInvoiceDeletes(idsToDelete);
        appServiceAsync.callServerSimple(processInvoiceDeletes,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(SimpleResponse result) {
                        hideWaitingForServer();
                        placeController.goTo(invoiceListPlaceProvider.get());
                    }
                });
    }

    private void loadInvoices() {

        showWaitingForServer();

        LoadInvoiceList loadInvoiceList = new LoadInvoiceList();

        appServiceAsync.callServer(loadInvoiceList,
                new AsyncCallback<AppResponse<InvoiceDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        caught.printStackTrace();

                    }

                    public void onSuccess(AppResponse<InvoiceDetailDto> result) {
//                        com.google.gwt.user.client.Window.alert("Success");
                        hideWaitingForServer();
                        invoiceDetailDtos = result.getDtos();
                        if (invoiceDetailDtos != null && invoiceDetailDtos.size() > 0) {
                            setInvoiceComponents();
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
                        itemDetailDtos = result.getDtos();
                        if (itemDetailDtos != null && itemDetailDtos.size() > 0) {
                            setItemListComponents();
                        }
                    }
                });
    }

    void loadCustomers(final boolean newInvoice) {

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
                        if (newInvoice) {
                            invoiceDetailDto = new InvoiceDetailDto();
                        } else {
                            setInvoiceData();
                            setItemsForInvoiceComponents();
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

        addItemWindow.addItem(createNewItemLayout());
        addItemWindow.show();
    }

    private void setCustomersList() {

        final LinkedHashMap<String, String> customerMap = new LinkedHashMap<String, String>();

        for (CustomerDetailDto customerDetailDto : customerDetailDtos) {
            customerMap.put(String.valueOf(customerDetailDto.getId()), customerDetailDto.getFirstName() + " " + customerDetailDto.getSurName());
        }
        invoiceDetailForm.getCustomer().setValueMap(customerMap);
    }

    private void setInvoiceComponents() {

        for (InvoiceDetailDto invoiceDetailDto : invoiceDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("customerName", invoiceDetailDto.getCustomerName());
            record.setAttribute("invoiceNumber", invoiceDetailDto.getInvoiceNumber());
            record.setAttribute("invoiceDate", invoiceDetailDto.getInvoiceDate());
            record.setAttribute("customerReference", invoiceDetailDto.getCustomerReference());
            record.setAttribute("description", invoiceDetailDto.getDescription());
            record.setAttribute("amount", invoiceDetailDto.getAmount() == null ? null : invoiceDetailDto.getAmount().floatValue());
            record.setAttribute("id", invoiceDetailDto.getId().intValue());

            invoiceDataSource.addData(record);
        }
        invoiceDataSource.fetchData();
        invoiceGrid.redraw();
    }

    private void setItemsForInvoiceComponents() {

        if (invoiceDetailDto != null && invoiceDetailDto.getItemDtoList() != null) {

            for (ItemDetailDto itemDetailDto : invoiceDetailDto.getItemDtoList().values()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("itemCode", itemDetailDto.getCode());
                record.setAttribute("itemName", itemDetailDto.getName());
                record.setAttribute("itemDescription", itemDetailDto.getDescription());
                record.setAttribute("amount", itemDetailDto.getAmount() == null ? null : itemDetailDto.getAmount().floatValue());
                record.setAttribute("id", itemDetailDto.getId().intValue());
                record.setAttribute("quantity", itemDetailDto.getQuantity());

                itemsForInvoiceDataSource.addData(record);
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
                record.setAttribute("quantity", itemDetailDto.getQuantity());

                itemsForInvoiceDataSource.addData(record);
            }
        }

        itemsForInvoiceDataSource.fetchData();
        itemsForInvoiceGrid.redraw();
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
        itemListDataSource.fetchData();
        itemListGrid.redraw();
    }

    public void doSaveInvoice() {

        showWaitingForServer();
        createIdsToRemoveList();

        SaveInvoiceDetail saveInvoiceDetail = new SaveInvoiceDetail(invoiceDetailDto, newItemDtos, itemIdsToRemove);
        invoiceDetailDto.setInvoiceDate(invoiceDetailForm.getInvoiceDate().getValueAsDate());
        invoiceDetailDto.setDescription(invoiceDetailForm.getInvoiceDescription().getEnteredValue());
        invoiceDetailDto.setCustomerReference(invoiceDetailForm.getCustomerReference().getValueAsString());
        invoiceDetailDto.setInvoiceNumber(invoiceDetailForm.getInvoiceNumber().getValueAsString());
        if (invoiceDetailForm.getCustomer().getValueAsString() != null && invoiceDetailForm.getCustomer().getValueAsString().length() > 0) {
            invoiceDetailDto.setCustomerId(Long.valueOf(invoiceDetailForm.getCustomer().getValueAsString()));
        }
        invoiceDetailDto.setAmount(invoiceDetailForm.getAmount().getValueAsString() != null && invoiceDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(invoiceDetailForm.getAmount().getValueAsString()) : null);
        appServiceAsync.callServerSimple(saveInvoiceDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        caught.printStackTrace();
                        caught.printStackTrace();
                    }

                    public void onSuccess(SimpleResponse result) {

                        hideWaitingForServer();
                        placeController.goTo(invoiceListPlaceProvider.get());
                        invoiceWindow.hide();
                    }
                });
    }

    public void saveItem() {

        ItemDetailDto itemDetailDto = new ItemDetailDto();
        itemDetailDto.setCode(itemCode.getEnteredValue());
        itemDetailDto.setDescription(itemDescription.getEnteredValue());
        itemDetailDto.setName(itemName.getEnteredValue());
        itemDetailDto.setAmount(itemAmount.getValueAsString() != null && itemAmount.getValueAsString().length() != 0 ? new BigDecimal(itemAmount.getValueAsString()) : null);

        SaveItemDetail saveItemDetail = new SaveItemDetail(itemDetailDto, invoiceId);
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
                        newItemDtos.put(result.getDtos().get(0).getId(), (ItemDetailDto) result.getDtos().get(0));
                        itemIdsToExclude.add(result.getDtos().get(0).getId());
                        addItemWindow.hide();
                        itemListWindow.hide();
                        invoiceWindow.hide();
                        displayInvoiceWindow(false);

                    }
                });
    }

    private void setInvoiceData() {

        invoiceDetailForm.getInvoiceDescription().setValue(invoiceDetailDto.getDescription());
        invoiceDetailForm.getCustomerReference().setValue(invoiceDetailDto.getCustomerReference());
        invoiceDetailForm.getInvoiceNumber().setValue(invoiceDetailDto.getInvoiceNumber());
        invoiceDetailForm.getAmount().setValue(invoiceDetailDto.getAmount());
        invoiceDetailForm.getInvoiceDate().setValue(invoiceDetailDto.getInvoiceDate());
        invoiceDetailForm.getCustomer().setValue(String.valueOf(invoiceDetailDto.getCustomerId()));
    }

    private void createIdsToRemoveList() {
        if (itemIdsToRemove == null) {
            itemIdsToRemove = new ArrayList<Long>();
        }

        for (ListGridRecord listGridRecord : itemsForInvoiceGrid.getRecords()) {
            if (listGridRecord.getAttribute("remove") != null && listGridRecord.getAttribute("remove").equals("true")) {
                itemIdsToRemove.add(Long.valueOf(listGridRecord.getAttribute("id")));
            }
        }
    }

    private void displayInvoiceWindow(boolean newInvoice) {

        resetItemsForInvoiceGrid();
        resetItemsForInvoiceDataSource();
        invoiceDetailForm = new InvoiceDetailForm();
        itemsForInvoiceGrid = new ItemListGrid(true, false, false, new ChangeHandler() {
            @Override
            public void onChange(final ChangeEvent changeEvent) {
                invoiceDetailDto.getItemDtoList().get(Long.valueOf(itemsForInvoiceGrid.getRecord(changeEvent.getRowNum()).getAttribute("id"))).setQuantity(changeEvent.getValue() == null ? 0 : Integer.valueOf((String)changeEvent.getValue()));
            }
        });
        createInvoiceButtons();

        CheckboxItem selectAllCheckbox;
        selectAllCheckbox = new CheckboxItem();
        selectAllCheckbox.setTitle("Select All");
        selectAllCheckbox.setAlign(Alignment.LEFT);
        selectAllCheckbox.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent changedEvent) {
                for (ListGridRecord listGridRecord : itemsForInvoiceGrid.getRecords()) {
                    listGridRecord.setAttribute("remove", (Boolean) changedEvent.getValue());
                }
                itemsForInvoiceGrid.redraw();
            }
        });

        DynamicForm form = new DynamicForm();
        form.setFields(selectAllCheckbox);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAlign(Alignment.LEFT);

        addInvoiceLayout = new InvoiceDetailLayout(itemsForInvoiceGrid, invoiceDetailForm, cancelButton, saveInvoiceButton, addItemButton, form);
        invoiceWindow = new InvoiceWindow(newInvoice);

        invoiceWindow.addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                invoiceDetailDto = new InvoiceDetailDto();
                invoiceWindow.hide();
            }
        });

        invoiceWindow.addItem(addInvoiceLayout);
        invoiceWindow.show();

        loadCustomers(newInvoice);
    }

    public void doAddNewInvoice() {
        itemIdsToExclude = new ArrayList<Long>();
        displayInvoiceWindow(true);
    }

    public void editInvoice() {
        displayInvoiceWindow(false);
    }

    private void memorizesInvoiceDetails() {
        invoiceDetailDto.setInvoiceDate(invoiceDetailForm.getInvoiceDate().getValueAsDate());
        if (invoiceDetailForm.getCustomer().getValue() != null) {
            invoiceDetailDto.setCustomerId(Long.valueOf(invoiceDetailForm.getCustomer().getValueAsString()));
        }
        invoiceDetailDto.setDescription(invoiceDetailForm.getInvoiceDescription().getEnteredValue());
        invoiceDetailDto.setCustomerReference(invoiceDetailForm.getCustomerReference().getValueAsString());
        invoiceDetailDto.setInvoiceNumber(invoiceDetailForm.getInvoiceNumber().getValueAsString());
        invoiceDetailDto.setAmount(invoiceDetailForm.getAmount().getValueAsString() != null && invoiceDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(invoiceDetailForm.getAmount().getValueAsString()) : null);
    }

    private void showWaitingForServer() {
        dlg = new Window();
        dlg.setWidth("100%");
        dlg.setHeight(25);
        dlg.setIsModal(true);
        dlg.setShowModalMask(true);
        Img loadingImg = new Img("/gwttestl/ezgif-save.gif", 3000, 20);
        dlg.addMember(loadingImg);
        dlg.moveTo(0, 0);
        dlg.show();
    }

    private void hideWaitingForServer() {
        dlg.hide();
    }
}


