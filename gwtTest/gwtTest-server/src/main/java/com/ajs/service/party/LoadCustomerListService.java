package com.ajs.service.party;

import com.ajs.dao.CustomerDao;
import com.ajs.domain.Customer;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.ajs.shared.commands.LoadCustomerList;
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
public class LoadCustomerListService implements Handler<LoadCustomerList> {

    @Autowired
    CustomerDao customerDao;

    public AppResponse execute(LoadCustomerList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<Customer> categories = customerDao.findAll();

        List<CustomerDetailDto> customerDetailDtos = new ArrayList<CustomerDetailDto>();
        for (Customer customer : categories){
            CustomerDetailDto customerDetailDto = new CustomerDetailDto();
            customerDetailDto.setId(customer.getId());
            customerDetailDto.setFirstName(customer.getFirstName());
            customerDetailDto.setSurName(customer.getSurName());
            customerDetailDtos.add(customerDetailDto);
        }
        response.setDtos(customerDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass(){
        return LoadCustomerList.class;
    }


}

