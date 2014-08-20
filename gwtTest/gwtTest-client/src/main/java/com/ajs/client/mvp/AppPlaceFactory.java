package com.ajs.client.mvp;

import com.ajs.client.activity.nav.NavActivity;
import com.ajs.client.place.*;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 *
 * A place factory which knows about all the tokenizers in the app
 *
 * @author moe
 *
 */
public class AppPlaceFactory {

    // A single instance of the tokenizer should work, since they don't have
    // state.
    @Inject
    CategoryListPlace.Tokenizer categoryListPlaceTokenizer;
    @Inject
    CategoryDetailPlace.Tokenizer categoryDetailPlaceTokenizer;
    @Inject
    InvoiceListPlace.Tokenizer invoiceListPlaceTokenizer;
    @Inject
    QuoteListPlace.Tokenizer quoteListPlaceTokenizer;
    @Inject
    CustomerOrderListPlace.Tokenizer customerOrderListPlaceTokenizer;
    @Inject
    PaymentListPlace.Tokenizer paymentListPlaceTokenizer;


    @Inject
    Provider<CategoryListPlace> categoryListPlaceProvider;
    @Inject
    Provider<CategoryDetailPlace> categoryDetailPlaceProvider;
    @Inject
    Provider<InvoiceListPlace> invoiceListPlaceProvider;
    @Inject
    Provider<QuoteListPlace> quoteListPlaceProvider;
    @Inject
    Provider<CustomerOrderListPlace> customerOrderListPlaceProvider;
    @Inject
    Provider<PaymentListPlace> paymentListPlaceProvider;

    // FOR NAV
    @Inject
    Provider<NavActivity> navActivityProvider;

    public CategoryListPlace.Tokenizer getCategoryListPlaceTokenizer() {
        return categoryListPlaceTokenizer;
    }

    public CategoryListPlace getCategoryListPlace() {
        return categoryListPlaceProvider.get();
    }

    public CategoryDetailPlace.Tokenizer getCategoryDetailPlaceTokenizer() {
        return categoryDetailPlaceTokenizer;
    }

    public CategoryDetailPlace getCategoryDetailPlace() {
        return categoryDetailPlaceProvider.get();
    }

    public InvoiceListPlace.Tokenizer getInvoiceListPlaceTokenizer() {
        return invoiceListPlaceTokenizer;
    }

    public InvoiceListPlace getInvoiceListPlace() {
        return invoiceListPlaceProvider.get();
    }

    public QuoteListPlace.Tokenizer getQuoteListPlaceTokenizer() {
        return quoteListPlaceTokenizer;
    }

    public QuoteListPlace getQuoteListPlace() {
        return quoteListPlaceProvider.get();
    }

    public CustomerOrderListPlace.Tokenizer getCustomerOrderListPlaceTokenizer() {
        return customerOrderListPlaceTokenizer;
    }

    public CustomerOrderListPlace getCustomerOrderListPlace() {
        return customerOrderListPlaceProvider.get();
    }

    public PaymentListPlace.Tokenizer getPaymentListPlaceTokenizer() {
        return paymentListPlaceTokenizer;
    }

    public PaymentListPlace getPaymentListPlace() {
        return paymentListPlaceProvider.get();
    }
}
