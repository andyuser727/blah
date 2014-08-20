package com.ajs.service.quote;

import com.ajs.shared.commands.quote.SaveQuoteDetail;
import com.ajs.dao.CustomerDao;
import com.ajs.dao.QuoteDao;
import com.ajs.dao.QuoteItemRlshipDao;
import com.ajs.dao.ItemDao;
import com.ajs.domain.Customer;
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

import java.util.Map;

@SuppressWarnings("serial")
@Component
@Transactional
public class SaveQuoteDetailService implements Handler<SaveQuoteDetail> {

    @Autowired
    QuoteDao quoteDao;

    @Autowired
    ItemDao itemDao;

    @Autowired
    CustomerDao customerDao;

    @Autowired
    QuoteItemRlshipDao quoteItemRlshipDao;

    public AppResponse execute(SaveQuoteDetail dto) {

        AppResponse response = new AppResponse();

        QuoteDetailDto quoteDetailDto = dto.getQuoteDetailDto();

        Quote quote = null;
        if (dto.getQuoteDetailDto().getId() != null) {
            quote = quoteDao.find(dto.getQuoteDetailDto().getId());
        } else {
            quote = new Quote();
            quoteDao.save(quote);
        }

        Map<Long, ItemDetailDto> newItemDtos = dto.getNewItemDtos();

        for (Long idToRemove : dto.getItemIdsToRemove()) {
            if (newItemDtos != null) {
                newItemDtos.remove(idToRemove);
            }
            QuoteItemRlship quoteItemRlship = quoteItemRlshipDao.findRelationship(quote.getId(), idToRemove);
            if (quoteItemRlship != null) {
                quoteItemRlshipDao.delete(quoteItemRlship);
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

                QuoteItemRlship quoteItemRlship = new QuoteItemRlship();
                quoteItemRlshipDao.save(quoteItemRlship);
                quoteItemRlship.setQuote(quote);
                quoteItemRlship.setItem(item);
            }
        }

        quote.setCustomerReference(quoteDetailDto.getCustomerReference());
        quote.setDescription(quoteDetailDto.getDescription());
        quote.setQuoteDate(quoteDetailDto.getQuoteDate());
        quote.setQuoteNumber(quoteDetailDto.getQuoteNumber());
        quote.setAmount(quoteDetailDto.getAmount());

        if (quoteDetailDto.getCustomerId() != null) {
            Customer customer = customerDao.find(quoteDetailDto.getCustomerId());
            quote.setCustomer(customer);
            customer.getQuotes().add(quote);
        }

        return response;
    }

    public Class getIncomingCommandClass() {
        return SaveQuoteDetail.class;
    }


}

