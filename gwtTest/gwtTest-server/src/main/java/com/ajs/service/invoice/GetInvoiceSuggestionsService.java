package com.ajs.service.invoice;

import com.ajs.shared.commands.invoice.GetInvoiceSuggestions;
import com.ajs.shared.commands.GoogleSuggestion;
import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 16/12/2013
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Component
@Transactional
public class GetInvoiceSuggestionsService implements Handler<GetInvoiceSuggestions> {

    @Autowired
    InvoiceDao invoiceDao;

    public AppResponse execute(GetInvoiceSuggestions dto) {

        AppResponse response = new AppResponse();

        List<Invoice> invoiceList = invoiceDao.findAllInvoicesByCode("%" + dto.getPartialQueryString() + "%");

        ArrayList<GoogleSuggestion> results = new ArrayList<GoogleSuggestion>();

        for (Invoice invoice : invoiceList) {
            results.add(new GoogleSuggestion(String.valueOf(invoice.getId()), invoice.getCustomerReference()));
        }

        response.setSuggestions(results);

        return response;
    }

    public Class getIncomingCommandClass() {
        return GetInvoiceSuggestions.class;
    }


}

