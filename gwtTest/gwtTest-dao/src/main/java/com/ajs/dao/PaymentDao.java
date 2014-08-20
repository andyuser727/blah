package com.ajs.dao;

import com.ajs.domain.Payment;
import org.hibernate.Query;
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
public class PaymentDao extends GenericDaoImpl<Payment>{

    public PaymentDao(){
        super(Payment.class);
    }

    public List<Payment> findAllCategoriesByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllPayments");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

}