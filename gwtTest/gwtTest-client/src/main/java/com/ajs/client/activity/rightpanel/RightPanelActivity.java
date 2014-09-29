package com.ajs.client.activity.rightpanel;

import com.ajs.shared.AppServiceAsync;
import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * Created with IntelliJ IDEA. User: AndySmith Date: 05/12/2013 Time: 20:01 To change this template use File | Settings
 * | File Templates.
 */
public class RightPanelActivity extends AbstractActivity {

    public interface IRightPanelViewDisplay {
        Widget asWidget();
        void setRightPanelLabel(String text);
    }

    private final AppServiceAsync appServiceAsync;
    private final EventBus eventBus;
    private final IRightPanelViewDisplay display;


    @Inject
    public RightPanelActivity(EventBus eventBus, IRightPanelViewDisplay display,
                              AppServiceAsync appServiceAsync) {
        this.appServiceAsync = appServiceAsync;
        this.eventBus = eventBus;
        this.display = display;
    }

    @Override
    public void start(AcceptsOneWidget container, EventBus eventBus) {
        container.setWidget(display.asWidget());
        display.setRightPanelLabel("Right Panel");
    }


}
