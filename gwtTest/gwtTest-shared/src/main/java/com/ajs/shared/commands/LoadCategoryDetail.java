package com.ajs.shared.commands;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadCategoryDetail extends RpcDto{

    Long categoryId;

    public LoadCategoryDetail(){
    }

    public LoadCategoryDetail(Long id){
        this.categoryId = id;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
