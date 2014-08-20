package com.ajs.shared.commands.invoice;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 22/12/2013
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class LoadInvoiceList extends RpcDto {

    Long customerId;

    public LoadInvoiceList(){}

    public LoadInvoiceList(Long customerId){
        this.customerId = customerId;
    }

    public Long getCustomerId() {
        return customerId;
    }
}