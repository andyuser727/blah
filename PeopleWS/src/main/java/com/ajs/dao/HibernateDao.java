package com.ajs.dao;

import com.ajs.domain.Invoice;
import com.ajs.domain.PersistentObject;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 06/07/2013
 * Time: 13:09
 * To change this template use File | Settings | File Templates.
 */
@Component
public class HibernateDao<T extends PersistentObject> {

    Class<T> type;

    public HibernateDao(Class<T> type) {
        this.type = type;
    }

    public HibernateDao(){

    }

    @Autowired
    SessionFactory sf;


    public PersistentObject findById(Long id){
        return (PersistentObject)sf.getCurrentSession().get(type, id);
    }

    @Transactional
    public void save(T objectToPersist){

        try {
            sf.getCurrentSession().save(objectToPersist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void persist(T objectToPersist){

        try {
            sf.getCurrentSession().persist(objectToPersist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public void delete(T objectToPersist){

        try {
            sf.getCurrentSession().delete(objectToPersist);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
    public List<T> findAll() {

        Session session;

        List<T> list = null;
        try {
            session = sf.getCurrentSession();
            list = session.createCriteria(type).list();
//            Hibernate.initialize(list);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
}
