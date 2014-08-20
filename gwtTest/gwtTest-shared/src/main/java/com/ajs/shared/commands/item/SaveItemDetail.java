package com.ajs.shared.commands.item;

import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveItemDetail extends RpcDto {

    ItemDetailDto itemDetailDto;
    Long invoiceId;

    public SaveItemDetail(){}

    public SaveItemDetail(ItemDetailDto itemDetailDto, Long invoiceId){
        this.itemDetailDto = itemDetailDto;
        this.invoiceId = invoiceId;
    }

    public ItemDetailDto getItemDetailDto() {
        return itemDetailDto;
    }

    public Long getInvoiceId() {
        return invoiceId;
    }
}
