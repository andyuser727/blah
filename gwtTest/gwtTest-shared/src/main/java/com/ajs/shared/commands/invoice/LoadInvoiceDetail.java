package com.ajs.shared.commands.invoice;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadInvoiceDetail extends RpcDto{

    Long invoiceId;

    public LoadInvoiceDetail(){
    }

    public LoadInvoiceDetail(Long id){
        this.invoiceId = id;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
}
