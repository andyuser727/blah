package com.ajs.client.place;

import com.ajs.client.activity.invoice.InvoiceListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class InvoiceListPlace extends ActivityPlace<InvoiceListActivity> {

    @Inject
    public InvoiceListPlace(InvoiceListActivity activity) {
        super(activity);
    }

    @Prefix("invoice_list")
    public static class Tokenizer extends AppTokenizer<InvoiceListPlace> implements PlaceTokenizer<InvoiceListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<InvoiceListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<InvoiceListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public InvoiceListPlace getPlace(String token) {
            InvoiceListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}