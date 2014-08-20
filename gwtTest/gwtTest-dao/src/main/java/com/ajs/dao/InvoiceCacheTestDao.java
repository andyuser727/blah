package com.ajs.dao;

import com.ajs.domain.Invoice;
import com.ajs.domain.InvoiceCacheTest;
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
public class InvoiceCacheTestDao extends GenericDaoImpl<InvoiceCacheTest>{

    public InvoiceCacheTestDao(){
        super(InvoiceCacheTest.class);
    }



}