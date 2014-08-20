package com.ajs.service.quote;

import com.ajs.dao.QuoteDao;
import com.ajs.domain.Quote;
import com.ajs.shared.dto.quote.QuoteDetailDto;
import com.ajs.shared.commands.quote.LoadQuoteList;
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
public class LoadQuoteListService implements Handler<LoadQuoteList> {

    @Autowired
    QuoteDao quoteDao;

    public AppResponse execute(LoadQuoteList dto) throws IllegalArgumentException {

        AppResponse response = new AppResponse();

        List<Quote> quotes = quoteDao.findAll();

        List<QuoteDetailDto> quoteDetailDtos = new ArrayList<QuoteDetailDto>();
        for (Quote quote : quotes) {
            QuoteDetailDto quoteDetailDto = new QuoteDetailDto();
            quoteDetailDto.setId(quote.getId());
            quoteDetailDto.setCustomerReference(quote.getCustomerReference());
            quoteDetailDto.setDescription(quote.getDescription());
            quoteDetailDto.setAmount(quote.getAmount());
            quoteDetailDto.setQuoteDate(quote.getQuoteDate());
            quoteDetailDto.setQuoteNumber(quote.getQuoteNumber());
            if (quote.getCustomer() != null) {
                quoteDetailDto.setCustomerName(quote.getCustomer().getFirstName() + " " + quote.getCustomer().getSurName());
            }
            quoteDetailDtos.add(quoteDetailDto);
        }
        response.setDtos(quoteDetailDtos);

        return response;
    }

    public Class getIncomingCommandClass() {
        return LoadQuoteList.class;
    }


}

