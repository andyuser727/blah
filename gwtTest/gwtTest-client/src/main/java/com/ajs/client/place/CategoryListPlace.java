package com.ajs.client.place;

import com.ajs.client.activity.category.CategoryListActivity;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CategoryListPlace extends ActivityPlace<CategoryListActivity> {

    @Inject
    public CategoryListPlace(CategoryListActivity activity) {
        super(activity);
    }

    @Prefix("category_list")
    public static class Tokenizer extends AppTokenizer<CategoryListPlace> implements PlaceTokenizer<CategoryListPlace> {

        // Since the place is injectable, we'll let Gin do the construction.
        private final Provider<CategoryListPlace> placeProvider;

        @Inject
        public Tokenizer(Provider<CategoryListPlace> placeProvider) {
            this.placeProvider = placeProvider;
        }

        @Override
        public CategoryListPlace getPlace(String token) {
            CategoryListPlace place = placeProvider.get();
            place.setProperties(parseProperties(token));
            return place;
        }

    }
}