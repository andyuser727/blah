package com.ajs.client.activity.payment;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.client.datasource.invoice.InvoicesForCustomerDataSource;
import com.ajs.client.datasource.payment.PaymentDataSource;
import com.ajs.client.form.payment.PaymentDetailForm;
import com.ajs.client.layout.payment.PaymentDetailLayout;
import com.ajs.client.listgrid.invoice.InvoicesForCustomerListGrid;
import com.ajs.client.place.PaymentListPlace;
import com.ajs.client.ui.payment.PaymentListView;
import com.ajs.client.window.payment.PaymentWindow;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.shared.Test;
import com.ajs.shared.commands.LoadCustomerList;
import com.ajs.shared.commands.invoice.LoadInvoiceList;
import com.ajs.shared.commands.payment.LoadPaymentDetail;
import com.ajs.shared.commands.payment.LoadPaymentList;
import com.ajs.shared.commands.payment.SavePaymentDetail;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.ajs.shared.dto.payment.PaymentDetailDto;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.smartgwt.client.types.ListGridFieldType;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickEvent;
import com.smartgwt.client.widgets.grid.events.HeaderDoubleClickHandler;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PaymentListActivity extends BaseAbstractActivity {

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private PlaceController placeController;
    private List<PaymentDetailDto> paymentDetailDtos;
    private PaymentDetailDto paymentDetailDto;
    private List<CustomerDetailDto> customerDetailDtos;
    private List<InvoiceDetailDto> invoiceDetailDtos;
    private Map<Long, ItemDetailDto> newItemDtos;
    private Provider<PaymentListPlace> paymentListPlaceProvider;

    private PaymentWindow paymentWindow;

    private ListGrid paymentGrid;
    private InvoicesForCustomerListGrid invoicesForCustomerListGrid;

    private PaymentDataSource paymentDataSource;
    private InvoicesForCustomerDataSource invoicesForCustomerDataSource;

    private Button cancelButton;
    private Button savePaymentButton;
    private Button applyPaymentButton;

    private PaymentDetailForm paymentDetailForm;

    private PaymentDetailLayout addPaymentLayout;

    private Long paymentId;

    private Window dlg;

    private Provider<Test> testProvider;

    @Inject
    public PaymentListActivity(PaymentDataSource paymentDataSource,
                               EventBus eventBus,
                               PaymentListView display,
                               PlaceController placeController,
                               AppServiceAsync appServiceAsync,
                               Provider<PaymentListPlace> paymentListPlaceProvider,
                               Provider<Test> testProvider) {
        super(display);
        this.paymentDataSource = paymentDataSource;
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.placeController = placeController;
        this.paymentListPlaceProvider = paymentListPlaceProvider;
        this.testProvider = testProvider;

//        com.google.gwt.user.client.Window.alert(testProvider.get().getMessage());
//
//        TestImpl test = new TestImpl();
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {

        // TODO for some reason, paymentDataSource won't work with singleton pattern, must be injected
        resetPaymentGrid();
        resetPaymentDataSource();
        invoicesForCustomerDataSource = InvoicesForCustomerDataSource.getInstance();

        createPaymentListGrid();

        container.setWidget(display.asWidget());
        createLoadingWindow();
        loadPayments();

    }

    private void createLoadingWindow() {
        dlg = new Window();
        dlg.setWidth("100%");
        dlg.setHeight(25);
        dlg.setIsModal(true);
        dlg.setShowModalMask(true);
//        dlg.centerInPage();
        dlg.moveTo(0, 0);
        Img loadingImg = new Img("/gwttestl/ezgif-save.gif", 3000, 20);
        dlg.addMember(loadingImg);
    }

    private void resetPaymentGrid() {

        if (paymentGrid == null) {
            paymentGrid = new ListGrid();
        } else {
            paymentGrid.destroy();
            paymentGrid = new ListGrid();
        }
    }

    private void resetPaymentDataSource() {

        if (paymentDataSource == null) {
            paymentDataSource = new PaymentDataSource();
        } else {
            paymentDataSource.destroy();
            paymentDataSource = new PaymentDataSource();
        }
    }

    private void resetInvoicesForCustomerGrid() {

        if (invoicesForCustomerListGrid == null) {
            invoicesForCustomerListGrid = new InvoicesForCustomerListGrid();
        } else {
            invoicesForCustomerListGrid.destroy();
        }
    }

    private void resetInvoicesForCustomerDataSource() {
        invoicesForCustomerDataSource.destroy();
        invoicesForCustomerDataSource = InvoicesForCustomerDataSource.getInstance();
    }


    private void createPaymentButtons() {

        cancelButton = new Button();
        savePaymentButton = new Button();
        applyPaymentButton = new Button();
        cancelButton.setTitle("Cancel");
        savePaymentButton.setTitle("Save");
        applyPaymentButton.setTitle("Apply Payment");

        cancelButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                paymentDetailDto = new PaymentDetailDto();
                paymentWindow.hide();
                placeController.goTo(paymentListPlaceProvider.get());
            }
        });

        savePaymentButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSavePayment(false);
            }
        });

        applyPaymentButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                doSavePayment(true);
            }
        });


    }

    public void createPaymentListGrid() {

        ListGridField customerName = new ListGridField("customerName", "Customer Name");
        ListGridField paymentNumber = new ListGridField("paymentNumber", "Payment Number");
        ListGridField paymentDate = new ListGridField("paymentDate", "Payment Date");
        ListGridField customerReference = new ListGridField("customerReference", "Customer Reference");
        ListGridField description = new ListGridField("description", "Description");
        ListGridField amount = new ListGridField("amount", "Amount");
        amount.setType(ListGridFieldType.FLOAT);
        ListGridField id = new ListGridField("id", "ID");
        id.setType(ListGridFieldType.INTEGER);

        paymentGrid.setFields(customerName, customerReference, paymentNumber, description, amount, paymentDate);

        paymentGrid.setMargin(10);
        paymentGrid.setHeight(300);
        paymentGrid.setWidth(900);
        paymentGrid.setTitle("Payments");
        paymentGrid.setDataSource(paymentDataSource);
        paymentGrid.setAutoFetchData(true);
        paymentGrid.setShowFilterEditor(true);
        paymentGrid.setCanSelectAll(true);
        paymentGrid.setSelectionAppearance(SelectionAppearance.CHECKBOX);
        paymentGrid.setSelectionType(SelectionStyle.SIMPLE);
        paymentGrid.setModalEditing(false);
        paymentGrid.setAlternateRecordStyles(true);

        paymentGrid.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
                if (!(event.getFieldNum() == 0)) {
                    paymentId = Long.valueOf(event.getRecord().getAttribute("id"));
                    loadPaymentDetails();
                }
            }
        });

        paymentGrid.addHeaderDoubleClickHandler(new HeaderDoubleClickHandler() {
            @Override
            public void onHeaderDoubleClick(HeaderDoubleClickEvent event) {
                // method stub
            }
        });

        Button addButton = new Button("Add Payment");
        addButton.setStyleName("marginButton", true);
        addButton.addClickHandler(new ClickHandler() {
                                      @Override
                                      public void onClick(final ClickEvent clickEvent) {
                                          doAddNewPayment();
                                      }
                                  });
        Button deleteButton = new Button("Delete Items");
        deleteButton.setStyleName("marginButton", true);

        ((PaymentListView) display).getGridPanel().add(addButton);
        ((PaymentListView) display).getGridPanel().add(deleteButton);

        ((PaymentListView) display).getGridPanel().add(paymentGrid);
    }

    private void loadPaymentDetails() {

        showWaitingForServer();
        LoadPaymentDetail loadPaymentDetail = new LoadPaymentDetail(paymentId);

        appServiceAsync.callServer(loadPaymentDetail,
                new AsyncCallback<AppResponse<PaymentDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        caught.printStackTrace();
                    }

                    public void onSuccess(AppResponse<PaymentDetailDto> result) {

                        hideWaitingForServer();
                        paymentDetailDto = result.getDtos().get(0);
                        editPayment();
                    }
                });
    }

    private void loadPayments() {

        showWaitingForServer();

        LoadPaymentList loadPaymentList = new LoadPaymentList();

        appServiceAsync.callServer(loadPaymentList,
                new AsyncCallback<AppResponse<PaymentDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        hideWaitingForServer();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<PaymentDetailDto> result) {
                        hideWaitingForServer();
                        paymentDetailDtos = result.getDtos();
                        if (paymentDetailDtos != null && paymentDetailDtos.size() > 0) {
                            setPaymentComponents();
                        }
                    }
                });
    }

    void loadInvoiceList(Long customerId) {

        addPaymentLayout.showLoadingLayout();

        LoadInvoiceList loadInvoiceList = new LoadInvoiceList(customerId);

        appServiceAsync.callServer(loadInvoiceList,
                new AsyncCallback<AppResponse<InvoiceDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        addPaymentLayout.hideLoadingLayout();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<InvoiceDetailDto> result) {
                        addPaymentLayout.hideLoadingLayout();
                        invoiceDetailDtos = result.getDtos();
                        if (invoiceDetailDtos != null && invoiceDetailDtos.size() > 0) {
                            setInvoiceComponents();
                        }
                    }
                });
    }

    void loadCustomers(final boolean newPayment) {

        addPaymentLayout.showLoadingLayout();
        LoadCustomerList loadCustomerList = new LoadCustomerList();

        appServiceAsync.callServer(loadCustomerList,
                new AsyncCallback<AppResponse<CustomerDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        addPaymentLayout.hideLoadingLayout();
                        System.out.println(caught.getMessage());
                    }

                    public void onSuccess(AppResponse<CustomerDetailDto> result) {
                        addPaymentLayout.hideLoadingLayout();
                        customerDetailDtos = result.getDtos();
                        if (customerDetailDtos != null && customerDetailDtos.size() > 0) {
                            setCustomersList();
                        }
                        if (newPayment) {
                            paymentDetailDto = new PaymentDetailDto();
                        } else {
                            setPaymentData();
                            loadInvoiceList(paymentDetailDto.getCustomerId());
                        }
                    }
                });
    }


    private void setCustomersList() {

        final LinkedHashMap<String, String> customerMap = new LinkedHashMap<String, String>();

        for (CustomerDetailDto customerDetailDto : customerDetailDtos) {
            customerMap.put(String.valueOf(customerDetailDto.getId()), customerDetailDto.getFirstName() + " " + customerDetailDto.getSurName());
        }
        paymentDetailForm.getCustomer().setValueMap(customerMap);
    }

    private void setPaymentComponents() {

        for (PaymentDetailDto paymentDetailDto : paymentDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("paymentNumber", paymentDetailDto.getPaymentNumber());
            record.setAttribute("paymentDate", paymentDetailDto.getPaymentDate());
            record.setAttribute("customerReference", paymentDetailDto.getCustomerReference());
            record.setAttribute("description", paymentDetailDto.getDescription());
            record.setAttribute("customerName", paymentDetailDto.getCustomerName());
            record.setAttribute("amount", paymentDetailDto.getTotalAmount() == null ? null : paymentDetailDto.getTotalAmount().floatValue());
            record.setAttribute("id", paymentDetailDto.getId().intValue());

            paymentDataSource.addData(record);
        }
        paymentDataSource.fetchData();
        paymentGrid.redraw();
    }

    private void setInvoiceComponents() {

        invoicesForCustomerListGrid.selectAllRecords();
        invoicesForCustomerListGrid.removeSelectedData();

        for (InvoiceDetailDto invoiceDetailDto : invoiceDetailDtos) {
            ListGridRecord record = new ListGridRecord();
            record.setAttribute("invoiceNumber", invoiceDetailDto.getInvoiceNumber());
            record.setAttribute("invoiceDescription", invoiceDetailDto.getDescription());
            record.setAttribute("invoiceDate", invoiceDetailDto.getInvoiceDate());
            record.setAttribute("amount", invoiceDetailDto.getAmount() == null ? null : invoiceDetailDto.getAmount().floatValue());
            record.setAttribute("id", invoiceDetailDto.getId().intValue());

            invoicesForCustomerDataSource.addData(record);
        }
        invoicesForCustomerDataSource.fetchData();
        invoicesForCustomerListGrid.redraw();
    }

    public void doSavePayment(boolean applyPayment) {

        addPaymentLayout.hideMsgLayout();

        Long selectedPayment = null;
        if (applyPayment) {
            selectedPayment = invoicesForCustomerListGrid.getSelectedRecord() == null ? null : Long.valueOf(invoicesForCustomerListGrid.getSelectedRecord().getAttribute("id"));
        }
        SavePaymentDetail savePaymentDetail = new SavePaymentDetail(paymentDetailDto, applyPayment, selectedPayment);
        paymentDetailDto.setPaymentDate(paymentDetailForm.getPaymentDate().getValueAsDate());
        paymentDetailDto.setDescription(paymentDetailForm.getPaymentDescription().getEnteredValue());
        paymentDetailDto.setCustomerReference(paymentDetailForm.getCustomerReference().getValueAsString());
        paymentDetailDto.setPaymentNumber(paymentDetailForm.getPaymentNumber().getValueAsString());
        if (paymentDetailForm.getCustomer().getValueAsString() != null && paymentDetailForm.getCustomer().getValueAsString().length() > 0) {
            paymentDetailDto.setCustomerId(Long.valueOf(paymentDetailForm.getCustomer().getValueAsString()));
        }
        paymentDetailDto.setTotalAmount(paymentDetailForm.getAmount().getValueAsString() != null && paymentDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(paymentDetailForm.getAmount().getValueAsString()) : null);
        paymentDetailDto.setAmountToAllocate(paymentDetailForm.getAmountToAllocate().getValueAsString() != null && paymentDetailForm.getAmountToAllocate().getValueAsString().length() != 0 ? new BigDecimal(paymentDetailForm.getAmountToAllocate().getValueAsString()) : null);

        addPaymentLayout.showLoadingLayout();

        appServiceAsync.callServer(savePaymentDetail,
                new AsyncCallback<AppResponse<PaymentDetailDto>>() {
                    public void onFailure(Throwable caught) {
                        // Show the RPC error message to the user
                        addPaymentLayout.hideLoadingLayout();
                        caught.printStackTrace();
                    }

                    public void onSuccess(AppResponse<PaymentDetailDto> result) {

                        addPaymentLayout.hideLoadingLayout();
                        paymentDetailDto = result.getDtos().get(0);
                        editPayment();
                        if (result.getValidationMessages() != null) {
                            addPaymentLayout.showMessages(result.getValidationMessages());
                        }
                        else {
                            placeController.goTo(paymentListPlaceProvider.get());
                            addPaymentLayout.hide();
                            paymentWindow.hide();
                        }
                    }
                });
    }

    private void setPaymentData() {

        paymentDetailForm.getPaymentDescription().setValue(paymentDetailDto.getDescription());
        paymentDetailForm.getCustomerReference().setValue(paymentDetailDto.getCustomerReference());
        paymentDetailForm.getPaymentNumber().setValue(paymentDetailDto.getPaymentNumber());
        paymentDetailForm.getAmount().setValue(paymentDetailDto.getTotalAmount());
        paymentDetailForm.getAllocatedAmount().setValue(paymentDetailDto.getAllocatedAmount());
        paymentDetailForm.getRemainingAmount().setValue(paymentDetailDto.getRemainingAmount());
        paymentDetailForm.getPaymentDate().setValue(paymentDetailDto.getPaymentDate());
        paymentDetailForm.getCustomer().setValue(String.valueOf(paymentDetailDto.getCustomerId()));
    }

    private void displayPaymentWindow(boolean newPayment) {

        resetInvoicesForCustomerGrid();
        resetInvoicesForCustomerDataSource();
        paymentDetailForm = new PaymentDetailForm();
        paymentDetailForm.setCustomerChangedHandler(new ChangeHandler() {
            @Override
            public void onChange(ChangeEvent changeEvent) {
                loadInvoiceList(Long.valueOf(changeEvent.getValue().toString()));
            }
        });
        invoicesForCustomerListGrid = new InvoicesForCustomerListGrid();
        createPaymentButtons();

        addPaymentLayout = new PaymentDetailLayout(invoicesForCustomerListGrid, paymentDetailForm, cancelButton, savePaymentButton, applyPaymentButton);

        addPaymentLayout.showLoadingLayout();

        if (paymentWindow != null) {
            paymentWindow.destroy();
        }
        paymentWindow = new PaymentWindow(newPayment);

        paymentWindow.addCloseClickHandler(new CloseClickHandler() {
            public void onCloseClick(CloseClickEvent event) {
                paymentDetailDto = new PaymentDetailDto();
                paymentWindow.hide();
                placeController.goTo(paymentListPlaceProvider.get());
            }
        });

        paymentWindow.addItem(addPaymentLayout);
        paymentWindow.show();

        loadCustomers(newPayment);

    }

    public void doAddNewPayment() {
        paymentDetailDto = new PaymentDetailDto();
        displayPaymentWindow(true);
    }

    public void editPayment() {
        displayPaymentWindow(false);
    }

    private void memorizesPaymentDetails() {
//        paymentDetailDto.setPaymentDate(paymentDetailForm.getPaymentDate().getValueAsDate());
//        if (paymentDetailForm.getCustomer().getValue() != null) {
//            paymentDetailDto.setCustomerId(Long.valueOf(paymentDetailForm.getCustomer().getValueAsString()));
//        }
//        paymentDetailDto.setDescription(paymentDetailForm.getPaymentDescription().getEnteredValue());
//        paymentDetailDto.setCustomerReference(paymentDetailForm.getCustomerReference().getValueAsString());
//        paymentDetailDto.setPaymentNumber(paymentDetailForm.getPaymentNumber().getValueAsString());
//        paymentDetailDto.setAmount(paymentDetailForm.getAmount().getValueAsString() != null && paymentDetailForm.getAmount().getValueAsString().length() != 0 ? new BigDecimal(paymentDetailForm.getAmount().getValueAsString()) : null);
    }

    private void showWaitingForServer() {
        dlg.show();
    }

    private void hideWaitingForServer() {
        dlg.hide();
    }
}