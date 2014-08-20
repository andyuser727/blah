package com.ajs.client.mvp;

import com.ajs.client.activity.BaseAbstractActivity;
import com.ajs.client.place.ActivityPlace;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;

public class AppActivityMapper implements ActivityMapper {

    @Override
    public Activity getActivity(Place place) {
        if (place instanceof ActivityPlace) {
            BaseAbstractActivity activity = (BaseAbstractActivity)((ActivityPlace) place).getActivity();
            activity.init((ActivityPlace)place);
            return activity;
        }
        return null;
    }

}
