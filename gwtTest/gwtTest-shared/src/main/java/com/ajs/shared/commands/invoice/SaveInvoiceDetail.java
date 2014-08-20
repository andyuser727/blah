package com.ajs.shared.commands.invoice;

import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.dto.RpcDto;
import com.ajs.shared.dto.item.ItemDetailDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveInvoiceDetail extends RpcDto {

    InvoiceDetailDto invoiceDetailDto;
    Map<Long, ItemDetailDto> newItemDtos;
    List<Long> itemIdsToRemove;

    public SaveInvoiceDetail(){}

    public SaveInvoiceDetail(InvoiceDetailDto invoiceDetailDto){
        this.invoiceDetailDto = invoiceDetailDto;
    }

    public SaveInvoiceDetail(InvoiceDetailDto invoiceDetailDto, Map<Long, ItemDetailDto> newItemDtos, List<Long> itemIdsToRemove){
        this.invoiceDetailDto = invoiceDetailDto;
        this.newItemDtos = newItemDtos;
        this.itemIdsToRemove = itemIdsToRemove;
    }

    public InvoiceDetailDto getInvoiceDetailDto() {
        return invoiceDetailDto;
    }

    public Map<Long, ItemDetailDto> getNewItemDtos() {
        return newItemDtos;
    }

    public List<Long> getItemIdsToRemove() {
        return itemIdsToRemove;
    }
}
