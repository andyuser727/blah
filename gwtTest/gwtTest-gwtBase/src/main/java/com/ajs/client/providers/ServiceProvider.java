package com.ajs.client.providers;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 11/07/2013
 * Time: 20:29
 * To change this template use File | Settings | File Templates.
 */

import com.ajs.shared.AppService;
import com.google.gwt.core.client.GWT;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.inject.Inject;


public class ServiceProvider
        implements com.google.inject.Provider<AppService>
{

    private String serviceLocation;
    @Inject
    private PlaceController placeController;

    public ServiceProvider(String serviceLocation) {
    }

    public ServiceProvider() {
    }

    @Override
    public AppService get() {
        AppService service = GWT.create(AppService.class);

        ((ServiceDefTarget) service).setServiceEntryPoint(serviceLocation);
//        return null;
        return service;
    }
}
