package com.ajs.shared.commands;

import com.ajs.shared.dto.party.CustomerDetailDto;
import com.ajs.shared.dto.RpcDto;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveCustomerDetail extends RpcDto {

    CustomerDetailDto customerDetailDto;

    public SaveCustomerDetail(){}

    public SaveCustomerDetail(CustomerDetailDto customerDetailDto){
        this.customerDetailDto = customerDetailDto;
    }

    public CustomerDetailDto getCustomerDetailDto() {
        return customerDetailDto;
    }
}
