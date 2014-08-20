package com.ajs.shared.commands.invoice;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 15/12/2013
 * Time: 14:28
 * To change this template use File | Settings | File Templates.
 */
public class ProcessInvoiceDeletes extends RpcDto {

    List<Long> idsToDelete;

    public ProcessInvoiceDeletes(){
    }

    public ProcessInvoiceDeletes(List<Long> idsToDelete){
        this.idsToDelete = idsToDelete;
    }

    public List<Long> getIdsToDelete() {
        return idsToDelete;
    }
}
