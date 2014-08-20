package com.ajs.service.customerorder;

import com.ajs.shared.commands.customerorder.LoadCustomerOrderDetail;
import com.ajs.dao.CustomerOrderDao;
import com.ajs.domain.Item;
import com.ajs.domain.CustomerOrder;
import com.ajs.domain.CustomerOrderItemRlship;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.dto.customerorder.CustomerOrderDetailDto;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
@Component
@Transactional
public class LoadCustomerOrderDetailService implements Handler<LoadCustomerOrderDetail> {

    @Autowired
    CustomerOrderDao customerOrderDao;

    public AppResponse execute(LoadCustomerOrderDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        CustomerOrder customerOrder = customerOrderDao.find(dto.getCustomerOrderId());
        CustomerOrderDetailDto customerOrderDetailDto = new CustomerOrderDetailDto();
        customerOrderDetailDto.setCustomerReference(customerOrder.getCustomerReference());
        customerOrderDetailDto.setDescription(customerOrder.getDescription());
        customerOrderDetailDto.setCustomerOrderNumber(customerOrder.getCustomerOrderNumber());
        customerOrderDetailDto.setCustomerOrderDate(customerOrder.getCustomerOrderDate());
        customerOrderDetailDto.setAmount(customerOrder.getAmount());
        if (customerOrder.getCustomer() != null) {
            customerOrderDetailDto.setCustomerId(customerOrder.getCustomer().getId());
        }
        customerOrderDetailDto.setId(customerOrder.getId());

        List<CustomerOrderDetailDto> customerOrderDetailDtos = new ArrayList<CustomerOrderDetailDto>();
        customerOrderDetailDtos.add(customerOrderDetailDto);

        Map<Long, ItemDetailDto> itemDtos = new HashMap<Long, ItemDetailDto>();
        if (customerOrder.getCustomerOrderItemRlships() != null && customerOrder.getCustomerOrderItemRlships().size() > 0)

            // TODO SHIT CHANGE IT LAZY
            for (CustomerOrderItemRlship customerOrderItemRlship : customerOrder.getCustomerOrderItemRlships()) {
                ItemDetailDto itemDto = new ItemDetailDto();
                Item item = customerOrderItemRlship.getItem();
                itemDto.setCode(item.getCode());
                itemDto.setDescription(item.getDescription());
                itemDto.setName(item.getName());
                itemDto.setAmount(item.getAmount());
                itemDto.setId(item.getId());
                itemDtos.put(item.getId(), itemDto);
            }

        customerOrderDetailDto.setItemDtoList(itemDtos);
        response.setDtos(customerOrderDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadCustomerOrderDetail.class;
    }


}

