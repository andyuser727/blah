/**
 * IncidentServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ajs.service.invoice.ws.camelws;

public class IncidentServiceLocator extends org.apache.axis.client.Service implements com.ajs.service.invoice.ws.camelws.IncidentService {

    public IncidentServiceLocator() {
    }


    public IncidentServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public IncidentServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for IncidentServicePort
    private java.lang.String IncidentServicePort_address = "http://localhost:8086/webservices/incident";

    public java.lang.String getIncidentServicePortAddress() {
        return IncidentServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String IncidentServicePortWSDDServiceName = "IncidentServicePort";

    public java.lang.String getIncidentServicePortWSDDServiceName() {
        return IncidentServicePortWSDDServiceName;
    }

    public void setIncidentServicePortWSDDServiceName(java.lang.String name) {
        IncidentServicePortWSDDServiceName = name;
    }

    public com.ajs.service.invoice.ws.camelws.IncidentServicePortType getIncidentServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(IncidentServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getIncidentServicePort(endpoint);
    }

    public com.ajs.service.invoice.ws.camelws.IncidentServicePortType getIncidentServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ajs.service.invoice.ws.camelws.IncidentServiceSoapBindingStub _stub = new com.ajs.service.invoice.ws.camelws.IncidentServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getIncidentServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setIncidentServicePortEndpointAddress(java.lang.String address) {
        IncidentServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ajs.service.invoice.ws.camelws.IncidentServicePortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ajs.service.invoice.ws.camelws.IncidentServiceSoapBindingStub _stub = new com.ajs.service.invoice.ws.camelws.IncidentServiceSoapBindingStub(new java.net.URL(IncidentServicePort_address), this);
                _stub.setPortName(getIncidentServicePortWSDDServiceName());
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
        if ("IncidentServicePort".equals(inputPortName)) {
            return getIncidentServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://incident.cxf.example.camel.apache.org/", "IncidentService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://incident.cxf.example.camel.apache.org/", "IncidentServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("IncidentServicePort".equals(portName)) {
            setIncidentServicePortEndpointAddress(address);
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
