package com.ajs.client.place;

import com.ajs.client.activity.category.CategoryDetailActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CategoryDetailPlace extends ActivityPlace<CategoryDetailActivity> {

    @Inject
    public CategoryDetailPlace(CategoryDetailActivity activity) {
        super(activity);

    }

    @Prefix("category_detail")
    public static class Tokenizer extends AppTokenizer<CategoryDetailPlace> implements PlaceTokenizer<CategoryDetailPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<CategoryDetailPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<CategoryDetailPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public CategoryDetailPlace getPlace(String token) {
            CategoryDetailPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}