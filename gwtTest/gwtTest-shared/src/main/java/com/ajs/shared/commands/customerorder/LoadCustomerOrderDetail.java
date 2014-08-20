package com.ajs.shared.commands.customerorder;

import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:11
 * To change this template use File | Settings | File Templates.
 */
public class LoadCustomerOrderDetail extends RpcDto{

    Long categoryId;

    public LoadCustomerOrderDetail(){
    }

    public LoadCustomerOrderDetail(Long id){
        this.categoryId = id;
    }

    public Long getCustomerOrderId() {
        return categoryId;
    }

    public void setOrdeId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
