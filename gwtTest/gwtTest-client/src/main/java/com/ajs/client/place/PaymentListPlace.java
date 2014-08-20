package com.ajs.client.place;

import com.ajs.client.activity.payment.PaymentListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class PaymentListPlace extends ActivityPlace<PaymentListActivity> {

    @Inject
    public PaymentListPlace(PaymentListActivity activity) {
        super(activity);
    }

    @Prefix("payment_list")
    public static class Tokenizer extends AppTokenizer<PaymentListPlace> implements PlaceTokenizer<PaymentListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<PaymentListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<PaymentListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public PaymentListPlace getPlace(String token) {
            PaymentListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}