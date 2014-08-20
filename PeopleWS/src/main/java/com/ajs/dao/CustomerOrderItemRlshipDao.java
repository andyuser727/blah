package com.ajs.dao;

import com.ajs.domain.Category;
import com.ajs.domain.CustomerOrderItemRlship;
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
public class CustomerOrderItemRlshipDao  extends HibernateDao<CustomerOrderItemRlship>{

    public CustomerOrderItemRlshipDao(){
        super(CustomerOrderItemRlship.class);
    }

    public List<CustomerOrderItemRlship> findByCustomerOrderId(Long customerOrderId){

        Criteria criteria = sf.getCurrentSession().createCriteria(CustomerOrderItemRlshipDao.class);
        criteria.add(Restrictions.eq("customerOrder.id", customerOrderId));
        return criteria.list();

    }

    public List<CustomerOrderItemRlship> findByItemId(Long customerOrderId){

        Criteria criteria = sf.getCurrentSession().createCriteria(CustomerOrderItemRlshipDao.class);
        criteria.add(Restrictions.eq("item.id", customerOrderId));
        return criteria.list();

    }

    public CustomerOrderItemRlship findRelationship(Long customerOrderId, Long itemId){

        Criteria criteria = sf.getCurrentSession().createCriteria(CustomerOrderItemRlship.class);
        criteria.add(Restrictions.eq("customerOrder.id", customerOrderId));
        criteria.add(Restrictions.eq("item.id", itemId));
        return (CustomerOrderItemRlship)criteria.uniqueResult();

    }

}