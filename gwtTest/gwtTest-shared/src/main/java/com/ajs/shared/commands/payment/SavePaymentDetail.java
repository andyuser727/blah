package com.ajs.shared.commands.payment;

import com.ajs.shared.dto.payment.PaymentDetailDto;
import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SavePaymentDetail extends RpcDto {

    PaymentDetailDto paymentDetailDto;
    boolean applyPayment;
    Long selectedInvoice;

    public SavePaymentDetail(){}

    public SavePaymentDetail(PaymentDetailDto paymentDetailDto, boolean applyPayment, Long selectedInvoice){
        this.paymentDetailDto = paymentDetailDto;
        this.applyPayment = applyPayment;
        this.selectedInvoice = selectedInvoice;
    }

    public PaymentDetailDto getPaymentDetailDto() {
        return paymentDetailDto;
    }

    public boolean isApplyPayment() {
        return applyPayment;
    }

    public Long getSelectedInvoice() {
        return selectedInvoice;
    }
}
