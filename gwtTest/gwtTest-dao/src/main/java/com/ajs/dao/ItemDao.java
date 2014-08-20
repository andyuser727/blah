package com.ajs.dao;

import com.ajs.domain.Item;
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
public class ItemDao extends GenericDaoImpl<Item> {

    public ItemDao() {
        super(Item.class);
    }

    public List<Item> findAllItemsExcludeIds(List<Long> ids) {
        Query query = sf.getCurrentSession().getNamedQuery("findAllItemsExcludeIds");
//        if (ids != null && ids.size() > 0) {
            query.setParameterList("ids", ids);
//        }
//        else {
//            query.setParameterList("ids", null);
//        }
        return query.list();
    }

}