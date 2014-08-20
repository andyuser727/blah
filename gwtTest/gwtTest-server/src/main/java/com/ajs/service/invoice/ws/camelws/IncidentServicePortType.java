/**
 * IncidentServicePortType.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ajs.service.invoice.ws.camelws;

public interface IncidentServicePortType extends java.rmi.Remote {
    public com.ajs.service.invoice.ws.camelws.OutputStatusIncident statusIncident(com.ajs.service.invoice.ws.camelws.InputStatusIncident arg0) throws java.rmi.RemoteException;
    public com.ajs.service.invoice.ws.camelws.OutputReportIncident reportIncident(com.ajs.service.invoice.ws.camelws.InputReportIncident arg0) throws java.rmi.RemoteException;
}
