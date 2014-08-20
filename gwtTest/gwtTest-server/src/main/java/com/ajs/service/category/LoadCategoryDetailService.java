package com.ajs.service.category;

import com.ajs.dao.CategoryDao;
import com.ajs.domain.Category;
import com.ajs.domain.Item;
import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.commands.LoadCategoryDetail;
import com.ajs.shared.dto.item.ItemDetailDto;
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
public class LoadCategoryDetailService implements Handler<LoadCategoryDetail> {

    @Autowired
    CategoryDao categoryDao;

    public AppResponse execute(LoadCategoryDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        Category category = categoryDao.find(dto.getCategoryId());
        CategoryDetailDto categoryDetailDto = new CategoryDetailDto();
        categoryDetailDto.setCode(category.getCode());
        categoryDetailDto.setDescription(category.getDescription());
        categoryDetailDto.setId(category.getId());

        List<CategoryDetailDto> categoryDetailDtos = new ArrayList<CategoryDetailDto>();
        categoryDetailDtos.add(categoryDetailDto);

        List<ItemDetailDto> itemDtos = new ArrayList<ItemDetailDto>();
        for (Item item : category.getItems()){
            ItemDetailDto itemDto = new ItemDetailDto();
            itemDto.setCode(item.getCode());
            itemDto.setDescription(item.getDescription());
            itemDto.setId(item.getId());
            itemDtos.add(itemDto);
        }
        categoryDetailDto.setItemDtoList(itemDtos);
        response.setDtos(categoryDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass(){
        return LoadCategoryDetail.class;
    }


}

