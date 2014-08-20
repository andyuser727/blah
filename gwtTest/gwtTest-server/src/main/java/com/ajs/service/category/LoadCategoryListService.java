package com.ajs.service.category;

import com.ajs.dao.CategoryDao;
import com.ajs.domain.Category;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.commands.LoadCategoryList;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadCategoryListService implements Handler<LoadCategoryList> {

    @Autowired
    CategoryDao categoryDao;

    public AppResponse execute(LoadCategoryList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<Category> categories = categoryDao.findAll();

        List<CategoryDetailDto> categoryDetailDtos = new ArrayList<CategoryDetailDto>();
        for (Category category : categories){
            CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
            categoryDetailDto.setId(category.getId());
            categoryDetailDto.setCode(category.getCode());
            categoryDetailDto.setDescription(category.getDescription());
            categoryDetailDtos.add(categoryDetailDto);
        }
        response.setDtos(categoryDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass(){
        return LoadCategoryList.class;
    }


}

