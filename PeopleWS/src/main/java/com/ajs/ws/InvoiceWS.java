package com.ajs.ws;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 08/02/2014
 * Time: 16:28
 * To change this template use File | Settings | File Templates.
 */

import com.ajs.dao.InvoiceDao;
import com.ajs.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.jws.WebService;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 07/02/2014
 * Time: 20:38
 * To change this template use File | Settings | File Templates.
 */
@WebService
@Component
public class InvoiceWS extends SpringBeanAutowiringSupport {

    @Autowired
    InvoiceDao invoiceDao;

    public String sayHelloWorldFrom(String from) {

        Invoice invoice = (Invoice) invoiceDao.findById(1L);
        String result = "Hello, world, from " + from;
        System.out.println(result);
        return result;
    }
}