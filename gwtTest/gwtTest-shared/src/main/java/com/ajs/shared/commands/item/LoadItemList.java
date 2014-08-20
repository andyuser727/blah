package com.ajs.shared.commands.item;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 22/12/2013
 * Time: 16:52
 * To change this template use File | Settings | File Templates.
 */
public class LoadItemList extends RpcDto {

    private Long invoiceId;
    private List<Long> idsToExclude;

    public LoadItemList() {
    }

    public LoadItemList(List<Long> idsToExclude) {
        this.idsToExclude = idsToExclude;
    }

    public LoadItemList(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }

    public List<Long> getIdsToExclude() {
        return idsToExclude;
    }
}