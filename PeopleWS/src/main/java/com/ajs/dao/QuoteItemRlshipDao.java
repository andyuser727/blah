package com.ajs.dao;

import com.ajs.domain.Category;
import com.ajs.domain.QuoteItemRlship;
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
public class QuoteItemRlshipDao  extends HibernateDao<QuoteItemRlship>{

    public QuoteItemRlshipDao(){
        super(QuoteItemRlship.class);
    }

    public List<QuoteItemRlship> findByQuoteId(Long quoteId){

        Criteria criteria = sf.getCurrentSession().createCriteria(QuoteItemRlshipDao.class);
        criteria.add(Restrictions.eq("quote.id", quoteId));
        return criteria.list();

    }

    public List<QuoteItemRlship> findByItemId(Long quoteId){

        Criteria criteria = sf.getCurrentSession().createCriteria(QuoteItemRlshipDao.class);
        criteria.add(Restrictions.eq("item.id", quoteId));
        return criteria.list();

    }

    public QuoteItemRlship findRelationship(Long quoteId, Long itemId){

        Criteria criteria = sf.getCurrentSession().createCriteria(QuoteItemRlship.class);
        criteria.add(Restrictions.eq("quote.id", quoteId));
        criteria.add(Restrictions.eq("item.id", itemId));
        return (QuoteItemRlship)criteria.uniqueResult();

    }

}