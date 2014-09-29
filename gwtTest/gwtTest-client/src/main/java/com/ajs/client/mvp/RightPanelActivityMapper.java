package com.ajs.client.mvp;

import com.ajs.client.activity.rightpanel.RightPanelActivity;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class RightPanelActivityMapper implements ActivityMapper {
    private Provider<RightPanelActivity> navActivityProvider;

    @Inject
    public RightPanelActivityMapper(Provider<RightPanelActivity> navActivityProvider) {
        this.navActivityProvider = navActivityProvider;
    }

    @Override
    public Activity getActivity(Place place) {
        return navActivityProvider.get();
    }
}
