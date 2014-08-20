package com.ajs.service.item;

import com.ajs.shared.commands.item.LoadItemList;
import com.ajs.dao.ItemDao;
import com.ajs.domain.Item;
import com.ajs.shared.dto.item.ItemDetailDto;
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
public class LoadItemListService implements Handler<LoadItemList> {

    @Autowired
    ItemDao itemDao;

    public AppResponse execute(LoadItemList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<Item> items;

        if (dto.getIdsToExclude() != null && dto.getIdsToExclude().size() > 0) {
            items = itemDao.findAllItemsExcludeIds(dto.getIdsToExclude());
        } else {
            items = itemDao.findAll();
        }

        List<ItemDetailDto> itemDetailDtos = new ArrayList<ItemDetailDto>();
        if (items != null) {
            for (Item item : items) {
                ItemDetailDto itemDetailDto = new ItemDetailDto();
                itemDetailDto.setId(item.getId());
                itemDetailDto.setName(item.getName());
                itemDetailDto.setCode(item.getCode());
                itemDetailDto.setDescription(item.getDescription());
                itemDetailDto.setAmount(item.getAmount());
                itemDetailDtos.add(itemDetailDto);
            }
        }
        response.setDtos(itemDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadItemList.class;
    }


}

