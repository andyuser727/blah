package com.ajs.dao;

import com.ajs.domain.CustomerOrder;
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
public class CustomerOrderDao extends GenericDaoImpl<CustomerOrder>{

    public CustomerOrderDao(){
        super(CustomerOrder.class);
    }

    public List<CustomerOrder> findAllCustomerOrdersByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllCustomerOrders");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

}