package com.ajs.service.customerorder;

import com.ajs.shared.commands.customerorder.LoadCustomerOrderList;
import com.ajs.dao.CustomerOrderDao;
import com.ajs.domain.CustomerOrder;
import com.ajs.shared.dto.customerorder.CustomerOrderDetailDto;
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
public class LoadCustomerOrderListService implements Handler<LoadCustomerOrderList> {

    @Autowired
    CustomerOrderDao customerOrderDao;

    public AppResponse execute(LoadCustomerOrderList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<CustomerOrder> customerOrders = customerOrderDao.findAll();

        List<CustomerOrderDetailDto> customerOrderDetailDtos = new ArrayList<CustomerOrderDetailDto>();
        if (customerOrders != null) {
            for (CustomerOrder customerOrder : customerOrders) {
                CustomerOrderDetailDto customerOrderDetailDto = new CustomerOrderDetailDto();
                customerOrderDetailDto.setId(customerOrder.getId());
                customerOrderDetailDto.setCustomerReference(customerOrder.getCustomerReference());
                customerOrderDetailDto.setDescription(customerOrder.getDescription());
                customerOrderDetailDto.setAmount(customerOrder.getAmount());
                customerOrderDetailDto.setCustomerOrderDate(customerOrder.getCustomerOrderDate());
                customerOrderDetailDto.setCustomerOrderNumber(customerOrder.getCustomerOrderNumber());
                if (customerOrder.getCustomer() != null) {
                    customerOrderDetailDto.setCustomerName(customerOrder.getCustomer().getFirstName() + " " + customerOrder.getCustomer().getSurName());
                }
                customerOrderDetailDtos.add(customerOrderDetailDto);
            }
        }
        response.setDtos(customerOrderDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadCustomerOrderList.class;
    }


}

