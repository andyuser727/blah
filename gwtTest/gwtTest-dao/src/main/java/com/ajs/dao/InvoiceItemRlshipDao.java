package com.ajs.dao;

import com.ajs.domain.Category;
import com.ajs.domain.InvoiceItemRlship;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class InvoiceItemRlshipDao extends GenericDaoImpl<InvoiceItemRlship>{

    public InvoiceItemRlshipDao(){
        super(InvoiceItemRlship.class);
    }

    public List<InvoiceItemRlship> findByInvoiceId(Long invoiceId){

        Criteria criteria = sf.getCurrentSession().createCriteria(InvoiceItemRlshipDao.class);
        criteria.add(Restrictions.eq("invoice.id", invoiceId));
        return criteria.list();

    }

    public List<InvoiceItemRlship> findByItemId(Long invoiceId){

        Criteria criteria = sf.getCurrentSession().createCriteria(InvoiceItemRlshipDao.class);
        criteria.add(Restrictions.eq("item.id", invoiceId));
        return criteria.list();

    }

    public InvoiceItemRlship findRelationship(Long invoiceId, Long itemId){

        Criteria criteria = sf.getCurrentSession().createCriteria(InvoiceItemRlship.class);
        criteria.add(Restrictions.eq("invoice.id", invoiceId));
        criteria.add(Restrictions.eq("item.id", itemId));
        return (InvoiceItemRlship)criteria.uniqueResult();

    }

}