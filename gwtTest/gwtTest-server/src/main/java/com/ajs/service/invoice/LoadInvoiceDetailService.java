package com.ajs.service.invoice;

import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;
import com.ajs.domain.InvoiceItemRlship;
import com.ajs.domain.Item;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.commands.invoice.LoadInvoiceDetail;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadInvoiceDetailService implements Handler<LoadInvoiceDetail> {

    @Autowired
    InvoiceDao invoiceDao;

    public AppResponse execute(LoadInvoiceDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        Invoice invoice = invoiceDao.find(dto.getInvoiceId());
        InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
        invoiceDetailDto.setCustomerReference(invoice.getCustomerReference());
        invoiceDetailDto.setDescription(invoice.getDescription());
        invoiceDetailDto.setInvoiceNumber(invoice.getInvoiceNumber());
        invoiceDetailDto.setInvoiceDate(invoice.getInvoiceDate());
        invoiceDetailDto.setAmount(invoice.getAmount());
        if (invoice.getCustomer() != null) {
            invoiceDetailDto.setCustomerId(invoice.getCustomer().getId());
        }
        invoiceDetailDto.setId(invoice.getId());

        List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<InvoiceDetailDto>();
        invoiceDetailDtos.add(invoiceDetailDto);

        Map<Long, ItemDetailDto> itemDtos = new HashMap<Long, ItemDetailDto>();
        if (invoice.getInvoiceItemRlships() != null && invoice.getInvoiceItemRlships().size() > 0)

            // TODO SHIT CHANGE IT LAZY
            for (InvoiceItemRlship invoiceItemRlship : invoice.getInvoiceItemRlships()) {
                ItemDetailDto itemDto = new ItemDetailDto();
                Item item = invoiceItemRlship.getItem();
                itemDto.setCode(item.getCode());
                itemDto.setDescription(item.getDescription());
                itemDto.setName(item.getName());
                itemDto.setAmount(item.getAmount());
                itemDto.setId(item.getId());
                itemDtos.put(item.getId(), itemDto);
            }

        invoiceDetailDto.setItemDtoList(itemDtos);
        response.setDtos(invoiceDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadInvoiceDetail.class;
    }


}

