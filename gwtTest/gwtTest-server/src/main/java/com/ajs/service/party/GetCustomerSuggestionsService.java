package com.ajs.service.party;

import com.ajs.shared.commands.GetCustomerSuggestions;
import com.ajs.shared.commands.GoogleSuggestion;
import com.ajs.dao.CustomerDao;
import com.ajs.domain.Customer;
import com.ajs.service.Handler;
import com.ajs.shared.AppResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 16/12/2013
 * Time: 20:58
 * To change this template use File | Settings | File Templates.
 */
@SuppressWarnings("serial")
@Component
@Transactional
public class GetCustomerSuggestionsService implements Handler<GetCustomerSuggestions> {

    @Autowired
    CustomerDao customerDao;

    public AppResponse execute(GetCustomerSuggestions dto) {

        AppResponse response = new AppResponse();

        List<Customer> customerList = customerDao.findAllCategoriesByCode("%" + dto.getPartialQueryString() + "%");

        ArrayList<GoogleSuggestion> results = new ArrayList<GoogleSuggestion>();

        for (Customer customer : customerList) {
            results.add(new GoogleSuggestion(String.valueOf(customer.getId()), customer.getFirstName() + " " + customer.getSurName()));
        }

        response.setSuggestions(results);

        return response;
    }

    public Class getIncomingCommandClass() {
        return GetCustomerSuggestions.class;
    }


}

