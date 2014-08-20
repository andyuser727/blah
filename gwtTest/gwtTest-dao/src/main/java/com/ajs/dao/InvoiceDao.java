package com.ajs.dao;

import com.ajs.domain.Invoice;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InvoiceDao extends GenericDaoImpl<Invoice>{

    public InvoiceDao(){
        super(Invoice.class);
    }

//    @Transactional
    public List<Invoice> findAllInvoicesByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllInvoices");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

//    @Transactional
    public List<Invoice> findByCustomerId(Long customerId){
        Criteria criteria = sf.getCurrentSession().createCriteria(Invoice.class);
        criteria.add(Restrictions.eq("customer.id", customerId));
        return criteria.list();
    }

    public List<Invoice> loadOutstandingInvoices(){
        return sf.getCurrentSession().getNamedQuery("getInvoicesWithItems").list();
    }

}