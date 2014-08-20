package com.ajs.shared.dto.invoice;

import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.RpcDto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:07
 * To change this template use File | Settings | File Templates.
 */
public class InvoiceDetailDto extends RpcDto {

    private String customerReference;
    private String description;
    private String invoiceNumber;
    private Map<Long, ItemDetailDto> itemDtoList;
    private Long customerId;
    private BigDecimal amount;
    private String customerName;
    private Date invoiceDate;

    private boolean selectedLine;

    public String getCustomerReference() {
        return customerReference;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<Long, ItemDetailDto> getItemDtoList() {
        return itemDtoList;
    }

    public void setItemDtoList(Map<Long, ItemDetailDto> itemDtoList) {
        this.itemDtoList = itemDtoList;
    }

    public boolean getSelectedLine() {
        return selectedLine;
    }

    public void setSelectedLine(boolean selectedLine) {
        this.selectedLine = selectedLine;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }
}
