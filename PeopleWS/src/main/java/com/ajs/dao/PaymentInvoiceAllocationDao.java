package com.ajs.dao;

import com.ajs.domain.Category;
import com.ajs.domain.InvoiceItemRlship;
import com.ajs.domain.PaymentInvoiceAllocation;
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
public class PaymentInvoiceAllocationDao  extends HibernateDao<PaymentInvoiceAllocation>{

    public PaymentInvoiceAllocationDao(){
        super(PaymentInvoiceAllocation.class);
    }

    public List<PaymentInvoiceAllocation> findByInvoiceId(Long invoiceId){

        Criteria criteria = sf.getCurrentSession().createCriteria(PaymentInvoiceAllocation.class);
        criteria.add(Restrictions.eq("invoice.id", invoiceId));
        return criteria.list();

    }

    public List<PaymentInvoiceAllocation> findByPaymentId(Long paymentId){

        Criteria criteria = sf.getCurrentSession().createCriteria(PaymentInvoiceAllocation.class);
        criteria.add(Restrictions.eq("payment.id", paymentId));
        return criteria.list();

    }



}