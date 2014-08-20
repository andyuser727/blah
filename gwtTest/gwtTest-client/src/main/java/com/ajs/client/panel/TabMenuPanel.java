package com.ajs.client.panel;

import com.ajs.client.place.*;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.smartgwt.client.types.Side;
import com.smartgwt.client.widgets.tab.Tab;
import com.smartgwt.client.widgets.tab.TabSet;
import com.smartgwt.client.widgets.tab.events.TabSelectedEvent;
import com.smartgwt.client.widgets.tab.events.TabSelectedHandler;

public class TabMenuPanel extends Composite {

    private final Provider<CategoryListPlace> categoryListPlaceProvider;
    private final Provider<InvoiceListPlace> invoiceListPlaceProvider;
    private final Provider<QuoteListPlace> quoteListPlaceProvider;
    private final Provider<CustomerOrderListPlace> customerOrderListPlaceProvider;
    private final Provider<PaymentListPlace> paymentListPlaceProvider;
    private final PlaceController placeController;

    @Inject
    public TabMenuPanel(final Provider<InvoiceListPlace> invoiceListPlaceProvider,
                        final Provider<CategoryListPlace> categoryListPlaceProvider,
                        final Provider<QuoteListPlace> quoteListPlaceProvider,
                        final Provider<CustomerOrderListPlace> customerOrderListPlaceProvider,
                        final Provider<PaymentListPlace> paymentListPlaceProvider,
                        final PlaceController placeController) {
        this.categoryListPlaceProvider = categoryListPlaceProvider;
        this.invoiceListPlaceProvider = invoiceListPlaceProvider;
        this.quoteListPlaceProvider = quoteListPlaceProvider;
        this.customerOrderListPlaceProvider = customerOrderListPlaceProvider;
        this.paymentListPlaceProvider = paymentListPlaceProvider;
        this.placeController = placeController;

//        MenuBar menu = new MenuBar();
//        menu.addItem("Invoices", new GoToInvoiceListPlace());
//        menu.addItem("Categories", new GoToCategoryListPlace());


        //

        final TabSet topTabSet = new TabSet();
        topTabSet.setTabBarPosition(Side.TOP);
        topTabSet.setWidth("100%");
        topTabSet.setHeight(23);

        Tab tTab1 = new Tab("Invoices");
        tTab1.setWidth(200);

        Tab tTab2 = new Tab("Payments");
//        Img tImg2 = new Img("/gwttestl/deleteIcon16.jpg", 48, 48);
//        tTab2.setPane(tImg2);
        tTab2.setWidth(200);

        Tab tTab3 = new Tab("Customer Orders");
        tTab3.setWidth(200);

        Tab tTab4 = new Tab("Quotes");
//        Img tImg2 = new Img("/gwttestl/deleteIcon16.jpg", 48, 48);
//        tTab2.setPane(tImg2);
        tTab4.setWidth(200);

        topTabSet.addTab(tTab1);
        topTabSet.addTab(tTab2);
        topTabSet.addTab(tTab3);
        topTabSet.addTab(tTab4);

        tTab1.addTabSelectedHandler(new TabSelectedHandler() {
            @Override
            public void onTabSelected(TabSelectedEvent tabSelectedEvent) {
                InvoiceListPlace invoiceListPlace = invoiceListPlaceProvider.get();
                // TODO use constructor with map
                placeController.goTo(invoiceListPlace);
            }
        });

        tTab2.addTabSelectedHandler(new TabSelectedHandler() {
            @Override
            public void onTabSelected(TabSelectedEvent tabSelectedEvent) {
                PaymentListPlace paymentListPlace = paymentListPlaceProvider.get();
                // TODO use constructor with map
                placeController.goTo(paymentListPlace);
            }
        });


        tTab3.addTabSelectedHandler(new TabSelectedHandler() {
            @Override
            public void onTabSelected(TabSelectedEvent tabSelectedEvent) {
                CustomerOrderListPlace customerOrderListPlace = customerOrderListPlaceProvider.get();
                // TODO use constructor with map
                placeController.goTo(customerOrderListPlace);
            }
        });


        tTab4.addTabSelectedHandler(new TabSelectedHandler() {
            @Override
            public void onTabSelected(TabSelectedEvent tabSelectedEvent) {
                QuoteListPlace quoteListPlace = quoteListPlaceProvider.get();
                // TODO use constructor with map
                placeController.goTo(quoteListPlace);
            }
        });

        //
        initWidget(topTabSet);
    }

    private class ChangePlace implements Command {
        @Override
        public void execute() {
        }
    }

//    private class GoToCategoryListPlace implements Command {
//        @Override
//        public void execute() {
//            CategoryListPlace categoryListPlace = categoryListPlaceProvider.get();
//            // TODO use constructor with map
//            placeController.goTo(categoryListPlace);
//        }
//    }
//
//    private class GoToInvoiceListPlace implements Command {
//        @Override
//        public void execute() {
//            InvoiceListPlace invoiceListPlace = invoiceListPlaceProvider.get();
//            // TODO use constructor with map
//            placeController.goTo(invoiceListPlace);
//        }
//    }
//
//    private class GoToQuoteListPlace implements Command {
//        @Override
//        public void execute() {
//            QuoteListPlace quoteListPlace = quoteListPlaceProvider.get();
//            // TODO use constructor with map
//            placeController.goTo(quoteListPlace);
//        }
//    }
}
