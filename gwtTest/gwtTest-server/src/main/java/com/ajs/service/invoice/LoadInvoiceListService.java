package com.ajs.service.invoice;

import com.ajs.dao.InvoiceDao;
import com.ajs.dao.Invoices;
import com.ajs.domain.Invoice;
import com.ajs.shared.dto.invoice.InvoiceDetailDto;
import com.ajs.shared.commands.invoice.LoadInvoiceList;
import com.ajs.service.Handler;
import com.ajs.service.invoice.ws.InvoiceWSServiceLocator;
import com.ajs.service.invoice.ws.InvoiceWS_PortType;
import com.ajs.service.invoice.ws.camelws.IncidentServiceLocator;
import com.ajs.service.invoice.ws.camelws.IncidentServicePortType;
import com.ajs.service.invoice.ws.camelws.InputStatusIncident;
import com.ajs.service.invoice.ws.camelws.OutputStatusIncident;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXB;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadInvoiceListService implements Handler<LoadInvoiceList> {

    @Autowired
    InvoiceDao invoiceDao;

    public AppResponse execute(LoadInvoiceList dto) throws IllegalArgumentException {
//
//        try {
//            URL url = new URL("http://localhost:8085/rest/hello/invoices");
//            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
//            Invoices invoices = JAXB.unmarshal(urlConnection.getInputStream(), Invoices.class);
//
//            for (Invoice invoice : invoices.getInvoices()){
//                System.out.println(invoice.getCustomerReference());
//                //test
//            }
//
//        }
//        catch (Exception e){
//            e.printStackTrace();
//        }
//
//        try
//        {
//        URL url = new URL("http://localhost:9998/helloworld");
//        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//
////            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//
//            BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            String inputLine;
//            while ((inputLine = in.readLine()) != null)
//                System.out.println(inputLine);
//            in.close();
//        }
//        catch (Exception e){}
//
//
//        String serviceTest = null;
//        try {
//            InvoiceWSServiceLocator locator = new InvoiceWSServiceLocator();
//            InvoiceWS_PortType service = locator.getInvoiceWS();
//            serviceTest = service.sayHelloWorldFrom("Poochie woo woo waa waa");
//        }
//        catch (NoClassDefFoundError e){
//            e.printStackTrace();
//        }
//        catch (Exception ex){
//
//        }
//
//        try {
//            IncidentServiceLocator locator = new IncidentServiceLocator();
//            IncidentServicePortType service = locator.getIncidentServicePort();
//            OutputStatusIncident outputStatusIncident = service.statusIncident(new InputStatusIncident());
//        }
//        catch (Exception e){}

        AppResponse response = new AppResponse();

        List<Invoice> invoices = new ArrayList<Invoice>();

        if (dto.getCustomerId() != null){
            invoices = invoiceDao.findByCustomerId(dto.getCustomerId());
        }
        else {
            invoices = invoiceDao.findAll();
        }

        List<InvoiceDetailDto> invoiceDetailDtos = new ArrayList<InvoiceDetailDto>();
        if (invoices != null) {
            for (Invoice invoice : invoices) {
                InvoiceDetailDto invoiceDetailDto = new InvoiceDetailDto();
                invoiceDetailDto.setId(invoice.getId());
                invoiceDetailDto.setCustomerReference(invoice.getCustomerReference());
                invoiceDetailDto.setDescription(invoice.getDescription());
                invoiceDetailDto.setAmount(invoice.getAmount());
                invoiceDetailDto.setInvoiceDate(invoice.getInvoiceDate());
                invoiceDetailDto.setInvoiceNumber(invoice.getInvoiceNumber());
                if (invoice.getCustomer() != null) {
                    invoiceDetailDto.setCustomerName(invoice.getCustomer().getFirstName() + " " + invoice.getCustomer().getSurName());
                }
                invoiceDetailDtos.add(invoiceDetailDto);
            }
        }
        response.setDtos(invoiceDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadInvoiceList.class;
    }


}

