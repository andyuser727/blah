package com.ajs.service.category;

import com.ajs.shared.commands.ProcessCategoryDeletes;
import com.ajs.dao.CategoryDao;
import com.ajs.domain.Category;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class ProcessCategoryDeletesService implements Handler<ProcessCategoryDeletes> {

    @Autowired
    CategoryDao categoryDao;

    public AppResponse execute(ProcessCategoryDeletes dto) {

        AppResponse response = new AppResponse();

        for (Long categoryId : dto.getIdsToDelete()){
            // good? bad? have to load first???
            Category category = categoryDao.find(categoryId);
            categoryDao.delete(category);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return ProcessCategoryDeletes.class;
    }


}

