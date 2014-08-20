package com.ajs.service.quote;

import com.ajs.shared.commands.quote.LoadQuoteDetail;
import com.ajs.dao.QuoteDao;
import com.ajs.domain.Quote;
import com.ajs.domain.QuoteItemRlship;
import com.ajs.domain.Item;
import com.ajs.shared.dto.quote.QuoteDetailDto;
import com.ajs.shared.dto.item.ItemDetailDto;
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
public class LoadQuoteDetailService implements Handler<LoadQuoteDetail> {

    @Autowired
    QuoteDao quoteDao;

    public AppResponse execute(LoadQuoteDetail dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        Quote quote = quoteDao.find(dto.getQuoteId());
        QuoteDetailDto quoteDetailDto = new QuoteDetailDto();
        quoteDetailDto.setCustomerReference(quote.getCustomerReference());
        quoteDetailDto.setDescription(quote.getDescription());
        quoteDetailDto.setQuoteNumber(quote.getQuoteNumber());
        quoteDetailDto.setQuoteDate(quote.getQuoteDate());
        quoteDetailDto.setAmount(quote.getAmount());
        if (quote.getCustomer() != null) {
            quoteDetailDto.setCustomerId(quote.getCustomer().getId());
        }
        quoteDetailDto.setId(quote.getId());

        List<QuoteDetailDto> quoteDetailDtos = new ArrayList<QuoteDetailDto>();
        quoteDetailDtos.add(quoteDetailDto);

        Map<Long, ItemDetailDto> itemDtos = new HashMap<Long, ItemDetailDto>();
        if (quote.getQuoteItemRlships() != null && quote.getQuoteItemRlships().size() > 0)

            // TODO SHIT CHANGE IT LAZY
            for (QuoteItemRlship quoteItemRlship : quote.getQuoteItemRlships()) {
                ItemDetailDto itemDto = new ItemDetailDto();
                Item item = quoteItemRlship.getItem();
                itemDto.setCode(item.getCode());
                itemDto.setDescription(item.getDescription());
                itemDto.setName(item.getName());
                itemDto.setAmount(item.getAmount());
                itemDto.setId(item.getId());
                itemDtos.put(item.getId(), itemDto);
            }

        quoteDetailDto.setItemDtoList(itemDtos);
        response.setDtos(quoteDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadQuoteDetail.class;
    }


}

