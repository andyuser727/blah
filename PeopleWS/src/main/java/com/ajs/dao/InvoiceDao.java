package com.ajs.dao;

import com.ajs.domain.Invoice;
import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InvoiceDao  extends HibernateDao<Invoice>{

    public InvoiceDao(){
        super(Invoice.class);
    }

    @Transactional
    public List<Invoice> findAllInvoicesByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllInvoices");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

}