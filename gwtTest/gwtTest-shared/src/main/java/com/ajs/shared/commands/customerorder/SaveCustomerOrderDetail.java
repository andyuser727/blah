package com.ajs.shared.commands.customerorder;

import com.ajs.shared.dto.RpcDto;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.customerorder.CustomerOrderDetailDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveCustomerOrderDetail extends RpcDto {

    CustomerOrderDetailDto customerOrderDetailDto;
    Map<Long, ItemDetailDto> newItemDtos;
    List<Long> itemIdsToRemove;

    public SaveCustomerOrderDetail(){}

    public SaveCustomerOrderDetail(CustomerOrderDetailDto customerOrderDetailDto){
        this.customerOrderDetailDto = customerOrderDetailDto;
    }

    public SaveCustomerOrderDetail(CustomerOrderDetailDto customerOrderDetailDto, Map<Long, ItemDetailDto> newItemDtos, List<Long> itemIdsToRemove){
        this.customerOrderDetailDto = customerOrderDetailDto;
        this.newItemDtos = newItemDtos;
        this.itemIdsToRemove = itemIdsToRemove;
    }

    public CustomerOrderDetailDto getCustomerOrderDetailDto() {
        return customerOrderDetailDto;
    }

    public Map<Long, ItemDetailDto> getNewItemDtos() {
        return newItemDtos;
    }

    public List<Long> getItemIdsToRemove() {
        return itemIdsToRemove;
    }
}
