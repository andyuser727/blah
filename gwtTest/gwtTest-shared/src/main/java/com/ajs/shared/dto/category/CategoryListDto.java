package com.ajs.shared.dto.category;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class CategoryListDto extends RpcDto {

    List<CategoryDetailDto> categoryDetailDtoList;

    public List<CategoryDetailDto> getCategoryDetailDtoList() {
        return categoryDetailDtoList;
    }

    public void setCategoryDetailDtoList(List<CategoryDetailDto> categoryDetailDtoList) {
        this.categoryDetailDtoList = categoryDetailDtoList;
    }
}
