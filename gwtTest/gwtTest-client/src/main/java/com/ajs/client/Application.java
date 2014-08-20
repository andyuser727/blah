package com.ajs.client;

import com.ajs.client.ioc.Injector;
import com.ajs.client.mvp.AppPlaceFactory;
import com.ajs.client.mvp.AppPlaceHistoryMapper;
import com.ajs.client.panel.ApplicationContainerPanel;
import com.ajs.client.place.CategoryListPlace;
import com.ajs.client.place.InvoiceListPlace;
import com.ajs.shared.AppService;
import com.ajs.shared.AppServiceAsync;
import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.RootLayoutPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Application implements EntryPoint
{
    private final AppServiceAsync greetingService = GWT
            .create(AppService.class);

    private Injector injector = GWT.create(Injector.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad()
	{
        EventBus eventBus = injector.getEventBus();
        PlaceController placeController = injector.getPlaceController();

        final ApplicationContainerPanel applicationContainerPanel = injector.getApplicationContainerPanel();

        ActivityMapper activityMapper = injector.getActivityMapper();
        ActivityManager activityManager = new ActivityManager(activityMapper, eventBus);
        activityManager.setDisplay(applicationContainerPanel.getContentWrapperPanel());

        // Main Navigation container Activity Mapper with our implementation
        ActivityMapper navActivityMapper = injector.getNavActivityMapper();
        // Main Navigation container ActivityManager keeps track of all
        // Activities
        ActivityManager navActivityManager = new ActivityManager(navActivityMapper, eventBus);
        navActivityManager.setDisplay(applicationContainerPanel.getMainNavPanel());

        AppPlaceFactory factory = injector.getAppPlaceFactory();
        InvoiceListPlace defaultPlace = factory.getInvoiceListPlace();

        final AppPlaceHistoryMapper historyMapper = GWT.create(AppPlaceHistoryMapper.class);
        historyMapper.setFactory(factory);

        PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
        historyHandler.register(placeController, eventBus, defaultPlace);

        History.fireCurrentHistoryState();

        RootLayoutPanel.get().add(applicationContainerPanel);
	}
}
