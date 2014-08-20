package com.ajs.client.suggest;

import com.ajs.shared.SimpleResponse;
import com.ajs.shared.commands.GetCustomerSuggestions;
import com.ajs.shared.commands.GoogleSuggestion;
import com.ajs.shared.AppResponse;
import com.ajs.shared.AppServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.SuggestBox;
import com.google.gwt.user.client.ui.SuggestOracle;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 22/12/2013
 * Time: 23:37
 * To change this template use File | Settings | File Templates.
 */
public class CustomerSuggest extends Composite {

    private static CustomerSuggestUiBinder uiBinder = GWT.create(CustomerSuggestUiBinder.class);

    interface CustomerSuggestUiBinder extends UiBinder<Widget, CustomerSuggest> {
    }

    AppServiceAsync appServiceAsync;

    @UiField(provided = true)
    SuggestBox suggestBox;

    private String id;

    public CustomerSuggest(){}

    @Inject
    public CustomerSuggest(final AppServiceAsync appServiceAsync){
        this.appServiceAsync = appServiceAsync;
        this.suggestBox = new SuggestBox(new SuggestOracle() {

            @Override
            public void requestSuggestions(final Request request, final Callback callback) {
                appServiceAsync.callServerSimple(new GetCustomerSuggestions(request.getQuery()),
                        new AsyncCallback<SimpleResponse>() {
                            @Override
                            public void onFailure(Throwable caught) {
                                callback.onSuggestionsReady(request, new Response());
                            }

                            @Override
                            public void onSuccess(SimpleResponse result) {
                                callback.onSuggestionsReady(request, new Response(result.getSuggestions()));
                            }
                        });
            }
        });

        suggestBox.addSelectionHandler(new SelectionHandler<SuggestOracle.Suggestion>() {

            @Override
            public void onSelection(com.google.gwt.event.logical.shared.SelectionEvent<SuggestOracle.Suggestion> event) {
                id = ((GoogleSuggestion)event.getSelectedItem()).getItemCode();
                suggestBox.getTextBox().setText(((GoogleSuggestion)event.getSelectedItem()).getDisplayString());

            }
        });

        initWidget(uiBinder.createAndBindUi(this));
    }

    public SuggestBox getSuggestBox() {
        return suggestBox;
    }

    public String getId() {
        return id;
    }
}

