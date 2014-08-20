package com.ajs.service.invoice;

import com.ajs.shared.commands.invoice.ProcessInvoiceDeletes;
import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class ProcessInvoiceDeletesService implements Handler<ProcessInvoiceDeletes> {

    @Autowired
    InvoiceDao invoiceDao;

    public AppResponse execute(ProcessInvoiceDeletes dto) {

        AppResponse response = new AppResponse();

        for (Long invoiceId : dto.getIdsToDelete()){
            // good? bad? have to load first???
            Invoice invoice = invoiceDao.find(invoiceId);
            invoiceDao.delete(invoice);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return ProcessInvoiceDeletes.class;
    }


}

