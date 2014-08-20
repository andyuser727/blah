package com.ajs.service.category;

import com.ajs.shared.commands.GetCategorySuggestions;
import com.ajs.shared.commands.GoogleSuggestion;
import com.ajs.dao.CategoryDao;
import com.ajs.domain.Category;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 16/12/2013
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Component
@Transactional
public class GetCategorySuggestionsService implements Handler<GetCategorySuggestions> {

    @Autowired
    CategoryDao categoryDao;

    public AppResponse execute(GetCategorySuggestions dto) {

        AppResponse response = new AppResponse();

        List<Category> categoryList = categoryDao.findAllCategoriesByCode("%" + dto.getPartialQueryString() + "%");

        ArrayList<GoogleSuggestion> results = new ArrayList<GoogleSuggestion>();

        for (Category category : categoryList) {
            results.add(new GoogleSuggestion(String.valueOf(category.getId()), category.getCode()));
        }

        response.setSuggestions(results);

        return response;
    }

    public Class getIncomingCommandClass() {
        return GetCategorySuggestions.class;
    }


}

