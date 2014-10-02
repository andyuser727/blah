package com.ajs.client.layout.payment;

import com.ajs.client.form.payment.PaymentDetailForm;
import com.ajs.client.listgrid.invoice.InvoicesForCustomerListGrid;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.Button;
import com.smartgwt.client.widgets.Img;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.StaticTextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 30/12/2013
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
public class PaymentDetailLayout extends VLayout {

    VLayout msgLayout;
    HLayout loadingLayout;

    public PaymentDetailLayout(InvoicesForCustomerListGrid invoicesForCustomerListGrid,
                               PaymentDetailForm paymentDetailForm,
                               Button cancelButton,
                               Button savePaymentButton,
                               Button applyPaymentButton) {

        msgLayout = new VLayout();

        loadingLayout = new HLayout();
        Img loadingImg = new Img("/gwttestl/ezgif-save.gif", 900, 20);
        loadingLayout.addMember(loadingImg);


        HLayout buttonLayout = new HLayout();
        buttonLayout.addMember(cancelButton);
        buttonLayout.addMember(savePaymentButton);
        buttonLayout.addMember(applyPaymentButton);
        buttonLayout.setPadding(20);
        buttonLayout.setAlign(Alignment.LEFT);
        buttonLayout.setWidth(900);
        buttonLayout.setBackgroundColor("#E9F5F2");

        HLayout hlayout = new HLayout();
        hlayout.addMember(paymentDetailForm);
        hlayout.setAlign(Alignment.LEFT);

        HLayout invoicesForCustomerLayout = new HLayout();
        invoicesForCustomerLayout.addMember(invoicesForCustomerListGrid);
        invoicesForCustomerLayout.setPadding(20);
        invoicesForCustomerLayout.setAlign(Alignment.LEFT);
        invoicesForCustomerLayout.setWidth(900);
        invoicesForCustomerLayout.setBackgroundColor("#E9F5F2");

        addMember(loadingLayout);
        loadingLayout.hide();

        addMember(msgLayout);
        msgLayout.hide();

        addMember(hlayout);
        addMember(invoicesForCustomerLayout);
        addMember(buttonLayout);
    }

    public void showMessages(List<String> msgs) {

        for (String msg : msgs) {
            DynamicForm form = new DynamicForm();
            form.setBorder("1px solid white");
            form.setBackgroundColor("#B30531");

            StaticTextItem msgLabel = new StaticTextItem();
            msgLabel.setValue(msg);
            msgLabel.setWidth(900);
            msgLabel.setShowTitle(false);
            msgLabel.setTextBoxStyle("italic");
            form.setFields(msgLabel);
            msgLayout.addMember(form);
        }
        msgLayout.show();
    }

    public void hideMsgLayout() {
        msgLayout.hide();
    }

    public void hideLoadingLayout() {
        loadingLayout.hide();
    }

    public void showLoadingLayout() {
        loadingLayout.show();
    }
}
