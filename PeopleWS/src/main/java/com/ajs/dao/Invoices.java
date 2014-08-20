package com.ajs.dao;

import com.ajs.domain.Invoice;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 09/02/2014
 * Time: 17:59
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement
public class Invoices {

    @XmlElement
    public List<Invoice> invoices;

}
