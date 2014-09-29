package com.ajs.client.ioc;

import com.ajs.client.activity.nav.NavActivity;
import com.ajs.client.activity.rightpanel.RightPanelActivity;
import com.ajs.client.datasource.customerorder.CustomerOrderDataSource;
import com.ajs.client.datasource.invoice.InvoiceDataSource;
import com.ajs.client.datasource.payment.PaymentDataSource;
import com.ajs.client.datasource.quote.QuoteDataSource;
import com.ajs.client.mvp.AppActivityMapper;
import com.ajs.client.mvp.AppPlaceFactory;
import com.ajs.client.mvp.InjectablePlaceController;
import com.ajs.client.providers.TestProvider;
import com.ajs.client.ui.nav.NavViewImpl;
import com.ajs.client.ui.rightpanel.RightPanelViewImpl;
import com.ajs.shared.Test;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;

public class InjectionModule extends AbstractGinModule {

    @Override
    protected void configure() {
        bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
        bind(PlaceController.class).to(InjectablePlaceController.class).in(Singleton.class);
        bind(AppPlaceFactory.class).in(Singleton.class);

        bind(InvoiceDataSource.class).in(Singleton.class);
        bind(PaymentDataSource.class).in(Singleton.class);
        bind(CustomerOrderDataSource.class).in(Singleton.class);
        bind(QuoteDataSource.class).in(Singleton.class);


        bind(Test.class).toProvider(TestProvider.class).in(Singleton.class);

        // bind the mapper
        bind(ActivityMapper.class).to(AppActivityMapper.class).in(Singleton.class);
        // TODO change
        bind(NavActivity.INavViewDisplay.class).to(NavViewImpl.class);
        bind(RightPanelActivity.IRightPanelViewDisplay.class).to(RightPanelViewImpl.class);
    }


}