package com.ajs.shared.commands.quote;

import com.ajs.shared.dto.quote.QuoteDetailDto;
import com.ajs.shared.dto.RpcDto;
import com.ajs.shared.dto.item.ItemDetailDto;

import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: AndySmith
 * Date: 13/12/2013
 * Time: 21:12
 * To change this template use File | Settings | File Templates.
 */
public class SaveQuoteDetail extends RpcDto {

    QuoteDetailDto quoteDetailDto;
    Map<Long, ItemDetailDto> newItemDtos;
    List<Long> itemIdsToRemove;

    public SaveQuoteDetail(){}

    public SaveQuoteDetail(QuoteDetailDto quoteDetailDto){
        this.quoteDetailDto = quoteDetailDto;
    }

    public SaveQuoteDetail(QuoteDetailDto quoteDetailDto, Map<Long, ItemDetailDto> newItemDtos, List<Long> itemIdsToRemove){
        this.quoteDetailDto = quoteDetailDto;
        this.newItemDtos = newItemDtos;
        this.itemIdsToRemove = itemIdsToRemove;
    }

    public QuoteDetailDto getQuoteDetailDto() {
        return quoteDetailDto;
    }

    public Map<Long, ItemDetailDto> getNewItemDtos() {
        return newItemDtos;
    }

    public List<Long> getItemIdsToRemove() {
        return itemIdsToRemove;
    }
}
