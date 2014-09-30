package com.ajs.client.activity.quote;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.client.datasource.item.ItemListDataSource;
import com.ajs.client.datasource.quote.ItemsForQuoteDataSource;
import com.ajs.client.datasource.quote.QuoteDataSource;
import com.ajs.client.form.quote.QuoteDetailForm;
import com.ajs.client.layout.item.ItemListLayout;
import com.ajs.client.layout.quote.QuoteDetailLayout;
import com.ajs.client.listgrid.item.ItemListGrid;
import com.ajs.client.place.QuoteListPlace;
import com.ajs.client.ui.quote.QuoteListView;
import com.ajs.client.window.item.ItemListWindow;
import com.ajs.client.window.quote.QuoteWindow;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.commands.LoadCustomerList;
import com.ajs.shared.commands.item.LoadItemList;
import com.ajs.shared.commands.item.SaveItemDetail;
import com.ajs.shared.commands.quote.LoadQuoteDetail;
import com.ajs.shared.commands.quote.LoadQuoteList;
import com.ajs.shared.commands.quote.SaveQuoteDetail;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.ajs.shared.dto.quote.QuoteDetailDto;
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
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.math.BigDecimal;
import java.util.*;

public class QuoteListActivity extends BaseAbstractActivity {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private List<QuoteDetailDto> quoteDetailDtos;
    private QuoteDetailDto quoteDetailDto;
    private List<CustomerDetailDto> customerDetailDtos;
    private List<ItemDetailDto> itemDetailDtos;
    private Map<Long, ItemDetailDto> newItemDtos;
    private Provider<QuoteListPlace> quoteListPlaceProvider;

    private List<Long> itemIdsToExclude;
    private List<Long> itemIdsToRemove;

    TextItem itemCode;
    TextItem itemName;
    TextItem itemAmount;
    TextAreaItem itemDescription;

    private Window quoteWindow;
    private Window itemListWindow;
    private Window addItemWindow;

    private ListGrid quoteGrid;
    private ItemListGrid itemsForQuoteGrid;
    private ItemListGrid itemListGrid;

    private QuoteDataSource quoteDataSource;
    private ItemsForQuoteDataSource itemsForQuoteDataSource;
    private ItemListDataSource itemListDataSource;

    private Button saveItemButton;
    private Button cancelButton;
    private Button saveQuoteButton;
    private Button addItemButton;
    private Button newItemButton;
    private Button addItemsButton;
    private Button deleteSelectedButton;

    private QuoteDetailForm quoteDetailForm;

    private VLayout addQuoteLayout;
    private VLayout itemListLayout;

    private Long quoteId;


    Window dlg;

    @Inject
    public QuoteListActivity(QuoteDataSource quoteDataSource,
                               EventBus eventBus,
                               QuoteListView display,
                               PlaceController placeController,
                               AppServiceAsync appServiceAsync,
                               Provider<QuoteListPlace> quoteListPlaceProvider) {
        super(display);
        this.quoteDataSource = quoteDataSource;
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.quoteListPlaceProvider = quoteListPlaceProvider;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        // TODO for some reason, quoteDataSource won't work with singleton pattern, must be injected
        resetQuoteGrid();
        resetQuoteDataSource();
        itemsForQuoteDataSource = ItemsForQuoteDataSource.getInstance();
        itemListDataSource = ItemListDataSource.getInstance();

        createQuoteListGrid();

        container.setWidget(display.asWidget());
        loadQuotes();
    }

    private void resetQuoteGrid() {

        if (quoteGrid == null) {
            quoteGrid = new ListGrid();
        } else {
            quoteGrid.destroy();
            quoteGrid = new ListGrid();
        }
    }

    private void resetQuoteDataSource() {

        if (quoteDataSource == null) {
            quoteDataSource = new QuoteDataSource();
        } else {
            quoteDataSource.destroy();
            quoteDataSource = new QuoteDataSource();
        }
    }

    private void resetItemsForQuoteGrid() {

        if (itemsForQuoteGrid == null) {
            itemsForQuoteGrid = new ItemListGrid(false, true, false, null);
        } else {
            itemsForQuoteGrid.destroy();
        }
    }

