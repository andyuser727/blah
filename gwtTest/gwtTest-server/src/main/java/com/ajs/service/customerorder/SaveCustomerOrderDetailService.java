package com.ajs.service.customerorder;

import com.ajs.shared.commands.customerorder.SaveCustomerOrderDetail;
import com.ajs.dao.CustomerDao;
import com.ajs.dao.ItemDao;
import com.ajs.dao.CustomerOrderDao;
import com.ajs.dao.CustomerOrderItemRlshipDao;
import com.ajs.domain.Customer;
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

import java.util.Map;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveCustomerOrderDetailService implements Handler<SaveCustomerOrderDetail> {

    @Autowired
    CustomerOrderDao customerOrderDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    CustomerOrderItemRlshipDao customerOrderItemRlshipDao;

    public AppResponse execute(SaveCustomerOrderDetail dto) {

        AppResponse response = new AppResponse();

        CustomerOrderDetailDto customerOrderDetailDto = dto.getCustomerOrderDetailDto();

        CustomerOrder customerOrder = null;
        if (dto.getCustomerOrderDetailDto().getId() != null) {
            customerOrder = customerOrderDao.find(dto.getCustomerOrderDetailDto().getId());
        } else {
            customerOrder = new CustomerOrder();
            customerOrderDao.save(customerOrder);
        }

        Map<Long, ItemDetailDto> newItemDtos = dto.getNewItemDtos();

        for (Long idToRemove : dto.getItemIdsToRemove()) {
            if (newItemDtos != null) {
                newItemDtos.remove(idToRemove);
            }
            CustomerOrderItemRlship customerOrderItemRlship = customerOrderItemRlshipDao.findRelationship(customerOrder.getId(), idToRemove);
            if (customerOrderItemRlship != null) {
                customerOrderItemRlshipDao.delete(customerOrderItemRlship);
            }
        }

        if (newItemDtos != null) {

            for (ItemDetailDto itemDetailDto : newItemDtos.values()) {
                Item item;
                if (itemDetailDto.getId() == null) {

                    item = new Item();

                    item.setCode(itemDetailDto.getCode());
                    item.setName(itemDetailDto.getName());
                    item.setDescription(itemDetailDto.getDescription());
                    item.setAmount(itemDetailDto.getAmount());

                    itemDao.save(item);

                } else {
                    item = itemDao.find(itemDetailDto.getId());
                }

                CustomerOrderItemRlship customerOrderItemRlship = new CustomerOrderItemRlship();
                customerOrderItemRlshipDao.save(customerOrderItemRlship);
                customerOrderItemRlship.setCustomerOrder(customerOrder);
                customerOrderItemRlship.setItem(item);
            }
        }

        customerOrder.setCustomerReference(customerOrderDetailDto.getCustomerReference());
        customerOrder.setDescription(customerOrderDetailDto.getDescription());
        customerOrder.setCustomerOrderDate(customerOrderDetailDto.getCustomerOrderDate());
        customerOrder.setCustomerOrderNumber(customerOrderDetailDto.getCustomerOrderNumber());
        customerOrder.setAmount(customerOrderDetailDto.getAmount());

        if (customerOrderDetailDto.getCustomerId() != null) {
            Customer customer = customerDao.find(customerOrderDetailDto.getCustomerId());
            customerOrder.setCustomer(customer);
            customer.getCustomerOrders().add(customerOrder);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return SaveCustomerOrderDetail.class;
    }


}

