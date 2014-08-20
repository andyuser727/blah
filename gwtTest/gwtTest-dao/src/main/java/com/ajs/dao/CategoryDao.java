package com.ajs.dao;

import com.ajs.domain.Category;
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
public class CategoryDao extends GenericDaoImpl<Category>{

    public CategoryDao(){
        super(Category.class);
    }

    public List<Category> findAllCategoriesByCode(String partialCode){
        Query query = sf.getCurrentSession().getNamedQuery("findAllCategories");
        query.setParameter("partialCode", partialCode);
        return query.list();
    }

}