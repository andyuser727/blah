package com.ajs.shared.commands;

import com.ajs.shared.dto.category.CategoryDetailDto;
import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveCategoryDetail extends RpcDto {

    CategoryDetailDto categoryDetailDto;

    public SaveCategoryDetail(){}

    public SaveCategoryDetail(CategoryDetailDto categoryDetailDto){
        this.categoryDetailDto = categoryDetailDto;
    }

    public CategoryDetailDto getCategoryDetailDto() {
        return categoryDetailDto;
    }
}
