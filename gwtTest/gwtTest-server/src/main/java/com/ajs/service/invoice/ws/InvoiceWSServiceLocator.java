/**
 * InvoiceWSServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ajs.service.invoice.ws;

public class InvoiceWSServiceLocator extends org.apache.axis.client.Service implements com.ajs.service.invoice.ws.InvoiceWSService {

    public InvoiceWSServiceLocator() {
    }


    public InvoiceWSServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public InvoiceWSServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for InvoiceWS
    private java.lang.String InvoiceWS_address = "http://localhost:8085/services/InvoiceWS";

    public java.lang.String getInvoiceWSAddress() {
        return InvoiceWS_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String InvoiceWSWSDDServiceName = "InvoiceWS";

    public java.lang.String getInvoiceWSWSDDServiceName() {
        return InvoiceWSWSDDServiceName;
    }

    public void setInvoiceWSWSDDServiceName(java.lang.String name) {
        InvoiceWSWSDDServiceName = name;
    }

    public com.ajs.service.invoice.ws.InvoiceWS_PortType getInvoiceWS() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(InvoiceWS_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getInvoiceWS(endpoint);
    }

    public com.ajs.service.invoice.ws.InvoiceWS_PortType getInvoiceWS(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ajs.service.invoice.ws.InvoiceWSSoapBindingStub _stub = new com.ajs.service.invoice.ws.InvoiceWSSoapBindingStub(portAddress, this);
            _stub.setPortName(getInvoiceWSWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setInvoiceWSEndpointAddress(java.lang.String address) {
        InvoiceWS_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ajs.service.invoice.ws.InvoiceWS_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ajs.service.invoice.ws.InvoiceWSSoapBindingStub _stub = new com.ajs.service.invoice.ws.InvoiceWSSoapBindingStub(new java.net.URL(InvoiceWS_address), this);
                _stub.setPortName(getInvoiceWSWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("InvoiceWS".equals(inputPortName)) {
            return getInvoiceWS();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://ws.ajs.com", "InvoiceWSService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://ws.ajs.com", "InvoiceWS"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("InvoiceWS".equals(portName)) {
            setInvoiceWSEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
