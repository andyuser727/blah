package com.ajs;

import java.io.StringWriter;
import java.util.List;

import com.ajs.domain.Invoice;
import org.springframework.batch.item.ItemWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

public class InvoiceWriter implements ItemWriter<Invoice> {

    @Override
    public void write(List<? extends Invoice> invoices) throws Exception {

        JAXBContext jc = JAXBContext.newInstance(Invoice.class);
        Marshaller marshaller = jc.createMarshaller();

        System.out.println("writer..." + invoices.size());
        for(Invoice invoice : invoices){
            System.out.println("BATCH OUTPUT" + invoice.getInvoiceNumber());
            System.out.println("BATCH OUTPUT" + invoice.getDescription());
            System.out.println("BATCH OUTPUT" + invoice.getAmount());

            StringWriter stringWriter = new StringWriter();
            marshaller.marshal(invoice, stringWriter);
            System.out.println(stringWriter.toString());
        }

    }

}