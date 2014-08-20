package com.ajs.dao;

import com.ajs.domain.PersistentObject;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: smithaj
 * Date: 12/02/2014
 * Time: 13:13
 * To change this template use File | Settings | File Templates.
 */
public interface GenericDao<T extends PersistentObject> {

    public void delete(T obj);

    public T find(Long id);

    public void save(T t);

    public List<T> findAll();
//    Integer countAll();
}