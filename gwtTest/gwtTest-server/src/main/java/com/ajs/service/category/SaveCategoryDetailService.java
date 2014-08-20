package com.ajs.service.category;

import com.ajs.dao.CategoryDao;
import com.ajs.domain.Category;
import com.ajs.service.SimpleHandler;
import com.ajs.shared.SimpleResponse;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.commands.SaveCategoryDetail;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveCategoryDetailService implements SimpleHandler<SaveCategoryDetail> {

    @Autowired
    CategoryDao categoryDao;

    public SimpleResponse executeSimple(SaveCategoryDetail dto) {

        SimpleResponse response = new SimpleResponse();

        CategoryDetailDto categoryDetailDto = dto.getCategoryDetailDto();

        if (dto.getCategoryDetailDto().getId() != null) {
            Category category = categoryDao.find(dto.getCategoryDetailDto().getId());
            category.setCode(dto.getCategoryDetailDto().getCode());
            category.setDescription(dto.getCategoryDetailDto().getDescription());
        } else {
            Category category = new Category();
            category.setCode(categoryDetailDto.getCode());
            category.setDescription(categoryDetailDto.getDescription());
            categoryDao.save(category);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return SaveCategoryDetail.class;
    }


}

