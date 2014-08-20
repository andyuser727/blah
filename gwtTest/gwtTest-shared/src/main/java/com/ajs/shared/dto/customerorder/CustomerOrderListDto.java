package com.ajs.shared.dto.customerorder;

import com.ajs.shared.dto.RpcDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:08
 * To change this template use File | Settings | File Templates.
 */
public class CustomerOrderListDto extends RpcDto {

    List<CustomerOrderDetailDto> customerOrderDetailDtoList;

    public List<CustomerOrderDetailDto> getCustomerOrderDetailDtoList() {
        return customerOrderDetailDtoList;
    }

    public void setCustomerOrderDetailDtoList(List<CustomerOrderDetailDto> customerOrderDetailDtoList) {
        this.customerOrderDetailDtoList = customerOrderDetailDtoList;
    }
}
