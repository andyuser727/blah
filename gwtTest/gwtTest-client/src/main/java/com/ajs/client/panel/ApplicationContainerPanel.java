package com.ajs.client.panel;

import com.google.gwt.core.client.GWT;
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
    protected DockLayoutPanel mainDockPanel;

    @UiField
    protected SimplePanel contentWrapperPanel;

    @UiField
    protected FlowPanel headerPanel;

    @UiField
    protected SimplePanel tabPanel;

    @UiField
    protected Image companyLogo;

    @UiField
    protected ScrollPanel mainNavPanel;

    @UiField
    protected Image invoiceLogo;

    @UiField
    protected Image quoteLogo;

    @UiField
    protected Image orderLogo;

    @UiField
    protected Image paymentLogo;

    @Inject
    public ApplicationContainerPanel(final TabMenuPanel tabMenuPanel) {

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

        mainDockPanel.setWidgetSize(mainNavPanel, 25);
        this.tabPanel.setWidget(tabMenuPanel);
        this.tabPanel.setSize("350px", "23px");
        contentWrapperPanel.setSize("1400px", "1000px");
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


}
