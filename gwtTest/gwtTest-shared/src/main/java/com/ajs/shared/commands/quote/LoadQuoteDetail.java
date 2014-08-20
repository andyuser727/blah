package com.ajs.shared.commands.quote;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadQuoteDetail extends RpcDto{

    Long categoryId;

    public LoadQuoteDetail(){
    }

    public LoadQuoteDetail(Long id){
        this.categoryId = id;
    }

    public Long getQuoteId() {
        return categoryId;
    }

    public void setQuoteId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
