package com.ajs.client.place;

import com.ajs.client.activity.quote.QuoteListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class QuoteListPlace extends ActivityPlace<QuoteListActivity> {

    @Inject
    public QuoteListPlace(QuoteListActivity activity) {
        super(activity);
    }

    @Prefix("quote_list")
    public static class Tokenizer extends AppTokenizer<QuoteListPlace> implements PlaceTokenizer<QuoteListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<QuoteListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<QuoteListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public QuoteListPlace getPlace(String token) {
            QuoteListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}