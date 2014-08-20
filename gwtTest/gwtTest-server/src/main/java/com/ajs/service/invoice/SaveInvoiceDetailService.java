package com.ajs.service.invoice;

import com.ajs.dao.CustomerDao;
import com.ajs.dao.InvoiceDao;
import com.ajs.dao.InvoiceItemRlshipDao;
import com.ajs.dao.ItemDao;
import com.ajs.domain.Customer;
import com.ajs.domain.Invoice;
import com.ajs.domain.InvoiceItemRlship;
import com.ajs.domain.Item;
import com.ajs.service.SimpleHandler;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.commands.invoice.SaveInvoiceDetail;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveInvoiceDetailService implements SimpleHandler<SaveInvoiceDetail> {

    @Autowired
    InvoiceDao invoiceDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    InvoiceItemRlshipDao invoiceItemRlshipDao;

    public SimpleResponse executeSimple(SaveInvoiceDetail dto) {

        SimpleResponse response = new SimpleResponse();

        InvoiceDetailDto invoiceDetailDto = dto.getInvoiceDetailDto();

        Invoice invoice = null;
        if (dto.getInvoiceDetailDto().getId() != null) {
            invoice = invoiceDao.find(dto.getInvoiceDetailDto().getId());
        } else {
            invoice = new Invoice();
            invoiceDao.save(invoice);
        }

        Map<Long, ItemDetailDto> newItemDtos = dto.getNewItemDtos();

        for (Long idToRemove : dto.getItemIdsToRemove()) {
            if (newItemDtos != null) {
                newItemDtos.remove(idToRemove);
            }
            InvoiceItemRlship invoiceItemRlship = invoiceItemRlshipDao.findRelationship(invoice.getId(), idToRemove);
            if (invoiceItemRlship != null) {
                invoiceItemRlshipDao.delete(invoiceItemRlship);
            }
        }

        if (newItemDtos != null) {

            for (ItemDetailDto itemDetailDto : newItemDtos.values()) {
                Item item;
                if (itemDetailDto.getId() == null) {

                    item = new Item();

                    item.setCode(itemDetailDto.getCode());
                    item.setName(itemDetailDto.getName());
                    item.setDescription(itemDetailDto.getDescription());
                    item.setAmount(itemDetailDto.getAmount());

                    itemDao.save(item);

                } else {
                    item = itemDao.find(itemDetailDto.getId());
                }

                InvoiceItemRlship invoiceItemRlship = new InvoiceItemRlship();
                invoiceItemRlshipDao.save(invoiceItemRlship);
                invoiceItemRlship.setInvoice(invoice);
                invoiceItemRlship.setItem(item);
            }
        }

        invoice.setCustomerReference(invoiceDetailDto.getCustomerReference());
        invoice.setDescription(invoiceDetailDto.getDescription());
        invoice.setInvoiceDate(invoiceDetailDto.getInvoiceDate());
        invoice.setInvoiceNumber(invoiceDetailDto.getInvoiceNumber());
        invoice.setAmount(invoiceDetailDto.getAmount());

        if (invoiceDetailDto.getCustomerId() != null) {
            Customer customer = customerDao.find(invoiceDetailDto.getCustomerId());
            invoice.setCustomer(customer);
            customer.getInvoices().add(invoice);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return SaveInvoiceDetail.class;
    }


}

