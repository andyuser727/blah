package com.ajs.dao;

import com.ajs.domain.Customer;
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
public class CustomerDao  extends HibernateDao<Customer>{

    public CustomerDao(){
        super(Customer.class);
    }

    public List<Customer> findAllCategoriesByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllCustomers");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

}