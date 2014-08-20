package com.ajs.client.providers;

import com.ajs.shared.Test;
import com.ajs.shared.TestImpl;
import com.ajs.shared.commands.invoice.LoadInvoiceDetail;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * Created with IntelliJ IDEA.
 * User: smithaj
 * Date: 26/02/2014
 * Time: 12:54
 * To change this template use File | Settings | File Templates.
 */
public class TestProvider implements Provider<Test> {


    private TestImpl test = new TestImpl();

    @Inject
    public TestProvider(AppServiceAsync appServiceAsync) {

        test.setMessage("waa");


        LoadInvoiceDetail loadInvoiceDetail = new LoadInvoiceDetail(1L);
//
//        appServiceAsync.callServer(loadInvoiceDetail,
//                new AsyncCallback<AppResponse<InvoiceDetailDto>>() {
//                    public void onFailure(Throwable caught) {
//                        // Show the RPC error message to the user
//                        System.out.println(caught.getMessage());
//                    }
//
//                    public void onSuccess(AppResponse<InvoiceDetailDto> result) {
//
//                        System.out.println("SERVICE CALLED");
//                    }
//                });

    }


    @Override
    public Test get() {
        return test;
    }
}