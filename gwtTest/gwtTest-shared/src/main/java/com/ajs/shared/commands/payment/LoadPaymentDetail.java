package com.ajs.shared.commands.payment;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadPaymentDetail extends RpcDto{

    Long paymentId;

    public LoadPaymentDetail(){
    }

    public LoadPaymentDetail(Long id){
        this.paymentId = id;
    }

    public Long getPaymentId() {
        return paymentId;
    }
}
