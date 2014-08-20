/**
 * InputReportIncident.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ajs.service.invoice.ws.camelws;

public class InputReportIncident  implements java.io.Serializable {
    private java.lang.String details;

    private java.lang.String email;

    private java.lang.String familyName;

    private java.lang.String givenName;

    private java.lang.String incidentDate;

    private java.lang.String incidentId;

    private java.lang.String phone;

    private java.lang.String summary;

    public InputReportIncident() {
    }

    public InputReportIncident(
           java.lang.String details,
           java.lang.String email,
           java.lang.String familyName,
           java.lang.String givenName,
           java.lang.String incidentDate,
           java.lang.String incidentId,
           java.lang.String phone,
           java.lang.String summary) {
           this.details = details;
           this.email = email;
           this.familyName = familyName;
           this.givenName = givenName;
           this.incidentDate = incidentDate;
           this.incidentId = incidentId;
           this.phone = phone;
           this.summary = summary;
    }


    /**
     * Gets the details value for this InputReportIncident.
     * 
     * @return details
     */
    public java.lang.String getDetails() {
        return details;
    }


    /**
     * Sets the details value for this InputReportIncident.
     * 
     * @param details
     */
    public void setDetails(java.lang.String details) {
        this.details = details;
    }


    /**
     * Gets the email value for this InputReportIncident.
     * 
     * @return email
     */
    public java.lang.String getEmail() {
        return email;
    }


    /**
     * Sets the email value for this InputReportIncident.
     * 
     * @param email
     */
    public void setEmail(java.lang.String email) {
        this.email = email;
    }


    /**
     * Gets the familyName value for this InputReportIncident.
     * 
     * @return familyName
     */
    public java.lang.String getFamilyName() {
        return familyName;
    }


    /**
     * Sets the familyName value for this InputReportIncident.
     * 
     * @param familyName
     */
    public void setFamilyName(java.lang.String familyName) {
        this.familyName = familyName;
    }


    /**
     * Gets the givenName value for this InputReportIncident.
     * 
     * @return givenName
     */
    public java.lang.String getGivenName() {
        return givenName;
    }


    /**
     * Sets the givenName value for this InputReportIncident.
     * 
     * @param givenName
     */
    public void setGivenName(java.lang.String givenName) {
        this.givenName = givenName;
    }


    /**
     * Gets the incidentDate value for this InputReportIncident.
     * 
     * @return incidentDate
     */
    public java.lang.String getIncidentDate() {
        return incidentDate;
    }


    /**
     * Sets the incidentDate value for this InputReportIncident.
     * 
     * @param incidentDate
     */
    public void setIncidentDate(java.lang.String incidentDate) {
        this.incidentDate = incidentDate;
    }


    /**
     * Gets the incidentId value for this InputReportIncident.
     * 
     * @return incidentId
     */
    public java.lang.String getIncidentId() {
        return incidentId;
    }


    /**
     * Sets the incidentId value for this InputReportIncident.
     * 
     * @param incidentId
     */
    public void setIncidentId(java.lang.String incidentId) {
        this.incidentId = incidentId;
    }


    /**
     * Gets the phone value for this InputReportIncident.
     * 
     * @return phone
     */
    public java.lang.String getPhone() {
        return phone;
    }


    /**
     * Sets the phone value for this InputReportIncident.
     * 
     * @param phone
     */
    public void setPhone(java.lang.String phone) {
        this.phone = phone;
    }


    /**
     * Gets the summary value for this InputReportIncident.
     * 
     * @return summary
     */
    public java.lang.String getSummary() {
        return summary;
    }


    /**
     * Sets the summary value for this InputReportIncident.
     * 
     * @param summary
     */
    public void setSummary(java.lang.String summary) {
        this.summary = summary;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof InputReportIncident)) return false;
        InputReportIncident other = (InputReportIncident) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.details==null && other.getDetails()==null) || 
             (this.details!=null &&
              this.details.equals(other.getDetails()))) &&
            ((this.email==null && other.getEmail()==null) || 
             (this.email!=null &&
              this.email.equals(other.getEmail()))) &&
            ((this.familyName==null && other.getFamilyName()==null) || 
             (this.familyName!=null &&
              this.familyName.equals(other.getFamilyName()))) &&
            ((this.givenName==null && other.getGivenName()==null) || 
             (this.givenName!=null &&
              this.givenName.equals(other.getGivenName()))) &&
            ((this.incidentDate==null && other.getIncidentDate()==null) || 
             (this.incidentDate!=null &&
              this.incidentDate.equals(other.getIncidentDate()))) &&
            ((this.incidentId==null && other.getIncidentId()==null) || 
             (this.incidentId!=null &&
              this.incidentId.equals(other.getIncidentId()))) &&
            ((this.phone==null && other.getPhone()==null) || 
             (this.phone!=null &&
              this.phone.equals(other.getPhone()))) &&
            ((this.summary==null && other.getSummary()==null) || 
             (this.summary!=null &&
              this.summary.equals(other.getSummary())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getDetails() != null) {
            _hashCode += getDetails().hashCode();
        }
        if (getEmail() != null) {
            _hashCode += getEmail().hashCode();
        }
        if (getFamilyName() != null) {
            _hashCode += getFamilyName().hashCode();
        }
        if (getGivenName() != null) {
            _hashCode += getGivenName().hashCode();
        }
        if (getIncidentDate() != null) {
            _hashCode += getIncidentDate().hashCode();
        }
        if (getIncidentId() != null) {
            _hashCode += getIncidentId().hashCode();
        }
        if (getPhone() != null) {
            _hashCode += getPhone().hashCode();
        }
        if (getSummary() != null) {
            _hashCode += getSummary().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(InputReportIncident.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://incident.cxf.example.camel.apache.org/", "inputReportIncident"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("details");
        elemField.setXmlName(new javax.xml.namespace.QName("", "details"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("email");
        elemField.setXmlName(new javax.xml.namespace.QName("", "email"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("familyName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "familyName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("givenName");
        elemField.setXmlName(new javax.xml.namespace.QName("", "givenName"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incidentDate");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incidentDate"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("incidentId");
        elemField.setXmlName(new javax.xml.namespace.QName("", "incidentId"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("phone");
        elemField.setXmlName(new javax.xml.namespace.QName("", "phone"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("summary");
        elemField.setXmlName(new javax.xml.namespace.QName("", "summary"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setMinOccurs(0);
        elemField.setNillable(false);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
