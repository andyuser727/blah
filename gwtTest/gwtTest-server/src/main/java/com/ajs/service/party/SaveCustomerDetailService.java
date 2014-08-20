package com.ajs.service.party;

import com.ajs.dao.CustomerDao;
import com.ajs.domain.Customer;
import com.ajs.shared.dto.party.CustomerDetailDto;
import com.ajs.shared.commands.SaveCustomerDetail;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveCustomerDetailService implements Handler<SaveCustomerDetail> {

    @Autowired
    CustomerDao customerDao;

    public AppResponse execute(SaveCustomerDetail dto) {

        AppResponse response = new AppResponse();

        CustomerDetailDto customerDetailDto = dto.getCustomerDetailDto();

        Customer customer = null;
        if (dto.getCustomerDetailDto().getId() != null) {
            customer = customerDao.find(dto.getCustomerDetailDto().getId());

        } else {
            customer = new Customer();
            customer.setFirstName(customerDetailDto.getFirstName());
            customer.setSurName(customerDetailDto.getSurName());

        }

        customerDao.save(customer);

        return response;
    }

    public Class getIncomingCommandClass() {
        return SaveCustomerDetail.class;
    }


}

