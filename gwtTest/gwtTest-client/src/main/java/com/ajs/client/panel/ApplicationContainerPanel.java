package com.ajs.client.panel;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.*;
import com.google.inject.Inject;
import com.google.inject.Singleton;

@Singleton
public class ApplicationContainerPanel extends Composite {
    interface Binder extends UiBinder<Widget, ApplicationContainerPanel> {
    }

    private static final Binder binder = GWT.create(Binder.class);

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected SimplePanel tabPanel;

    @UiField
    protected SimplePanel rightPanel;

    @UiField
    protected Image companyLogo;

    @UiField
    protected SimplePanel mainNavPanel;

    @UiField
    protected Image invoiceLogo;

    @UiField
    protected Image quoteLogo;

    @UiField
    protected Image orderLogo;

    @UiField
    protected Image paymentLogo;

    private EventBus eventBus;
    @Inject
    public ApplicationContainerPanel(final TabMenuPanel tabMenuPanel, final EventBus eventBus) {

        this.eventBus = eventBus;

        initWidget(binder.createAndBindUi(this));

        companyLogo.setUrl("/gwttestl/header-photo.jpg");
        invoiceLogo.setUrl("/gwttestl/invoice-icon.jpeg");
        invoiceLogo.setHeight("50px");
        invoiceLogo.setWidth("50px");
        orderLogo.setUrl("/gwttestl/order-icon.jpeg");
        orderLogo.setHeight("55px");
        orderLogo.setWidth("55px");
        quoteLogo.setUrl("/gwttestl/quote-icon.jpeg");
        quoteLogo.setHeight("55px");
        quoteLogo.setWidth("55px");
        paymentLogo.setUrl("/gwttestl/payment-icon.jpeg");
        paymentLogo.setHeight("55px");
        paymentLogo.setWidth("55px");

        tabPanel.setWidget(tabMenuPanel);
        tabPanel.setSize("826px", "23px");
        mainNavPanel.setHeight("497px");
        contentWrapperPanel.setStyleName("shadow", true);
        contentWrapperPanel.setStyleName("border", true);
        contentWrapperPanel.setWidth("826px");
        contentWrapperPanel.setHeight("438px");
}

    public AcceptsOneWidget getContentWrapperPanel() {
        return new AcceptsOneWidget() {
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                contentWrapperPanel.setWidget(widget);
            }
        };
    }

    public AcceptsOneWidget getMainNavPanel() {
        return new AcceptsOneWidget() {
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                mainNavPanel.setWidget(widget);
            }
        };
    }

    public AcceptsOneWidget getRightPanel() {
        return new AcceptsOneWidget() {
            public void setWidget(IsWidget w) {
                Widget widget = Widget.asWidgetOrNull(w);
                rightPanel.setWidget(widget);
            }
        };
    }


}
