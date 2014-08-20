package com.ajs.shared.dto.invoice;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
    public class InvoiceListDto extends RpcDto {

    List<InvoiceDetailDto> invoiceDetailDtoList;

    public List<InvoiceDetailDto> getInvoiceDetailDtoList() {
        return invoiceDetailDtoList;
    }

    public void setInvoiceDetailDtoList(List<InvoiceDetailDto> invoiceDetailDtoList) {
        this.invoiceDetailDtoList = invoiceDetailDtoList;
    }
}
