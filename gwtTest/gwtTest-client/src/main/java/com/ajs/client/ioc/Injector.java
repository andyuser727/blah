package com.ajs.client.ioc;

import com.ajs.client.mvp.AppActivityMapper;
import com.ajs.client.mvp.AppPlaceFactory;
import com.ajs.client.mvp.NavActivityMapper;
import com.ajs.client.mvp.RightPanelActivityMapper;
import com.ajs.client.panel.ApplicationContainerPanel;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

@GinModules({InjectionModule.class})
public interface Injector extends Ginjector {

    AppPlaceFactory getAppPlaceFactory();

    EventBus getEventBus();

    AppActivityMapper getActivityMapper();

    PlaceController getPlaceController();

    ApplicationContainerPanel getApplicationContainerPanel();

    NavActivityMapper getNavActivityMapper();

    RightPanelActivityMapper getRightPanelActivityMapper();
}