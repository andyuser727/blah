package com.ajs.ws;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 09/02/2014
 * Time: 14:44
 * To change this template use File | Settings | File Templates.
 */

import com.ajs.dao.InvoiceDao;
import com.ajs.dao.Invoices;
import com.ajs.domain.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/hello")
//@Produces({"text/plain","application/xml","application/json"})
@Component
public class TestRest extends SpringBeanAutowiringSupport {

    @Autowired
    InvoiceDao invoiceDao;

    @GET
    @Path("/testinvoices")
    @Produces(MediaType.APPLICATION_XML)
    public Response getMsg(@PathParam("param") String msg) {

        Invoices invoices = new Invoices();
        invoices.invoices = invoiceDao.findAll();
        return Response.status(200).entity(invoices).build();

    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/invoices")
    public Invoices findInvoices() {
        Invoices invoices = new Invoices();
        invoices.invoices = invoiceDao.findAll();
        return invoices;
    }

}


