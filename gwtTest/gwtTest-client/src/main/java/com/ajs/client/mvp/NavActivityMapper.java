package com.ajs.client.mvp;

import com.ajs.client.activity.nav.NavActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class NavActivityMapper implements ActivityMapper {
    private Provider<NavActivity> navActivityProvider;

    @Inject
    public NavActivityMapper(Provider<NavActivity> navActivityProvider) {
        this.navActivityProvider = navActivityProvider;
    }

    @Override
    public Activity getActivity(Place place) {
        return navActivityProvider.get();
    }
}