    private void resetItemsForQuoteDataSource() {
        itemsForQuoteDataSource.destroy();
        itemsForQuoteDataSource = ItemsForQuoteDataSource.getInstance();
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

    private void createQuoteButtons() {

        cancelButton = new Button();
        saveQuoteButton = new Button();
        addItemButton = new Button();
        deleteSelectedButton = new Button();
        cancelButton.setTitle("Cancel");
        saveQuoteButton.setTitle("Save");
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
                quoteDetailDto = new QuoteDetailDto();
                quoteWindow.hide();
            }
        });

        saveQuoteButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSaveQuote();
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

                memorizesQuoteDetails();
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
                quoteWindow.hide();
                displayQuoteWindow(false);
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
                    quoteWindow.hide();
                    displayQuoteWindow(false);
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

    public void createQuoteListGrid() {

        ListGridField customerName = new ListGridField("customerName", "Customer Name");
        ListGridField quoteNumber = new ListGridField("quoteNumber", "Quote Number");
        ListGridField quoteDate = new ListGridField("quoteDate", "Quote Date");
        ListGridField customerReference = new ListGridField("customerReference", "Customer Reference");
        ListGridField description = new ListGridField("description", "Description");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);
        ListGridField id = new ListGridField("id", "ID");
        id.setType(ListGridFieldType.INTEGER);

        quoteGrid.setFields(id, customerName, customerReference, quoteNumber, description, amount, quoteDate);

        quoteGrid.setHeight(670);
        quoteGrid.setWidth(1350);
        quoteGrid.setTitle("Quotes");
        quoteGrid.setDataSource(quoteDataSource);
        quoteGrid.setAutoFetchData(true);
        quoteGrid.setShowFilterEditor(true);
        quoteGrid.setCanSelectAll(true);
        quoteGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        quoteGrid.setSelectionType(SelectionStyle.SIMPLE);
        quoteGrid.setModalEditing(false);
        quoteGrid.setAlternateRecordStyles(true);

        quoteGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
                if (!(event.getFieldNum() == 0)) {
                    quoteId = Long.valueOf(event.getRecord().getAttribute("id"));
                    loadQuoteDetails();
                }
            }
        });

        quoteGrid.addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        ((QuoteListView) display).getGridPanel().add(quoteGrid);
    }

    private void loadQuoteDetails() {

        showWaitingForServer();
        LoadQuoteDetail loadQuoteDetail = new LoadQuoteDetail(quoteId);

        appServiceAsync.callServer(loadQuoteDetail,
                new AsyncCallback<AppResponse<QuoteDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<QuoteDetailDto> result) {

                        hideWaitingForServer();
                        // TODO SHIT! CHANGE IT
                        itemIdsToExclude = new ArrayList<Long>();
                        quoteDetailDto = result.getDtos().get(0);
                        for (ItemDetailDto itemDetailDto : quoteDetailDto.getItemDtoList().values()) {
                            itemIdsToExclude.add(itemDetailDto.getId());
                        }
                        editQuote();
                    }
                });
    }

    public void processDeletes() {

//        showWaitingForServer();
//
//        List<Long> idsToDelete = new ArrayList<Long>();
//
//        for (ListGridRecord listGridRecord : quoteGrid.getSelectedRecords()) {
//            idsToDelete.add(Long.valueOf(listGridRecord.getAttribute("id")));
//        }
//
//        ProcessQuoteDeletes processQuoteDeletes = new ProcessQuoteDeletes(idsToDelete);
//        appServiceAsync.callServer(processQuoteDeletes,
//                new AsyncCallback<AppResponse>() {
//                    public void onFailure(Throwable caught) {
//                        // Show the RPC error message to the user
//                        hideWaitingForServer();
//                        System.out.println(caught.getMessage());
//                    }
//
//                    public void onSuccess(AppResponse result) {
//                        hideWaitingForServer();
//                        placeController.goTo(quoteListPlaceProvider.get());
//                    }
//                });
    }

    private void loadQuotes() {

        showWaitingForServer();

        LoadQuoteList loadQuoteList = new LoadQuoteList();

        appServiceAsync.callServer(loadQuoteList,
                new AsyncCallback<AppResponse<QuoteDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<QuoteDetailDto> result) {
                        hideWaitingForServer();
                        // TODO seems wrong, read the fucking generics chapter (for the 50th fucking time... a fucking gain)
                        quoteDetailDtos = result.getDtos();
                        if (quoteDetailDtos != null && quoteDetailDtos.size() > 0) {
                            setQuoteComponents();
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

    void loadCustomers(final boolean newQuote) {

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
                        if (newQuote) {
                            quoteDetailDto = new QuoteDetailDto();
                        } else {
                            setQuoteData();
                            setItemsForQuoteComponents();
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
        quoteDetailForm.getCustomer().setValueMap(customerMap);
    }

    private void setQuoteComponents() {

        for (QuoteDetailDto quoteDetailDto : quoteDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("customerName", quoteDetailDto.getCustomerName());
            record.setAttribute("quoteNumber", quoteDetailDto.getQuoteNumber());
            record.setAttribute("quoteDate", quoteDetailDto.getQuoteDate());
            record.setAttribute("customerReference", quoteDetailDto.getCustomerReference());
            record.setAttribute("description", quoteDetailDto.getDescription());
            record.setAttribute("amount", quoteDetailDto.getAmount() == null ? null : quoteDetailDto.getAmount().floatValue());
            record.setAttribute("id", quoteDetailDto.getId().intValue());

            quoteDataSource.addData(record);
        }
        quoteDataSource.fetchData();
        quoteGrid.redraw();
    }

    private void setItemsForQuoteComponents() {

        if (quoteDetailDto != null && quoteDetailDto.getItemDtoList() != null) {

            for (ItemDetailDto itemDetailDto : quoteDetailDto.getItemDtoList().values()) {
                ListGridRecord record = new ListGridRecord();
                record.setAttribute("itemCode", itemDetailDto.getCode());
                record.setAttribute("itemName", itemDetailDto.getName());
                record.setAttribute("itemDescription", itemDetailDto.getDescription());
                record.setAttribute("amount", itemDetailDto.getAmount() == null ? null : itemDetailDto.getAmount().floatValue());
                record.setAttribute("id", itemDetailDto.getId().intValue());

                itemsForQuoteDataSource.addData(record);
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

                itemsForQuoteDataSource.addData(record);
            }
        }

        itemsForQuoteDataSource.fetchData();
        itemsForQuoteGrid.redraw();
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

    public void doSaveQuote() {

        showWaitingForServer();
        createIdsToRemoveList();

        SaveQuoteDetail saveQuoteDetail = new SaveQuoteDetail(quoteDetailDto, newItemDtos, itemIdsToRemove);
        quoteDetailDto.setQuoteDate(quoteDetailForm.getQuoteDate().getValueAsDate());
        quoteDetailDto.setDescription(quoteDetailForm.getQuoteDescription().getEnteredValue());
        quoteDetailDto.setCustomerReference(quoteDetailForm.getCustomerReference().getValueAsString());
        quoteDetailDto.setQuoteNumber(quoteDetailForm.getQuoteNumber().getValueAsString());
        if (quoteDetailForm.getCustomer().getValueAsString() != null && quoteDetailForm.getCustomer().getValueAsString().length() > 0) {
            quoteDetailDto.setCustomerId(Long.valueOf(quoteDetailForm.getCustomer().getValueAsString()));
        }
        quoteDetailDto.setAmount(quoteDetailForm.getAmount().getValueAsString() != null && quoteDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(quoteDetailForm.getAmount().getValueAsString()) : null);
        appServiceAsync.callServerSimple(saveQuoteDetail,
                new AsyncCallback<SimpleResponse>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        caught.printStackTrace();
                    }

                    public void onSuccess(SimpleResponse result) {

                        hideWaitingForServer();
                        placeController.goTo(quoteListPlaceProvider.get());
                        quoteWindow.hide();
                    }
                });
    }

    public void saveItem() {

        ItemDetailDto itemDetailDto = new ItemDetailDto();
        itemDetailDto.setCode(itemCode.getEnteredValue());
        itemDetailDto.setDescription(itemDescription.getEnteredValue());
        itemDetailDto.setName(itemName.getEnteredValue());
        itemDetailDto.setAmount(itemAmount.getValueAsString() != null && itemAmount.getValueAsString().length() != 0 ? new BigDecimal(itemAmount.getValueAsString()) : null);

        SaveItemDetail saveItemDetail = new SaveItemDetail(itemDetailDto, quoteId);
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
                        quoteWindow.hide();
                        displayQuoteWindow(false);

                    }
                });
    }

    private void setQuoteData() {

        quoteDetailForm.getQuoteDescription().setValue(quoteDetailDto.getDescription());
        quoteDetailForm.getCustomerReference().setValue(quoteDetailDto.getCustomerReference());
        quoteDetailForm.getQuoteNumber().setValue(quoteDetailDto.getQuoteNumber());
        quoteDetailForm.getAmount().setValue(quoteDetailDto.getAmount());
        quoteDetailForm.getQuoteDate().setValue(quoteDetailDto.getQuoteDate());
        quoteDetailForm.getCustomer().setValue(String.valueOf(quoteDetailDto.getCustomerId()));
    }

    private void createIdsToRemoveList() {
        if (itemIdsToRemove == null) {
            itemIdsToRemove = new ArrayList<Long>();
        }

        for (ListGridRecord listGridRecord : itemsForQuoteGrid.getRecords()) {
            if (listGridRecord.getAttribute("remove") != null && listGridRecord.getAttribute("remove").equals("true")) {
                itemIdsToRemove.add(Long.valueOf(listGridRecord.getAttribute("id")));
            }
        }
    }

    private void displayQuoteWindow(boolean newQuote) {

        resetItemsForQuoteGrid();
        resetItemsForQuoteDataSource();
        quoteDetailForm = new QuoteDetailForm();
        itemsForQuoteGrid = new ItemListGrid(false, true, false, null);
        createQuoteButtons();

        //
        CheckboxItem selectAllCheckbox;
        selectAllCheckbox = new CheckboxItem();
        selectAllCheckbox.setTitle("Select All");
        selectAllCheckbox.setAlign(Alignment.LEFT);
        selectAllCheckbox.addChangedHandler(new ChangedHandler() {
            @Override
            public void onChanged(ChangedEvent changedEvent) {
                for (ListGridRecord listGridRecord : itemsForQuoteGrid.getRecords()) {
                    listGridRecord.setAttribute("remove", (Boolean) changedEvent.getValue());
                }
                itemsForQuoteGrid.redraw();
            }
        });

        DynamicForm form = new DynamicForm();
        form.setFields(selectAllCheckbox);
        form.setLayoutAlign(VerticalAlignment.BOTTOM);
        form.setAlign(Alignment.LEFT);

        //

        addQuoteLayout = new QuoteDetailLayout(itemsForQuoteGrid, quoteDetailForm, cancelButton, saveQuoteButton, addItemButton, form);
        quoteWindow = new QuoteWindow(newQuote);

        quoteWindow.addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                quoteDetailDto = new QuoteDetailDto();
                quoteWindow.hide();
            }
        });

        quoteWindow.addItem(addQuoteLayout);
        quoteWindow.show();

        loadCustomers(newQuote);
    }

    public void doAddNewQuote() {
        itemIdsToExclude = new ArrayList<Long>();
        displayQuoteWindow(true);
    }

    public void editQuote() {
        displayQuoteWindow(false);
    }

    private void memorizesQuoteDetails() {
        quoteDetailDto.setQuoteDate(quoteDetailForm.getQuoteDate().getValueAsDate());
        if (quoteDetailForm.getCustomer().getValue() != null) {
            quoteDetailDto.setCustomerId(Long.valueOf(quoteDetailForm.getCustomer().getValueAsString()));
        }
        quoteDetailDto.setDescription(quoteDetailForm.getQuoteDescription().getEnteredValue());
        quoteDetailDto.setCustomerReference(quoteDetailForm.getCustomerReference().getValueAsString());
        quoteDetailDto.setQuoteNumber(quoteDetailForm.getQuoteNumber().getValueAsString());
        quoteDetailDto.setAmount(quoteDetailForm.getAmount().getValueAsString() != null && quoteDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(quoteDetailForm.getAmount().getValueAsString()) : null);
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


