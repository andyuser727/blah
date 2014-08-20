package com.ajs.client.place;

import com.ajs.client.activity.customerorder.CustomerOrderListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CustomerOrderListPlace extends ActivityPlace<CustomerOrderListActivity> {

    @Inject
    public CustomerOrderListPlace(CustomerOrderListActivity activity) {
        super(activity);
    }

    @Prefix("customerOrder_list")
    public static class Tokenizer extends AppTokenizer<CustomerOrderListPlace> implements PlaceTokenizer<CustomerOrderListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<CustomerOrderListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<CustomerOrderListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public CustomerOrderListPlace getPlace(String token) {
            CustomerOrderListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}