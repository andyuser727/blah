/**
 * InvoiceWSService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ajs.service.invoice.ws;

public interface InvoiceWSService extends javax.xml.rpc.Service {
    public java.lang.String getInvoiceWSAddress();

    public com.ajs.service.invoice.ws.InvoiceWS_PortType getInvoiceWS() throws javax.xml.rpc.ServiceException;

    public com.ajs.service.invoice.ws.InvoiceWS_PortType getInvoiceWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
