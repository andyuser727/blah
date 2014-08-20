package com.ajs.service.item;

import com.ajs.dao.InvoiceDao;
import com.ajs.dao.ItemDao;
import com.ajs.domain.Item;
import com.ajs.shared.dto.item.ItemDetailDto;
import com.ajs.shared.commands.item.SaveItemDetail;
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
public class SaveItemDetailService implements Handler<SaveItemDetail> {

    @Autowired
    ItemDao itemDao;

    @Autowired
    InvoiceDao invoiceDao;

    public AppResponse execute(SaveItemDetail dto) {

        AppResponse response = new AppResponse();

        ItemDetailDto itemDetailDto = dto.getItemDetailDto();

        Item item = new Item();
        item.setCode(itemDetailDto.getCode());
        item.setDescription(itemDetailDto.getDescription());
        item.setName(itemDetailDto.getName());
        item.setAmount(itemDetailDto.getAmount());

        itemDao.save(item);

        itemDetailDto.setId(item.getId());

        // save item or both cat and item or what????
//        Invoice invoice = (Invoice) invoiceDao.findById(dto.getInvoiceId());
//        item.setInvoice(invoice);
        // TODO (think) i'm sure some times i've had to add the child to the parent set too
        // to get it to save!!
//        itemDao.save(item);
//        invoice.getItems().add(item);
//        invoiceDao.persist(invoice);

//        ItemDetailDto persistedItemDetailDto = new ItemDetailDto();
//
//        persistedItemDetailDto.setId(item.getId());
//        persistedItemDetailDto.setCode(item.getCode());
//        persistedItemDetailDto.setName(item.getName());
//        persistedItemDetailDto.setDescription(item.getDescription());
//        persistedItemDetailDto.setAmount(item.getAmount());

        List<ItemDetailDto> dtos = new ArrayList<ItemDetailDto>();
        dtos.add(itemDetailDto);
        response.setDtos(dtos);
        return response;
    }

    public Class getIncomingCommandClass(){
        return SaveItemDetail.class;
    }


}

