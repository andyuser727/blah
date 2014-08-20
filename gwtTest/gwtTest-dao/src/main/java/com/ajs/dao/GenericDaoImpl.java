package com.ajs.dao;

/**
 * Created with IntelliJ IDEA.
 * User: smithaj
 * Date: 12/02/2014
 * Time: 13:10
 * To change this template use File | Settings | File Templates.
 */

import com.ajs.domain.PersistentObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class GenericDaoImpl<T extends PersistentObject> implements GenericDao<T>{

    Class<T> type;

    public GenericDaoImpl(Class<T> type) {
        this.type = type;
    }

    public GenericDaoImpl() {

    }

    @Autowired
    SessionFactory sf;

//    @Transactional(readOnly = true)
    @Override
    public T find(Long id) {
        return (T) sf.getCurrentSession().get(type, id);
    }

//    @Transactional
    @Override
    public void delete(T obj) {
        sf.getCurrentSession().delete(obj);
    }

    @Override
//    @Transactional
    public void save(T obj) {
        sf.getCurrentSession().save(obj);
    }

    @SuppressWarnings("unchecked")
    @Override
//    @Transactional
    public List<T> findAll() {
        return (List<T>) sf.getCurrentSession().createCriteria(type).list();
    }


//    @Transactional
//    @Override
//    public void saveOrUpdate(T obj) {
//        getSessionFactory().getCurrentSession().saveOrUpdate(obj);
//    }
}